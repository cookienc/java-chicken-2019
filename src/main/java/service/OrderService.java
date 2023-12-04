package service;

import domain.order.Order;
import domain.order.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderService {

    public static void takeOrder(int tableNumber, int menuNumber, int menuQuantity) {
        // 주문 로직

        // 1. 주문 테이블에 주문 내역 중 같은 메뉴가 있으면 새로 받은 주문 내역과 기존 주문 내역의 수량이 99개가 넘지 않는지 확인한다
//        Order existOrderedMenu = findMenuByTableNumberAndMenuNumber(tableNumber,
//            menuNumber);

//        if (existOrderedMenu.getQuantity() + menuQuantity > 99) {
//            throw new IllegalArgumentException("[ERROR] 한 메뉴당 최대 주문 수량은 99개 입니다.");
//        }

        Order order = new Order(MenuService.findMenuByNumber(menuNumber), menuQuantity);
        System.out.println("order = " + order);

        setOrderByNumber(tableNumber, order);
    }

    public static List<Order> findOrdersByTableNumber(int tableNumber) {
        Map<Integer, ArrayList<Order>> orders = OrderRepository.orders();
        return orders.get(tableNumber);
    }

    public static void setOrderByNumber(int tableNumber, Order order) {
        // 테이블 정보가 있으면 수정할 것인지 추가할 것인지에 대한 판단 필요

        // 현재 해당 테이블에 대한 주문 내역이 있음
        if (OrderRepository.orders().containsKey(tableNumber)) {
            // 주문받은 Order 의 메뉴가 기존 주문 내역에 포함되어 있는지 확인해야함
            if (isOrderedMenu(tableNumber, order)) {
                // 기존 주문 내역에 이미 해당 메뉴에 대한 정보가 있다
                Order orderInfo = getOrderInfoByTableNumberAndOrder(
                    tableNumber, order);
                orderInfo.setQuantity(order.getQuantity());

            } else {
                // 주문 내역에 새로운 주문 정보를 추가한다
                OrderRepository.orders().get(tableNumber).add(order);
            }
        } else {
            OrderRepository.orders().put(tableNumber, new ArrayList<>(List.of(order)));
        }
    }

    public static int getTotalQuantityByTableNumber(int tableNumber) {

        return OrderRepository.orders().get(tableNumber).stream()
            .mapToInt(Order::getQuantity)
            .sum();
    }

    public static int getTotalPriceByTableNumber(int tableNumber) {
        return OrderRepository.orders().get(tableNumber).stream()
            .mapToInt(Order::getPrice)
            .sum();
    }

    private static boolean isOrderedMenu(int tableNumber, Order order) {
        List<Order> ordersByTableNumber = findOrdersByTableNumber(tableNumber);
        for (Order order1 : ordersByTableNumber) {
            if (order.getMenu() == order1.getMenu()) {
                return true;
            }
        }
        return false;
    }

    private static Order getOrderInfoByTableNumberAndOrder(int tableNumber, Order other) {
        return OrderRepository.orders().get(tableNumber).stream()
            .filter(order -> order.getMenu() == other.getMenu())
            .findFirst()
            .orElseThrow(null);
    }
}
