package trojan.taco.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "TACO_SEQ")
    private long id;

    private Date createdAt;

    @NotNull
    @Size(min=5, message = "Name must be at least 5 characters long")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    @NotNull(message = "You must choose at least 2 ingredient.")
    @Size(min=2, message = "You must choose at least 2 ingredient.")
    private List<Ingredient> ingredients;

    @PrePersist
    void createdAt(){
        this.createdAt = new Date();
    }

}