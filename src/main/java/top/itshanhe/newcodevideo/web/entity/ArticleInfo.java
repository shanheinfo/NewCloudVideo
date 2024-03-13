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
 * 图文信息表
 * </p>
 *
 * @author shanhe
 * @since 2024-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("article_info")
public class ArticleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 图文ID
     */
    private String articleId;

    /**
     * 发布者用户ID
     */
    private String userId;

    /**
     * 图文标题
     */
    private String articleTitle;

    /**
     * 图文内容
     */
    private String articleContent;

    /**
     * 封面图片ID
     */
    private Long coverImgId;

    /**
     * 发布时间
     */
    private LocalDateTime articleCreateTime;

    /**
     * 标签ID用，分隔开
     */
    private String keywordsId;

    /**
     * 分类ID
     */
    private String categoryId;


}
