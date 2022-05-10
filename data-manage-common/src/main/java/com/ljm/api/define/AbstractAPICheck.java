package com.ljm.api.define;

import com.ljm.entity.Api;

/**
 * @ClassName APICheck 接口校验模板类 （接口定义过程）
 * @Description
 * @Author Jim
 * @Date 2022/5/9 15:05
 **/
public abstract class AbstractAPICheck {
    /**
     * @description  校验条件
     * @return
     * @exception
     * @author Jim
     * @date 2022/5/9 9:47
     **/
    abstract boolean checkFilter(Api api);

    /**
     * @description  校验排序
     * @return
     * @exception
     * @author Jim
     * @date 2022/5/9 9:47
     **/
    abstract boolean checkSort(Api api);

    /**
     * @description  校验分页
     * @return
     * @exception
     * @author Jim
     * @date 2022/5/9 9:47
     **/
    abstract boolean checkPage(Api api);

    /**
     * @description 校验API对象
     * @return
     * @exception
     * @author Jim
     * @date 2022/5/9 9:52
     **/
    public final boolean checkApi(Api api){
        return checkFilter(api) && checkSort(api) && checkPage(api);
    }
}
