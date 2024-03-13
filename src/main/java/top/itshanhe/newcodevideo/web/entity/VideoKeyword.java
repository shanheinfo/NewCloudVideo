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
 * 视频关键词表
 * </p>
 *
 * @author shanhe
 * @since 2024-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("video_keyword")
public class VideoKeyword implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关键词ID
     */
    @TableId(value = "keyword_id")
    private String keywordId;

    /**
     * 关键词名称
     */
    private String keywordName;


}
