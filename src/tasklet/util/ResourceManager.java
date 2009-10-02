/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * リソース管理を行うクラスです。
 * @author Y.Ikeda
 *
 */
public class ResourceManager {

	/** データソース */
	private static DataSource dataSource = null;

	/**
	 * DataSourceからDB接続を得るメソッドです。
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		if (dataSource == null) {
			try {
				Context context = new InitialContext();
				dataSource = (DataSource) context.lookup("java:comp/env/jdbc/taskletds");
			} catch (NamingException e) {
				throw new SQLException(e.getMessage());
			}
		}
		return dataSource.getConnection();
	}
}
