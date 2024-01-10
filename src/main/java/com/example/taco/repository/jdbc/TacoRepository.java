package com.example.taco.repository.jdbc;

import com.example.taco.model.Taco;

public interface TacoRepository {

    Taco save(final Taco taco);
}
