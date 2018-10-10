package com.jk.service;

import com.jk.model.Menu;
import com.jk.model.User;

import java.util.List;

public interface UserService {

    User getUserByName(String userName);

    List<User> getUser();

    List<Menu> getUserMenu(String userId);

    List<String> getRoleByUserId(String id);

    List<Menu> getUserMenuAll(String id);
}
