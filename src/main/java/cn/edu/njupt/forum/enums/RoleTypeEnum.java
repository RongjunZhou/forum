package cn.edu.njupt.forum.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum RoleTypeEnum {
    ADMIN(1),
    USER(0);
    @EnumValue
    private final Integer role;

    RoleTypeEnum(Integer role) {
        this.role = role;
    }

}
