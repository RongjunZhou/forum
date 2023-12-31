package cn.edu.njupt.forum.controller;

import cn.edu.njupt.forum.annotation.Info;
import cn.edu.njupt.forum.data.CommentDO;
import cn.edu.njupt.forum.data.Post;
import cn.edu.njupt.forum.enums.PlateTypeEnum;
import cn.edu.njupt.forum.model.UserInfo;
import cn.edu.njupt.forum.service.CommentService;
import cn.edu.njupt.forum.service.PostService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Valid
@RestController
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
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
    public Boolean addPost(@Info UserInfo userInfo, Integer plate, String title, String content, List<MultipartFile> resources) {
        return postService.addPost(userInfo.getId(), plate, title, content, resources);
    }

    @GetMapping("/comment")
    public List<CommentDO> getComment(@NotNull Integer postId, @Info UserInfo userInfo,
                                      @RequestParam(required = false, defaultValue = "1") Integer page) {
        return commentService.getComment(postId, page, userInfo.getId());
    }

    @PutMapping("/comment")
    public Boolean addComment(@NotNull Integer postId, Integer fatherId, String content, @Info UserInfo userInfo) {
        return commentService.addComment(postId, fatherId, content, userInfo.getId());
    }

    @PutMapping("/like/post")
    public Boolean likePost(@Info UserInfo userInfo, Integer postId) {
        return postService.like(userInfo.getId(), postId);
    }

    @PutMapping("/like/comment")
    public Boolean likeComment(Integer commentId) {
        return commentService.like(commentId);
    }
}
