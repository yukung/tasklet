/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.dao;

import java.sql.SQLException;

import tasklet.entity.User;

/**
 * ユーザ情報DAOインターフェースです。
 * 
 * @author Y.Ikeda
 */
public interface UserDao {

	/**
	 * ユーザ名をキーにユーザ情報エンティティを取得します。
	 * 
	 * @param ユーザ名
	 * @return ユーザ情報エンティティ
	 */
	public User findUserByUserName(String userName);

	/**
	 * ユーザ名とパスワードをキーにユーザ情報エンティティを取得します。
	 * 
	 * @param ユーザ名
	 * @param パスワード
	 * @return ユーザー情報オブジェクト
	 */
	public User findUserByUserNameAndPassword(String userName, String password);

	/**
	 * ユーザ情報エンティティをDBに登録します。
	 * 
	 * @param ユーザ情報エンティティ
	 * @return 更新件数
	 * @throws SQLException
	 */
	public int registerUser(User user) throws SQLException;

	/**
	 * カテゴリ「未分類」をDBに登録します。
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public int registerDefaultCategory(User user) throws SQLException;

}
