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

import tasklet.DataAccessException;
import tasklet.entity.User;

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
	public User findUserByUserName(String userName) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			// SQLの取得
			String sql = getSQLFromProperty("findUserByUserName");

			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, userName);
			rs = statement.executeQuery();

			if (!rs.next()) {
				return null;
			}

			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUserName((rs.getString("user_name")));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setDisplayName(rs.getString("display_name"));
			user.setCreatedOn(rs.getTimestamp("created_on"));
			user.setUpdatedOn(rs.getTimestamp("updated_on"));
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
	public User findUserByUserNameAndPassword(String userName, String password) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			// SQLの取得
			String sql = getSQLFromProperty("findUserByUserNameAndPassword");

			// DBから取得
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, userName);
			statement.setString(2, password);
			rs = statement.executeQuery();

			if (!rs.next()) {
				return null;
			}

			User user = new User();
			user.setId((rs.getInt("id")));
			user.setUserName((rs.getString("user_name")));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setDisplayName(rs.getString("display_name"));
			user.setCreatedOn((rs.getTimestamp("created_on")));
			user.setUpdatedOn(rs.getTimestamp("updated_on"));
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
	 * @see tasklet.dao.UserDao#isRegistered(java.lang.String)
	 */
	public boolean isRegistered(String userName) {

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			// SQLの取得
			String sql = getSQLFromProperty("isRegistered");

			// DBから取得
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, userName);
			rs = statement.executeQuery();

			if (!rs.next()) {
				return false;
			}

			if (rs.getInt("user_count") > 0) {
				return true;
			} else {
				return false;
			}

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
	public void registerUser(User user) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			// SQLの取得
			String sql = getSQLFromProperty("registerUser");

			// SQLの実行
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getDisplayName());
			// 登録日、更新日はSQLでカレント日付を設定

			statement.executeUpdate();

		} catch (SQLException e) {
			rollback(conn);
			throw new DataAccessException(e.getMessage(), e);
		} finally {
			close(statement);
			close(conn);
		}
	}

	/*
	 * (非 Javadoc)
	 * @see tasklet.dao.UserDao#registerDefaultCategory(int)
	 */
	public void registerDefaultCategory(int userId) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			// SQLの取得
			String sql = getSQLFromProperty("registerCategoryDefault");

			// SQLの実行
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setInt(1, userId);
			// カテゴリは'未分類'を固定で登録

			statement.executeUpdate();

		} catch (SQLException e) {
			rollback(conn);
			throw new DataAccessException(e.getMessage(), e);
		} finally {
			close(statement);
			close(conn);
		}
	}
}
