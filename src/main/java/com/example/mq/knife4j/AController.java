package com.example.mq.knife4j;

import com.example.mq.kafka.KafkaProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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


    @Autowired
    private KafkaProvider kafkaProvider;

    @RequestMapping(value = "sendMsg", method = RequestMethod.GET)
    public String sendMsg() {

        for (int i = 0; i < 600; i++) {

            kafkaProvider.sendMessage(1L, "1", LocalDateTime.now());
        }
        return "sad";
    }
}
