package cn.edu.njupt.forum.controller;

import cn.edu.njupt.forum.annotation.Info;
import cn.edu.njupt.forum.data.PraiseDO;
import cn.edu.njupt.forum.model.UserInfo;
import cn.edu.njupt.forum.service.OtherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/other")
public class OtherController {

    private final OtherService otherService;

    public OtherController(OtherService otherService) {
        this.otherService = otherService;
    }

    @GetMapping("/myLike")
    public List<PraiseDO> myLike(@Info UserInfo userInfo) {
        return otherService.myLike(userInfo.getId());
    }

    @GetMapping("/myView")
    public String myView() {
        return "myView";
    }

    @GetMapping("/myComment")
    public String myComment() {
        return "myComment";
    }


    @GetMapping("/static/{path}")
    public MultipartFile resources(@PathVariable String path){
        return null;
    }
}