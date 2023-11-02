package cn.edu.njupt.forum.service.impl;

import cn.edu.njupt.forum.data.CommentDO;
import cn.edu.njupt.forum.enums.ErrorEnum;
import cn.edu.njupt.forum.exception.LocalRuntimeException;
import cn.edu.njupt.forum.mapper.CommentMapper;
import cn.edu.njupt.forum.mapper.HistoryMapper;
import cn.edu.njupt.forum.mapper.UserInfoMapper;
import cn.edu.njupt.forum.model.Comment;
import cn.edu.njupt.forum.model.History;
import cn.edu.njupt.forum.model.UserInfo;
import cn.edu.njupt.forum.service.CommentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final UserInfoMapper userInfoMapper;
    private final HistoryMapper historyMapper;

    public CommentServiceImpl(CommentMapper commentMapper, UserInfoMapper userInfoMapper, HistoryMapper historyMapper) {
        this.commentMapper = commentMapper;
        this.userInfoMapper = userInfoMapper;
        this.historyMapper = historyMapper;
    }

    @Override
    public List<CommentDO> getComment(Integer postId, Integer pageNum, Integer userId) {
        IPage<Comment> commentIPage = new Page<>(20, pageNum);
        commentIPage = commentMapper.selectPage(commentIPage, Wrappers.<Comment>lambdaQuery()
                .eq(Comment::getPostId, postId)
                .isNull(Comment::getFatherId));
        historyMapper.insert(new History(null, null, postId, LocalDateTime.now()));
        return commentIPage.getRecords().stream().map(this::toCommentDO).toList();
    }

    @Override
    public Boolean addComment(Integer postId, Integer fatherId, String content, Integer userId) {
        Comment comment = new Comment(null, userId, postId, content, 0, fatherId);
        return commentMapper.insert(comment) > 0;
    }


    @Override
    @Transactional
    public Boolean like(Integer commentId) {
        Comment comment = commentMapper.selectOne(Wrappers.<Comment>lambdaQuery()
                .eq(Comment::getId, commentId)
                .last("for update"));
        if(comment == null) throw new LocalRuntimeException(ErrorEnum.PARAMS_ERROR, "评论不存在");
        comment.setLikeCount(comment.getLikeCount() + 1);
        return commentMapper.updateById(comment) > 0;
    }

    private CommentDO toCommentDO(Comment comment){
        CommentDO.CommentDOBuilder builder = CommentDO.builder().id(comment.getId());
        UserInfo userInfo = userInfoMapper.selectById(comment.getUserId());
        builder.username(userInfo.getUsername()).userAvatar(userInfo.getAvatar())
                .content(comment.getContent()).likeCount(comment.getLikeCount());
        if(comment.getFatherId() == null)
            builder.leafComments(commentMapper.selectList(Wrappers.<Comment>lambdaQuery()
                    .eq(Comment::getFatherId, comment.getId())).stream().map(this::toCommentDO).toList());
        return builder.build();
    }
}
