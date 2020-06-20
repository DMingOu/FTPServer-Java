package com.odm.ftp.entity;

/**
 * @ClassName: Result
 * @Auther: DMingO
 * @Date: 2020/6/20 14:28
 * @Description: ²Ù×÷½á¹û
 */
public class Result {

    int code;

    String msg;

    Object data;

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

    public Object getData() {
        if(this.data == null){
            return "null";
        }
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result [" +
                " code : " + getCode() +
                ".  msg : " + getMsg() +
                ",  data : " + getData().toString() +
                " ]";
    }
}
