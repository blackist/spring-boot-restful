package org.blackist.web.springbootor.web.quiz;

import org.blackist.web.springbootor.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController extends BaseController {

    @GetMapping
    public String thymeleaf(ModelMap modelMap) {

        modelMap.put("blog", "https://blackist.org");

        return "hello-index";
    }
}
