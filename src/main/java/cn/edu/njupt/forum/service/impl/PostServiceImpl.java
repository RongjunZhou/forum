package cn.edu.njupt.forum.service.impl;

import cn.edu.njupt.forum.data.CommentDO;
import cn.edu.njupt.forum.data.Post;
import cn.edu.njupt.forum.enums.PlateTypeEnum;
import cn.edu.njupt.forum.mapper.CommentMapper;
import cn.edu.njupt.forum.mapper.PostInfoMapper;
import cn.edu.njupt.forum.mapper.PostMapper;
import cn.edu.njupt.forum.mapper.UserInfoMapper;
import cn.edu.njupt.forum.model.Comment;
import cn.edu.njupt.forum.model.UserInfo;
import cn.edu.njupt.forum.service.PostService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final PostInfoMapper postInfoMapper;
    private final CommentMapper commentMapper;
    private final UserInfoMapper userInfoMapper;

    public PostServiceImpl(PostMapper postMapper, PostInfoMapper postInfoMapper, CommentMapper commentMapper, UserInfoMapper userInfoMapper) {
        this.postMapper = postMapper;
        this.postInfoMapper = postInfoMapper;
        this.commentMapper = commentMapper;
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public PlateTypeEnum[] getPlates() {
        return PlateTypeEnum.values();
    }

    @Override
    public IPage<Post> getPostPage(Integer plate, Integer page) {
        IPage<Post> iPage = new Page<>(page, 10);
        return postMapper.selectPage(iPage, Wrappers.<Post>lambdaQuery()
                .eq(Post::getPlateId, plate));
    }

    @Override
    public List<CommentDO> getComment(Integer postId, Integer pageNum) {
        IPage<Comment> commentIPage = new Page<>(20, pageNum);
        commentIPage = commentMapper.selectPage(commentIPage, Wrappers.<Comment>lambdaQuery()
                .eq(Comment::getPostId, postId)
                .isNull(Comment::getFatherId));
        return commentIPage.getRecords().stream().map(this::toCommentDO).toList();
    }

    @Override
    public Boolean addComment(Integer postId, Integer fatherId, String content, Integer userId) {
        Comment comment = new Comment(null, userId, postId, content, 0, fatherId);
        return commentMapper.insert(comment) > 0;
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
