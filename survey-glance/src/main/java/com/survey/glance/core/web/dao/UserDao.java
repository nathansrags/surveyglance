package com.survey.glance.core.web.dao;

import com.survey.glance.core.web.domain.User;


/**
 * @author Administrator
 *
 */
public interface UserDao {

	/**
	 * @param id
	 * @return
	 */
	User findById(final Long id);
	
	/**
	 * @param sso
	 * @return
	 */
	User findBySSO(final String sso);
	
}

