package cn.edu.njupt.forum.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommentOperation extends OperationDO{

    private String comment;
}
