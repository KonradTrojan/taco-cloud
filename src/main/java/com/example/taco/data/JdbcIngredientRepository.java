package com.example.taco.data;

import com.example.taco.model.Ingredient;
import com.example.taco.repository.jdbc.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcIngredientRepository(final JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Ingredient> findAll(){
        return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);
    }

    @Override
    public Ingredient findById(final String id){
        return jdbc.queryForObject("select id, name, type from Ingredient where id=?", this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(final Ingredient ingredient){
        jdbc.update("insert into Ingredient (id, name, type) values (?,?,?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

    private Ingredient mapRowToIngredient(final ResultSet rs, final int rowNum) throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));

    }

}
