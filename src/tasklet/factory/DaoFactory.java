/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.factory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import tasklet.common.DataAccessException;
import tasklet.dao.ActivityDao;
import tasklet.dao.ActivityDaoImpl;
import tasklet.dao.TaskDao;
import tasklet.dao.TaskDaoImpl;
import tasklet.dao.UserDao;
import tasklet.dao.UserDaoImpl;

/**
 * DAOの生成を行うFactoryクラスです。
 *
 * @author Y.Ikeda
 *
 */
public class DaoFactory {

	/** シングルトンオブジェクト */
	private static DaoFactory instance = null;

	/**
	 * シングルトンのためコンストラクタは外部から直接アクセス不可。
	 */
	private DaoFactory() {
	}

	/**
	 * 当クラスのインスタンス取得時はこのメソッドを利用する。
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
	 * UserDaoを生成します。
	 *
	 * @return UserDao
	 */
	public UserDao createUserDao() {
		return new UserDaoImpl(getDataSource());
	}

	public ActivityDao createActivityDao() {
		return new ActivityDaoImpl(getDataSource());
	}

	public TaskDao createTaskDao() {
		return new TaskDaoImpl(getDataSource());
	}

	/**
	 * データソースを取得します。
	 *
	 * @return データソース
	 */
	private DataSource getDataSource() {
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
					throw new DataAccessException();
				}
			}
			throw new DataAccessException();
		}
		return ds;
	}

}