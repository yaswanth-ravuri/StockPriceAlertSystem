package com.springkafkaproject.user_service.controller;

import com.springkafkaproject.user_service.dto.requestDTO.UserRequestDTO;
import com.springkafkaproject.user_service.dto.responseDTO.UserResponseDTO;
import com.springkafkaproject.user_service.entities.User;
import com.springkafkaproject.user_service.mappings.UserMapper;
import com.springkafkaproject.user_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping(path = "/getuser")
    public ResponseEntity<UserResponseDTO> getUser(@RequestParam("id") Long id){
        User user = userService.getUser(id);
        if(user!=null){
            return new ResponseEntity<>(userMapper.userToUserResponseDTO(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getAllUsers")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<User> users = userService.getUsers();
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        for(User user: users){
            userResponseDTOS.add(userMapper.userToUserResponseDTO(user));
        }
        return new ResponseEntity<>(userResponseDTOS, HttpStatus.OK);
    }

    @PostMapping(path = "/createuser")
    public ResponseEntity<String> createUser(@RequestBody UserRequestDTO userRequest){
        Pair<Boolean, String> responsePair = userService.addUser(userRequest.getEmail(), userRequest.getName());
        if(responsePair.getFirst())
            return new ResponseEntity<>(responsePair.getSecond(), HttpStatus.CREATED);
        else
            return new ResponseEntity<>(responsePair.getSecond(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody UserRequestDTO userRequest){
        Pair<Boolean, String> responsepair = userService.updateUser(userRequest.getId(), userRequest.getEmail(), userRequest.getName());
        if(responsepair.getFirst())
            return new ResponseEntity<>(responsepair.getSecond(), HttpStatus.OK);
        return new ResponseEntity<>(responsepair.getSecond(), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/removeUser")
    public ResponseEntity<String> deleteUser(@RequestParam("id") Long id){
        return new ResponseEntity<>(userService.removeUser(id).getSecond(), HttpStatus.OK);
    }

}
