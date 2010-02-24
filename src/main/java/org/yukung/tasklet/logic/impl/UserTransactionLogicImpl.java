/**
 * Copyright 2009-2010 Yusuke Ikeda. (@yukung) <yukung.i@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.yukung.tasklet.logic.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.yukung.tasklet.dao.UserDao;
import org.yukung.tasklet.entity.User;
import org.yukung.tasklet.exception.TaskletException;
import org.yukung.tasklet.factory.DaoFactory;
import org.yukung.tasklet.logic.AbstractTransactionLogic;
import org.yukung.tasklet.logic.TransactionLogic;

/**
 * <p>
 * ユーザアカウント関連のトランザクションを実行するLogicの実装クラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class UserTransactionLogicImpl extends AbstractTransactionLogic
		implements TransactionLogic<User> {

	/** ユーザ情報DAO */
	private UserDao userDao;

	/**
	 * <p>
	 * デフォルトコンストラクタ。
	 * </p>
	 */
	public UserTransactionLogicImpl() {
		this(DaoFactory.getInstance().createUserDao());
	}

	/**
	 * <p>
	 * コンストラクタ。DAOを外からもらう場合はこちらを使用します。
	 * </p>
	 * 
	 * @param userDao
	 *            ユーザ情報DAO
	 */
	public UserTransactionLogicImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.logic.TransactionLogic#store(java.lang.Object)
	 */
	@Override
	public void store(User user) throws TaskletException {
		Connection conn = null;
		try {
			conn = runner.getDataSource().getConnection();
			conn.setAutoCommit(false);

			// usersテーブルへデータを追加
			userDao.addUser(user);
			// 払い出されたユーザIDを取得
			int userId = userDao.findUserByUserName(user.getUserName())
					.getId();
			// categoriesテーブルへデータを追加
			userDao.addDefaultCategory(userId);

			DbUtils.commitAndCloseQuietly(conn);
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			throw new TaskletException(e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.logic.TransactionLogic#store(java.lang.Object[])
	 */
	@Override
	public void store(Object... entities) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
