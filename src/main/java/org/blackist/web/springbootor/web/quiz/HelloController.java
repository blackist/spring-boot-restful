package org.blackist.web.springbootor.web.quiz;

import org.blackist.web.springbootor.common.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String index() {
        return "Hello, SpringBootor!";
    }

    @GetMapping("/test")
    public String test() {
        return "Test SpringBootor!";
    }

    @GetMapping("/header")
    public Response testHeader(@RequestHeader("id") Long id) {

        return Response.SUCCESS(id);
    }
}
