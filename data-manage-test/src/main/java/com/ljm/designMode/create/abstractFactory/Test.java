package com.ljm.designMode.create.abstractFactory;

import com.ljm.designMode.create.abstractFactory.shape.Shape;

/**
 * @ClassName Test
 * @Description
 * @Author Jim
 * @Date 2022/4/1 20:47
 **/
public class Test {
    public static void main(String[] args) {
        AbstractFactory shapeFactory = FactoryProducer.getFactory("shape");
        Shape shape1 = shapeFactory.getShapeFactory("circle");
        shape1.draw();
    }
}
