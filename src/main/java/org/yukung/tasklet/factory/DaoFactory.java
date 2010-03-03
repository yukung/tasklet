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
package org.yukung.tasklet.factory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.yukung.tasklet.dao.ActivityDao;
import org.yukung.tasklet.dao.CategoryDao;
import org.yukung.tasklet.dao.TaskDao;
import org.yukung.tasklet.dao.UserDao;
import org.yukung.tasklet.dao.impl.ActivityDaoImpl;
import org.yukung.tasklet.dao.impl.CategoryDaoImpl;
import org.yukung.tasklet.dao.impl.TaskDaoImpl;
import org.yukung.tasklet.dao.impl.UserDaoImpl;
import org.yukung.tasklet.exception.DataAccessException;

/**
 * <p>
 * 各種DAOの生成を行うFactoryクラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public final class DaoFactory {

	/**
	 * <p>
	 * DaoFactoryのインスタンス。<br>
	 * シングルトンオブジェクトです。
	 * </p>
	 */
	private static DaoFactory instance = null;

	/**
	 * <p>
	 * デフォルトコンストラクタ。<br>
	 * シングルトンのためコンストラクタは外部から直接アクセスできません。
	 * </p>
	 */
	private DaoFactory() {
	}

	/**
	 * <p>
	 * 当クラスのインスタンス取得時はこのメソッドを利用してください。
	 * 
	 * @return DaoFactoryのシングルトンオブジェクト
	 */
	public static DaoFactory getInstance() {
		if (instance == null) {
			instance = new DaoFactory();
		}
		return instance;
	}

	/**
	 * <p>
	 * データソースを取得します。
	 * </p>
	 * 
	 * @return データソース
	 */
	public DataSource getDataSource() {
		InitialContext context = null;
		DataSource ds = null;
		try {
			context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/taskletds");
		} catch (NamingException e) {
			e.printStackTrace();
			if (context != null) {
				try {
					context.close();
				} catch (NamingException ex) {
					ex.printStackTrace();
					throw new DataAccessException(ex.getMessage(), ex);
				}
			}
			throw new DataAccessException(e.getMessage(), e);
		}
		return ds;
	}

	/**
	 * <p>
	 * UserDaoの実装クラスを生成します。
	 * </p>
	 * 
	 * @return ユーザ情報DAO実装クラスのインスタンス
	 */
	public UserDao createUserDao() {
		return new UserDaoImpl(getDataSource());
	}

	/**
	 * <p>
	 * ActivityDaoの実装クラスを生成します。
	 * </p>
	 * 
	 * @return アクティビティ情報DAO実装クラスのインスタンス
	 */
	public ActivityDao createActivityDao() {
		return new ActivityDaoImpl(getDataSource());
	}

	/**
	 * <p>
	 * TaskDaoの実装クラスを生成します。
	 * </p>
	 * 
	 * @return タスク情報DAO実装クラスのインスタンス
	 */
	public TaskDao createTaskDao() {
		return new TaskDaoImpl(getDataSource());
	}

	/**
	 * <p>
	 * CategoryDaoの実装クラスを生成します。
	 * </p>
	 * 
	 * @return カテゴリ情報DAO実装クラスのインスタンス
	 */
	public CategoryDao createCategoryDao() {
		return new CategoryDaoImpl(getDataSource());
	}
}
