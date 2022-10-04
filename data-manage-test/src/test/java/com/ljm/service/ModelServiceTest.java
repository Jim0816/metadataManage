package com.ljm.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

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
    public void testCreateModelByProperties() throws IOException {
        // 1.读取配置文件，转换为JOSNObject
        File file = new File("C:\\Users\\acer\\Desktop\\元数据\\matadata\\metadata\\metadataManage\\data-manage-api\\src\\main\\resources\\modelTemplate\\proposal.json");
        String content= FileUtils.readFileToString(file,"UTF-8");
        JSONObject jsonObject=JSONObject.parseObject(content);

        modelService.createModelByProperties(jsonObject);
        // 2.JOSNObject提取字段项、字段树、模型
        int count = modelService.count();
        //modelService.createModelByProperties();
        log.info(String.valueOf(count));
    }
}