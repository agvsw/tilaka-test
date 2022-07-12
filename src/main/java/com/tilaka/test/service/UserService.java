package com.tilaka.test.service;


import com.tilaka.test.dto.UserDTO;
import com.tilaka.test.entity.User;
import com.tilaka.test.exception.DataExistException;
import com.tilaka.test.exception.UserDataNotFoundException;

public interface UserService {
    User register(UserDTO userDTO, String role);
    void deleteUSer(Long id);
    User getUserById(Long id) throws UserDataNotFoundException;
    User saveUser(UserDTO userDTO) throws UserDataNotFoundException, DataExistException;


}
