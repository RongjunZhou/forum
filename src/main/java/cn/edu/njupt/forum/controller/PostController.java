package cn.edu.njupt.forum.controller;

import cn.edu.njupt.forum.annotation.Info;
import cn.edu.njupt.forum.data.CommentDO;
import cn.edu.njupt.forum.data.Post;
import cn.edu.njupt.forum.enums.PlateTypeEnum;
import cn.edu.njupt.forum.model.UserInfo;
import cn.edu.njupt.forum.service.PostService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/post")
    public Boolean addPost(@Info UserInfo userInfo, Integer plate, String title, String content, List<String> resources){
        //return postService.addPost(userInfo.getId(), plate, title, content, resources);
        return null;
    }

    @GetMapping("/comment")
    public List<CommentDO> getComment(@NotNull Integer postId, @Info UserInfo userInfo,
                                      @RequestParam(required = false, defaultValue = "1") Integer page){
        return postService.getComment(postId, page, userInfo.getId());
    }

    @PutMapping("/comment")
    public Boolean addComment(@NotNull Integer postId, Integer fatherId, String content, @Info UserInfo userInfo){
        return postService.addComment(postId, fatherId, content, userInfo.getId());
    }

    @PutMapping("/like/post")
    public Boolean likePost(@Info UserInfo userInfo, Integer postId){
        return postService.like(userInfo.getId(), postId);
    }

    @PutMapping("/like/comment")
    public Boolean likeComment(Integer commentId){
        return postService.like(commentId);
    }
}
