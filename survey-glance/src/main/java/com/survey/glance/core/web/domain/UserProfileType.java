package com.survey.glance.core.web.domain;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
    USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");
     
    String userProfileType;
     
    private UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }
     
    public String getUserProfileType(){
        return userProfileType;
    }
     
}
