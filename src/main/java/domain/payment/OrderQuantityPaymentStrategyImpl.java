package domain.payment;

public class OrderQuantityPaymentStrategyImpl implements PaymentStrategy {

    private final int STANDARD_QUANTITY = 10;
    private final int DISCOUNT_AMOUNT_PER_10_ITEMS = 10000;

    @Override
    public int calculateDiscountAmount(PaymentContext context) {
        int discountMultiplier = context.getTotalQuantity() / STANDARD_QUANTITY;
        return DISCOUNT_AMOUNT_PER_10_ITEMS * discountMultiplier;
    }

    @Override
    public boolean isApplied(PaymentContext context) {
        return context.getChickenQuantity() >= STANDARD_QUANTITY;
    }
}
