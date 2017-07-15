package com.survey.glance.core.web.common.util;

/**
 * @author Administrator
 *
 */
public final class Constants {

	public class NavigationConstants {
		public static final String SLASH_LOGIN = "/login";
		public static final String LOGIN = "login";
		
		public static final String SLASH_HOME = "/home";
		
		public static final String SLASH_VISITOR = "/visitor";
		public static final String SLASH_VISITOR_HOME = SLASH_VISITOR + SLASH_HOME;
		public static final String VISITOR_HOME = "visitor/visitorHome";
		
		public static final String SLASH_ADMIN = "/admin";
		public static final String SLASH_ADMIN_HOME = SLASH_ADMIN + SLASH_HOME;
		public static final String ADMIN_HOME = "admin/adminHome";
		
		public static final String SLASH_DBA = "/dba";
		public static final String SLASH_DBA_HOME = SLASH_DBA + SLASH_HOME;
		public static final String DBA_HOME = "dba/dbaHome";
		
		public static final String SLASH_PROFILE = "/profile";
		public static final String PROFILE = "profile";
		
		
		public static final String SLASH_ACCESS_DENIED = "/Access_Denied";
		public static final String ACCESS_DENIED = "accessDenied";
		
	}

	public class ROLE_CONSTANTS {
		public static final String ROLE_USER = "ROLE_USER";
		public static final String ROLE_DBA = "ROLE_DBA";
		public static final String ROLE_ADMIN = "ROLE_ADMIN";
	}

}
