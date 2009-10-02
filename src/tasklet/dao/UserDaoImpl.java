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

import tasklet.bean.User;
import tasklet.util.ResourceManager;

/**
 * ユーザ情報DAO実装クラスです。
 * @author Y.Ikeda
 */
public class UserDaoImpl implements UserDao {

	/**
	 * デフォルトコンストラクタ
	 */
	public UserDaoImpl() {}

	/* (非 Javadoc)
	 * @see tasklet.dao.UserDao#findUser(java.lang.String)
	 */
	public User findUser(String userId) {
		// TODO 引数を元にDB問い合わせしてUserオブジェクトを返す
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = new ResourceManager().getConnection();

			statement = connection.prepareStatement("SELECT * FROM tl_users WHERE user_id = ?");
			statement.setString(1, userId);
			ResultSet resultSet = statement.executeQuery();

			if (!resultSet.next()) { return null; }

			User user = new User();
			user.setId(resultSet.getInt("id"));
			user.setUserId(resultSet.getString("user_id"));
			user.setFirstName(resultSet.getString("user_first_name"));
			user.setLastName(resultSet.getString("user_last_name"));
			user.setPassword(resultSet.getString("user_password"));
			user.setEmail(resultSet.getString("user_email"));
			return user;

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			throw new IllegalArgumentException();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ignore) {
					// TODO: handle exception
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
					// TODO: handle exception
				}
			}
		}
	}

}
