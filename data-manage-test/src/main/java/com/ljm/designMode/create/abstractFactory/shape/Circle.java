package com.ljm.designMode.create.abstractFactory.shape;

/**
 * @ClassName Circle
 * @Description
 * @Author Jim
 * @Date 2022/4/1 20:39
 **/
public class Circle implements Shape{
    @Override
    public void draw() {
        System.out.println("圆");
    }
}
