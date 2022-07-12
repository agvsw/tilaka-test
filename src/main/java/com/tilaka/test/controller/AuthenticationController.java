package com.tilaka.test.controller;

import com.tilaka.test.dto.CommonResponse;
import com.tilaka.test.dto.UserDTO;
import com.tilaka.test.entity.User;
import com.tilaka.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {
    @Autowired
    private UserService userService;


    @PostMapping(path = "/register")
    public CommonResponse<User> registerAdmin(@RequestBody UserDTO userDto) {
        CommonResponse<User> response = new CommonResponse<>();
        response.setData(userService.register(userDto, "ADMIN"));
        return response;
    }


    @PostMapping(path = "/logout")
    public CommonResponse<User> logout(HttpServletRequest request) {
        CommonResponse<User> response = new CommonResponse<>();
        new SecurityContextLogoutHandler().logout(request, null, null);
        return response;
    }
}
