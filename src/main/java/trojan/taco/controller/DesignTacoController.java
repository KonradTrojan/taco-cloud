package trojan.taco.controller;

import org.springframework.ui.ConcurrentModel;
import trojan.taco.model.Ingredient;
import trojan.taco.model.Order;
import trojan.taco.model.Taco;
import trojan.taco.model.security.User;
import trojan.taco.repository.jpa.IngredientRepository;
import trojan.taco.repository.jpa.TacoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Controller
@SessionAttributes({"order", "design"})
@RequestMapping("/design")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;
    @Autowired
    public DesignTacoController(final IngredientRepository ingredientRepository,
                                final TacoRepository tacoRepository){
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name="taco")
    public Taco taco(){
        return new Taco();
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "design")
    public Model design() {
        return new ConcurrentModel();
    }

    @GetMapping
    public String showDesignForm(Model model, @ModelAttribute("design") Model design, @AuthenticationPrincipal User user) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        Ingredient.Type[] types = Ingredient.Type.values();
        for(Ingredient.Type type : types){
            design.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        if (user != null) {
            design.addAttribute("user", user);
        }
        model.addAllAttributes(design.asMap());
        return "design";
    }

    @PostMapping
    public String processDesign(Model model, @Valid Taco taco, Errors errors, @ModelAttribute Order order,
                                @AuthenticationPrincipal User user,@ModelAttribute("design") Model design) {
        if (errors.hasErrors()) {
            model.addAllAttributes(design.asMap());
            return "design";
        }

        Taco saved = tacoRepository.save(taco);
        order.addTaco(saved);

        log.info("Processing design: " + taco);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}
