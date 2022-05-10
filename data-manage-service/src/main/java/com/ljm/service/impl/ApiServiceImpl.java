package com.ljm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljm.bo.ConditionNode;
import com.ljm.entity.Api;
import com.ljm.mapper.ApiMapper;
import com.ljm.service.ApiService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper, Api> implements ApiService {

    private ApiMapper apiMapper;





    /**
     * @description TODO 改掉硬编码
     * @return
     * @exception
     * @author hwl
     * @date 2022/4/14 20:33
     **/
    @Override
    public List<Api> listApi(Api api) {
        List<Api> apis = apiMapper.selectList(new QueryWrapper<Api>().eq("name",api.getName()));
        return apis;
    }

}
