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
	 * 
	 * @see tasklet.service.AccountService#register(tasklet.entity.User)
	 */
	public int register(User user) {
		// TODO とりあえずベタ書き、後でリファクタリングする
		try {
			// 0件は更新なし
			return userDao.registerUser(user);
			// TODO userDao.registerDefaultCategory(user);
		} catch (SQLException e) {
			int errorCode = e.getErrorCode();
			// TODO ここのエラーハンドリングはDBMSごとにエラーコード変るのでうまく抽象化したい
			// HSQLDBはシリアル実行なので排他制御要らない、IDの重複も考えなくて良い
			if (errorCode == -9) {
				// HSQLDBの一意性制約違反
				// TODO SELECT FOR UPDATEでユーザーIDを先読みしてチェックを入れればこのロジックは要らない？
				return -1;
			} else if (errorCode == -124) {
				// HSQLDBの項目桁数あふれ
				// TODO フレームワークのバリデーションチェックを入れればこのロジックは要らない？
				return -2;
			} else {
				return 0;
			}

		}
	}
}
