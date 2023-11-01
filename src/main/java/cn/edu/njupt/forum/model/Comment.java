package cn.edu.njupt.forum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer id;
    private Integer userId;
    private Integer postId;
    private String content;
    private Integer likeCount;
    @Nullable
    private Integer fatherId;
}
