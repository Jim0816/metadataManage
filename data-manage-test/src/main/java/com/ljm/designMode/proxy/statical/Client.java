package com.ljm.designMode.proxy.statical;

import com.ljm.designMode.proxy.actruely.AppleCompany;
import com.ljm.designMode.proxy.actruely.NikeShoesCompany;

/**
 * @ClassName Client
 * @Description
 * @Author Jim
 * @Date 2022/4/25 17:11
 **/
public class Client {
    public static void main(String[] args) {
        NikeShoesCompany nikeShoesCompany = new NikeShoesCompany();
        AppleCompany appleCompany = new AppleCompany();
        Agent agent = new Agent(nikeShoesCompany, appleCompany);
        agent.saleShoes();
        agent.salePhone();
    }
}
