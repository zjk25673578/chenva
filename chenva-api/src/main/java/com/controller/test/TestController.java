package com.controller.test;

import com.util.MyDateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/demo")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("page/demo");
        String currentDateTime = MyDateUtil.getDateStr();
        modelAndView.addObject("currentDateTime", currentDateTime);
        return modelAndView;
    }
}
