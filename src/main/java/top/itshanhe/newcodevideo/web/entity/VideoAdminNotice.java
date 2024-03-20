package top.itshanhe.newcodevideo.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 管理员公告表
 * </p>
 *
 * @author shanhe
 * @since 2024-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("video_admin_notice")
public class VideoAdminNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 管理员ID
     */
    private String adminId;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 发布时间
     */
    private LocalDateTime createTime;

    /**
     * 是否顶置 0: 否，1: 是
     */
    private Boolean isTop;

    /**
     * 是否过期 0: 否，1: 是
     */
    private Boolean isExpired;

    /**
     * 是否首页 0: 否，1: 是
     */
    private Boolean isHomepage;


}
