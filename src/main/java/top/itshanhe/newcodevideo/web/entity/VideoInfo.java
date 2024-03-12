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
 * @since 2024-03-12
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
     * 视频地址
     */
    private Long videoAddressId;

    /**
     * 上传时间
     */
    private LocalDateTime videoCreateTime;


}
