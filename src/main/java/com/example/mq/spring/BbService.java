package com.example.mq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 钟金灿
 * @since 2022/9/9
 */
@Component
public class BbService {
    @Autowired
    private AaService aaService;
}
