package com.ljm.designMode.create.abstractFactory;

import com.ljm.designMode.create.abstractFactory.color.Blue;
import com.ljm.designMode.create.abstractFactory.color.Color;
import com.ljm.designMode.create.abstractFactory.color.Red;
import com.ljm.designMode.create.abstractFactory.shape.Circle;
import com.ljm.designMode.create.abstractFactory.shape.Rectangle;
import com.ljm.designMode.create.abstractFactory.shape.Shape;

/**
 * @ClassName ColorFactory
 * @Description
 * @Author Jim
 * @Date 2022/4/1 20:46
 **/
public class ColorFactory extends AbstractFactory{
    @Override
    public Color getColorFactory(String type) {
        if ("red".equals(type)){
            return new Red();
        }else{
            return new Blue();
        }
    }

    @Override
    public Shape getShapeFactory(String type) {
        return null;
    }
}
