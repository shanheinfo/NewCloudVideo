package top.itshanhe.newcodevideo.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 视频信息表
 * </p>
 *
 * @author shanhe
 * @since 2024-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("video_info")
public class VideoInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 视频ID
     */
    @TableId(value = "video_id")
    private String videoId;

    /**
     * 上传者用户ID
     */
    private String userId;

    /**
     * 视频名称
     */
    private String videoName;

    /**
     * 视频描述
     */
    private String videoDescription;

    /**
     * 视频地址id
     */
    private Long videoAddressId;

    /**
     * 封面图片ID,没有就以视频第一帧为封面
     */
    private Long coverImgId;

    /**
     * 上传时间
     */
    private LocalDateTime videoCreateTime;

    /**
     * 是否是组合表
     */
    private Boolean isCombined;

    /**
     * 组合id，相同组合就是同一个组合Id，非组合就为 0 
     */
    private Long combinedId;

    /**
     * 标签id用,分隔开
     */
    private String keywordsId;

    /**
     * 分类id
     */
    private String categoryId;


}
