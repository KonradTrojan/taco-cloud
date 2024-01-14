package trojan.taco.model;

import trojan.taco.model.security.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "TACO_ORDER_SEQ")
    private long id;
    private Date placedAt;

    @NotBlank(message="Name is required")
    private String deliveryName;
    @NotBlank(message="Street is required")
    private String deliveryStreet;
    @NotBlank(message="City is required")
    private String deliveryCity;
    @NotBlank(message="State is required")
    private String deliveryState;
    @NotBlank(message="Zip code is required")
    private String deliveryZip;
    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCvv;

    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    @ManyToOne
    private User user;

    public void addTaco(final Taco taco){
        this.tacos.add(taco);
    }

    @PrePersist
    void placedAt(){
        this.placedAt = new Date();
    }
}
