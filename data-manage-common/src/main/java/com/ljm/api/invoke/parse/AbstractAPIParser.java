package com.ljm.api.invoke.parse;


import com.ljm.bo.ApiResult;
import com.ljm.bo.ConditionNode;
import com.ljm.bo.ModelDetail;
import com.ljm.bo.ParamData;
import com.ljm.entity.Api;
import com.ljm.entity.SysUser;
import com.ljm.enums.ResCodeEnum;
import com.ljm.except.CustomException;

import java.util.List;
import java.util.Map;

/**
 * @ClassName APIParser 接口解析模板类 （调用接口过程）
 * @Description
 * @Author Jim
 * @Date 2022/4/23 15:05
 **/
public abstract class AbstractAPIParser {
    /**
     * @description 获取接口实体对象
     * @param id 接口ID
     * @return Api对象
     * @exception
     * @author Jim
     * @date 2022/4/23 15:12
     **/
    abstract Api parseApi(Long id);

    /**
     * @description 获取接口中具备访问权限的用户列表
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/23 15:17
     **/
    abstract List<SysUser> parseAccessUsers(Api api);

    /**
     * @description 获取接口中涉及的所有模型的详细信息
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/23 15:17
     **/
    abstract List<ModelDetail> parseAllModel(Api api);

    /**
     * @description 获取接口中参数信息
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/23 15:17
     **/
    abstract Map<String, ParamData> parseParams(Api api);

    /**
     * @description 获取接口操作条件信息
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/23 15:17
     **/
    abstract ConditionNode parseCondition(Api api);

    /**
     * @description 定义接口解析模板方法
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/23 15:29
     **/
    public final ApiResult parse(Long id){
        ApiResult apiResult = new ApiResult();
        Api api = null;
        if ((api = parseApi(id)) == null)
            throw new CustomException(ResCodeEnum.API_IS_NOT_EXIST);
        apiResult.setApi(api);
        apiResult.setAccessor(parseAccessUsers(api));
        apiResult.setModels(parseAllModel(api));
        apiResult.setParams(parseParams(api));
        apiResult.setConditionNode(parseCondition(api));
        return apiResult;
    }

}
