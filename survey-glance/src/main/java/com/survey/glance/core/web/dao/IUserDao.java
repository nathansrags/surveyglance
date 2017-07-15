package com.survey.glance.core.web.dao;

import java.util.List;

import com.survey.glance.core.web.domain.User;


/**
 * @author Administrator
 *
 */
public interface IUserDao {

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
	
	/**
	 * @param user
	 * @return
	 */
	User find(final User user); 

	/**
	 * @param user
	 */
	User createUser(final User user);
	
    /**
     * @param sso
     */
    void deleteBySSO(String sso);

	/**
	 * @return
	 */
	List<User> findAllUsers();
	
}

