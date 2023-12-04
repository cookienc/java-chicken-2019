package controller;

import domain.payment.Payment;
import domain.payment.PaymentContext;
import domain.table.Table;
import domain.table.TableRepository;
import java.util.List;
import service.OrderService;
import service.PaymentService;
import service.TableService;
import util.Retry;
import view.InputView;
import view.OutputView;

public class PaymentController implements Controller {

    @Override
    public void run() {
        printTables();

        final int tableNumber = getTableNumber();
        OutputView.printStartPay(tableNumber);

        // 결제할 목록이 없을 수도 있음
        final int paymentCommand = getPaymentCommand();

        PaymentContext paymentContext = new PaymentContext(paymentCommand,
            OrderService.findOrdersByTableNumber(tableNumber),
            OrderService.getTotalQuantityByTableNumber(tableNumber),
            OrderService.getTotalPriceByTableNumber(tableNumber));

        int calculatedPaymentAmount = PaymentService.calculatePayment(paymentContext);

        OutputView.printTotalPaymentAmount(calculatedPaymentAmount);
    }

    private static void printTables() {
        final List<Table> tables = TableRepository.tables();
        OutputView.printTables(tables);
    }

    private static int getTableNumber() {
        return Retry.execute(() -> {
            final int tableNumber = InputView.inputTableNumber();
            TableService.validateTableExistsByTableNumber(tableNumber);
            return tableNumber;
        });
    }

    private static int getPaymentCommand() {
        return Retry.execute(() -> {
            final int paymentCommand = InputView.inputPaymentCommand();
            Payment.validateCommand(paymentCommand);
            return paymentCommand;
        });
    }
}
