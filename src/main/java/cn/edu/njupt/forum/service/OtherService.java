package cn.edu.njupt.forum.service;

import cn.edu.njupt.forum.data.CommentOperation;
import cn.edu.njupt.forum.data.OperationDO;

import java.util.List;

public interface OtherService {
    List<OperationDO> myLike(Integer id);

    List<OperationDO> myView(Integer id);

    List<CommentOperation> myComment(Integer id);

    byte[] getFile(String path);
}
