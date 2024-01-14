package trojan.taco.configuration;

import trojan.taco.model.Ingredient;
import trojan.taco.model.security.User;
import trojan.taco.repository.jpa.IngredientRepository;
import trojan.taco.repository.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("!prod")
@Configuration
@RequiredArgsConstructor
public class DevConfig {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository ingredientRepository,
                                        UserRepository userRepository, PasswordEncoder encoder) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
                ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
                ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
                ingredientRepository.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
                ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
                ingredientRepository.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
                ingredientRepository.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
                ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
                ingredientRepository.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
                ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));

                userRepository.save(new User("admin", encoder.encode("admin"),
                        "Konrad Trojan", "123 North Street", "Cross Roads", "PL",
                        "76227", "123-123-1234", true));
            }
        };
    }
}
