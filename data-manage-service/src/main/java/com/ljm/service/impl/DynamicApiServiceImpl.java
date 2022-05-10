package com.ljm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljm.bo.*;
import com.ljm.entity.*;
import com.ljm.mapper.ApiMapper;
import com.ljm.mapper.SysUserMapper;
import com.ljm.service.*;
import com.ljm.utils.ConditionParserUtil;
import com.ljm.utils.RedisUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName IDynamicApiServiceImpl
 * @Description
 * @Author Jim
 * @Date 2022/2/25 10:10
 **/
@Slf4j
@AllArgsConstructor
@Service
public class DynamicApiServiceImpl implements DynamicApiService {
    private ApiMapper apiMapper;


    private ModelService modelService;

    private SysUserMapper sysUserMapper;


    private RedisUtil redisUtil;

    private FieldInfoService fieldInfoService;





    /**
     * @description TODO 动态分析api，并将api封装成ApiResult返回
     * @return  封装成ApiResult
     * @exception
     * @author Gcy
     * @date 2022/4/13 16:56
     **/
    @Override
    public ApiResult parseApi(Api api) {
          if (redisUtil.hasKey(api.getName())){
            /*String apiString = (String) redisUtil.get(api.getName());
            ApiResult apiResult = JSONObject.parseObject(apiString,ApiResult.class);
            return JSON.parseObject(apiString,ApiResult.class) ;*/
        }
        QueryWrapper<Api> queryWrapper = new QueryWrapper<>();
        Api dbApi = apiMapper.selectOne(queryWrapper.eq("id", api.getId()));
        if (dbApi == null){
            return null;
        }

        //开始解析Api对象 获取ApiResult
        ApiResult apiResult = new ApiResult();

        // 装入API基本信息
        apiResult.setApi(dbApi);

        // 装入API的可访问用户列表
        apiResult.setAccessor(getApiWithAccessor(dbApi.getAccessUser()));

        // 装入本接口涉及到的所有模型详细信息
        apiResult.setModels(getApiWithModels(dbApi.getModelNames()));


        // 装入所有模型之间的关联信息 TODO 暂时预留，后面考虑
        //apiResult.setRelations(null);
        //ConditionNode conditionNode = new ConditionNode(ConditionNode.ROOT_NODE,dbApi.getFilter());
        //ConditionNode conditionNodeTree = ConditionParserUtil.buildConditionNode(conditionNode,dbApi.getFilter());
        //将conditionNodes插入表tb_condition
        //creatConditionTree(conditionNodeTree,-1);
        // 装入接口查询数据条件
        //apiResult.setConditionNode(conditionNodeTree);
        // 键可以这样 api:user:addUser 存储到redis key(api.getName()) : hash(dbApi.toString(),sysUserList)
        //redisUtil.hset(dbApi.getName(),dbApi.toString(),sysUserList);

        // 键可以这样 apiName:apiResult 存储到redis

        /*redisUtil.set(dbApi.getName(),SerializeUtil.serialize(apiResult));
        System.out.println(redisUtil.get(dbApi.getName()));
        ApiResult apiResult1 = (ApiResult) SerializeUtil.deserialize((byte[]) redisUtil.get(dbApi.getName()));
        System.out.println("==="+apiResult1);*/
        return apiResult;
    }

    /**
     * @description TODO 获取本API解析规则
     * @return List<ParamData>
     * @exception
     * @author Gcy
     * @date 2022/4/21 19:08
     **/
    private List<ParamData> getApiWithParamData(String data) {
        List<ParamData> dataList = new LinkedList<>();

        //将data转为JSON
        JSONObject jsonData = JSON.parseObject(data);

        //遍历JSON获取信息
        for (Object o : jsonData.keySet()){
            ParamData paramData = new ParamData();
            paramData.setParamName(String.valueOf(o));
            JSONObject jsonObject= (JSONObject) jsonData.get(o);
            for (Object t : jsonObject.keySet()){

                if (String.valueOf(t).equals("type")){
                    paramData.setType(String.valueOf(jsonObject.get(t)));
                }
                if (String.valueOf(t).equals("id")){
                    if (paramData.getType() == null){
                        for (Object a : jsonObject.keySet()){
                            if (String.valueOf(a).equals("type")){
                                paramData.setType(String.valueOf(jsonObject.get(a)));
                            }
                        }
                    }
                    if (paramData.getType().equals(ParamData.PARAM_FIELD_TYPE)){
                        paramData.setValue(fieldInfoService.getById((Serializable) jsonObject.get(t)));
                    }else if (paramData.getType().equals(ParamData.PARAM_MODEL_TYPE)){
                        paramData.setValue( modelService.getModelDetail
                                (modelService.getById((Serializable) jsonObject.get(t))));
                    }
                }
            }
            dataList.add(paramData);
        }
        return dataList;
    }

    /**
     * @description TODO 获取拥有本API的用户列表
     * @return List<SysUser>
     * @exception
     * @author Gcy
     * @date 2022/4/21 15:08
     **/
    private List<SysUser> getApiWithAccessor(String accessUserId) {

        accessUserId = StringUtils.strip(accessUserId,"[]");
        String[] strs = accessUserId.split(",");//根据，切分字符串

        //将字符串数组转成int数组，方便后续查询
        int[] accssUserIds = new int[strs.length];

        for(int i = 0;i < strs.length; i++){
            accssUserIds[i] = Integer.parseInt(strs[i]);
        }

        //将int数组转成list，然后通过sysUserMapper.selectBatchIds(ids)查询
        List<Integer> ids = Arrays.stream(accssUserIds).boxed().collect(Collectors.toList());
        List<SysUser> sysUserList = sysUserMapper.selectBatchIds(ids);
        return sysUserList;

    }

    /**
     * 将bean存入Redis中
     */

    @Override
    public boolean checkApiData(JSONObject data, List<ParamData> rules) {
        if (rules == null || rules.size() == 0){
            log.info("接口没有传参要求，不需要校验参数数据");
            return true;
        }

        if (data == null){
                log.info("接口要求传参，调用方没有传入参数，接口使用不合法");
                return false;
        }

        // 开始按照rules去校验参数（即接口中传输的数据）
        for (ParamData paramRule : rules){
            if (ParamData.PARAM_FIELD_TYPE.equals(paramRule.getType())){
                // 字段类型
                if ( !checkField(data, paramRule)){
                    // 校验失败 -> 结束校验
                    return false;
                }
            }else if (ParamData.PARAM_MODEL_TYPE.equals(paramRule.getType())){
                // 实体对象（即模型）类型
                if ( !checkModel(data, paramRule)){
                    // 校验失败 -> 结束校验
                    return false;
                }
            }
        }

        // 数据已经通过校验，也有可能数据已经被预处理（修正）了


        return false;
    }

    /**
     * @description TODO 校验字段类型的参数  校验过程中注意考虑数据修正情况，这种情况是合法的
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/19 18:37
     **/
    private boolean checkField(JSONObject data, ParamData<FieldInfo> fieldRule){
        return false;
    }

    /**
     * @description TODO 校验实体类型的数据（即模型），实际上就是去拿到模型的字段树进行校验
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/19 18:37
     **/
    private boolean checkModel(JSONObject data, ParamData<ModelDetail> modelRule){
        // 拿模型的字段树去校验数据
        return false;
    }


    /**
     * @description TODO 获取本API所涉及的所有模型对象（模型详细信息）
     * @return
     * @exception
     * @author Gcy
     * @date 2022/3/29 19:08
     **/
    private List<ModelDetail> getApiWithModels(String getModelNames){
        List<String> names = new ArrayList<>();
        if (getModelNames.contains(",")){
            String[] splits = getModelNames.split(",");
            for (String name : splits){
                names.add(name);
            }
        }else{
            names.add(getModelNames);
        }

        List<ModelDetail> modelDetailList = new ArrayList<>();
        for (String name : names){
            // TODO 有bug
            Model model = modelService.getOne(new QueryWrapper<Model>().eq("model_name",name));
            ModelDetail modelDetail = modelService.getModelDetail(model);
            if (modelDetail != null){
                modelDetailList.add(modelDetail);
            }
        }
        return modelDetailList;
    }
}
