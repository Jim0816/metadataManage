package com.ljm.bo;

import com.ljm.entity.Api;
import com.ljm.entity.SysUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ApiResult
 * @Description API解析结果对象
 * @Author Jim
 * @Date 2022/2/25 12:06
 **/
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class ApiResult implements Serializable {
    private static final long serialVersionUID = 1L;
    // 解析接口对象 调用getApi
    private Api api;

    // 接口操作涉及的数据模型实体(详细信息)   调用getModelDetail
    private List<ModelDetail> models;

    // 接口查询数据条件filter 调用APIParser.buildConditionNode
    private ConditionNode conditionNode;

    // 可使用该接口的用户列表 调用SysUserService.getUserList
    private List<SysUser> accessor;

    // 接口参数列表
    private Map<String,ParamData> params;
}
