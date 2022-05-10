package com.ljm.api.invoke.check;

import com.alibaba.fastjson.JSONObject;
import com.ljm.bo.ParamData;
import com.ljm.enums.ResCodeEnum;
import com.ljm.except.CustomException;

import java.util.Map;

/**
 * @ClassName CheckParams
 * @Description
 * @Author Jim
 * @Date 2022/5/10 9:39
 **/
public class CheckParams {
    /**
     * @description TODO 校验接口所有参数数据
     * @param data 在校验过程中可能会被预处理
     * @return
     * @exception
     * @author Jim
     * @date 2022/5/10 9:31
     **/
    public static void checkParams(Map<String, ParamData> params, JSONObject data){
        // 遍历接口要求中的每一个参数
        for (String paramName : params.keySet()){
            ParamData param = params.get(paramName);
            // 按照接口参数的定义规范，去校验数据中参数是否合法
            CheckContext checkContext = new CheckContext(param.getType());
            if (!checkContext.executeCheck(data, param)){
                // 本参数校验失败，直接结束
                throw new CustomException(ResCodeEnum.API_DATA_IS_NOT_VALID);
            }
        }
    }
}
