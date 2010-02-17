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
package org.yukung.tasklet.dao.impl;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.yukung.tasklet.dao.AbstractDao;
import org.yukung.tasklet.dao.UserDao;
import org.yukung.tasklet.entity.User;
import org.yukung.tasklet.exception.DataAccessException;

/**
 * <p>
 * ユーザ情報DAOの実装クラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class UserDaoImpl extends AbstractDao implements UserDao {

	/**
	 * <p>
	 * デフォルトコンストラクタ。
	 * </p>
	 */
	public UserDaoImpl() {
		super();
	}

	/**
	 * <p>
	 * コンストラクタ。DataSource経由での接続はこちらを使用します。
	 * </p>
	 * 
	 * @param ds
	 *            データソース
	 */
	public UserDaoImpl(DataSource ds) {
		super(ds);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.UserDao#findUserByUserName(java.lang.String)
	 */
	@Override
	public User findUserByUserName(String userName) {
		String sql = getSQLFromPropertyFile("findUserByUserName");
		BeanHandler<User> rsh = new BeanHandler<User>(User.class);
		try {
			return runner.query(sql, rsh, userName);
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.dao.UserDao#findUserByUserNameAndPassword(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public User findUserByUserNameAndPassword(String userName, String password) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.UserDao#isRegisterd(java.lang.String)
	 */
	@Override
	public boolean isRegisterd(String userName) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
