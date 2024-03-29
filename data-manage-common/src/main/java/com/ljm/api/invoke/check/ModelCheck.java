package com.ljm.api.invoke.check;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ljm.bo.ModelDetail;
import com.ljm.bo.ParamData;
import com.ljm.entity.FieldInfo;
import com.ljm.vo.FieldNodeVO;
import com.ljm.vo.FieldTreeVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ModelCheck
 * @Description 模型类型参数校验
 * @Author Jim
 * @Date 2022/4/23 16:13
 **/
public class ModelCheck implements CheckStrategy{

    private boolean checkResult;
    @Override
    public boolean check(JSONObject data, ParamData param) {
        JSONObject checkData = JSON.parseObject(data.toString());
        ModelDetail modelDetail = (ModelDetail) param.getValue();
        FieldTreeVO fieldTree = modelDetail.getFieldTree();

        if (!checkData.containsKey(param.getParamName())) {
            return false;
        }
        JSONObject dataValue = (JSONObject) checkData.get(param.getParamName());
        List<FieldNodeVO> nodeVOS = getFieldNodeVOFromFieldTreeVO(fieldTree);
        //遍历校验树中所有字段
        for (FieldNodeVO fn : nodeVOS) {
            if (fn.getFieldInfoId() != -1) {
                ParamData<FieldInfo> fieldRule = new ParamData<>();
                fieldRule.setParamName(fn.getFieldInfo().getFieldName());
                fieldRule.setValue(fn.getFieldInfo());
                CheckContext checkContext = new CheckContext(ParamData.PARAM_FIELD_TYPE);
                boolean checkResult = checkContext.executeCheck(dataValue, fieldRule);
            }
            if (!checkResult) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return
     * @throws
     * @description TODO 遍历FieldNodeVO
     * @author Zrj
     * @date 2022/4/23 16:05
     **/
    private List<FieldNodeVO> getFieldNodeVOFromFieldTreeVO(FieldTreeVO fieldTree) {
        List<FieldNodeVO> resList = new ArrayList<>();
        resList.add(fieldTree.getNode());
        if (fieldTree.getChildren() != null) {
            for (FieldTreeVO fieldTreeVO : fieldTree.getChildren()) {
                resList.addAll(getFieldNodeVOFromFieldTreeVO(fieldTreeVO));
            }
        }
        return resList;
    }
}
