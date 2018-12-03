package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UserDao;
import pojo.User;
import service.UserManager;
@Service
public class UserManagerImpl implements UserManager {
	@Autowired
	private UserDao userDao;
	@Transactional
	@Override
	public User regist(User u) {
		
		return userDao.addUser(u);
	}
	@Transactional
	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return userDao.findAllUser();
	}
	
}
