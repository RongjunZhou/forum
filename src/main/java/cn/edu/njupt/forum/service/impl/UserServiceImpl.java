package cn.edu.njupt.forum.service.impl;

import cn.edu.njupt.forum.data.Post;
import cn.edu.njupt.forum.data.UserInfoDO;
import cn.edu.njupt.forum.enums.ErrorEnum;
import cn.edu.njupt.forum.exception.LocalRuntimeException;
import cn.edu.njupt.forum.mapper.HistoryMapper;
import cn.edu.njupt.forum.mapper.PostMapper;
import cn.edu.njupt.forum.mapper.UserInfoMapper;
import cn.edu.njupt.forum.model.History;
import cn.edu.njupt.forum.model.UserInfo;
import cn.edu.njupt.forum.service.UserService;
import cn.edu.njupt.forum.util.EncryptUtil;
import cn.edu.njupt.forum.util.JwtUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserInfoMapper userInfoMapper;
    private final PostMapper postMapper;
    private final HistoryMapper historyMapper;
    public UserServiceImpl(UserInfoMapper userInfoMapper, PostMapper postMapper, HistoryMapper historyMapper) {
        this.userInfoMapper = userInfoMapper;
        this.postMapper = postMapper;
        this.historyMapper = historyMapper;
    }

    @Override
    public String login(String username, String password) {
        UserInfo userInfo = userInfoMapper.selectOne(Wrappers.<UserInfo>lambdaQuery()
                .select(UserInfo::getPassword)
                .eq(UserInfo::getUsername, username));
        if (userInfo == null || !EncryptUtil.decode(userInfo.getPassword()).equals(password))
            throw new LocalRuntimeException(ErrorEnum.PARAMS_ERROR, "用户名或密码错误");
        userInfo = userInfoMapper.selectOne(Wrappers.<UserInfo>lambdaQuery()
                .eq(UserInfo::getUsername, username));
        return JwtUtil.generateJwt(userInfo);
    }

    @Override
    public Boolean changePassword(UserInfo userInfo, String password) {
        UserInfo userInfoOrigin = userInfoMapper.selectById(userInfo.getId());
        if (!EncryptUtil.decode(userInfoOrigin.getPassword()).equals(userInfo.getPassword()))
            throw new LocalRuntimeException(ErrorEnum.PARAMS_ERROR, "原密码错误");
        userInfo.setPassword(EncryptUtil.encode(password));
        return userInfoMapper.updateById(userInfo) > 0;
    }

    @Override
    public String register(String username, String email) {
        return null;
    }

    @Override
    public UserInfoDO getUserInfo(UserInfo userInfo) {
        List<Post> posts = postMapper.selectList(Wrappers.<Post>lambdaQuery().eq(Post::getUserId, userInfo.getId()));
        History history = historyMapper.selectOne(Wrappers.<History>lambdaQuery().eq(History::getUserId, userInfo.getId()).orderByDesc(History::getTime).last("limit 1"));
        LocalDate historyTime = history == null ? LocalDate.now() : history.getTime().toLocalDate();
        return new UserInfoDO(userInfo,historyTime,posts.size(), Math.toIntExact(posts.stream().map(Post::getLikeCount).count()));
    }

}
