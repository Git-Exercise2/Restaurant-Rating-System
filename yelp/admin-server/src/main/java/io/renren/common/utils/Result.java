

package io.renren.common.utils;

import io.renren.common.exception.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * Response data
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Schema(title = "response")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Encoding: 0 means success, other values ​​means failure
     */
    @Schema(title = "Encoding: 0 means success, other values ​​means failure")
    private int code = 0;
    /**
     * Message content
     */
    @Schema(title = "Message content")
    private String msg = "success";
    /**
     * Response data
     */
    @Schema(title = "response数据")
    private T data;

    public Result<T> ok(T data) {
        this.setData(data);
        return this;
    }

    public boolean success(){
        return code == 0;
    }

    public Result<T> error() {
        this.code = ErrorCode.INTERNAL_SERVER_ERROR;
        this.msg = MessageUtils.getMessage(this.code);
        return this;
    }

    public Result<T> error(int code) {
        this.code = code;
        this.msg = MessageUtils.getMessage(this.code);
        return this;
    }

    public Result<T> error(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public Result<T> error(String msg) {
        this.code = ErrorCode.INTERNAL_SERVER_ERROR;
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
