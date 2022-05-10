package com.ljm.api.define;

import com.ljm.entity.Api;

/**
 * @ClassName APICheck
 * @Description TODO 待实现
 * @Author Jim
 * @Date 2022/5/9 9:53
 **/
public class APICheck extends AbstractAPICheck{
    @Override
    boolean checkFilter(Api api) {
        return true;
    }

    @Override
    boolean checkSort(Api api) {
        return true;
    }

    @Override
    boolean checkPage(Api api) {
        return true;
    }
}
