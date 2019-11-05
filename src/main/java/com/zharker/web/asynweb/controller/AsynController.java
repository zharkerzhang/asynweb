package com.zharker.web.asynweb.controller;

import com.zharker.web.asynweb.service.CountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

@Log4j2
@RestController
public class AsynController {

    @Value("${asyn.task.timeout}")
    private long timeout;

    @Autowired
    private CountService countService;

    @GetMapping("/sync")
    public String sync() {
        return countService.count("sync");
    }

    @GetMapping("/callable")
    public Callable<String> callable(){
        return ()->countService.count("callable");
    }

    @GetMapping("/async")
    public WebAsyncTask<String> async(){
        WebAsyncTask<String> task = new WebAsyncTask<>(timeout,()->countService.count("async"));
        task.onCompletion(()->log.debug("call task request execute completed"));
        task.onTimeout(()->{
            log.error("call async request timeout, will throw an exception");
            throw new TimeoutException("call timeout");
        });
        return task;
    }
}