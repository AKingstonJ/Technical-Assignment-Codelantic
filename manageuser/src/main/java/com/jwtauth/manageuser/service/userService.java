package com.jwtauth.manageuser.service;

import com.jwtauth.manageuser.entity.UserDetails;
import com.jwtauth.manageuser.entity.UserLoginDetails;

import java.util.List;

public interface userService {
    UserDetails save(UserDetails userDetails);
    UserLoginDetails saveLoginUser(UserLoginDetails userLoginDetails);
    List<UserDetails> getUsers();
    UserLoginDetails getUserByUserName(String userName);
    boolean isExistUserByUserName(String userName);
}
