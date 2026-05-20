package com.example.scope.session;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final ShoppingCart cart;

    // Spring fetches the right cart based on the user's HTTP Session.
    public CartController(ShoppingCart cart) {
        this.cart = cart;
    }

    @PostMapping("/add")
    public String add(@RequestParam String item) {
        cart.addItem(item);
        return item + " added!";
    }

    @GetMapping
    public List<String> getCart() {
        return cart.getItems();
    }
}
