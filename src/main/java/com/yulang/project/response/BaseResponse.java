package com.yulang.project.response;
public class BaseResponse<T> {

    private String code;
    private String msg;
    private T data;

    private BaseResponse(T data) {
        this.code= "000000";
        this.msg="请求成功";
        this.data=data;
    }

    private BaseResponse(CodeMessage codeMessage){
        if(codeMessage==null){
            return;
        }
        this.code=codeMessage.getCode();
        this.msg=codeMessage.getMessage();
    }
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse(data);
    }
    public static <T> BaseResponse<T> erro(CodeMessage codeMessage){
        return new BaseResponse(codeMessage);
    }
    public String getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    public Object getData() {
        return data;
    }
}
