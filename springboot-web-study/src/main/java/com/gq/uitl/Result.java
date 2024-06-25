package com.gq.uitl;

/*
* 用来同一相应数据的格式
* */
public class  Result {
    //响应码，1代表成功；0代表失败
    private Integer code;
    //提示信息
    private String msg;
    //返回的数据
    private Object data;

    public static Result success(Object data){
        Result result = new Result(1,"成功",data);
        return result;
    }
    public static Result success(){
        Result result = new Result(1,"成功",null);
        return result;
    }
    public static Result err(String msg){
        Result result = new Result(0,msg,null);
        return result;
    }
    public Result() {
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
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
}
