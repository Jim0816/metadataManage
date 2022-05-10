package com.ljm.bo;

import com.ljm.entity.Model;
import com.ljm.vo.FieldTreeVO;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ModelVO
 * @Description 模型VO对象：整个模型数据视图
 * @Author Jim
 * @Date 2022/3/29 16:03
 **/
@Data
public class ModelDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    private Model model;
    private FieldTreeVO fieldTree;
}
