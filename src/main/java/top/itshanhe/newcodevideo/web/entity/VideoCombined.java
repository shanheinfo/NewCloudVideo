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
 * 视频组合表
 * </p>
 *
 * @author shanhe
 * @since 2024-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("video_combined")
public class VideoCombined implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 组合ID
     */
    private Long combinedId;

    /**
     * 视频ID
     */
    @TableId(value = "video_id")
    private String videoId;

    /**
     * 组合名称
     */
    private String combinedName;

    /**
     * 组合描述
     */
    private String combinedDescription;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
