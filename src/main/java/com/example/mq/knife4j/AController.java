package com.example.mq.knife4j;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author 钟金灿
 * @since 2022/3/30
 */
@Api
@RestController
public class AController {
    @PostMapping("post")
    @ApiOperation("post")
    public String post(@RequestBody A a) {
        return "asdsd";
    }

    @GetMapping("get")
    @ApiOperation("get")
    public String get(A a) {
        return "asdsd";
    }
}
