package com.ljm.designMode.proxy.actruely;

import com.ljm.designMode.proxy.inte.PhoneCompany;

/**
 * @ClassName SamsungCompany
 * @Description
 * @Author Jim
 * @Date 2022/4/25 17:33
 **/
public class SamsungCompany implements PhoneCompany {
    @Override
    public void salePhone() {
        System.out.println("=== 正在购买SAMSUNG手机 ===");
    }
}
