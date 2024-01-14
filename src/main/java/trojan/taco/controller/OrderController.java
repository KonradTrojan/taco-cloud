package trojan.taco.controller;

import trojan.taco.model.Order;
import trojan.taco.model.security.User;
import trojan.taco.repository.jpa.OrderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@SessionAttributes("order")
@RequestMapping("/orders")
public class OrderController {

    private OrderRepository orderRepository;

    @Autowired
    public OrderController(final OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user,
                            @ModelAttribute Order order) {
        if (order.getDeliveryName() == null) {
            order.setDeliveryName(user.getFullname());
        }
        if (order.getDeliveryStreet() == null) {
            order.setDeliveryStreet(user.getStreet());
        }
        if (order.getDeliveryCity() == null) {
            order.setDeliveryCity(user.getCity());
        }
        if (order.getDeliveryState() == null) {
            order.setDeliveryState(user.getState());
        }
        if (order.getDeliveryZip() == null) {
            order.setDeliveryZip(user.getZip());
        }
        order.setCcNumber("374245455400126");
        order.setCcExpiration("10/99");
        order.setCcCvv("100");
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user){
        if (errors.hasErrors()){
            return "orderForm";
        }
        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();
        log.info("Order submitted:" + order);

        return "redirect:/home";
    }
}
