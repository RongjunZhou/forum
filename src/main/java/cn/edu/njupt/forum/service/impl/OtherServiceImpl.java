package cn.edu.njupt.forum.service.impl;

import cn.edu.njupt.forum.data.CommentOperation;
import cn.edu.njupt.forum.data.OperationDO;
import cn.edu.njupt.forum.mapper.*;
import cn.edu.njupt.forum.model.Praise;
import cn.edu.njupt.forum.service.OtherService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherServiceImpl implements OtherService {

    private final PraiseMapper praiseMapper;
    private final PostMapper postMapper;
    private final HistoryMapper historyMapper;
    private final CommentMapper commentMapper;
    private final FileMapper fileMapper;

    public OtherServiceImpl(PraiseMapper praiseMapper, PostMapper postMapper, HistoryMapper historyMapper, CommentMapper commentMapper, FileMapper fileMapper) {
        this.praiseMapper = praiseMapper;
        this.postMapper = postMapper;
        this.historyMapper = historyMapper;
        this.commentMapper = commentMapper;
        this.fileMapper = fileMapper;
    }

    @Override
    public List<OperationDO> myLike(Integer id) {
        return praiseMapper.selectList(Wrappers.<Praise>lambdaQuery()
                .eq(Praise::getUserId, id)).stream().map(praise -> {
            OperationDO operationDO = new OperationDO();
            operationDO.setPostId(praise.getPostId());
            operationDO.setTime(praise.getTime());
            operationDO.setTitle(postMapper.selectById(praise.getPostId()).getTitle());
            return operationDO;
        }).toList();
    }

    @Override
    public List<OperationDO> myView(Integer id) {
        return historyMapper.selectList(Wrappers.<cn.edu.njupt.forum.model.History>lambdaQuery()
                .eq(cn.edu.njupt.forum.model.History::getUserId, id)).stream().map(history -> {
            OperationDO operationDO = new OperationDO();
            operationDO.setPostId(history.getPostId());
            operationDO.setTime(history.getTime());
            operationDO.setTitle(postMapper.selectById(history.getPostId()).getTitle());
            return operationDO;
        }).toList();
    }

    @Override
    public List<CommentOperation> myComment(Integer id) {
        return commentMapper.selectList(Wrappers.<cn.edu.njupt.forum.model.Comment>lambdaQuery()
                .eq(cn.edu.njupt.forum.model.Comment::getUserId, id)).stream().map(comment -> {
            CommentOperation commentOperation = new CommentOperation();
            commentOperation.setPostId(comment.getPostId());
            commentOperation.setTime(comment.getTime());
            commentOperation.setTitle(postMapper.selectById(comment.getPostId()).getTitle());
            commentOperation.setComment(comment.getContent());
            return commentOperation;
        }).toList();
    }

    @Override
    @SneakyThrows
    public byte[] getFile(String path) {
        return fileMapper.selectById(path).getContent().getBinaryStream().readAllBytes();
    }
}
