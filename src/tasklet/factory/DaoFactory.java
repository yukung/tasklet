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
import tasklet.dao.UserDao;
import tasklet.dao.UserDaoImpl;

/**
 * DAOの生成を行うFactoryクラスです。
 * @author Y.Ikeda
 *
 */
public class DaoFactory {

	/**
	 * UserDaoを生成します。
	 * @return UserDao
	 */
	public static UserDao createUserDao() {
		return new UserDaoImpl(getDataSource());
	}

	private static DataSource getDataSource() {
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