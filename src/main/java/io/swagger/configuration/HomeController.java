package io.swagger.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

/**
 * Home redirection to swagger api documentation 
 */
@Controller
//@Api(value = "home", description = " ",tags= {"home"})

public class HomeController {
    @RequestMapping(value = "/")
    public String index() {
        System.out.println("swagger-ui.html");
        return "redirect:swagger-ui.html";
    }
}
