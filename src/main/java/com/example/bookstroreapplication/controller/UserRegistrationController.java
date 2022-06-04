package com.example.bookstroreapplication.controller;


import com.example.bookstroreapplication.dto.ResponseDTO;
import com.example.bookstroreapplication.dto.UserDTO;
import com.example.bookstroreapplication.dto.UserLoginDTO;
import com.example.bookstroreapplication.model.UserRegistration;
import com.example.bookstroreapplication.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 *  1) @RestController :-
 *           @RestController is used for making restful web services with the help of the @RestController annotation.
 *           This annotation is used at the class level and allows the class to handle the requests made by the client
 * 2) @RequestMapping :-
 *           @RequestMapping used to map web requests onto specific handler classes and/or handler methods.
 *           RequestMapping can be applied to the controller class as well as methods
 *
 * - Created controller so that we can perform rest api calls
 */

@RestController
@RequestMapping("/user")
/**
 * create a class name as UserRegistrationController
 */
public class UserRegistrationController {
   /**
     * 3) @AutoMapping :-
     *          @Autowiring feature of spring framework enables you to inject the object dependency implicitly.
     *          It internally uses setter or constructor injection.
     *
     * - Autowired  IUserService interface, so we can inject its dependency here
     **/
    @Autowired
    IUserService userRegistrationService;
    /**
     * - Ability to save book details to repository
     * @apiNote- accepts the book data in JSON format and stores it in DB
     * @param userDTO - book data
     * @return :- responseDTO
     */
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
        String newUser = userRegistrationService.addUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("User Registered Successfully", newUser);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }
    /**
     * ability to user login
     * @param userLoginDTO - email and password
     * @return - login successfully msg show
     */
    //Login
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> userLogin(@RequestBody UserLoginDTO userLoginDTO) {
        return new ResponseEntity<ResponseDTO>(userRegistrationService.loginUser(userLoginDTO), HttpStatus.OK);
    }
    /**
     * - Ability to get all book' data by findAll() method
     * @return :- showing all data
     */
    @GetMapping(value = "/getAll")
    public ResponseEntity<String> getAllUser() {
        List<UserRegistration> listOfUsers = userRegistrationService.getAllUsers();
        ResponseDTO dto = new ResponseDTO("User retrieved successfully (:", listOfUsers);
        return new ResponseEntity(dto, HttpStatus.OK);
    }
    /**
     *get all data by using token
     * @param token:-i/p token in the form of string
     * @return fields with Http status
     */
    @GetMapping(value = "/getAll/{token}")
    public ResponseEntity<ResponseDTO> getAllUserDataByToken(@PathVariable String token) {
        List<UserRegistration> listOfUser = userRegistrationService.getAllUserDataByToken(token);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:", listOfUser);
        return new ResponseEntity(dto, HttpStatus.OK);
    }
    /**
     * get data for particular emailId
     * Ability to get a record by emailId
     */
    @GetMapping("/getByEmailId/{emailId}")
    public ResponseEntity<ResponseDTO> getUserByEmailId(@PathVariable("emailId") String emailId) {
        return new ResponseEntity<ResponseDTO>(new
                ResponseDTO("Get User Data by Email",
                userRegistrationService.getUserByEmailId(emailId)), HttpStatus.OK);
    }
    /**
     * get user by token
     * Ability to get a record by token
     */
    //Get user by user id
    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestParam String email, @RequestParam String password) {
        String resp = userRegistrationService.forgotPassword(email, password);
        return new ResponseEntity(resp, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRecordById(@PathVariable Integer id, @Valid @RequestBody UserDTO userDTO) {
        UserRegistration entity = userRegistrationService.updateRecordById(id, userDTO);
        ResponseDTO dto = new ResponseDTO("User Record updated successfully", entity);
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }
}