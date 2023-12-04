import controller.OrderRegistrationController;
import controller.PaymentController;
import domain.Command;
import util.Retry;
import view.InputView;
import view.OutputView;

public class Application {
    // TODO 구현 진행
    public static void main(String[] args) {
        OrderRegistrationController orderRegistrationController = new OrderRegistrationController();
        PaymentController paymentController = new PaymentController();

        while (true) {
            OutputView.printMainDisplay();
            final int command = getCommand();

            if (command == 1) {
                orderRegistrationController.run();
            }
            if (command == 2) {
                paymentController.run();
            }
            if (command == 3) {
                return;
            }
        }
    }

    private static int getCommand() {
        return Retry.execute(() -> {
            final int command = InputView.inputCommand();
            Command.validateCommand(command);
            return command;
        });
    }
}
