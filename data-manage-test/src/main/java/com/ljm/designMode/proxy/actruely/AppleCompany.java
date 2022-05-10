package com.ljm.designMode.proxy.actruely;

import com.ljm.designMode.proxy.inte.PhoneCompany;

/**
 * @ClassName AppleCompany
 * @Description
 * @Author Jim
 * @Date 2022/4/25 17:30
 **/
public class AppleCompany implements PhoneCompany {
    @Override
    public void salePhone() {
        System.out.println("=== 正在购买Apple手机 ===");
    }
}
