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
 * 粉丝关注表
 * </p>
 *
 * @author shanhe
 * @since 2024-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_follow_relation")
public class UserFollowRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关注ID
     */
    @TableId(value = "follow_id", type = IdType.AUTO)
    private Long followId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 被关注者用户ID
     */
    private String followedUserId;


}
