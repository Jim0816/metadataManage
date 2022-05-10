package com.ljm.designMode.create.abstractFactory.color;

/**
 * @ClassName Blue
 * @Description
 * @Author Jim
 * @Date 2022/4/1 20:38
 **/
public class Blue implements Color{
    @Override
    public void fill() {
        System.out.println("染蓝色");
    }
}
