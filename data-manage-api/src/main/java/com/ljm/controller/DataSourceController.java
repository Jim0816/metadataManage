package com.ljm.controller;


import com.ljm.entity.DataSource;
import com.ljm.service.DataSourceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("dataSource")
public class DataSourceController {
    private DataSourceService dataSourceService;

    /**
     * @description TODO 123
     * @return 
     * @exception 
     * @author Jim
     * @date 2022/2/22 17:40
     **/
    @GetMapping(value = "/test")
    public String test(){
        dataSourceService.list(new DataSource());
        return "";
    }
}
