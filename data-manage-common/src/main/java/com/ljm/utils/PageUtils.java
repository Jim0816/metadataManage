package com.ljm.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @ClassName PageTrasform
 * @Description
 * @Author Jim
 * @Date 2022/4/22 16:05
 **/
public class PageUtils {

    public static <T1,T2> Page<T2> changeRecord(Page<T1> page, List<T2> record){
        Page<T2> newPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        newPage.setRecords(record);
        return newPage;
    }
}
