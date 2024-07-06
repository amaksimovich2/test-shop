package Abstractions;

import Models.Order;

public interface IOrderDetailsWriter {
    Integer Write(Order order);
}

