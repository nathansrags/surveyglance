package com.survey.glance.core.web.transformer.impl;

import org.springframework.stereotype.Component;

import com.survey.glance.core.web.domain.State;
import com.survey.glance.core.web.domain.User;
import com.survey.glance.core.web.forms.RegisterFormTO;
import com.survey.glance.core.web.transformer.IGenericTransformer;

/**
 * @author Administrator
 *
 * @param <User>
 * @param <RegisterFormTO>
 */
@Component("pUserTransformerImpl")
public class UserTransformerImpl<E, T> implements IGenericTransformer<E, T> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.survey.glance.core.web.transformer.IGenericTransformer#
	 * transformEntityToTO(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T transformEntityToTO(final E e) {
		final User user = (User) e;
		final RegisterFormTO registerFormTO = new RegisterFormTO();
		registerFormTO.setRegemail(user.getEmail());
		registerFormTO.setRegusername(user.getSsoId());
		registerFormTO.setRegpassword(user.getPassword());
		return (T) registerFormTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.survey.glance.core.web.transformer.IGenericTransformer#
	 * transformTOToEntity(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E transformTOToEntity(final T to) {
		final RegisterFormTO registerFormTO = (RegisterFormTO) to;
		final User user = new User();
		user.setEmail(registerFormTO.getRegemail());
		user.setSsoId(registerFormTO.getRegusername());
		user.setPassword(registerFormTO.getRegpassword());
		user.setFirstName("sample");
		user.setLastName("sample");
		user.setState(State.ACTIVE.getState());
		return (E) user;
	}

	/*public static void main(String[] args) {
		IGenericTransformer<User, RegisterFormTO> transformer = new UserTransformerImpl<User, RegisterFormTO>();
		User user = new User();
		user.setEmail("email");
		user.setPassword("password");
		user.setState("active");
		user.setSsoId("test");
		final RegisterFormTO registerFormTO = transformer
				.transformEntityToTO(user);
		System.out.println(transformer.transformEntityToTO(user));
		final User users = transformer.transformTOToEntity(registerFormTO);
		System.out.println(users);
	}*/

}
