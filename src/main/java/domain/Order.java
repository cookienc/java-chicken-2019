package domain;

import java.util.ArrayList;

public class Order extends ArrayList<Order> {

    private final Menu menu;

    private final int quantity;

    public Order(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public int getPrice() {
        return menu.getPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return menu.toString() + " : " + quantity + "개";
    }
}
