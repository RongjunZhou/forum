package cn.edu.njupt.forum.controller;

import cn.edu.njupt.forum.data.Post;
import cn.edu.njupt.forum.enums.PlateTypeEnum;
import cn.edu.njupt.forum.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Post> getPostPage(@PathVariable Integer plate, @PathVariable Integer page) {
        return null;
    }

}
