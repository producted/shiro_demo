package com.jk.service;

import com.jk.mapper.UserMapper;
import com.jk.model.Menu;
import com.jk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }

    @Override
    public List<User> getUser() {
        return userMapper.getUser();
    }

    @Override
    public List<Menu> getUserMenu(String userId) {
        return userMapper.getUserMenu(userId);
    }

    @Override
    public List<String> getRoleByUserId(String id) {
        return userMapper.getRoleByUserId(id);
    }

    @Override
    public List<Menu> getUserMenuAll(String id) {
        return userMapper.getUserMenuAll(id);
    }
}
