package org.fyahskuztum.ecommerce.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class YourController {

    @GetMapping("/index")
    public String showHome() {

        return "home";
    }
}
