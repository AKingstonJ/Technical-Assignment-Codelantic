package com.jwtauth.manageuser.service.impl;

import com.jwtauth.manageuser.entity.UserDetails;
import com.jwtauth.manageuser.entity.UserLoginDetails;
import com.jwtauth.manageuser.repository.UserLoginRepository;
import com.jwtauth.manageuser.repository.UserRepository;
import com.jwtauth.manageuser.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class userServiceImpl implements userService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLoginRepository userLoginRepository;
    @Override
    public UserDetails save(UserDetails userDetails) {
        return userRepository.save(userDetails);
    }

    @Override
    public UserLoginDetails saveLoginUser(UserLoginDetails userLoginDetails) {
        return userLoginRepository.save(userLoginDetails);
    }

    @Override
    public List<UserDetails> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserLoginDetails getUserByUserName(String userName) {
        return null;
    }
    @Override
    public boolean isExistUserByUserName(String userName) {
        return userLoginRepository.existsByUsername(userName);
    }
}
