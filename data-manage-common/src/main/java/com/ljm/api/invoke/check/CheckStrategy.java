package com.ljm.api.invoke.check;

import com.ljm.bo.CheckResult;
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
    public <T1, T2> CheckResult<T1> check(T1 data, ParamData<T2> param);
}
