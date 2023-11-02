package cn.edu.njupt.forum.service;

import cn.edu.njupt.forum.data.CommentDO;
import cn.edu.njupt.forum.data.Post;
import cn.edu.njupt.forum.enums.PlateTypeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface PostService {
    PlateTypeEnum[] getPlates();

    IPage<Post> getPostPage(Integer plate, Integer page);

    List<CommentDO> getComment(Integer postId, Integer pageNum);

    Boolean addComment(Integer postId, Integer fatherId, String content, Integer userId);
}
