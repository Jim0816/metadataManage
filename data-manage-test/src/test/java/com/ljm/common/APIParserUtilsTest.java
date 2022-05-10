package com.ljm.common;

import com.ljm.bo.ConditionNode;
import com.ljm.utils.ConditionParserUtil;
import org.junit.Test;

/**
 * @ClassName APIParser
 * @Description
 * @Author Jim
 * @Date 2022/4/21 14:06
 **/
public class APIParserUtilsTest {

    @Test
    public void testBuildCondition(){
        //
        String where_1 = "user.dept_id = dept.id or user.age > 20 and user.name = \"ljm\"";
        String where_2 = "user.dept_id = dept.id and user.age > 20";
        ConditionNode conditionNode = new ConditionNode(ConditionNode.ROOT_NODE,"");
        ConditionNode conditionNodeTree = ConditionParserUtil.buildConditionNode(conditionNode,where_1);
        System.out.println(conditionNodeTree);
    }
}
