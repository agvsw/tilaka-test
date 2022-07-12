package com.tilaka.test.controller;


import com.tilaka.test.dto.CommonResponse;
import com.tilaka.test.dto.UserDTO;
import com.tilaka.test.entity.User;
import com.tilaka.test.exception.DataExistException;
import com.tilaka.test.exception.UserDataNotFoundException;
import com.tilaka.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@EnableWebSecurity
@EnableAuthorizationServer
@EnableResourceServer
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping(path = "/user/{id}")
    public CommonResponse<User> getById(@PathVariable(value = "id") Long id) throws UserDataNotFoundException {
        CommonResponse<User> response = new CommonResponse<>();
        response.setData(userService.getUserById(id));
        return response;
    }

    @PutMapping(path = "/user")
    public CommonResponse<User> updateById(@RequestBody UserDTO userDTO) throws UserDataNotFoundException, DataExistException {
        CommonResponse<User> response = new CommonResponse<>();
        response.setData(userService.saveUser(userDTO));
        return response;
    }

    @DeleteMapping(path = "/user/{id}")
    public CommonResponse<User> deleteById(@PathVariable(value = "id") Long id) {
        CommonResponse<User> response = new CommonResponse<>();
        userService.deleteUSer(id);
        return response;
    }
}
