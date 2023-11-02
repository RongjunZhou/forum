package cn.edu.njupt.forum.service.impl;

import cn.edu.njupt.forum.enums.PlateTypeEnum;
import cn.edu.njupt.forum.mapper.CommentMapper;
import cn.edu.njupt.forum.mapper.PostInfoMapper;
import cn.edu.njupt.forum.mapper.PostMapper;
import cn.edu.njupt.forum.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final PostInfoMapper postInfoMapper;
    private final CommentMapper commentMapper;

    public PostServiceImpl(PostMapper postMapper, PostInfoMapper postInfoMapper, CommentMapper commentMapper) {
        this.postMapper = postMapper;
        this.postInfoMapper = postInfoMapper;
        this.commentMapper = commentMapper;
    }

    @Override
    public PlateTypeEnum[] getPlates() {
        return PlateTypeEnum.values();
    }
}
