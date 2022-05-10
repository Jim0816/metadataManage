package com.ljm.aqs;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ConditionTest
 * @Description
 * @Author Jim
 * @Date 2022/3/21 19:16
 **/
public class ConditionTest {
    private int curNum = 1;
    private int index = 1;
    // 非公平锁
    private final Lock lock = new ReentrantLock();
    // 四个Condition
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final Condition condition3 = lock.newCondition();

    public void printNum(){
        if (curNum > 100){
            Thread.currentThread().interrupt();
            return;
        }
        for (int i = 0 ; i < 2 ; i++){
            System.out.println(Thread.currentThread().getName() + " : " + (curNum++));
        }
    }

    public void process1(){
        lock.lock();
        try {
            if (index != 1){
                condition1.await();
            }
            printNum();
            index = 2;
            condition2.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public void process2(){
        lock.lock();
        try {
            if (index != 2){
                condition2.await();
            }
            printNum();
            index = 3;
            condition3.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public void process3(){
        lock.lock();
        try {
            if (index != 3){
                condition3.await();
            }
            printNum();
            index = 1;
            condition1.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        final ConditionTest t = new ConditionTest();
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                t.process1();
            }

        }).start();

        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                t.process2();
            }

        }).start();

        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                t.process3();
            }

        }).start();
    }
}
