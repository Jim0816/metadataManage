package com.ljm.designMode.create.abstractFactory.shape;

/**
 * @ClassName Rectangle
 * @Description
 * @Author Jim
 * @Date 2022/4/1 20:39
 **/
public class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("矩形");
    }
}
