package pl.edu.wszib.sport.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wszib.sport.store.database.InItemDAO;
import pl.edu.wszib.sport.store.database.IOrderDAO;
import pl.edu.wszib.sport.store.model.Item;
import pl.edu.wszib.sport.store.model.Order;
import pl.edu.wszib.sport.store.model.OrderPosition;
import pl.edu.wszib.sport.store.service.IOrderService;
import pl.edu.wszib.sport.store.session.SessionObject;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import java.util.Optional;

public class OrderServiceOld implements IOrderService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IOrderDAO orderDAO;

    @Autowired
    InItemDAO bookDAO;

    @Override
    public void confirmOrder() {
        Order order = new Order(this.sessionObject.getUser(), new HashSet<>(this.sessionObject.getCart().getOrderPositions()));
        this.orderDAO.addOrder(order);
        for (OrderPosition orderPosition : order.getOrderPositions()) {
            Optional<Item> bookBox = bookDAO.getItemById(orderPosition.geItem().getId());
            if(bookBox.isPresent()) {
                bookBox.get().setQuantity(bookBox.get().getQuantity() - orderPosition.getQuantity());
            }
        }
        this.sessionObject.getCart().clearOrderPositions();
    }

    @Override
    public List<Order> getOrdersForCurrentUser() {
        return this.orderDAO.getOrdersByUserId(this.sessionObject.getUser().getId());
    }
}
