package com.springkafkaproject.user_service.service;


import com.springkafkaproject.user_service.repositories.UserRepository;
import com.springkafkaproject.user_service.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public Pair<Boolean, String> addUser(String email, String name){
        User user = null;
        if(getUserByEmail(email)==null){
            try{
                user = new User(name, email);
                userRepository.save(user);
                logger.info("user created successfully with " + user.getEmail());
                return Pair.of(true, "user created successfully");
            } catch (Exception e){
                logger.error("Error while creating user"+ e.getMessage());
                return Pair.of(false, "Error while creating user");
            }
        }
        else {
            logger.info("User already exists with this email " + email);
            return Pair.of(false, "User already exists with this email");
        }
    }

    public Pair<Boolean, String> removeUser(Long id){
        try{
            User user1 = getUser(id);
            userRepository.delete(user1);
            return Pair.of(true, "user deleted successfully");
        } catch (Exception e){
            logger.error("error while deleting user "+e.getMessage());
            return Pair.of(false, "Error while deleting user");
        }
    }

    public Pair<Boolean, String> updateUser(Long id, String email, String name){
        try {
            User user1 = getUser(id);
            user1.setEmail(email);
            user1.setName(name);
            userRepository.save(user1);
            return Pair.of(true, "user details updated successfully");
        }catch (Exception e){
            logger.error("Error while updating user details "+e.getMessage());
            return Pair.of(false, "error while updating user details");
        }
    }

    public User getUser(Long id){
        try{
            Optional<User> user = userRepository.findById(id);
            return user.orElse(null);
        }catch (Exception e){
            logger.error("error while fetching user "+e.getMessage());
            return null;
        }
    }

    public User getUserByEmail(String email){
        try{
            User user = userRepository.findByEmail(email);
            return user;
        }catch (Exception e){
            logger.error("error while fetching user "+e.getMessage());
            return null;
        }
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
