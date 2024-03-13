package top.itshanhe.newcodevideo.web.service.impl;

import top.itshanhe.newcodevideo.web.entity.VideoComment;
import top.itshanhe.newcodevideo.web.mapper.VideoCommentMapper;
import top.itshanhe.newcodevideo.web.service.IVideoCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 视频评论表 服务实现类
 * </p>
 *
 * @author shanhe
 * @since 2024-03-13
 */
@Service
public class VideoCommentServiceImpl extends ServiceImpl<VideoCommentMapper, VideoComment> implements IVideoCommentService {

}
