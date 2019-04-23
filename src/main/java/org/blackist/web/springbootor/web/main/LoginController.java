package org.blackist.web.springbootor.web.main;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String index(ModelMap modelMap) {
        return "login";
    }

    @PostMapping
    public String login(ModelMap modelMap, @RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println(username + " " + password);
        return "login";
    }

}
