package trojan.taco.repository.jpa;

import trojan.taco.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
