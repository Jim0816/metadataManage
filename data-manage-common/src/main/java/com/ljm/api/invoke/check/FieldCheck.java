package com.ljm.api.invoke.check;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ljm.bo.CheckResult;
import com.ljm.bo.ParamData;
import com.ljm.entity.FieldInfo;


/**
 * @ClassName FieldCheck
 * @Description 字段类型参数校验
 * @Author Jim
 * @Date 2022/4/23 16:12
 **/
public class FieldCheck implements CheckStrategy {

    @Override
    public <T1, T2> CheckResult<T1> check(T1 data, ParamData<T2> param) {
        JSONObject checkData = JSON.parseObject(data.toString());

        //没有校验规则时直接返回true
        if (param == null) {
            return new CheckResult<>(true, data);
        }
        // 参数名称
        String paramName = param.getParamName();
        // 参数数据类型
        FieldInfo paramType = (FieldInfo) param.getValue();
        //如果data中不存在字段
        if (!checkData.containsKey(paramName)) {
            //当前参数没有传递数据 TODO 后期判断参数是否必填
            //存在默认值或者为非必填项返回true
            if (paramType.getIsRequire() != 1 || paramType.getDefaultValue() != null) {
                return new CheckResult<>(true, data);
            }
            return new CheckResult<>(false, data);
        }
        Object dataValue = checkData.get(paramName);
        //校验数据类型
        if (!judgeDataTypeIsValid(paramType.getFieldType(), dataValue)) {
            return new CheckResult<>(false, data);
        }
        //校验数据长度
        if (paramType.getLength() < dataValue.toString().length()) {
            return new CheckResult<>(false, data);
        }
        return new CheckResult<>(true, data);
    }

    /**
     * @return
     * @throws
     * @description TODO 判断字段数据类型是否合法
     * @author Zrj
     * @date 2022/4/25 10:41
     **/
    private boolean judgeDataTypeIsValid(String dataType, Object value) {
        if (dataType.equals("Integer")) {
            return value instanceof Integer;
        } else if (dataType.equals("Character")) {
            return value instanceof String;
        } else if (dataType.equals("String")) {
            return value instanceof Character;
        }
        return false;
    }
}
