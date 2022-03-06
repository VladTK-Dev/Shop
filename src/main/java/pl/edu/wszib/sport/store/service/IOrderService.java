package pl.edu.wszib.sport.store.service;

import pl.edu.wszib.sport.store.model.Order;

import java.util.List;

public interface IOrderService {
    void confirmOrder();
    List<Order> getOrdersForCurrentUser();
}
