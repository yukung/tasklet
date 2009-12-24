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
	public User findUserByUserName(String userName) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append(
					"findUserByUserName").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

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
			user.setCreatedOn(rs.getDate("created_on"));
			user.setUpdatedOn(rs.getDate("updated_on"));
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
	public User findUserByUserNameAndPassword(String userName, String password) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append(
					"findUserByUserNameAndPassword").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

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
			user.setCreatedOn((rs.getDate("created_on")));
			user.setUpdatedOn(rs.getDate("updated_on"));
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
	public int registerUser(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append(
					"registerUser").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			// DB更新
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getDisplayName());
			// 登録日、更新日はSQLでカレント日付を設定

			// TODO 初期登録時に、カテゴリの未分類をユーザ毎に追加する処理を追加
			// トランザクションを考慮する必要あり

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
