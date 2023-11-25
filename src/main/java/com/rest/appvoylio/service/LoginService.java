package com.rest.appvoylio.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.appvoylio.dto.LoginDTO;
import com.rest.appvoylio.entity.SignupEntity;
import com.rest.appvoylio.repository.SignupRepository;

@Service
public class LoginService implements LoginServiceInterface {
	
	  private final SignupRepository signupRepository;

	    @Autowired
	    public LoginService(SignupRepository signupRepository) {
	        this.signupRepository = signupRepository;
	    }

	@Override
	public boolean loginUser(LoginDTO loginDTO) {
		
		Optional<SignupEntity> optionalUser = signupRepository.findByUsername(loginDTO.getUsername());
		
		if (optionalUser.isPresent()) {
			
            SignupEntity user = optionalUser.get();
            if (user.getPassword().equals(loginDTO.getPassword())) {
                return true; 
            }
        }
		return false;
	}

}
