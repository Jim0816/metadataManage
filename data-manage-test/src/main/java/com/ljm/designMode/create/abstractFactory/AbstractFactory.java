package com.ljm.designMode.create.abstractFactory;

import com.ljm.designMode.create.abstractFactory.color.Color;
import com.ljm.designMode.create.abstractFactory.shape.Shape;

public abstract class AbstractFactory {
    public abstract Color getColorFactory(String type);

    public abstract Shape getShapeFactory(String type);
}
