package com.enigma.tokonyadiaa.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @GetMapping(value = "/hello")
    public String hello(){
        return "<h1>Hello Oyyyy<h1>";
    }
    @GetMapping(value = "/hobbies")
    public String[] getHobbies(){
        return new String[] {"<h2>membaca" , "maen game<h2>"};
    }

    @GetMapping("/request-param{key}")
    public  String getRequestParam(@RequestParam String key) {
        return key;
    }//http://localhost:8080/request-param?key=contoh

    @GetMapping(value = "/{id}")
    public String getDataById(@PathVariable String id){
        return "Data" + id;
    }//http://localhost:8080/123
}