package com.lawless.restoperation.controller;

import com.lawless.restoperation.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/")
public class RestOperationController {

    @Autowired
    OperationService service;

    @GetMapping(path = "/", produces = "application/json")
    public String index() {
        return "Welcome to Lawless Software";
    }

    @GetMapping(path = "add", produces = "application/json")
    public Number addValues (@RequestParam Number x, @RequestParam Number y) {
        return service.add(x, y);
    }

    @GetMapping(path = "multiply", produces = "application/json")
    public Number multiplyValues (@RequestParam Number x, @RequestParam Number y) {
        return service.mul(x, y);
    }
}
