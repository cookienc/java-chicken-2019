package domain.payment;

public interface PaymentStrategy {

    int calculateDiscountAmount(PaymentContext context);

    boolean isApplied(PaymentContext context);
}
