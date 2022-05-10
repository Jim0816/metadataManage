package com.ljm.api.invoke.check;

import com.alibaba.fastjson.JSONObject;
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

    public boolean executeCheck(JSONObject data, ParamData param){
        return checkStrategy.check(data, param);
    }


}
