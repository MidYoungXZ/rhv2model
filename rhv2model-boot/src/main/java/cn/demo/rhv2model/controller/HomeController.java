package cn.demo.rhv2model.controller;

import cn.demo.rhv2model.constant.RhModelConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/home")
    public String home() {
        return "rhv2model OK. version："+ RhModelConstant.VERSION;
    }
}
