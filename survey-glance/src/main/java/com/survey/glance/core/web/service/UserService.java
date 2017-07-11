package com.survey.glance.core.web.service;

import com.survey.glance.core.web.domain.User;


/**
 * @author Administrator
 *
 */
public interface UserService {

	/**
	 * @param id
	 * @return
	 */
	User findById(final Long id);
	
	/**
	 * @param sso
	 * @return
	 */
	User findBySso(final String sso);
	
}