/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.dao;


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
	 * ユーザ名が既に登録されているかどうかを返します。
	 * @param userName
	 * @return 登録済みの場合はtrue,未登録の場合はfalse
	 */
	public boolean isRegistered(String userName);

	/**
	 * ユーザ情報エンティティをDBに登録します。
	 *
	 * @param ユーザ情報エンティティ
	 */
	public void registerUser(User user);

	/**
	 * カテゴリ「未分類」をDBに登録します。
	 * @param userId DB登録後のユーザID
	 */
	public void registerDefaultCategory(int userId);

}
