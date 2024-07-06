package Abstractions;

import Models.OrderItem;

public interface IDiscount {
    boolean CanBeApplied(OrderItem item);
    void Apply(OrderItem item);
}
