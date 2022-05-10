package com.ljm.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName CodeTest
 * @Description 测试闲散代码
 * @Author Jim
 * @Date 2022/3/21 13:40
 **/

@RunWith(SpringRunner.class)
public class CodeTest {

    @Test
    public void test() {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        System.out.println(s);
    }


}
