package com.healthturing.healthturing_server.mapper;

import com.healthturing.healthturing_server.dto.DoctorDTO;
import com.healthturing.healthturing_server.models.User;

public class DoctorMapper {

    public static DoctorDTO toDto(User user) {
        return new DoctorDTO(user.getId(), user.getName());
    }

}
