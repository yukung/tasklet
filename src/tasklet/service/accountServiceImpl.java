/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.service;

import java.sql.SQLException;

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

	public int regist(User user) {
		try {
			// 0件は更新なし
			return userDao.registUser(user);
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			// TODO ここのエラーハンドリングはDBMSごとにエラーコード変るのでうまく抽象化したいなぁ

			System.out.println(e.getErrorCode());
			if (errorCode == 9) {
				// HSQLDBの一意性制約違反
				return -1;
			} else if (errorCode == 124) {
				// HSQLDBの項目桁数あふれ
				return -2;
			} else {
				return 0;
			}

		}
	}
}
