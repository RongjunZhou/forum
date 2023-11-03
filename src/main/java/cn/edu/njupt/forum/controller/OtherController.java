package cn.edu.njupt.forum.controller;

import cn.edu.njupt.forum.annotation.Info;
import cn.edu.njupt.forum.data.CommentOperation;
import cn.edu.njupt.forum.data.OperationDO;
import cn.edu.njupt.forum.model.UserInfo;
import cn.edu.njupt.forum.service.OtherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/other")
public class OtherController {

    private final OtherService otherService;

    public OtherController(OtherService otherService) {
        this.otherService = otherService;
    }

    @GetMapping("/myLike")
    public List<OperationDO> myLike(@Info UserInfo userInfo) {
        return otherService.myLike(userInfo.getId());
    }

    @GetMapping("/myView")
    public List<OperationDO> myView(@Info UserInfo userInfo) {
        return otherService.myView(userInfo.getId());
    }

    @GetMapping("/myComment")
    public List<CommentOperation> myComment(@Info UserInfo userInfo) {
        return otherService.myComment(userInfo.getId());
    }


    @GetMapping("/static/{path}")
    public byte[] resources(@PathVariable String path) {
        return otherService.getFile(path);
    }
}
