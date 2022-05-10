package com.ljm.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljm.entity.FieldInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName QueryUtils
 * @Description
 * @Author Jim
 * @Date 2022/4/11 10:00
 **/
public class QueryUtils {

    /**
     * @description TODO 提取对象的表字段名称，对应值  （待优化）
     * 可能存在问题：父类提取不到
     * @param o 实例对象
     * @param c 类对象
     * @param excludeAttributeName 不提取的字段名称  eg "serialVersionUID,xx,xx,xx“
     * @return
     * @exception
     * @author Jim
     * @date 2022/4/11 10:42
     **/
    public static Map<String, Object> getFieldAndValue(Object o, Class<?> c, String excludeAttributeName) {
        Map<String, Object> res = new LinkedHashMap<>();
        String result = c.getSimpleName( ) + ":";
        // 获取父类。推断是否为实体类
//        if ( c.getSuperclass( ).getName( ).indexOf( "entity" ) >= 0 ) {
//            getQueryWrapper( o , (Class<T>) c.getSuperclass(), queryWrapper);
//        }
        // 获取类中的全部定义字段
        Field[] fields = c.getDeclaredFields( );
        // 循环遍历字段，获取字段相应的属性值
        for ( Field field : fields ) {
            // 假设不为空。设置可见性，然后返回
            field.setAccessible( true );

            try {
                // 设置字段可见，就可以用get方法获取属性值。
                String name = field.getName();
                String tableFieldName = StringUtils.humpToLine(name);
                Object value = field.get(o);
                boolean isStatic = Modifier.isStatic(field.getModifiers());
                boolean isFinal = Modifier.isFinal(field.getModifiers());

                if (!isFinal && !isStatic){
                    if (excludeAttributeName == null || !excludeAttributeName.contains(name)){
                        res.put(tableFieldName, value);
                    }

                }
            }
            catch ( Exception e ) {
                // System.out.println("error--------"+methodName+".Reason is:"+e.getMessage());
            }
        }
        return res;
    }
}
