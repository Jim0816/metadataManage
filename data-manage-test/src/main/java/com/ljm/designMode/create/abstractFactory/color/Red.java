package com.ljm.designMode.create.abstractFactory.color;

/**
 * @ClassName Red
 * @Description
 * @Author Jim
 * @Date 2022/4/1 20:37
 **/
public class Red implements Color{
    @Override
    public void fill() {
        System.out.println("染红色");
    }
}
