package org.blackist.web.springbootor.web.quiz;

import org.blackist.web.springbootor.entity.user.User;
import org.blackist.web.springbootor.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController extends BaseController {

    @GetMapping
    public String thymeleaf(ModelMap modelMap) {

        modelMap.put("blog", "https://blackist.org");

        return "hello-index";
    }

    @GetMapping("/add")
    public String add(User user) {

        return "user-add";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute User user) {

        System.out.println(user.getName());

        return "hello-index";
    }

    @GetMapping("/get")
    public String get(ModelMap map) {
        User user = new User();
        user.setId(1L);
        user.setName("blackist");
        user.setPassword("black");
        map.put("user", user);

        return "user-detail";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute User user, @PathVariable("id") Long id) {

        System.out.println(id);

        return "redirect:/thymeleaf/";
    }

}
