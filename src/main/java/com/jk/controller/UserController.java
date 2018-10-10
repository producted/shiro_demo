package com.jk.controller;

import com.jk.model.Menu;
import com.jk.model.User;
import com.jk.service.UserService;
import com.jk.util.UserUtil;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("user/toUserList")
    public String toUserList(){
        return "userList";
    }

    @RequestMapping("user/getUserMenu")
    @ResponseBody
    public List<Menu> getUserMenu(){
        String userId = UserUtil.getUserId();
        return userService.getUserMenu(userId);
    }

    @RequestMapping("user/getUser")
    @ResponseBody
    @RequiresPermissions("user:list")//shiro授权基于aop实现
    public List<User> getUser(){
        List<User> userList = new ArrayList<>();
        try {
            userList = userService.getUser();
        }catch(AuthorizationException e){

        }
        return userList;
    }
}
