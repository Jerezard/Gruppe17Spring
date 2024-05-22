package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.User;
import com.example.demo.Exception.DuplicateUserException;
import com.example.demo.Exception.UserAlreadyExists;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDto userDto){
        Optional<User> existingUser = userRepository.findById(userDto.getUserID());
        if(existingUser.isPresent()){
            throw new DuplicateUserException("A user with the id " + userDto.getUserID() + " already exists.");
        }
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new UserAlreadyExists("A user with this email already exists");
        }
        if(userRepository.existsByPhoneNumber(userDto.getPhoneNumber())){
            throw new UserAlreadyExists("A user with this phone number already exists");
        }

        User user = convertToEntity(userDto);
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int userID){
        return userRepository.findById(userID);
    }

    private User convertToEntity(UserDto userDto){
        User user = new User(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), 
        userDto.getPhoneNumber(), userDto.getPassword() ,userDto.isAdmin());
        return user;
    }
}
