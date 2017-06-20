package com.survey.glance.core.web.dao;

import com.survey.glance.core.web.model.User;


public interface UserDao {

	User findById(int id);
	
	User findBySSO(String sso);
	
}

