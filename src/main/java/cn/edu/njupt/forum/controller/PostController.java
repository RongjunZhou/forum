package cn.edu.njupt.forum.controller;

import cn.edu.njupt.forum.data.CommentDO;
import cn.edu.njupt.forum.data.Post;
import cn.edu.njupt.forum.enums.PlateTypeEnum;
import cn.edu.njupt.forum.service.PostService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/plate")
    public PlateTypeEnum[] getPlates() {
        return postService.getPlates();
    }

    @GetMapping("/post/{plate}/{page}")
    public IPage<Post> getPostPage(@PathVariable @RequestParam(required = false, defaultValue = "1") Integer plate,
                                   @PathVariable @RequestParam(required = false, defaultValue = "1") Integer page) {
        return postService.getPostPage(plate, page);
    }

    @GetMapping("/comment")
    public List<CommentDO> getComment(@NotNull Integer postId,
                                      @RequestParam(required = false, defaultValue = "1") Integer page){
        return postService.getComment(postId, page);
    }
}
