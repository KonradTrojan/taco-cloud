package com.example.taco.data;

import com.example.taco.model.Ingredient;
import com.example.taco.model.Taco;
import com.example.taco.repository.jdbc.TacoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcTacoRepository implements TacoRepository {

    private JdbcTemplate jdbc;

    public JdbcTacoRepository(final JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(final Taco taco){
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for (Ingredient ingredient : taco.getIngredients()){
            saveIngredientToTaco(ingredient, tacoId);
        }
        return taco;
    }

    private long saveTacoInfo(final Taco taco){
        taco.setCreatedAt(new Date());

        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
                "insert into Taco (name, createdAt) values (?,?)",
                Types.CHAR, Types.TIMESTAMP);
        factory.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = factory.newPreparedStatementCreator(
                        Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime()))
                );
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(final Ingredient ingredient, final long tacoId){
        jdbc.update("insert into taco_ingredients (taco, ingredient) values (?,?)", tacoId, ingredient.getId());
    }
}
