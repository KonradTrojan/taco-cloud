package com.example.taco.repository.jpa;

import com.example.taco.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
