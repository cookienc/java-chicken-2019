package domain.payment;

public class CreditPaymentStrategyImpl implements PaymentStrategy {

    @Override
    public int calculateDiscountAmount(PaymentContext context) {
        return 0;
    }

    @Override
    public boolean isApplied(PaymentContext context) {
        return context.getCommand() == 1;
    }
}
