package domain.payment;

import java.util.Arrays;
import java.util.List;

public class PaymentStrategiesSingleton {

    private static PaymentStrategiesSingleton instance;

    private final List<PaymentStrategy> paymentStrategies = Arrays.asList(
        new OrderQuantityPaymentStrategyImpl(),
        new CashPaymentStrategyImpl(),
        new CreditPaymentStrategyImpl()
    );

    public static PaymentStrategiesSingleton getInstance() {
        if (instance == null) {
            instance = new PaymentStrategiesSingleton();
        }
        return instance;
    }

    public List<PaymentStrategy> getPaymentStrategies() {
        return paymentStrategies;
    }
}
