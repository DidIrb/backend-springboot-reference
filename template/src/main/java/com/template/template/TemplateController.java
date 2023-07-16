package com.template.template;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateController {
    @RequestMapping("/welcome")
    public String welcome() {
        return "Welcome you have successfully initiated spring boot!";
    }

}
