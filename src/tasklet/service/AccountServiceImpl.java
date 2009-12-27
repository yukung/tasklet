/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.service;


import tasklet.common.TaskletException;
import tasklet.dao.UserDao;
import tasklet.entity.User;
import tasklet.factory.DaoFactory;

/**
 * ユーザーアカウント関連のビジネスロジックを実行するServiceの実装クラスです。
 *
 * @author Y.Ikeda
 *
 */
public class AccountServiceImpl implements AccountService {

	private UserDao userDao = DaoFactory.getInstance().createUserDao();

	/*
	 * (非 Javadoc)
	 *
	 * @see tasklet.service.AccountService#login(java.lang.String,
	 * java.lang.String)
	 */
	public User login(String userName, String password) {
		User user = userDao.findUserByUserNameAndPassword(userName, password);
		if (user == null) {
			user = null;
		}
		return user;
	}

	/*
	 * (非 Javadoc)
	 * @see tasklet.service.AccountService#register(tasklet.entity.User)
	 */
	public void register(User user) throws TaskletException {

		// ユーザID重複チェック
		if (userDao.isRegistered(user.getUserName())) {
			throw new TaskletException("errors.already");
		}

		// ユーザ登録処理
		userDao.registerUser(user);
		int userId = userDao.findUserByUserName(user.getUserName()).getId();
		userDao.registerDefaultCategory(userId);

	}
}
