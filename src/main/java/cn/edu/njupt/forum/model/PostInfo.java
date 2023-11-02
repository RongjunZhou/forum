package cn.edu.njupt.forum.model;

import cn.edu.njupt.forum.enums.PlateTypeEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(autoResultMap = true)
public class PostInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private PlateTypeEnum plateId;
    private String title;
    private String content;
    @TableField(typeHandler = GsonTypeHandler.class)
    private List<String> resources;
    private LocalDateTime createTime; //发帖时间
}
