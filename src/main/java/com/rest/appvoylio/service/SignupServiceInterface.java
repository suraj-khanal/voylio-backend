package com.rest.appvoylio.service;

import com.rest.appvoylio.dto.SignupDTO;

public interface SignupServiceInterface {
	
    void signupUser(SignupDTO signupDTO);
    
    boolean checkIfUsernameAlreadyExist(String username);
}