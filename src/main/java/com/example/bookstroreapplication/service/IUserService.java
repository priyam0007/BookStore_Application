package com.example.bookstroreapplication.service;

import com.example.bookstroreapplication.dto.ResponseDTO;
import com.example.bookstroreapplication.dto.UserDTO;
import com.example.bookstroreapplication.dto.UserLoginDTO;
import com.example.bookstroreapplication.model.UserRegistration;

import java.util.List;

public interface IUserService {
    String addUser(UserDTO userDTO);

    List<UserRegistration> getAllUsers();

    ResponseDTO loginUser(UserLoginDTO userLoginDTO);

    Object getUserByToken(String token);
   // String getToken(String email);

    String forgotPassword(String email, String password);

    Object getUserByEmailId(String emailId);

    UserRegistration updateUser(String email, UserDTO userDTO);

    List<UserRegistration> getAllUserDataByToken(String token);

    UserRegistration updateRecordById(Integer id, UserDTO userDTO);

}


