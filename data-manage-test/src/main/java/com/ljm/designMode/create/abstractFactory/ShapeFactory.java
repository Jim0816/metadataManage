package com.ljm.designMode.create.abstractFactory;

import com.ljm.designMode.create.abstractFactory.color.Color;
import com.ljm.designMode.create.abstractFactory.color.Red;
import com.ljm.designMode.create.abstractFactory.shape.Circle;
import com.ljm.designMode.create.abstractFactory.shape.Rectangle;
import com.ljm.designMode.create.abstractFactory.shape.Shape;

/**
 * @ClassName ShapeFactory
 * @Description
 * @Author Jim
 * @Date 2022/4/1 20:42
 **/
public class ShapeFactory extends AbstractFactory{
    @Override
    public Color getColorFactory(String type) {
        return null;
    }

    @Override
    public Shape getShapeFactory(String type) {
        if ("circle".equals(type)){
            return new Circle();
        }else{
            return new Rectangle();
        }
    }
}
