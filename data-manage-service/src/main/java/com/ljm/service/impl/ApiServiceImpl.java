package com.ljm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljm.bo.ConditionNode;
import com.ljm.entity.Api;
import com.ljm.enums.ResCodeEnum;
import com.ljm.except.CustomException;
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

    @Override
    public List<Api> listApi() {
        return apiMapper.selectList(new QueryWrapper<Api>().eq("status",1));
    }

    @Override
    public boolean save(Api api) {
        // 添加前先校验api是否合法
        if (!checkApi(api)){
            throw new CustomException(ResCodeEnum.API_IS_ILLEGAL);
        }

        // 接口校验通过，接口入库
        return baseMapper.insert(api) > 0 ? true : false;
    }

    /**
     * @description TODO 校验api
     * @return
     * @exception
     * @author Jim
     * @date 2022/5/16 20:09
     **/
    private boolean checkApi(Api api){
        return true;
    }

}
