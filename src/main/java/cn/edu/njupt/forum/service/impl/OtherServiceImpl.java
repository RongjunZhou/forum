package cn.edu.njupt.forum.service.impl;

import cn.edu.njupt.forum.data.PraiseDO;
import cn.edu.njupt.forum.data.ViewDO;
import cn.edu.njupt.forum.mapper.HistoryMapper;
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
    private final HistoryMapper historyMapper;

    public OtherServiceImpl(PraiseMapper praiseMapper, PostMapper postMapper, HistoryMapper historyMapper) {
        this.praiseMapper = praiseMapper;
        this.postMapper = postMapper;
        this.historyMapper = historyMapper;
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

    @Override
    public List<ViewDO> myView(Integer id) {
        return historyMapper.selectList(Wrappers.<cn.edu.njupt.forum.model.History>lambdaQuery()
                .eq(cn.edu.njupt.forum.model.History::getUserId, id)).stream().map(history -> {
            ViewDO viewDO = new ViewDO();
            viewDO.setPostId(history.getPostId());
            viewDO.setTime(history.getTime());
            viewDO.setTitle(postMapper.selectById(history.getPostId()).getTitle());
            return viewDO;
        }).toList();
    }
}
