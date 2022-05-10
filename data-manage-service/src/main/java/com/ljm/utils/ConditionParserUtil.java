package com.ljm.utils;

import com.ljm.bo.ConditionNode;
import com.ljm.bo.IndexRange;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @ClassName APIParser
 * @Description API解析工具类
 * @Author Jim
 * @Date 2022/3/15 15:58
 **/
@Slf4j
public class ConditionParserUtil {

    public static void main(String[] args) {
        String a = "user.dept_id = dept.id or user.age > 20 and user.name = \"ljm\"";
        ConditionNode conditionNode = new ConditionNode(ConditionNode.ROOT_NODE,a);
        ConditionParserUtil.buildCondition(conditionNode,a);
        System.out.println(conditionNode);
    }

    /**
     * @description TODO 将sql条件解析为条件树 暂不支持括号
     * @return
     * @exception
     * @author Jim
     * @date 2022/5/9 15:01
     **/
    public static void buildCondition(ConditionNode parent, String whereSql){
        if (whereSql.contains("and")){
            // 当前子句包含and
            // 拆分子句
            ConditionNode andNode = new ConditionNode(ConditionNode.AND_NODE, whereSql);
            parent.addChildren(andNode);
            String[] subs = splitSqlByLogic(whereSql, "and");
            buildCondition(andNode, subs[0]);
            buildCondition(andNode, subs[1]);
        }else if (whereSql.contains("or")){
            // 当前子句包含or
            ConditionNode orNode = new ConditionNode(ConditionNode.OR_NODE, whereSql);
            parent.addChildren(orNode);
            String[] subs = splitSqlByLogic(whereSql, "or");
            buildCondition(orNode, subs[0]);
            buildCondition(orNode, subs[1]);
        }else{
            // 当前子句不包含逻辑运算符
            ConditionNode valueNode = new ConditionNode(ConditionNode.VALUE_NODE, whereSql);
            parent.addChildren(valueNode);
        }
    }

    /**
     * @description TODO 按照逻辑运算符拆分语句
     * @return String[0] 左边子句  String[1] 右边子句
     * @exception
     * @author Jim
     * @date 2022/5/9 14:51
     **/
    private static String[] splitSqlByLogic(String whereSql, String logic){
        List<Integer> locations = getSubStringLocation(whereSql, logic);
        if (locations.size() > 0){
            // 获取分割点 （只取出现的第一个logic位置）
            int cutPoint = locations.get(0);
            String[] subs = new String[2];
            subs[0] = whereSql.substring(0, cutPoint).trim();
            subs[1] = whereSql.substring(cutPoint + logic.length(), whereSql.length()).trim();
            return subs;
        }
        return null;
    }

    /**
     * @description 从字符串中获取括号范围
     * @return
     * @exception
     * @author Jim
     * @date 2022/3/14 16:12
     **/
    private static List<IndexRange> getIndexRangesFromStr(String whereSql){
        List<IndexRange> indexRanges = new ArrayList<>();
        for (int i = 0 ; i < whereSql.length() ; i++){
            if (whereSql.charAt(i) == '('){
                int end = i;
                boolean isFind = false;
                // 寻找最近的 )
                for (int j = i + 1 ; j < whereSql.length() ; j++){
                    if(whereSql.charAt(j) == ')'){
                        end = j;
                        // 已经匹配完本括号
                        isFind = true;
                        break;
                    }
                }

                if (isFind){
                    indexRanges.add(new IndexRange(i, end));
                    i = end + 1;
                }else{
                    // 括号匹配失败
                    System.out.println("括号匹配失败.............");
                    return null;
                }
            }
        }
        return indexRanges;
    }

    /**
     * @description 寻找所有字串出现的位置 （废弃）
     * @return
     * @exception
     * @author Jim
     * @date 2022/3/14 15:48
     **/
    private static List<Integer> getSubStringLocation(String parent, String child){
        List<Integer> res = new ArrayList<>();
        int index = 0;
        while ((index = parent.indexOf(child, index)) != -1) {
            res.add(index);
            index = index + child.length();
        }
        return res;
    }

    /**
     * @description TODO 通过指定逻辑运算符号，拆分where条件  （废弃）
     * @return
     * @exception
     * @author Jim
     * @date 2022/3/15 14:11
     **/
    private static Map<String, List<String>> splitConditionByIndex(String whereSql, List<Integer> logicOpeIndexes){
        Map<String, List<String>> result = new LinkedHashMap<>();
        List<String> andConditionList = new ArrayList<>();
        List<String> orConditionList = new ArrayList<>();
        // 首次出现的运算符
        String firstLogic = "";
        for (int i = 0 ; i < logicOpeIndexes.size() ; i++){
            int index = logicOpeIndexes.get(i);
            if (i == 0){
                String leftCondition = whereSql.substring(0, index);
                if (whereSql.charAt(index) == 'o'){
                    firstLogic = "or";
                    orConditionList.add(formatConditionSql(leftCondition));
                }else {
                    firstLogic = "and";
                    andConditionList.add(formatConditionSql(leftCondition));
                }
            }

            int wordGap = whereSql.charAt(index) == 'o' ? 2 : 3;
            int end = whereSql.length();
            if (i < logicOpeIndexes.size() - 1){
                end = logicOpeIndexes.get(i + 1);
            }
            String rightCondition = whereSql.substring(index + wordGap, end);
            if (whereSql.charAt(index) == 'o'){
                orConditionList.add(formatConditionSql(rightCondition));
            }else {
                andConditionList.add(formatConditionSql(rightCondition));
            }
        }


        if ("and".equals(firstLogic)){
            if (andConditionList.size() > 0){
                result.put("and", andConditionList);
            }
            if (orConditionList.size() > 0){
                result.put("or", orConditionList);
            }
        }else{
            if (orConditionList.size() > 0){
                result.put("or", orConditionList);
            }
            if (andConditionList.size() > 0){
                result.put("and", andConditionList);
            }
        }
        return result;
    }

    /**
     * @description TODO 格式化语句 去除首位空格、括号 （废弃）
     * @return
     * @exception
     * @author Jim
     * @date 2022/3/15 14:58
     **/
    private static String formatConditionSql(String condition){
        if (condition.length() == 0){
            return "";
        }
        // 格式化
        condition = condition.trim();
        if (condition.charAt(0) == '('){
            condition = condition.substring(1);
        }
        if (condition.charAt(condition.length() - 1) == ')'){
            condition = condition.substring(0, condition.length() - 1);
        }
        return condition;
    }
    /**
     * @description TODO 构建查询条件 （废弃）
     * @return
     * @exception
     * @author Jim
     * @date 2022/3/14 17:38
     **/
    private static ConditionNode buildConditionNode(ConditionNode parent, String whereSql){
        // 1.先记录所有and or 所在的位置
        List<Integer> indexes = getSubStringLocation(whereSql, "and");
        List<Integer> orIndexes = getSubStringLocation(whereSql, "or");
        indexes.addAll(orIndexes); // addALL 将otIndex的元素添加到indexes的尾部。
        Collections.sort(indexes);

        // 2.获取括号所在位置范围34
        List<IndexRange> indexRanges = getIndexRangesFromStr(whereSql);

        // 3.提取本层级的逻辑运算符位置
        List<Integer> logicOpes = new ArrayList<>();
        for (Integer index : indexes){
            boolean inRange = false;
            for (IndexRange indexRange : indexRanges) {
                if (index >= indexRange.getStart() && index <= indexRange.getEnd()){
                    inRange = true;
                    break;
                }
            }

            if (!inRange){
                //当前逻辑运算符不是出现在括号中
                logicOpes.add(index);
            }
        }

        // 4.按照逻辑运算符拆分当前
        Map<String, List<String>> conditions = splitConditionByIndex(whereSql, logicOpes);
        if (conditions.size() > 0){
            if (parent != null){
                for (String key : conditions.keySet()){
                    if ("and".equals(key)){
                        ConditionNode andNode = new ConditionNode(ConditionNode.AND_NODE, "");
                        parent.addChildren(andNode);
                        List<String> andConditionList = conditions.get("and");
                        for (String condition : andConditionList){
                            // 最小单元，无法拆分
                            if (!condition.contains("and") && !condition.contains("or")){
                                ConditionNode conditionNode = new ConditionNode(ConditionNode.VALUE_NODE, condition);
                                andNode.addChildren(conditionNode);
                            }else{
                                // 还包含逻辑运算符号
                                buildConditionNode(andNode, condition);
                            }
                        }
                    }else if("or".equals(key)){
                        ConditionNode orNode = new ConditionNode(ConditionNode.OR_NODE, "");
                        parent.addChildren(orNode);
                        List<String> orConditionList = conditions.get("or");
                        for (String condition : orConditionList){
                            // 最小单元，无法拆分
                            if (!condition.contains("and") && !condition.contains("or")){
                                ConditionNode conditionNode = new ConditionNode(ConditionNode.VALUE_NODE, condition);
                                orNode.addChildren(conditionNode);
                            }else{
                                // 还包含逻辑运算符号
                                buildConditionNode(orNode, condition);
                            }
                        }
                    }
                }
            }
        }

        return parent;
    }




}
