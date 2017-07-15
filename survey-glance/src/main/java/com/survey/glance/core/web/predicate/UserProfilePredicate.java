package com.survey.glance.core.web.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.survey.glance.core.web.domain.QUserProfile;

public class UserProfilePredicate {
	
	private static QUserProfile userProfile = QUserProfile.userProfile;
	
	public static BooleanExpression forUserProfileId(final Integer id){
		return userProfile.id.eq(id);
	}
	
	public static BooleanExpression forUserProfileType(final String roleType){
		return userProfile.type.eq(roleType);
	}

}
