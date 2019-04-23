package org.blackist.web.springbootor.web.quiz;

import org.blackist.web.springbootor.common.exception.ApiException;
import org.blackist.web.springbootor.common.exception.WebException;
import org.blackist.web.springbootor.common.response.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@RequestMapping("/hello")
@ApiIgnore
public class HelloController {

    @GetMapping
    @ResponseBody
    public String index() {
        return "Hello, SpringBootor!";
    }

    @GetMapping("/test")
    public String test() {
        return "Test SpringBootor!";
    }

    @GetMapping("/param")
    public @ResponseBody
    Response testParam(@RequestParam("id") Long id) {

        return Response.SUCCESS(id);
    }

    @GetMapping("/header")
    public @ResponseBody
    Response testHeader(@RequestHeader("id") Long id) {

        return Response.SUCCESS(id);
    }

    @GetMapping("/exception")
    public String testException() throws Exception {
        return 5 / 0 + "";
    }

    @GetMapping("/exception/web")
    public String testWebException() throws Exception {
        try {
            int a = 5 / 0;
        } catch (Exception e) {
            throw new WebException(e);
        }
        return "hello-index";
    }

    @GetMapping("/exception/api")
    public Response testApiException() throws Exception {
        try {
            int a = 5 / 0;
        } catch (Exception e) {
            throw new ApiException(e);
        }
        return Response.SUCCESS(null);
    }
}
