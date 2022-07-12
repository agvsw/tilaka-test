package com.tilaka.test.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tilaka.test.dto.UserDTO;
import com.tilaka.test.entity.User;
import com.tilaka.test.exception.DataExistException;
import com.tilaka.test.exception.UserDataNotFoundException;
import com.tilaka.test.repository.RoleRepository;
import com.tilaka.test.repository.UserRepository;
import com.tilaka.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User register(UserDTO userDTO, String role) {
        User user = new ObjectMapper().convertValue(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(Collections.singletonList(roleRepository.findByRoleNameContaining(role)));
        Optional<User> optUser = userRepository.findByUserNameOrEmail(userDTO.getUserName(), userDTO.getEmail());
        if (optUser.isEmpty()) {
            return userRepository.save(user);
        }
        throw new RuntimeException("User already exist");
    }

    @Override
    public void deleteUSer(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Long id) throws UserDataNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new UserDataNotFoundException(44, "data not found");
        }
        return user.get();
    }

    @Override
    public User saveUser(UserDTO userDTO) throws UserDataNotFoundException, DataExistException {
        Optional<User> userOptional = userRepository.findById(userDTO.getId());
        if (userOptional.isEmpty()){
            throw new UserDataNotFoundException(44, "data not found");
        }

        User user = userOptional.get();
        if (userDTO.getPassword() != null && !userDTO.getPassword().equals("")){
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        user.setEmail(userDTO.getEmail());
        user.setMobile(userDTO.getMobile());
        user.setUserName(userDTO.getUserName());

        return userRepository.save(user);
    }

}
