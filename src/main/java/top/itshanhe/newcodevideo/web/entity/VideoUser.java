package top.itshanhe.newcodevideo.web.entity;

import java.math.BigDecimal;
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
 * 用户表
 * </p>
 *
 * @author shanhe
 * @since 2024-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("video_user")
public class VideoUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 账号
     */
    private String userName;

    /**
     * 昵称
     */
    private String userNickName;

    /**
     * 邮箱
     */
    private String userMail;

    /**
     * 手机号
     */
    private String userPhone;

    /**
     * 密码
     */
    private String userPwd;

    /**
     * 支付宝用户ID
     */
    private String alipayUserId;

    /**
     * 粉丝数
     */
    private Integer userFans;

    /**
     * 关注数
     */
    private Integer userFollow;

    /**
     * 余额
     */
    private BigDecimal moneyData;

    /**
     * 头像地址id,默认为默认头像地址
     */
    private Long userIcon;

    /**
     * 创建时IP
     */
    private String userCreateIp;

    /**
     * 创建时间
     */
    private LocalDateTime userCreateTime;

    /**
     * 修改时间
     */
    private LocalDateTime userUpdateTime;

    /**
     * 用户是否封禁
     */
    private Boolean userStatus;


}
