package com.ljm.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_api")
@ApiModel(value="Api对象", description="")
public class Api implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "接口名称，唯一")
    private String name;

    @ApiModelProperty(value = "接口描述")
    private String remark;

    @ApiModelProperty(value = "接口请求类型 post get")
    @TableField("http_type")
    private String httpType;

    @ApiModelProperty(value = "接口数据操作类型 put post delete get")
    @TableField("op_type")
    private String opType;

    @ApiModelProperty(value = "接口地址")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "操作的集合名称，针对增删改有意义，查询操作为空")
    @TableField("model")
    private String model;

    @ApiModelProperty(value = "接口涉及到的模型name  逗号分割")
    @TableField("model_names")
    private String modelNames;

    @ApiModelProperty(value = "接口参数格式")
    @TableField("params")
    private String params;

    @ApiModelProperty(value = "查询条件")
    @TableField("filter")
    private String filter;

    @ApiModelProperty(value = "分页信息")
    @TableField("page")
    private String page;

    @ApiModelProperty(value = "查询结果排序")
    @TableField("sort")
    private String sort;

    @ApiModelProperty(value = "查询结果排序")
    @TableField("return_data")
    private String returnData;

    @ApiModelProperty(value = "接口状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "创建者用户ID")
    @TableField("create_user")
    private Long createUser;

    @ApiModelProperty(value = "接口授权用户ID列表，逗号分割")
    @TableField("access_user")
    private String accessUser;

    public Api(String str, String url){
        // TODO 暂时不用枚举，只有这一个地方会写这些字段名称
        JSONObject json = JSONObject.parseObject(str);
        this.name = json.getString("name");
        this.remark = json.getString("remark");
        this.httpType = json.getString("httpType");
        this.opType = json.getString("opType");
        this.model = json.getString("model");
        this.modelNames = json.getString("modelNames");
        this.params = json.getString("params");
        this.filter = json.getString("filter");
        this.page = json.getString("page");
        this.sort = json.getString("sort");
        this.returnData = json.getString("returnData");
        this.createUser = Long.valueOf(json.getString("createUser"));
        this.accessUser = json.getString("accessUser");
        this.status = Integer.valueOf(json.getString("status"));
        // 替换url
        this.url = url.replace("{model}", this.model)
                .replace("{opType}", this.opType)
                .replace("{name}", this.name);
    }

    /**
     * @description 获取相对路径 即url端口后路径
     * @return
     * @exception
     * @author Jim
     * @date 2022/10/2 下午3:52
     **/
    public String getApiPath(){
        return "/api/" + this.model + "/" + this.opType + "/" + this.name;
    }
}
