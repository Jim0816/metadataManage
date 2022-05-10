package com.ljm.api.invoke.check;

import com.ljm.bo.CheckResult;
import com.ljm.bo.ParamData;

/**
 * @ClassName CheckContext
 * @Description 校验策略上下文切换，修改策略
 * @Author Jim
 * @Date 2022/4/23 16:19
 **/
public class CheckContext {
    private CheckStrategy checkStrategy;

    public CheckContext(String paramType){
        this.checkStrategy = ParamData.PARAM_FIELD_TYPE.equals(paramType) ?
                new FieldCheck() : new ModelCheck();
    }

    public <T1, T2> CheckResult<T1> executeCheck(T1 data, ParamData<T2> param){
        return checkStrategy.check(data, param);
    }
}
