package com.shl.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class HandleResult {
	// 全局成功标识代码
    public final static Integer SUCCESS = 0;

    // 全局错误标识代码
    public final static Integer FAILURE = -1;


    /** 标识操作结果类型 */
    public enum RESULT_TYPE {
        // @MetaData(value = "成功", comments = "操作处理成功。")
        success,

        // @MetaData(value = "警告", comments = "用于提示业务处理基本完成，但是其中存在一些需要注意事项放在message或data中的提示信息。")
        warning,

        // @MetaData(value = "失败", comments = "操作处理失败。")
        failure,

        // @MetaData(value = "确认", comments = "本次提交中止，提示用户进行确认。")
        confirm,

        // @MetaData(value = "失败", comments = "操作未授权。")
        unauthorized
    }

    /** 返回success或failure操作标识 */
    private String type;

    /** 成功：0，其他标识错误 */
    private Integer code;

    /** 消息正文 */
    private String message;

    /** 补充的业务数据 */
    private Object data;


    public String getType() {
        return type;
    }

    public HandleResult setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public HandleResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public HandleResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public HandleResult setData(Object data) {
        this.data = data;
        return this;
    }


    public static HandleResult buildSuccessResult(String message, Object data) {
        return new HandleResult(RESULT_TYPE.success, message, data).setCode(SUCCESS);
    }

    public static HandleResult buildSuccessResult() {
        return new HandleResult(RESULT_TYPE.success, "操作成功").setCode(SUCCESS);
    }

    public static HandleResult buildSuccessResult(String message) {
        return new HandleResult(RESULT_TYPE.success, message).setCode(SUCCESS);
    }

    public static HandleResult buildSuccessResult(Object data) {
        return new HandleResult(RESULT_TYPE.success, "操作成功", data).setCode(SUCCESS);
    }

    public static HandleResult buildWarningResult(String message, Object data) {
        return new HandleResult(RESULT_TYPE.warning, message, data).setCode(SUCCESS);
    }

    public static HandleResult buildFailureResult(String message) {
        return new HandleResult(RESULT_TYPE.failure, message).setCode(FAILURE);
    }

    public static HandleResult buildFailureResult(String message, Object data) {
        return new HandleResult(RESULT_TYPE.failure, message, data).setCode(FAILURE);
    }

    public static HandleResult buildUnauthorizedResult(String message) {
        return new HandleResult(RESULT_TYPE.unauthorized, message).setCode(FAILURE);
    }

    public static HandleResult buildUnauthorizedResult(String message, Object data) {
        return new HandleResult(RESULT_TYPE.unauthorized, message, data).setCode(FAILURE);
    }

    public static HandleResult buildConfirmResult(String message, Object data) {
        return new HandleResult(RESULT_TYPE.confirm, message, data).setCode(SUCCESS);
    }

    public static HandleResult buildConfirmResult(String message) {
        return new HandleResult(RESULT_TYPE.confirm, message, null).setCode(SUCCESS);
    }

    public static HandleResult buildExceptionResult(BaseException baseException) {
        return new HandleResult(baseException);
    }

    private HandleResult() {
    }

    private HandleResult(BaseException baseException)
    {
        this.type = RESULT_TYPE.failure.name();
        this.code = baseException.getCode();
        this.message = baseException.getMessage();
    }

    private HandleResult(RESULT_TYPE type, String message) {
        this.type = type.name();
        this.message = message;
    }

    private HandleResult(RESULT_TYPE type, String message, Object data) {
        this.type = type.name();
        this.message = message;
        this.data = data;
    }

}
