package com.ljm.bo;

import lombok.Data;

/**
 * @ClassName CheckResult
 * @Description 接口参数校验结果
 * @Author Jim
 * @Date 2022/4/23 16:23
 **/
@Data
public class CheckResult<T> {
    // 校验结果
    private Boolean result;
    // 校验后数据
    private T data;

    public CheckResult(Boolean result, T data) {
        this.result = result;
        this.data = data;
    }
}
