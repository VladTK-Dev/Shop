package pl.edu.wszib.sport.store.database;

import pl.edu.wszib.sport.store.model.OrderPosition;
import java.util.List;

public interface IOrderPositionDAO {
    void addOrderPosition(OrderPosition orderPosition, int orderId);
    List<OrderPosition> getOrderPositionsByOrderId(int orderId);
}
