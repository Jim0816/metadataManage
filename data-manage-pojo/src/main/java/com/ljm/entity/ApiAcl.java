package com.ljm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName ApiAcl
 * @Description
 * @Author Jim
 * @Date 2022/3/29 18:02
 **/
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_api_acl")
@ApiModel(value="Api权限对象", description="")
public class ApiAcl {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键自增")
    private Long id;

    @ApiModelProperty(value = "API ID")
    private Long apiId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;
}
