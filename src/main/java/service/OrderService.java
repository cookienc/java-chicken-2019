package service;

import domain.Order;
import domain.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderService {

    public static void takeOrder(int tableNumber, int menuNumber, int menuQuantity) {
        System.out.println("tableNumber = " + tableNumber);
        System.out.println("menuNumber = " + menuNumber);
        System.out.println("menuQuantity = " + menuQuantity);

        System.out.println(MenuService.findMenuByNumber(menuNumber));

        Order order = new Order(MenuService.findMenuByNumber(menuNumber), menuQuantity);
        System.out.println("order = " + order);

        setOrderByNumber(tableNumber, order);
    }

    public static List<Order> findOrdersByNumber(int number) {
        Map<Integer, ArrayList<Order>> orders = OrderRepository.orders();
        return orders.get(number);
    }

    public static void setOrderByNumber(int number, Order order) {
        // 테이블 정보가 있으면 수정할 것인지 추가할 것인지에 대한 판단 필요
        if (OrderRepository.orders().containsKey(number)) {

            OrderRepository.orders().get(number).add(order);
        } else {
            OrderRepository.orders().put(number, new ArrayList<>(List.of(order)));
        }
    }
}
