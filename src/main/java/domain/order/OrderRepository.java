package domain.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderRepository {

    private static final Map<Integer, ArrayList<Order>> orders = new HashMap<>();

    public static Map<Integer, ArrayList<Order>> orders() {
        return orders;
    }
}
