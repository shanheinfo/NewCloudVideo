package top.itshanhe.newcodevideo.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 内容
 * </p>
 *
 * @author shanhe
 * @date 2024/3/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultUtil<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;
    
    public ResultUtil(Integer code, T data) {
        this.code = code;
        this.data = data;
    }
    public ResultUtil(Integer code, String msg, T data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
    
    public ResultUtil(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
