import Abstractions.IDataProvider;
import Models.*;

import java.util.HashMap;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        IDataProvider<Item> itemsDataProvider = new DumbStoreDataProvider();
        var idQuantityMap = new HashMap<Integer, Integer>();
        var order = new Order();

        for (String arg : args) {
            //Parse items and quantity
            if (arg.contains("-")) {
                var parts = arg.split("-");
                var key = Integer.parseInt(parts[0]);
                var value = Integer.parseInt(parts[1]);

                if (idQuantityMap.containsKey(key)) {
                    idQuantityMap.put(key, idQuantityMap.get(key) + value);
                } else {
                    idQuantityMap.put(key, value);
                }
            }

            //Parse discount card
            if (arg.contains("=")) {
                var parts = arg.split("=");
                var key = parts[0];
                var value = parts[1];

                if (Objects.equals(key, "discountCard")) {
                    order.DiscountCard = value;
                }

                if (Objects.equals(key, "balanceDebitCard")) {
                    order.Balance = Integer.parseInt(value);
                }
            }
        }

        for (var entry : idQuantityMap.entrySet()) {
            order.AddItem(new OrderItem(itemsDataProvider.Get(entry.getKey()), entry.getValue()));
        }
        
        order.CalculateOrder();
        
        var detailsWriter = new OrderDetailsToCsvWriter("");
        detailsWriter.Write(order);
    }
}

