package com.example.taco.repository.jdbc;

import com.example.taco.model.Ingredient;

public interface IngredientRepository{
    Iterable<Ingredient> findAll();
    Ingredient findById(final String id);
    Ingredient save(final Ingredient ingredient);

}
