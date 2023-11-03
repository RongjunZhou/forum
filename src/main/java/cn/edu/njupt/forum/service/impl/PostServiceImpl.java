package cn.edu.njupt.forum.service.impl;

import cn.edu.njupt.forum.data.Post;
import cn.edu.njupt.forum.enums.ErrorEnum;
import cn.edu.njupt.forum.enums.PlateTypeEnum;
import cn.edu.njupt.forum.exception.LocalRuntimeException;
import cn.edu.njupt.forum.mapper.FileMapper;
import cn.edu.njupt.forum.mapper.PostInfoMapper;
import cn.edu.njupt.forum.mapper.PostMapper;
import cn.edu.njupt.forum.mapper.PraiseMapper;
import cn.edu.njupt.forum.model.File;
import cn.edu.njupt.forum.model.PostInfo;
import cn.edu.njupt.forum.model.Praise;
import cn.edu.njupt.forum.service.PostService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final PostInfoMapper postInfoMapper;

    private final PraiseMapper praiseMapper;
    private final FileMapper fileMapper;

    public PostServiceImpl(PostMapper postMapper, PostInfoMapper postInfoMapper, PraiseMapper praiseMapper, FileMapper fileMapper) {
        this.postMapper = postMapper;
        this.postInfoMapper = postInfoMapper;
        this.praiseMapper = praiseMapper;
        this.fileMapper = fileMapper;
    }

    @Override
    public PlateTypeEnum[] getPlates() {
        return PlateTypeEnum.values();
    }

    @Override
    public IPage<Post> getPostPage(Integer plate, Integer page) {
        IPage<Post> iPage = new Page<>(page, 10);
        iPage = postMapper.selectPage(iPage, Wrappers.<Post>lambdaQuery()
                .eq(Post::getPlateId, plate)
                .orderByDesc(Post::getUpdateTime));
        iPage.getRecords().forEach(post -> post.setLike(praiseMapper.selectCount(Wrappers.<Praise>lambdaQuery()
                .eq(Praise::getUserId, post.getUserId())
                .eq(Praise::getPostId, post.getId())) > 0));
        return iPage;
    }


    @Override
    public Boolean like(Integer userId, Integer postId) {
        Praise praise = praiseMapper.selectOne(Wrappers.<Praise>lambdaQuery()
                .eq(Praise::getUserId, userId)
                .eq(Praise::getPostId, postId));
        if (praise == null) return praiseMapper.insert(new Praise(null, userId, postId, LocalDateTime.now())) > 0;
        return praiseMapper.deleteById(praise.getId()) < 0;
    }

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public Boolean addPost(Integer id, Integer plate, String title, String content, List<MultipartFile> resources) {
        List<String> resourceUrls = resources == null ? null : resources.stream().map(resource -> {
            try {
                String url = UUID.randomUUID().toString();
                File file = new File(url, resource);
                fileMapper.insert(file);
                return url;
            } catch (IOException | SQLException e) {
                throw new LocalRuntimeException(ErrorEnum.INTERNAL_ERROR, "文件上传失败");
            }
        }).toList();
        PostInfo postInfo =
                new PostInfo(null, id, PlateTypeEnum.getById(plate), title, content, resourceUrls, LocalDateTime.now());
        return postInfoMapper.insert(postInfo) > 0;
    }

}
