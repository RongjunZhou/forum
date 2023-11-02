package cn.edu.njupt.forum.controller;

import cn.edu.njupt.forum.annotation.Info;
import cn.edu.njupt.forum.model.UserInfo;
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
    public Boolean login(@NotEmpty String username, @NotEmpty String password, HttpServletResponse response) {
        String token = userService.login(username, password);
        response.setHeader("token", token);
        return true;
    }

    @PostMapping("/changePassword")
    public Boolean changePassword(@Info UserInfo userInfo, @NotEmpty String originPassword, @NotEmpty String newPassword) {
        userInfo.setPassword(originPassword);
        return userService.changePassword(userInfo, newPassword);
    }
}
