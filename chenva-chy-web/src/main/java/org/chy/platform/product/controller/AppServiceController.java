package org.chy.platform.product.controller;

import org.chy.platform.product.entity.AppService;
import org.chy.platform.product.service.AppServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/service")
public class AppServiceController {

    @Value("${test.name}")
    private String test;

    @Autowired
    protected AppServiceService appServiceService;

    @GetMapping("/index")
    public String index() {
        System.out.println("========================查询 - appService===========================");
        System.out.println(test);
        AppService product = appServiceService.getById("9fbfaa3178454451be653ae42631ffd3");
        error();
        return product.toString();
    }

    public void error() {
        throw new RuntimeException("sfghdrgsgdrgffgrng");
    }
}
