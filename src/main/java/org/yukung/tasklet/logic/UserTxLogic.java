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
package org.yukung.tasklet.logic;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.yukung.tasklet.dao.CategoryDao;
import org.yukung.tasklet.dao.UserDao;
import org.yukung.tasklet.entity.User;
import org.yukung.tasklet.exception.TaskletException;
import org.yukung.tasklet.factory.DaoFactory;

/**
 * <p>
 * ユーザーアカウント関連のトランザクション単位を規定するクラスです。<br>
 * 1メソッドが1トランザクションを表現します。
 * </p>
 * 
 * @author yukung
 * 
 */
public class UserTxLogic {

	/** ユーザ情報DAO */
	private UserDao userDao;

	/** カテゴリ情報DAO */
	private CategoryDao categoryDao;

	/**
	 * <p>
	 * デフォルトコンストラクタ。
	 * </p>
	 * <p>
	 * リソース有効利用の観点から、各Serviceクラスから利用する場合は極力Service自身が持つDAOを渡す形で利用してください。
	 * </p>
	 */
	public UserTxLogic() {
		this(DaoFactory.getInstance().createUserDao(), DaoFactory.getInstance()
				.createCategoryDao());
	}

	/**
	 * <p>
	 * コンストラクタ。
	 * </p>
	 * 
	 * @param userDao
	 * @param categoryDao
	 */
	public UserTxLogic(UserDao userDao, CategoryDao categoryDao) {
		this.userDao = userDao;
		this.categoryDao = categoryDao;
	}

	/**
	 * <p>
	 * ユーザ登録処理を行います。
	 * </p>
	 * 
	 * @param user
	 *            ユーザ情報Entity
	 * @throws TaskletException
	 *             DB更新時のエラー
	 */
	public void register(User user) throws TaskletException {
		Connection conn = null;
		try {
			conn = DaoFactory.getInstance().getConnection();
			conn.setAutoCommit(false);

			// usersテーブルへユーザを追加
			userDao.addUser(conn, user);
			// 払い出されたユーザIDを取得
			int userId = userDao.findUserByUserName(user.getUserName()).getId();
			// categoriesテーブルへデフォルトのカテゴリを追加
			categoryDao.addDefaultCategory(conn, userId);

			DbUtils.commitAndCloseQuietly(conn);
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			throw new TaskletException(e.getMessage(), e);
		}
	}
}
