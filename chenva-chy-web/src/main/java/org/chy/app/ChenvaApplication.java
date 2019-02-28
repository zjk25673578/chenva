package org.chy.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan({"com.dao", "org.chy.platform.*.dao"})
@ComponentScan({
        "com.controller",
        "com.service",
        "org.chy.platform.*.controller",
        "org.chy.platform.*.service"
})
public class ChenvaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChenvaApplication.class, args);
    }

}

