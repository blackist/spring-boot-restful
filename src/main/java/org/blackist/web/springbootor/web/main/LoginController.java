package org.blackist.web.springbootor.web.main;


import org.blackist.web.springbootor.common.response.Data;
import org.blackist.web.springbootor.common.response.Response;
import org.blackist.web.springbootor.model.security.TokenDetail;
import org.blackist.web.springbootor.service.system.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Value("${token.header}")
    private String tokenHeader;

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String index(ModelMap modelMap) {
        return "login";
    }

    @PostMapping
    @ResponseBody
    public Response login(ModelMap modelMap, @RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println(username + " " + password);

        TokenDetail tokenDetail = loginService.getTokenDetail(username);

        Data data = new Data();
        data.put(tokenHeader, loginService.generateToken(tokenDetail));

        return Response.SUCCESS(data).message("Login Success!");
    }

}
