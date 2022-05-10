package com.ljm.utils;

import com.ljm.bo.mongo.FilterModel;
import com.ljm.bo.mongo.QueryModel;
import com.ljm.bo.mongo.SortModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName MQLParser
 * @Description
 * @Author Jim
 * @Date 2022/3/29 18:39
 **/
public class MQLParser {
    /**
     * @description 添加分页条件
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/5 19:19
     **/
    public static Query addLimit(Query query, QueryModel model) {
        /***************分页*************/
        //设置页大小
        if (model.getPageSize() == null || model.getPageSize() <= 0) {
            model.setPageSize(50);
        }
        //分页
        if (model.getPageNow() != null && model.getPageNow() > 1) {
            query.skip((model.getPageNow() - 1) * model.getPageSize());
        }
        query.limit(model.getPageSize());
        return query;
    }

    /**
     * @description 添加结果排序条件
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/5 19:19
     **/
    public static Query addSorts(Query query, List<SortModel> sortList) {
        /***************排序***************/
        if (sortList != null && sortList.size() > 0) {
            List<Sort.Order> orders = new ArrayList<>();
            //添加多个排序条件
            for (int i = 0; i < sortList.size(); i++) {
                SortModel sortModel = sortList.get(i);
                Sort.Direction direction = Sort.Direction.ASC;
                if (sortModel.getSortWay() < 0) {
                    direction = Sort.Direction.DESC;
                }
                Sort.Order order = new Sort.Order(direction, sortModel.getSortName());
                orders.add(order);
            }
            query.with(Sort.by(orders));
        }
        return query;
    }

    /**
     * @description TODO 添加过滤条件
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/5 19:21
     **/
    public static Query addFilters(Query query, List<FilterModel> filterList) {
        /***********字段条件过滤***********/
        if (filterList != null && filterList.size() > 0) {
            List<Criteria> andList = new ArrayList<>();
            List<Criteria> orList = new ArrayList<>();
            for (FilterModel filter : filterList) {
                Object value = filter.getValue();
                String type = filter.getValueType();
                Criteria criteria;
                Object v = value;

                //处理字段值类型
                switch (type){
                    case "string":
                        v = value.toString();
                        break;
                    case "date":
                        break;
                    case "int":
                        v = Integer.valueOf(value.toString());
                        break;
                    default:
                        break;
                }

                //处理操作类型
                switch (filter.getOper()) {
                    case "=":
                        criteria = Criteria.where(filter.getKey()).is(v);
                        break;
                    case ">":
                        criteria = Criteria.where(filter.getKey()).gt(v);
                        break;
                    case "<":
                        criteria = Criteria.where(filter.getKey()).lt(v);
                        break;
                    case ">=":
                        criteria = Criteria.where(filter.getKey()).gte(v);
                        break;
                    case "<=":
                        criteria = Criteria.where(filter.getKey()).lte(v);
                        break;
                    case "<>":
                        criteria = Criteria.where(filter.getKey()).ne(v);
                        break;
                    case "in":
                        //源数据
                        if (filter.getValue() == null) {
                            continue;
                        }
                        String valueStr = value.toString();
                        String[] values = valueStr.split(",");
                        Object[] para = values;
                        //日期in查询数组
                        Date[] dates = null;
                        //字符串类型
                        if (values != null && values[0].startsWith("\"")) {
                            String[] strs = new String[values.length];
                            for (int i = 0; i < values.length; i++) {
                                valueStr = valueStr.substring(1, valueStr.length() - 1);
                                strs[i] = valueStr;
                            }
                            para = strs;
                        }
                        //日期类型
                        else if (values != null && values[0].startsWith("'")) {
                            dates = new Date[values.length];
                            for (int i = 0; i < values.length; i++) {
                                //Date date = DateUtil.dateToISODate(values[i].substring(1, values[i].length() - 1), "yyyy-MM-dd");
                                //dates[i] = date;
                            }
                            para = dates;
                        }
                        //数字类型
                        else {
                            //logger.info("判断是否为数字类型");
                            Double[] doubles = new Double[values.length];
                            try {
                                for (int i = 0; i < values.length; i++) {
                                    Double dv = Double.parseDouble(values[i]);
                                    doubles[i] = dv;
                                }
                                para = doubles;
                                //logger.info("in 里的为数字类型");
                            } catch (Exception ex) {
                            }
                        }
                        if (para != null && para.length > 0) {
                            //logger.info(para[0].getClass() + "");
                        }
                        //添加in过滤条件
                        criteria = Criteria.where(filter.getKey()).in(para);
                        break;
                    default:
                        continue;
                }
                //或条件
                if (filter.getLogOper().equals("or")) {
                    orList.add(criteria);
                } else {
                    andList.add(criteria);
                }
            }
            //或条件数组,数组上线+1是为了把且条件集合加进去
            Criteria[] orC = new Criteria[1];
            if (orList.size() > 0) {
                orC = new Criteria[orList.size() + 1];
                for (int i = 0; i < orList.size(); i++) {
                    Criteria or = orList.get(i);
                    orC[i] = or;
                }
            }
            //且条件数组
            Criteria[] andC = new Criteria[0];
            if (andList.size() > 0) {
                andC = new Criteria[andList.size()];
                for (int i = 0; i < andList.size(); i++) {
                    Criteria and = andList.get(i);
                    andC[i] = and;
                }
            }
            //把全部且条件放在一起合成一个，再与其他的或条件一起组装
            orC[orC.length - 1] = new Criteria().andOperator(andC);
            query.addCriteria(new Criteria().orOperator(orC));
        }
        return query;
    }

    /**
     * @description TODO 添加返回字段设置
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/5 19:55
     **/
    public static Query addStys(Query query, String resFields) {
        /***********返回字段过滤***********/
        String[] stys = resFields.split(",");
        if (stys == null || stys.length == 0) {
            query = null;
            return query;
        }
        query.fields().exclude("_id");
        if (!(stys.length == 1 && stys[0].equals("*"))) {
            for (String sty : stys) {
                query.fields().include(sty.trim());
            }
        }
        return query;
    }
}
