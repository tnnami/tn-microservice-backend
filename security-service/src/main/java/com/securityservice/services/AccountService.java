package com.securityservice.services;

import com.securityservice.entities.AppRole;
import com.securityservice.entities.User;

import java.util.List;

public interface AccountService {
    User addNewUser(User user);
    AppRole addNewRole (AppRole role);
    void addRoleToUser(String email, String role);
    User loadUserByEmail(String email);
    User loadUserByeUserName(String username);

}
