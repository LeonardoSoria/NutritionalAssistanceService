package com.core.webapi.mapper;

import com.core.domain.models.user.User;
import com.core.webapi.dto.response.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserResponse mapToUserResponse(User user) {
        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .id(user.getId().toString())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .createdAt(user.getCreatedAt().toLocalDate())
                .build();
    }

    public static List<UserResponse> mapToUserList(List<User> users) {
        return users.stream()
                .map(UserMapper::mapToUserResponse)
                .collect(Collectors.toList());
    }
}
