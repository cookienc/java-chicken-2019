package domain.payment;

import domain.Category;
import domain.order.Order;
import java.util.List;

public class PaymentContext {

    private final int command;
    private final List<Order> orders;
    private final int totalQuantity;

    private int totalPrice;

    public PaymentContext(int command, List<Order> orders, int totalQuantity, int totalPrice) {
        this.command = command;
        this.orders = orders;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public void setTotalPrice(int price) {
        this.totalPrice -= price;
    }

    public int getCommand() {
        return command;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getChickenQuantity() {
        return orders.stream()
            .filter(order -> order.getCategory() == Category.CHICKEN)
            .mapToInt(Order::getQuantity)
            .sum();
    }
}
