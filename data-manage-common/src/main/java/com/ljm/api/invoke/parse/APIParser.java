package com.ljm.api.invoke.parse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljm.bo.ConditionNode;
import com.ljm.bo.ModelDetail;
import com.ljm.bo.ParamData;
import com.ljm.entity.Api;
import com.ljm.entity.SysUser;
import com.ljm.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @ClassName APIParser
 * @Description
 * @Author Jim
 * @Date 2022/4/23 15:39
 **/
@Component
public class APIParser extends AbstractAPIParser {
    @Autowired
    private ApiService apiService;

    @Override
    Api parseApi(String apiName) {
        return apiService.getOne(new QueryWrapper<Api>().eq("name", apiName));
    }

    @Override
    List<SysUser> parseAccessUsers(Api api) {
        return null;
    }

    @Override
    List<ModelDetail> parseAllModel(Api api) {
        return null;
    }

    @Override
    Map<String, ParamData> parseParams(Api api) {
        return null;
    }

    @Override
    ConditionNode parseCondition(Api api) {
        return null;
    }
}
