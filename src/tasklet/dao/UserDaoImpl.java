/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet
 */
package tasklet.dao;

import static tasklet.Constants.PROPERTY_KEY_SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import tasklet.common.TaskletException;
import tasklet.entity.User;
import tasklet.util.PropertyUtil;

/**
 * ユーザ情報DAO実装クラスです。
 * 
 * @author Y.Ikeda
 */
public class UserDaoImpl extends AbstractDao implements UserDao {

	/**
	 * 新しいUserDaoのインスタンスを生成します。
	 * 
	 * @param source
	 */
	public UserDaoImpl(DataSource source) {
		this.source = source;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see tasklet.dao.UserDao#findUser(java.lang.String)
	 */
	public User findByUserId(String userId) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append(
					"findByUserId").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, userId);
			rs = statement.executeQuery();

			if (!rs.next()) {
				return null;
			}

			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUserId(rs.getString("user_id"));
			user.setFirstName(rs.getString("user_first_name"));
			user.setLastName(rs.getString("user_last_name"));
			user.setPassword(rs.getString("user_password"));
			user.setEmail(rs.getString("user_email"));
			return user;

		} catch (SQLException e) {
			throw new TaskletException(e.getMessage(), e);
		} finally {
			close(rs);
			close(statement);
			close(conn);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see tasklet.dao.UserDao#findUser(java.lang.String)
	 */
	public User findByUserIdAndPassword(String userId, String password) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append(
					"findByUserIdAndPassword").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			// DBから取得
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, userId);
			statement.setString(2, password);
			rs = statement.executeQuery();

			if (!rs.next()) {
				return null;
			}

			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUserId(rs.getString("user_id"));
			user.setFirstName(rs.getString("user_first_name"));
			user.setLastName(rs.getString("user_last_name"));
			user.setPassword(rs.getString("user_password"));
			user.setEmail(rs.getString("user_email"));
			return user;

		} catch (SQLException e) {
			throw new TaskletException(e.getMessage(), e);
		} finally {
			close(rs);
			close(statement);
			close(conn);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see tasklet.dao.UserDao#findUser(java.lang.String)
	 */
	public int registUser(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append(
					"registUser").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			// DB更新
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, user.getUserId());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getEmail());

			return statement.executeUpdate();

		} catch (SQLException e) {
			rollback(conn);
			throw e;
		} finally {
			close(statement);
			close(conn);
		}
	}
}
