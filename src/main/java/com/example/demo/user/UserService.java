package com.example.demo.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDto userDto){
        User user = new User(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), 
        userDto.getPhoneNumber(), userDto.isAdmin());
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int userID){
        return userRepository.findById(userID);
    }
}
