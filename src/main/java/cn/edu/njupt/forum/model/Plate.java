package cn.edu.njupt.forum.model;

import cn.edu.njupt.forum.enums.PlateTypeEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plate {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private PlateTypeEnum type;
}
