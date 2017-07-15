package com.survey.glance.core.web.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.survey.glance.core.web.domain.QUser;
import com.survey.glance.core.web.domain.User;

/**
 * @author Administrator
 *
 */
public class UserPredicate {

	private static QUser quser = QUser.user;

	/**
	 * @param id
	 * @return
	 */
	public static BooleanExpression forUserId(final Integer id) {
		return quser.id.eq(id);
	}

	/**
	 * @param ssoid
	 * @return
	 */
	public static BooleanExpression forSsoId(final String ssoid) {
		return quser.ssoId.eq(ssoid);
	}

	/**
	 * @param email
	 * @return
	 */
	public static BooleanExpression forUserEmail(final String email) {
		return quser.email.eq(email);
	}

	/**
	 * @param user
	 * @return
	 */
	public static BooleanExpression forUser(final User user) {
		return forSsoId(user.getSsoId());
	}

}
