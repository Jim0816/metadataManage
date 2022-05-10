package com.ljm.designMode.proxy.statical;

import com.ljm.designMode.proxy.inte.PhoneCompany;
import com.ljm.designMode.proxy.inte.ShoesCompany;
/**
 * @description 注意: 静态代理类增加一个新业务就要修改代码结构 继承多个业务对象
 * 违背了开闭原则，不利于代码的维护。假设新业务不断出现 -> 需要不断实现业务接口
 * @return
 * @exception
 * @author Jim
 * @date 2022/4/25 17:53
 **/
public class Agent implements ShoesCompany, PhoneCompany {
    // 被代理的真实对象
    private ShoesCompany shoesCompany;
    private PhoneCompany phoneCompany;

    public Agent(ShoesCompany shoesCompany, PhoneCompany phoneCompany){
        this.shoesCompany = shoesCompany;
        this.phoneCompany = phoneCompany;
    }

    private void beforeSale(){
        System.out.println("代购售前服务......");
    }

    private void afterSale(){
        System.out.println("代购售后服务......");
    }

    @Override
    public void saleShoes() {
        beforeSale();
        shoesCompany.saleShoes();
        afterSale();
    }

    @Override
    public void salePhone() {
        beforeSale();
        phoneCompany.salePhone();
        afterSale();
    }
}
