/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.service;

import tasklet.dao.UserDao;
import tasklet.entity.User;
import tasklet.factory.DaoFactory;

/**
 * ユーザーアカウント関連のビジネスロジックを実行するServiceの実装クラスです。
 * 
 * @author Y.Ikeda
 * 
 */
public class accountServiceImpl implements accountService {

	private UserDao userDao = DaoFactory.getInstance().createUserDao();

	/*
	 * (非 Javadoc)
	 * 
	 * @see tasklet.service.accountService#login(java.lang.String,
	 * java.lang.String)
	 */
	public User login(String userId, String password) {
		User user = userDao.findByUserIdAndPassword(userId, password);
		if (user == null) {
			user = null;
		}
		return user;
	}

	public boolean regist(User user) {
		int result = userDao.registUser(user);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}
}
