package service;

import domain.payment.PaymentContext;
import domain.payment.PaymentStrategiesSingleton;
import domain.payment.PaymentStrategy;
import java.util.List;

public class PaymentService {

    public static int calculatePayment(PaymentContext paymentContext) {
        List<PaymentStrategy> paymentStrategies = PaymentStrategiesSingleton.getInstance()
            .getPaymentStrategies().stream()
            .filter(paymentStrategy -> paymentStrategy.isApplied(paymentContext))
            .toList();

        for (PaymentStrategy paymentStrategy : paymentStrategies) {
            paymentContext.setTotalPrice(paymentStrategy.calculateDiscountAmount(paymentContext));
        }

        return paymentContext.getTotalPrice();
    }
}
