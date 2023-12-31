package com.neublog.neublogwebapp.service;

import com.neublog.neublogwebapp.dto.RegistrationDto;
import com.neublog.neublogwebapp.entity.User;

public interface UserService {

    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);
}
