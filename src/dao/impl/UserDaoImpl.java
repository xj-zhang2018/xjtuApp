package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.UserDao;
import pojo.User;
@Repository
public class UserDaoImpl implements UserDao{
	
	@PersistenceContext(name="myorcl") 
	private EntityManager manager;
	
	@Override
	public User addUser(User u) {
		System.out.println("Ãû×ÖÊÇ2"+u.getUname());
		manager.persist(u);
		
		return u;
	}

	@Override
	public List<User> findAllUser() {
		String jpql="from User u";
	
		List<User> users=manager.createQuery(jpql).getResultList();
		return users;
	}
	
}
