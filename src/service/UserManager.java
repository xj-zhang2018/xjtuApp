package service;

import java.util.List;

import pojo.User;

public interface UserManager {
	public User regist(User u);
	public List<User> findAllUser();
}
