package com.jwtauth.manageuser.utils;

import com.jwtauth.manageuser.dto.UserDetailsDto;
import com.jwtauth.manageuser.entity.UserDetails;
import com.jwtauth.manageuser.entity.UserLoginDetails;

public class Converter {
    public static UserDetails getUserDetails(UserDetailsDto userDetailsDto, UserLoginDetails userLoginDetails) {
        UserDetails userDetails=new UserDetails();
        userDetails.setUserLoginDetails(userLoginDetails);
        userDetails.setFirstname(userDetailsDto.getFirstname());
        userDetails.setLastname(userDetailsDto.getLastname());
        userDetails.setAge(userDetailsDto.getAge());
        userDetails.setAddress(userDetailsDto.getAddress());
        userDetails.setCountry(userDetailsDto.getCountry());
        userDetails.setCountryCodes(userDetailsDto.getCountryCodes());
        userDetails.setDateOfBirth(userDetailsDto.getDateOfBirth());
        return userDetails;
    }

}
