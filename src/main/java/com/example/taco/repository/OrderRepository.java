package com.example.taco.repository;

import com.example.taco.model.Order;

public interface OrderRepository {

    Order save(final Order order);
}
