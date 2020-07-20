package com.example.websocket.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/return")
public class Index {
    @RequestMapping("/a")
    public String geta() {
        System.out.println("test aaa~~~");
        return "/index.html";
    }
}
