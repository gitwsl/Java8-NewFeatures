package com.example.websocket.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/return")
public class Index {
    @RequestMapping("/a")
    public String geta(){
        System.out.println("aaaaaaaaa");
        return "/index.html";
    }
    @RequestMapping("/b")
    public String getb(){
        System.out.println("bbbb");
        return "/hello";
    }

    @RequestMapping("/c")
    public String getc(){
        System.out.println("ccccc");
        return "/login";
    }
}
