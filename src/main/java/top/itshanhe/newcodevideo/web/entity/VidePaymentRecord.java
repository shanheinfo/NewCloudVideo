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
 * 支付记录表
 * </p>
 *
 * @author shanhe
 * @since 2024-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("vide_payment_record")
public class VidePaymentRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 支付记录ID
     */
    @TableId(value = "payment_id", type = IdType.AUTO)
    private Integer paymentId;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 支付类型
     */
    private String paymentType;

    /**
     * 支付金额
     */
    private BigDecimal paymentAmount;

    /**
     * 支付宝交易号
     */
    private String alipayTradeNo;

    /**
     * 支付状态
     */
    private String paymentStatus;

    /**
     * 支付时间
     */
    private LocalDateTime paymentTime;


}
