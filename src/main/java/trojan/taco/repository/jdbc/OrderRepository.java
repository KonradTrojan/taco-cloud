package trojan.taco.repository.jdbc;

import trojan.taco.model.Order;

public interface OrderRepository {

    Order save(final Order order);
}
