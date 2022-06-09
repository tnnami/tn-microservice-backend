package com.securityservice.services;

import com.securityservice.entities.AppRole;
import com.securityservice.entities.User;
import com.securityservice.repositories.AppRoleRepository;
import com.securityservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AppRoleRepository appRoleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public User addNewUser(User user) {
    	String pwd = user.getPassword();
    	user.setPassword(passwordEncoder.encode(pwd));
        return userRepository.save(user);
    }

    @Override
    public AppRole addNewRole(AppRole role){
       return appRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String role) {
    	User user = userRepository.findByEmail(email);
    	AppRole appRole = appRoleRepository.findByRoleName(role);
    	user.getRoles().add(appRole);



    }

    @Override
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email);

    }
    public List<User> loadAllUsers(){
        return userRepository.findAll();
    }

	@Override
	public User loadUserByeUserName(String username) {
        return userRepository.findByUserName(username);

	}
}
