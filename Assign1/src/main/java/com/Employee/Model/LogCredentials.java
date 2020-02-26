package com.Employee.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LogCredentials {
	
	private static HashMap<String, String> login =  new HashMap<String,String>();
 
    
    public LogCredentials(HashMap<String, String> login) {
	super();
	this.login = login;
	login.put("Shob", "Pwd");
	login.put("Selenium", "sel");
	login.put("Java", "lang");
	login.put("Springboot", "boot");
	
}

	public static boolean logValidation(String uname,String pwd) {
        if(login.containsKey(uname)) {
            if(login.get(uname).contains(pwd))
            	return true;
        }
        return false;
    }
 
 }
