package cn.edu.njupt.forum.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PlateTypeEnum {
    COMPUTER_SERVICE(1, "计算机服务"),
    SCHOOL_LIFE(2, "校园生活");

    @EnumValue
    private final Integer id;
    private final String type;

}
