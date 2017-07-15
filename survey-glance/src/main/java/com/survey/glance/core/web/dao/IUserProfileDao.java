package com.survey.glance.core.web.dao;

import java.util.List;

import com.survey.glance.core.web.domain.UserProfile;

/**
 * @author Administrator
 *
 */
public interface IUserProfileDao {
	
	List<UserProfile> findAll();

	UserProfile findByType(String type);

	UserProfile findById(int id);
}
