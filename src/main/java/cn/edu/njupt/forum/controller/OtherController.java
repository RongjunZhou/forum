package cn.edu.njupt.forum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/other")
public class OtherController {

    @GetMapping("/myLike")
    public String myLike() {
        return "myLike";
    }

    @GetMapping("/myView")
    public String myView() {
        return "myView";
    }

    @GetMapping("/myComment")
    public String myComment() {
        return "myComment";
    }

    @GetMapping("/myPost")
    public String myPost() {
        return "myPost";
    }

    @GetMapping("/static/{path}")
    public MultipartFile resources(@PathVariable String path){
        return null;
    }
}
