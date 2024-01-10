package com.example.taco.repository.jdbc;

import com.example.taco.model.Order;

public interface OrderRepository {

    Order save(final Order order);
}
