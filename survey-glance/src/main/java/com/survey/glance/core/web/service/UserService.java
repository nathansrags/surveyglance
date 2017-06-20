package com.survey.glance.core.web.service;

import com.survey.glance.core.web.model.User;


public interface UserService {

	User findById(int id);
	
	User findBySso(String sso);
	
}