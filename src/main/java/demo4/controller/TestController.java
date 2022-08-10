package demo4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
public class TestController {

    @ResponseBody
    @RequestMapping("/redisTest")
    public String test1(HttpSession session) {

        session.setAttribute("redisTest","youngyun");

        return "test";
    }

}