package com.ljm.thread.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPoolTest
 * @Description
 * @Author Jim
 * @Date 2022/4/18 18:42
 **/
public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3,
                5,
                1,
                TimeUnit.MINUTES, new LinkedBlockingQueue<>(2));

        for (int i = 1 ; i <= 10 ; i++){
            MyTask myTask = new MyTask("客户-" + i);
            pool.execute(myTask);
        }

        pool.shutdown();
    }
}
