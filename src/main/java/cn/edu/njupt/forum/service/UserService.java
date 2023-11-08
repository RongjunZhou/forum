package cn.edu.njupt.forum.service;

import cn.edu.njupt.forum.data.UserInfoDO;
import cn.edu.njupt.forum.model.UserInfo;

public interface UserService {
    String login(String username, String password);

    Boolean changePassword(UserInfo userInfo, String password);

    String register(String username, String email);

    UserInfoDO getUserInfo(UserInfo userInfo);
}
