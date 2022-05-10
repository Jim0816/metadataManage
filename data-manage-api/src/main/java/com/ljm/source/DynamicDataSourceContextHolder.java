package com.ljm.source;

/**
 * @ClassName DataSourceContextHolder
 * @Description 动态数据源控制器
 * @Author Jim
 * @Date 2022/2/24 10:29
 **/
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new InheritableThreadLocal<>();

    /**
     *  设置数据源
     * @param db
     */
    public static void setDataSource(String db){
        contextHolder.set(db);
    }

    /**
     * 取得当前数据源
     * @return
     */
    public static String getDataSource(){
        return contextHolder.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clear(){
        contextHolder.remove();
    }
}
