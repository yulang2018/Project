package com.yulang.project.response;

public class CodeMessage {
    private String code;
    private String message;

    private CodeMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public static final CodeMessage ERROR=new CodeMessage("100000","请求失败");
    public static final CodeMessage PARAMS_ERROR=new CodeMessage("100001","参数异常");
    public static final CodeMessage USER_NOT_EXITS=new CodeMessage("200000","用户不存在");
    public static final CodeMessage USER_PASSWORD_ERROR=new CodeMessage("200001","密码错误");
    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }


}
