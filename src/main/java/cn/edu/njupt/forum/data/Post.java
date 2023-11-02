package cn.edu.njupt.forum.data;

import cn.edu.njupt.forum.enums.PlateTypeEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(autoResultMap = true)
public class Post {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    @JsonIgnore
    private Integer plateId;
    private String title;
    private String content;
    @TableField(typeHandler = GsonTypeHandler.class)
    private List<String> resources;
    @TableField(exist = false)
    private Boolean like;
    private Integer likeCount;
    private Integer viewCount;
    private Integer commentCount;
    private LocalDateTime createTime; //发帖时间
    private LocalDateTime updateTime; //最近回帖
}
