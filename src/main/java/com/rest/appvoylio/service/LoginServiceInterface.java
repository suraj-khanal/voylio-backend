package com.rest.appvoylio.service;

import com.rest.appvoylio.dto.LoginDTO;

public interface LoginServiceInterface {
	
	boolean loginUser(LoginDTO loginDTO);
	
}
