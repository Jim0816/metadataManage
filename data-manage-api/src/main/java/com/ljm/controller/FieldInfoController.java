package com.ljm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljm.entity.FieldInfo;
import com.ljm.service.FieldInfoService;
import com.ljm.utils.QueryUtils;
import com.ljm.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@AllArgsConstructor
@RestController
@RequestMapping("/field")
public class FieldInfoController extends BaseController{

    private FieldInfoService fieldInfoService;

    /**
     * @description TODO 添加字段
     * @return
     * @exception
     * @author zrj
     * @date 2022/4/1 16:06
     **/
    @PostMapping(value = "/save")
    public Result save(@RequestBody FieldInfo fieldInfo){
        return fieldInfoService.add(fieldInfo) > 0 ? Result.ok() : Result.failed();
    }

    /**
     * @description TODO 修改字段
     * @return
     * @exception
     * @author zrj
     * @date 2022/4/1 16:07
     **/
    @PostMapping(value = "/update")
    public Result update(@RequestBody FieldInfo fieldInfo){
        return fieldInfoService.update(fieldInfo) > 0 ? Result.ok() : Result.failed();
    }

    /**
     * @description TODO 删除字段
     * @return
     * @exception
     * @author zrj
     * @date 2022/4/1 16:07
     **/
    @PostMapping(value = "/remove")
    public Result remove(@RequestBody List<Long> ids){
        return fieldInfoService.remove(ids)>0 ? Result.ok() : Result.failed();
    }

    /**
     * @description TODO 无条件查询所有字段
     * @return
     * @exception
     * @author zjm
     * @date 2022/4/1 16:08
     **/
    @GetMapping(value = "/list")
    public Result list(){
        return fieldInfoService.list().size() > 0 ? Result.ok(fieldInfoService.list()) : Result.failed();
    }


    /**
     * @description 删除单个字段 TODO 废弃
     * @return
     * @exception
     * @author zrj
     * @date 2022/4/7 18:41
     **/
//    @GetMapping(value = "/remove/{id}")
//    public Result remove(@PathVariable(name = "id") String id) {
//        return fieldInfoService.remove(id) > 0 ? Result.ok() : Result.failed();
//    }

    /**
     * @description TODO 条件查询单条字段
     * @return
     * @exception
     * @author zjm
     * @date 2022/4/1 16:29
     **/
    @PostMapping(value = "/get")
    public Result get(@RequestBody FieldInfo fieldInfo){
        return Result.ok(fieldInfoService.get(fieldInfo));
    }


    /**
     * @description TODO 条件分页查询  入参需要改成你封装好的对象
     * @return
     * @exception
     * @author zyt
     * @date 2022/4/1 16:29
     **/
    @PostMapping(value = "/page")
    public Result page(@RequestBody FieldInfo field){
        //List<FieldInfo> fieldList = fieldInfoService.getFieldList(fieldInfoDto);
        //return Result.ok(fieldList);
        // 提取对象的数据,构建条件器
        Map<String, Object> map = QueryUtils.getFieldAndValue(field, FieldInfo.class, "serialVersionUID");
        QueryWrapper query = new QueryWrapper<FieldInfo>();
        for (String key : map.keySet()){
            Object value = map.get(key);
            query.eq(value != null && StringUtils.isNotBlank(value.toString()), key, value);
        }

        Page<FieldInfo> pageData = fieldInfoService.page(getPage(), query);
        return Result.ok(pageData);
    }
}
