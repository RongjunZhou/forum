package cn.edu.njupt.forum.service.impl;

import cn.edu.njupt.forum.data.PraiseDO;
import cn.edu.njupt.forum.mapper.PostMapper;
import cn.edu.njupt.forum.mapper.PraiseMapper;
import cn.edu.njupt.forum.model.Praise;
import cn.edu.njupt.forum.service.OtherService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherServiceImpl implements OtherService {

    private final PraiseMapper praiseMapper;
    private final PostMapper postMapper;

    public OtherServiceImpl(PraiseMapper praiseMapper, PostMapper postMapper) {
        this.praiseMapper = praiseMapper;
        this.postMapper = postMapper;
    }

    @Override
    public List<PraiseDO> myLike(Integer id) {
        return praiseMapper.selectList(Wrappers.<Praise>lambdaQuery()
                .eq(Praise::getUserId, id)).stream().map(praise -> {
            PraiseDO praiseDO = new PraiseDO();
            praiseDO.setPostId(praise.getPostId());
            praiseDO.setTime(praise.getTime());
            praiseDO.setTitle(postMapper.selectById(praise.getPostId()).getTitle());
            return praiseDO;
        }).toList();
    }
}
