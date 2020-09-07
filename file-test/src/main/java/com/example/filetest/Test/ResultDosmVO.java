package com.example.filetest.Test;

/**
 * @author Silen
 */
public class ResultDosmVO {
    private Integer code;
    private String msg;
    private Object data;
    private Object status = "success";

    private static final Integer SUCCESS_CODE = 200;
    private final static int EXCEPTION_CODE = 500;
    private final static String EXCEPTION_MSG = "服务异常";

    public ResultDosmVO(Integer code, String message, Object data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public ResultDosmVO(Integer code, String message, Object data, Object status) {
        this.code = code;
        this.msg = message;
        this.data = data;
        this.status = status;
    }

    public ResultDosmVO() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultVO{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static ResultDosmVO success(String message, Object data) {
        return new ResultDosmVO(SUCCESS_CODE, message, data);
    }

    public static ResultDosmVO success(Object data) {
        return success("success", data);
    }

    public static ResultDosmVO success() {
        return success(null);
    }

    public static ResultDosmVO error() {
        return getException();
    }

    public static ResultDosmVO getException() {
        return new ResultDosmVO(EXCEPTION_CODE, EXCEPTION_MSG, null, 500);
    }

    public static ResultDosmVO getException(String msg) {
        return new ResultDosmVO(EXCEPTION_CODE, msg, null, 500);
    }
}
