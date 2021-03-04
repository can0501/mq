package com.example.mq.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("a")
public class asdasdasd {
    @PostMapping("upload")
    public Boolean upload(HttpServletRequest request) {
        MultipartRequest request1 = (MultipartRequest) request;
        List<MultipartFile> file = request1.getFiles("file");
        int i = 1;
        System.out.println(file.size());
        return true;
    }

    @PostMapping("upload1")
    public Boolean upload1(MultipartFile file) {

         int i = 1;
        System.out.println(file==null);
        return file==null;
    }

    @GetMapping("aspect")
    public Boolean aspect(String s) {

        System.out.println("aspect");
//        if (1 == 1) {
//            throw new RuntimeException("asdasdas");
//        }
        return true;
    }

    @Value("${qaq}")
    String qaq;
    @GetMapping("qaq")
    public String qaq() {

        System.out.println("aspect");
//        if (1 == 1) {
//            throw new RuntimeException("asdasdas");
//        }
        return qaq;
    }
}
