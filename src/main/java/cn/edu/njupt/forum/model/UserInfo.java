package cn.edu.njupt.forum.model;

import cn.edu.njupt.forum.enums.RoleTypeEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private RoleTypeEnum role;
    @TableField("sex")
    private Boolean man;
    private String avatar;
    private String sign;
    private String email;
}
