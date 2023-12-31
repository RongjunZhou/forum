package cn.edu.njupt.forum.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDO {
    private Integer id;
    private String username;
    private String userAvatar;
    private String content;
    private Integer likeCount;
    private LocalDateTime time;
    private List<CommentDO> leafComments;
}
