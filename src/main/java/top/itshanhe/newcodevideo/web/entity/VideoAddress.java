package top.itshanhe.newcodevideo.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 视频地址表
 * </p>
 *
 * @author shanhe
 * @since 2024-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("video_address")
public class VideoAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 视频地址ID
     */
    private Long videoAddressId;

    /**
     * 视频ID
     */
    @TableId(value = "video_id")
    private String videoId;

    /**
     * 视频地址
     */
    private String videoUrl;


}
