/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import tasklet.DataAccessException;

/**
 * Daoの基底クラスです。
 * Daoを作成する場合はこのクラスを継承して作成してください。
 *
 * @author Y.Ikeda
 *
 */

public abstract class AbstractDao {

	/** データソース */
	protected DataSource source;

	public AbstractDao() {
		super();
	}

	/**
	 * コミットします。
	 *
	 * @param conn
	 */
	protected void commit(Connection conn) {
		if (conn != null) {
			try {
				conn.commit();
			} catch (SQLException e) {
				throw new DataAccessException(e.getMessage(), e);
			}
		}
	}

	/**
	 * ロールバックします。
	 *
	 * @param conn
	 */
	protected void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new DataAccessException(e.getMessage(), e);
			}
		}
	}

	/**
	 * DB接続をクローズします。
	 *
	 * @param conn
	 */
	protected void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DataAccessException(e.getMessage(), e);
			}
		}
	}

	/**
	 * ステートメントをクローズします。
	 *
	 * @param statement
	 */
	protected void close(PreparedStatement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new DataAccessException(e.getMessage(), e);
			}
		}
	}

	/**
	 * 結果セットをクローズします。
	 *
	 * @param rs
	 */
	protected void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DataAccessException(e.getMessage(), e);
			}
		}
	}

}