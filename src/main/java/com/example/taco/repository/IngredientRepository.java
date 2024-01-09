package com.example.taco.repository;

import com.example.taco.model.Ingredient;

public interface IngredientRepository{
    Iterable<Ingredient> findAll();
    Ingredient findById(String id);
    Ingredient save(final Ingredient ingredient);

}
