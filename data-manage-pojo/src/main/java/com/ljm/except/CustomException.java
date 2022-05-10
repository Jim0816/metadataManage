package com.ljm.except;

import com.ljm.enums.ResCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ModelException
 * @Description
 * @Author zyt
 * @Date 2022/4/10 18:11
 **/
@Data
@NoArgsConstructor
public class CustomException extends RuntimeException {

    private ResCodeEnum resCodeEnum;


    public CustomException(ResCodeEnum resCodeEnum) {
        super(resCodeEnum.getMsg());
        this.resCodeEnum = resCodeEnum;
    }
}
