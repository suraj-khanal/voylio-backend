package com.rest.appvoylio.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.appvoylio.dto.AuthResponseDTO;
import com.rest.appvoylio.dto.LoginDTO;
import com.rest.appvoylio.dto.SignupDTO;
import com.rest.appvoylio.security.JwtTokenGenerator;
import com.rest.appvoylio.service.LoginServiceInterface;
import com.rest.appvoylio.service.SignupServiceInterface;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api")
public class AuthController {

	private final SignupServiceInterface signupServiceInterface;	
	private final LoginServiceInterface loginServiceInterface;
	private AuthenticationManager authenticationManager;
	private JwtTokenGenerator jwtTokenGenerator;

	@Autowired
	public AuthController(SignupServiceInterface signupServiceInterface,
							LoginServiceInterface loginServiceInterface,
							AuthenticationManager authenticationManager,
							JwtTokenGenerator jwtTokenGenerator) {
		this.signupServiceInterface = signupServiceInterface;
		this.loginServiceInterface = loginServiceInterface;
		this.authenticationManager = authenticationManager;
		this.jwtTokenGenerator = jwtTokenGenerator;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> signupUser(@RequestBody SignupDTO signupDTO) {

		if (signupServiceInterface.checkIfUsernameAlreadyExist(signupDTO.getUsername())) {

			return new ResponseEntity<>("Username is Taken", HttpStatus.BAD_REQUEST);
		} else {
			try {
				signupServiceInterface.signupUser(signupDTO);
				return new ResponseEntity<>("User signed up successfully", HttpStatus.CREATED);

			} catch (Exception e) {

				return new ResponseEntity<>("Error Signing Up User", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	
	// ResponseEntity<?> ->> allows different return type for success or failure

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO, HttpSession session) {
	    try {
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String token = jwtTokenGenerator.generateToken(authentication);

	        session.setAttribute("username", authentication.getName());

	        AuthResponseDTO authResponseDTO = new AuthResponseDTO(token);
	        return ResponseEntity.ok(authResponseDTO);
	        
	    } catch (AuthenticationException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
	    } catch (Exception e) {
	        // Handle other exceptions
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during login");
	    }
	}

	
	
/*	
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(@RequestBody LoginDTO loginDTO, HttpSession session) {
            
    	Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenGenerator.generateToken(authentication);
            
            session.setAttribute("username", authentication.getName());
            
            return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
         
    }
	
	@PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO,  HttpSession session) {
        try {
            boolean isAuthenticated = loginServiceInterface.loginUser(loginDTO);
            
            if (isAuthenticated) {
            	session.setAttribute("username", loginDTO.getUsername());
                return new ResponseEntity<>("User logged in successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid Username or Password", HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            return new ResponseEntity<>("Error during login", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    */
	
	@GetMapping("/wow")
	@PreAuthorize("hasAuthority('USER')")
	public Map<String,Object> wow(){
		Map<String,Object> response=new HashMap<>();
		response.put("message", "magic magic!");
		response.put("code", "204");
		return response;
	}

	@GetMapping(path = "/hello-world/path-variable/{username}")
	public String helloWorldPathVariable(@PathVariable String username) {

		return (String.format("Hello World, %s", username));
	}

}
