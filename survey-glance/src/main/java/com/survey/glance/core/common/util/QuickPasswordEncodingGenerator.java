package com.survey.glance.core.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Administrator
 *
 */
public class QuickPasswordEncodingGenerator {
  
    /**
     * @param args
     */
    public static void main(String[] args) {
            String password = "root123";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            System.out.println(passwordEncoder.encode(password));
    }
  
}
