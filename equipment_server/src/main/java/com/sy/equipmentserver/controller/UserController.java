package com.sy.equipmentserver.controller;

import com.sy.equipmentserver.pojo.TbUser;
import com.sy.equipmentserver.service.UserService;
import com.sy.equipmentserver.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/regist")
    public ResultVo regist(@RequestBody TbUser user){
        System.out.println(user);
        return userService.regist(user);
    }

    @PostMapping("/login")
    public ResultVo login(@RequestBody TbUser user){
        System.out.println(user);
        return userService.login(user);
    }

    @PostMapping("/show")
    public ResultVo show(@RequestBody TbUser user){
        return userService.show(user);
    }

    @PostMapping("/change")
    public ResultVo change(@RequestBody TbUser user){
        return userService.change(user);
    }
}
