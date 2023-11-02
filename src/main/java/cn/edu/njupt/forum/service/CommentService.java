package cn.edu.njupt.forum.service;

import cn.edu.njupt.forum.data.CommentDO;

import java.util.List;

public interface CommentService {
    List<CommentDO> getComment(Integer postId, Integer pageNum, Integer userId);

    Boolean addComment(Integer postId, Integer fatherId, String content, Integer userId);

    Boolean like(Integer commentId);
}
