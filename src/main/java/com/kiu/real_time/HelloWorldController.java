package com.kiu.real_time;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String test() {
        return "이건 스프링부트, 리액트 연동 테스트 (삭제 요망!!)";
    }

}