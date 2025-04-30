package com.healthturing.healthturing_server.mapper;

import com.healthturing.healthturing_server.dto.UserDTO;
import com.healthturing.healthturing_server.models.User;

/**
 * Funciona junto con UserDto para enviar la información necesaria del usuario al front, todavía no completado
 */
public class UserMapper {

    public static UserDTO toUserDto(User user) {
        return new UserDTO(
            user.getName(),
            user.getEmail()
        );
    }
    
}
