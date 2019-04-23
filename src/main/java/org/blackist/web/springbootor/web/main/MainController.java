package org.blackist.web.springbootor.web.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String index(ModelMap modelMap) {

        modelMap.put("blog", "https://blackist.org");

        return "index";
    }
}
