package com.rest.appvoylio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.appvoylio.dto.TaskDTO;
import com.rest.appvoylio.service.TaskServiceInterface;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api")
public class TaskController {
	
	private final TaskServiceInterface taskServiceInterface;
	
	@Autowired
    public TaskController(TaskServiceInterface taskServiceInterface) {
        this.taskServiceInterface = taskServiceInterface;
    }
	
	@PostMapping("/task")
    public ResponseEntity<String> createTask(@RequestBody TaskDTO taskDTO, HttpSession session) {
        try {
        	 String username = (String) session.getAttribute("username");
        	 
        	 if (!username.isEmpty()) {
        		 
        		 taskDTO.getSignupEntity().setUsername(username);
        		 taskServiceInterface.createTask(taskDTO);
                 return new ResponseEntity<>("Task Created Successfully", HttpStatus.CREATED);
        	 }
        	 else {
        		 return new ResponseEntity<>("Authentication has Failed", HttpStatus.UNAUTHORIZED);
        	 }
            
            
        } catch (Exception e) {
            
            return new ResponseEntity<>("Error Creating Task", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
