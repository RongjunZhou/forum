package cn.edu.njupt.forum.service;

import cn.edu.njupt.forum.data.PraiseDO;
import cn.edu.njupt.forum.data.ViewDO;

import java.util.List;

public interface OtherService {
    List<PraiseDO> myLike(Integer id);

    List<ViewDO> myView(Integer id);
}
