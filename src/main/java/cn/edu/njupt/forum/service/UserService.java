package cn.edu.njupt.forum.service;

public interface UserService {
    String login(String username, String password);

    Boolean changePassword(String username, String password, String newPassword);
}
