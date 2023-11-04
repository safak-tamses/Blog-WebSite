package com.example.springsocial.service;

import com.example.springsocial.exception.UserNotFound;
import com.example.springsocial.model.User;
import com.example.springsocial.payload.CurrentUserResponse;
import com.example.springsocial.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFound(""));
    }
    public CurrentUserResponse showCurrentUser(Long id){
        User u = userRepository.findById(id).orElseThrow(() -> new UserNotFound(""));
        return new CurrentUserResponse(
                u.getName(),
                u.getEmail(),
                u.getImageUrl()
        );

    }


}
