package org.game.entity;

/**
 * Created by yp on 2017/4/30.
 */
public class User {

    private String userId;
    
    private String userName;
    
    private String password;
    
    private double account;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}

	public User(String userId) {
		super();
		this.userId = userId;
	}

}