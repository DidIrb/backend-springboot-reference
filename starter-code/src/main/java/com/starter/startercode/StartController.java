package com.starter.startercode;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartController {

    @RequestMapping("/welcome")
    public String welcome() {
        return "Welcome you have successfully generated your spring boot application!";
    }
}
