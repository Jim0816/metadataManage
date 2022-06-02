package com.ljm.enums;

/**
 * @ClassName ResData
 * @Description 返回状态枚举
 * @Author Jim
 * @Date 2022/2/22 16:17
 **/
public enum ResCodeEnum {
    // 通用
    SUCCESS(200, "SUCCESS"),
    FAILED(500, "FAILED"),

    //用户模块 5002xx
    LOGIN_SUCCESS(500200, "登录成功"),
    LOGIN_ERROR_1(500201, "系统不存在该账号"),
    LOGIN_ERROR_2(500202, "密码错误"),
    LOGIN_ERROR_3(500203, "接受登录用户校验数据失败"),
    LOGIN_ERROR_4(500204, "用户校验成功，服务器异常导致token无效"),
    CREATE_USER_FAILED(500205, "用户创建失败"),
    UPDATE_USER_FAILED(500206, "用户修改失败"),
    DELETE_USER_FAILED(500207, "用户删除失败"),
    TOKEN_ERROR_INVALID(500202, "token不存在或者token格式有误"),
    TOKEN_ERROR_EXPIRE(500203, "token已经过期"),
    TOKEN_ERROR_EXIT(500204, "用户已经离线，token已被清理"),

    //接口模块 5003xx
    API_NAME_IS_EXIST(500300, "接口名称已存在"),
    API_DATA_IS_NOT_VALID(500301, "接口参数不合法"),
    API_IS_NOT_EXIST(500302, "接口不存在"),
    API_IS_ILLEGAL(500303, "接口不合法，校验不通过"),

    //模型模块 5004xx zyt 50040x 模型创建 50041x 模型
    MODEL_CREATE_SUCCESS(500400, "模型创建成功"),
    MODEL_CREATE_ERROR(500401, "模型创建失败"),
    MODEL_NAME_EXISTS(500402, "模型名称已经存在"),
    MODEL_DELETE_SUCCESS(500410, "模型删除成功"),
    MODEL_DELETE_ERROR(500411, "模型删除失败"),
    MODEL_UPDATE_SUCCESS(500420, "模型更新成功"),
    MODEL_NOT_UPDATE_SUCCESS(500421, "未修改相关数据"),
    MODEL_UPDATE_ERRORS(500422, "模型更新失败"),
    MODEL_DATA_EXISTS(500423, "存在数据，拒绝更新模型"),

    //字段模块 5005xx
    FIELD_EXISTED_CREATE_ERROR(500500, "字段已经存在，不能重复创建"),
    FIELD_EXISTED_UPDATE_ERROR(500501, "字段已经存在，拒绝修改"),

    // 封装执行mongodb模块 5006xx
    CONDITION_ERROR(500600, "注入数据失败，条件不合法");



    private final long code;
    private final String msg;

    ResCodeEnum(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ResCodeEnum getResCode(long code){
        ResCodeEnum[] resCodeEnums = ResCodeEnum.values();
        for (ResCodeEnum resCodeEnum : resCodeEnums) {
            if (resCodeEnum.getCode() == code){
                return resCodeEnum;
            }
        }
        return null;
    }

}
