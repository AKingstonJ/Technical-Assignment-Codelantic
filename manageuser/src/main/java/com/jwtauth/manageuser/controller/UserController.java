package com.jwtauth.manageuser.controller;

import com.jwtauth.manageuser.config.JwtTokenUtil;
import com.jwtauth.manageuser.config.UserInfoService;
import com.jwtauth.manageuser.dto.JwtRequest;
import com.jwtauth.manageuser.dto.JwtResponse;
import com.jwtauth.manageuser.dto.ResUserDetailsDto;
import com.jwtauth.manageuser.dto.UserDetailsDto;
import com.jwtauth.manageuser.entity.UserDetails;
import com.jwtauth.manageuser.entity.UserLoginDetails;
import com.jwtauth.manageuser.service.userService;
import com.jwtauth.manageuser.utils.Converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private userService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public ResponseEntity<?> welcome() {
        return ResponseEntity.ok("Welcome to Codelantic!");
    }

    @GetMapping("/alluser")
    public ResponseEntity<?> getAllUser() {
        List<UserDetails> userDetails=userService.getUsers();
        List<ResUserDetailsDto> resUserDetailsDtos=new ArrayList();
        for(UserDetails userDetail:userDetails) {
        	ResUserDetailsDto resUserDetailsDto=new ResUserDetailsDto();
        	resUserDetailsDto.setId(userDetail.getId());
        	resUserDetailsDto.setFirstname(userDetail.getFirstname());
        	resUserDetailsDto.setLastname(userDetail.getLastname());
        	resUserDetailsDto.setUsername(userDetail.getUserLoginDetails().getUsername());
        	resUserDetailsDto.setDateOfBirth(userDetail.getDateOfBirth());
        	resUserDetailsDto.setAddress(userDetail.getAddress());
        	resUserDetailsDto.setAge(userDetail.getAge());
        	resUserDetailsDto.setCountry(userDetail.getCountry());
        	resUserDetailsDtos.add(resUserDetailsDto);
        }
        return ResponseEntity.ok(resUserDetailsDtos);
    }

    @PostMapping("/singup")
    public ResponseEntity singupUser(@RequestBody UserDetailsDto userDetailsDto) throws Exception {
        if(userService.isExistUserByUserName(userDetailsDto.getUsername())){
            return new ResponseEntity<>("Username already exists..!",HttpStatus.BAD_REQUEST);
        }
        UserLoginDetails userLoginDetails=new UserLoginDetails();
        userLoginDetails.setUsername(userDetailsDto.getUsername());
        userLoginDetails.setPassword(passwordEncoder.encode(userDetailsDto.getPassword()) );
        userLoginDetails=userService.saveLoginUser(userLoginDetails);

        UserDetails userDetails = Converter.getUserDetails(userDetailsDto, userLoginDetails);
        userDetails=userService.save(userDetails);
        return new ResponseEntity<>(userDetails,HttpStatus.OK);
    }



    @PostMapping("/generatetoken")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody JwtRequest jwtRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            final org.springframework.security.core.userdetails.UserDetails userDetails = userInfoService
                    .loadUserByUsername(jwtRequest.getUsername());
            final String token = jwtService.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
