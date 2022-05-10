package com.ljm.thread.pool;

/**
 * @ClassName MyTask
 * @Description
 * @Author Jim
 * @Date 2022/4/18 17:31
 **/
public class MyTask implements Runnable{
    private static int id = 10;
    private String userName;

    public MyTask(String userName) {
        this.userName = userName;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(userName + "正在使用" + threadName + "参加秒杀任务...");
        synchronized (MyTask.class){
            if (id > 0){
                System.out.println(userName + "正在使用" + threadName + "秒杀商品:" + id--);
            }else{
                System.out.println(userName + "正在使用" + threadName + "秒杀商品失败");
            }
        }
    }
}
