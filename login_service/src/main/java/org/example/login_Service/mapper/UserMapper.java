package org.example.login_Service.mapper;

import org.springframework.stereotype.Service;

import org.example.login_Service.dto.UserRequest;
import org.example.login_Service.dto.UserResponse;
import org.example.login_Service.entity.User;


@Service
public class UserMapper {
    public User toEntity(UserRequest request) {
        return User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .build();
    }
    public UserResponse toCustomerResponse(User user) {
        return new UserResponse(user.getFirstName(), user.getLastName(), user.getEmail());
    }
}