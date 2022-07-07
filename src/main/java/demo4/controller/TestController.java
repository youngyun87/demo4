package demo4.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class TestController {

    @RequestMapping("/redisTest")
    public String test1(HttpSession session) {

        session.setAttribute("redisTest","youngyun");

        return "test";
    }
}