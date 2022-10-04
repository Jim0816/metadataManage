package com.ljm.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName ModelServiceTest
 * @Description
 * @Author Jim
 * @Date 2022/10/4 下午2:48
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelServiceTest {

    @Autowired
    private ModelService modelService;

    @Test
    public void testCreateModelByProperties() {
        // 1.读取配置文件，转换为JOSNObject

        // 2.JOSNObject提取字段项、字段树、模型
        int count = modelService.count();
        log.info(String.valueOf(count));
    }
}