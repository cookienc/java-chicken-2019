package domain.order;

import domain.Category;
import domain.menu.Menu;

public class Order {

    private final int MAX_QUANTITY = 99;
    private final Menu menu;

    private int quantity;

    public Order(Menu menu, int quantity) {
        validateInvalidQuantity(quantity);

        this.menu = menu;
        this.quantity = quantity;
    }

    private void validateInvalidQuantity(int quantuty) {
        if (quantuty > MAX_QUANTITY) {
            throw new IllegalArgumentException("[ERROR] 한 메뉴당 최대 주문 수량은 99개 입니다.");
        }
    }

    public void setQuantity(int newQuantity) {
        validateInvalidQuantity(newQuantity + quantity);
        quantity += newQuantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getPrice() {
        return menu.getPrice() * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Category getCategory() {
        return menu.getCategory();
    }

    @Override
    public String toString() {
        return menu.toString() + " : " + quantity + "개";
    }
}
