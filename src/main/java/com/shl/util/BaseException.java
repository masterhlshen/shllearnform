package com.shl.util;

public class BaseException extends RuntimeException {

    private Integer code;

    public BaseException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public BaseException(String formatMsg, Integer code, Object ... formatArgs) {
        super(String.format(formatMsg,formatArgs));
        this.code = code;
    }
    
    /**
     *    默认-1
     * @param message
     */
    public BaseException(String message) {
        super(message);
        this.code = -1;
    }


    public Integer getCode() {
        return code;
    }


}
