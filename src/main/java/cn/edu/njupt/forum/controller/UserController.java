package cn.edu.njupt.forum.controller;

import cn.edu.njupt.forum.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@NotEmpty String username, @NotEmpty String password, HttpServletResponse response){
        String token = userService.login(username, password);
        response.setHeader("token", token);
        return String.valueOf(System.currentTimeMillis());
    }
}
