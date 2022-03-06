package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class DemoController {

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/")
    public String home() {
        return "Welcome home!";
    }

    @GetMapping("/public")
    public String publicUrl() {
        return "Hello everyone!";
    }

    @GetMapping("/secret")
    public String secret() {
        return "Welcome to our secret page!";
    }

    @GetMapping("/addtocart")
    public String secret(String product) {

        List<String> userProducts = (List<String>) httpSession.getAttribute("products");
        if (userProducts == null) {
            userProducts = new ArrayList<>();
        }
        userProducts.add(product);
        httpSession.setAttribute("products", userProducts);

        return "Thank you for you purchase! You bought: " + product;
    }

    @GetMapping("/cart")
    public List<String> cart() {
        return (List<String>) httpSession.getAttribute("products");
    }

}
