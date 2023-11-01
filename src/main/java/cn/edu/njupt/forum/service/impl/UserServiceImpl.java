package cn.edu.njupt.forum.service.impl;

import cn.edu.njupt.forum.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserService userService;

    public UserServiceImpl(UserService userService) {
        this.userService = userService;
    }
}
