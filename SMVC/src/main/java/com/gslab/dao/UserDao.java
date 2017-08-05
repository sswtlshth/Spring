package com.gslab.dao;

import com.gslab.model.Login;
import com.gslab.model.User;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


public interface UserDao {
	public void register(User user) throws MySQLIntegrityConstraintViolationException;
	public User validateUser(Login login);
}
