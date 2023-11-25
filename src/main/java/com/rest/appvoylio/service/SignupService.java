package com.rest.appvoylio.service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rest.appvoylio.dto.SignupDTO;
import com.rest.appvoylio.entity.RoleEntity;
import com.rest.appvoylio.entity.SignupEntity;
import com.rest.appvoylio.repository.RoleRepository;
import com.rest.appvoylio.repository.SignupRepository;

@Service
public class SignupService implements SignupServiceInterface {
	
	private AuthenticationManager authenticationManager;
    private SignupRepository signupRepository;   
    private PasswordEncoder passwordEncoder;   
    private RoleRepository roleRepository;

    @Autowired
    public SignupService(SignupRepository signupRepository, 
    					 PasswordEncoder passwordEncoder,
    					 RoleRepository roleRepository,
    					 AuthenticationManager authenticationManager) {
        this.signupRepository = signupRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
    }
    
    @Override
    public boolean checkIfUsernameAlreadyExist(String username) {
    	
    	return signupRepository.existsByUsername(username);
    }

    @Override
    public void signupUser(SignupDTO signupDTO) {
    	
    	validatePassword(signupDTO.getPassword());
    	validateUsername(signupDTO.getUsername());
    	
        SignupEntity signupEntity = new SignupEntity();
        signupEntity.setUsername(signupDTO.getUsername());
        signupEntity.setName(signupDTO.getName());
        signupEntity.setEmail(signupDTO.getEmail());
        signupEntity.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        signupEntity.setDos(new Timestamp(System.currentTimeMillis()));
        
        RoleEntity roles = roleRepository.findByRoleName("USER").get();
        signupEntity.setRoles(Collections.singleton(roles));
   
        signupRepository.save(signupEntity);
    }
    
    
    
    private void validatePassword(String password) {

        if (password.length() < 8) {
            throw new IllegalArgumentException("Password should be at least 8 characters long.");
        }

        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            throw new IllegalArgumentException("Password should have at least one uppercase letter.");
        }

        if (!Pattern.compile("[a-z]").matcher(password).find()) {
            throw new IllegalArgumentException("Password should have at least one lowercase letter.");
        }

        if (!Pattern.compile("[!@#$%^&*(),.?\":{}|<>]").matcher(password).find()) {
            throw new IllegalArgumentException("Password should have at least one special character.");
        }

        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            throw new IllegalArgumentException("Password should have at least one numeric character.");
        }
    }
    
    private void validateUsername(String username) {
        
        if (username.length() < 5) {
            throw new IllegalArgumentException("Username should be at least 5 characters long.");
        }

        if (Pattern.compile("[^a-zA-Z0-9]").matcher(username).find()) {
            throw new IllegalArgumentException("Username should not contain any special character.");
        }

        if (Pattern.compile("[A-Z]").matcher(username).find()) {
            throw new IllegalArgumentException("Username should not contain uppercase characters.");
        }
    }
}
