package com.autoservice.commons.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordBusiness {

	String password;
	String oldPassword;
	
	public PasswordBusiness(String password) {
		this.password =  password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	
	public String getMD5() throws Exception {
		String hashed = "$#%43456546&^5743534";
		try {
			MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm
			mdEnc.update(this.password.getBytes(), 0, this.password.length());
			hashed = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string		
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("NoSuchAlgorithException ocurred... hashing the password...");
		}
		
		return hashed;

	}
}
