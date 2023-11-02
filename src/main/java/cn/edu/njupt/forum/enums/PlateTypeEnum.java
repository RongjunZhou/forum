package cn.edu.njupt.forum.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum PlateTypeEnum {
    COMPUTER_SERVICE("计算机服务");

    @EnumValue
    private final String type;

    PlateTypeEnum(String type) {
        this.type = type;
    }

}
