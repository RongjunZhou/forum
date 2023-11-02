package cn.edu.njupt.forum.service;

import cn.edu.njupt.forum.data.PraiseDO;

import java.util.List;

public interface OtherService {
    List<PraiseDO> myLike(Integer id);
}
