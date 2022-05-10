package com.ljm.designMode.create.abstractFactory;

/**
 * @ClassName FactoryProducer
 * @Description
 * @Author Jim
 * @Date 2022/4/1 20:49
 **/
public class FactoryProducer {
    public static AbstractFactory getFactory(String factoryType){
        if (factoryType.equalsIgnoreCase("shape")){
            return new ShapeFactory();
        }else{
            return new ColorFactory();
        }
    }
}
