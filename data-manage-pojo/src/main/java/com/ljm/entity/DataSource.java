package com.ljm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ljm
 * @since 2022-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_data_source")
@ApiModel(value="DataSource对象", description="")
public class DataSource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "数据源类型")
    @TableField("db_type")
    private String dbType;

    @ApiModelProperty(value = "数据源地址")
    private String host;

    @ApiModelProperty(value = "端口")
    private String port;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;


}
