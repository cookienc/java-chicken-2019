package domain.payment;

public class CashPaymentStrategyImpl implements PaymentStrategy {

    private final int CASH_DISCOUNT_PERCENT = 5;

    @Override
    public int calculateDiscountAmount(PaymentContext context) {
        return context.getTotalPrice() * CASH_DISCOUNT_PERCENT / 100;
    }

    @Override
    public boolean isApplied(PaymentContext context) {
        return context.getCommand() == 2;
    }
}
