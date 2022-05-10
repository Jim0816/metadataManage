package com.ljm.designMode.proxy.actruely;

import com.ljm.designMode.proxy.inte.ShoesCompany;

/**
 * @ClassName AdidasShoesCompany
 * @Description
 * @Author Jim
 * @Date 2022/4/25 17:20
 **/
public class AdidasShoesCompany implements ShoesCompany {
    @Override
    public void saleShoes() {
        System.out.println("=== 正在购买Adidas的鞋子 ===");
    }
}
