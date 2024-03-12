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
 * 视频浏览记录表
 * </p>
 *
 * @author shanhe
 * @since 2024-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("video_view_history")
public class VideoViewHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 视频ID
     */
    private String videoId;

    /**
     * 浏览次数
     */
    private Long historyId;


}
