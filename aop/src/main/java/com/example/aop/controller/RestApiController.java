package com.example.aop.controller;


import com.example.aop.annotation.Timer;
import com.example.aop.dto.User;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name) {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        //TODO

//        System.out.println("get method");
//        System.out.println("get method : " + id);
//        System.out.println("get method : " + name);

        stopWatch.stop();
        System.out.println("total time : " + stopWatch.getTotalTimeSeconds());
        return id + " " + name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user) {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        stopWatch.stop();
        System.out.println("total time : " + stopWatch.getTotalTimeSeconds());
        //System.out.println("post method : " + user);
        return user;
    }

    @Timer //직접 만든 @annotation
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        //db logic
        Thread.sleep(1000*2);

        stopWatch.stop();
        System.out.println("total time : " + stopWatch.getTotalTimeSeconds());
    }
}
