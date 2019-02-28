package com.controller.user;

import com.pojo.user.AppUser;
import com.service.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appuser")
public class AppUserController {

    @Autowired
    protected AppUserService appUserService;

    @GetMapping("/index")
    public String index() {
        AppUser user = appUserService.getById("1");
        return user.toString();
    }
}
