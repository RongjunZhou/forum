package cn.edu.njupt.forum.service;

import cn.edu.njupt.forum.data.Post;
import cn.edu.njupt.forum.enums.PlateTypeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    PlateTypeEnum[] getPlates();

    IPage<Post> getPostPage(Integer plate, Integer page);

    Boolean like(Integer userId, Integer postId);

    Boolean addPost(Integer id, Integer plate, String title, String content, List<MultipartFile> resources);
}
