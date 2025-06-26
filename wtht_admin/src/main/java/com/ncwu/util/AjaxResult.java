package com.ncwu.util;

/**
 * ClassName: AjaxResult
 * Package: com.ncwu.domain
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/6 11:54
 * @Version 1.0
 */
public class AjaxResult {
    private Object data;
    private int code;
    private String msg;


    public AjaxResult(Object data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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

    @Override
    public String toString() {
        return "AjaxResult{" +
                "data=" + data +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
