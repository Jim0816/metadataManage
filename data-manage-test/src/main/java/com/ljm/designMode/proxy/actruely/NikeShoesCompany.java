package com.ljm.designMode.proxy.actruely;

import com.ljm.designMode.proxy.inte.ShoesCompany;

public class NikeShoesCompany implements ShoesCompany {
    @Override
    public void saleShoes() {
        System.out.println("=== 正在购买Nike的鞋子 ===");
    }
}
