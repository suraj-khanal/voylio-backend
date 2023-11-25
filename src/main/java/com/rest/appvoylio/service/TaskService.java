package com.rest.appvoylio.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.appvoylio.dto.TaskDTO;
import com.rest.appvoylio.entity.SignupEntity;
import com.rest.appvoylio.entity.TaskEntity;
import com.rest.appvoylio.repository.TaskRepository;

@Service
public class TaskService implements TaskServiceInterface {
	
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public void createTask(TaskDTO taskDTO) {
		
		TaskEntity taskEntity = convertToEntity(taskDTO);
		taskRepository.save(taskEntity);
	}

	private TaskEntity convertToEntity(TaskDTO taskDTO) {
		
		TaskEntity taskEntity = new TaskEntity();
	    BeanUtils.copyProperties(taskDTO, taskEntity);
	    
	    SignupEntity signupEntity = new SignupEntity();
	    signupEntity.setUsername(taskDTO.getSignupEntity().getUsername());
	    
	    taskEntity.setSignupEntity(signupEntity);
	    
	    LocalDateTime now = LocalDateTime.now().plusMonths(3);
	    Timestamp dueDate = Timestamp.valueOf(now);
	    taskEntity.setDueDate(dueDate);
	    
		return taskEntity;
	}

}
