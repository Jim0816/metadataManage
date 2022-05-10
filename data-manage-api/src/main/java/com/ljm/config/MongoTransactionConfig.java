package com.ljm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

/**
 * @ClassName MongoTransactionConfig
 * @Description mongoDB事务配置,经过调研事务编号只允许在副本集成员或mongos上，
 * mongoDb单点模式的下不支持事务
 * Transaction numbers are only allowed on a replica set member or mongos
 *
 * @Author zyt
 * @Date 2022/4/18 15:27
 **/
//@Configuration
public class MongoTransactionConfig {
//    @Bean
//    MongoTransactionManager transactionManager(MongoDatabaseFactory factory){
//        return new MongoTransactionManager(factory);
//    }

}
