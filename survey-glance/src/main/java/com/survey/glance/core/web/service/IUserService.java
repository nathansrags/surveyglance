package com.survey.glance.core.web.service;

import java.sql.SQLException;
import java.util.List;

import com.survey.glance.core.web.domain.User;

/**
 * @author Administrator
 *
 */
public interface IUserService {

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
	
	/**
	 * @param user
	 * @return
	 */
	User find(final User user);

	/**
	 * @param user
	 * @throws SQLException
	 */
	User createUser(final User user);

	/**
	 * @param user
	 */
	void updateUser(final User user);

	/**
	 * @param sso
	 */
	void deleteUserBySSO(final String sso);

	/**
	 * @return
	 */
	List<User> findAllUsers();

	/**
	 * @param id
	 * @param sso
	 * @return
	 */
	boolean isUserSSOUnique(Integer id, String sso);

}