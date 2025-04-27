package com.healthturing.healthturing_server.mapper;

import com.healthturing.healthturing_server.dto.UserDTO;
import com.healthturing.healthturing_server.models.User;

public class UserMapper {

    public static UserDTO toUserDto(User user) {
        return new UserDTO(
            user.getName(),
            user.getEmail()
        );
    }
    
}
