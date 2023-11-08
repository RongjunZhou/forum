package cn.edu.njupt.forum.data;

import cn.edu.njupt.forum.model.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfoDO extends UserInfo {
    private LocalDate lastLoginTime;
    private Integer postCount;
    private Integer likeCount;

    public UserInfoDO(UserInfo userInfo, LocalDate lastLoginTime, Integer postCount, Integer likeCount){
        super(userInfo.getId(), userInfo.getUsername(), userInfo.getPassword(), userInfo.getRole(), userInfo.getMan(), userInfo.getAvatar(), userInfo.getSign(), userInfo.getAvatar());
        this.lastLoginTime = lastLoginTime;
        this.postCount = postCount;
        this.likeCount = likeCount;
    }
}
