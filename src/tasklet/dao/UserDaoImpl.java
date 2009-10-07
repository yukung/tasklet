/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet
 */
package tasklet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import tasklet.bean.User;
import tasklet.common.DataAccessException;

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

	public User findByUserId(String userId) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			conn = source.getConnection();
			statement = conn
					.prepareStatement("SELECT * FROM tl_users WHERE user_id = ?");
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
			throw new DataAccessException(e.getMessage(), e);
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
			conn = source.getConnection();
			statement = conn
					.prepareStatement("SELECT * FROM tl_users WHERE user_id = ? AND user_password = ?");
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
			throw new DataAccessException(e.getMessage(), e);
		} finally {
			close(rs);
			close(statement);
			close(conn);
		}
	}
}
