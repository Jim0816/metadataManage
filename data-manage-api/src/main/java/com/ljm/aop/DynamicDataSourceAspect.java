package com.ljm.aop;

import com.ljm.annotation.MyDataSource;
import com.ljm.source.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName DynamicDataSourceAspect
 * @Description 动态数据源面向切面编程AOP 用于在业务层切换数据源的
 * 注解@Order作用是定义Spring IOC容器中Bean的执行顺序的优先级
 * 注解@Aspect:作用是把当前类标识为一个切面供容器读取
 * @Author Jim
 * @Date 2022/2/24 11:04
 **/
@Aspect
@Component
@Slf4j
@Order(-100) 
public class DynamicDataSourceAspect {
    /**
     * @description 为本切面类设置一个触发条件(切点)
     * @return
     * @exception
     * @author Jim
     * @date 2022/2/24 14:34
     **/
    @Pointcut("execution(* com.ljm.service..*(..))")
    public void pointCut(){}

    /**
     * @description 前置通知,在方法执行前执行本通知方法
     * @return
     * @exception
     * @author 的
     * @date 2022/2/24 11:17
     **/
    @Before("pointCut() && @annotation(myDataSource)")
    public void doBefore(MyDataSource myDataSource){
        log.info("选择数据源---"+ myDataSource.value().getValue());
        DynamicDataSourceContextHolder.setDataSource(myDataSource.value().getValue());
    }

    /**
     * @description 后置通知,在方法执行后执行本通知方法
     * @return
     * @exception
     * @author Jim
     * @date 2022/2/24 11:18
     **/
    @After("pointCut()")
    public void doAfter(){
        DynamicDataSourceContextHolder.clear();
    }
}
