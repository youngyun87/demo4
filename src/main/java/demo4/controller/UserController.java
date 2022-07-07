package demo4.controller;

import demo4.service.UserService;
import demo4.vo.Member;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "thymeleaf/member/loginForm.html";
    }

    @GetMapping("/signup")
    public String signup(Member member) {
        return "thymeleaf/member/signUp.html";
    }

    @PostMapping("/signup")
    public String signup(@Valid Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "thymeleaf/member/signUp.html";
        }

        if (!member.getPassword().equals(member.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "thymeleaf/member/signUp.html";
        }

     //   userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());
        try{

            // 중복 체크
            int duCheck = userService.insertMemberDuCheck(member);

            if(duCheck > 0){
                bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
                return "thymeleaf/member/signUp.html";
            }else{
                userService.insertMember(member);
            }

        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "thymeleaf/member/signUp.html";
        }

        return "redirect:/";
    }

}