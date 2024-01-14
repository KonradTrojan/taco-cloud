package trojan.taco.repository.jdbc;

import trojan.taco.model.Ingredient;

public interface IngredientRepository{
    Iterable<Ingredient> findAll();
    Ingredient findById(final String id);
    Ingredient save(final Ingredient ingredient);

}
