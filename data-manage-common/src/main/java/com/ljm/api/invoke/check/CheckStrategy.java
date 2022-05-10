package com.ljm.api.invoke.check;

import com.alibaba.fastjson.JSONObject;
import com.ljm.bo.ParamData;


/**
 * @description 校验接口数据策略
 * @return
 * @exception
 * @author Jim
 * @date 2022/4/23 16:10
 **/
public interface CheckStrategy {
    /**
     * @description 校验参数策略
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/23 16:16
     **/
    public boolean check(JSONObject data, ParamData param);
}
