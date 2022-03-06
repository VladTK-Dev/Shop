package pl.edu.wszib.sport.store.database;

import pl.edu.wszib.sport.store.model.Order;

import java.util.List;

public interface IOrderDAO {
    void addOrder(Order order);
    List<Order> getOrdersByUserId(int userId);
}
