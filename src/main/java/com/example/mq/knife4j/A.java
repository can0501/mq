package com.example.mq.knife4j;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 钟金灿
 * @since 2022/3/30
 */
@Data
@ApiModel
public class A {

//    @ApiModelProperty(value = "asdasdsada")
    private AEnum anEnum;

//    @ApiModelProperty(value = "数字" )
    private Integer intttt;

//    @ApiModelProperty(value = "bbbbbb")
    private Benum benum;

    private List<AEnum> enumList;

    @ApiModelProperty(value = "enumList2")
    private List<AEnum> enumList2;

}
