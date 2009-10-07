/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.dao;

import tasklet.bean.User;

/**
 * ユーザ情報DAOインターフェースです。
 * @author Y.Ikeda
 */
public interface UserDao {

	/**
	 * ユーザIDをキーにユーザ情報Beanを取得します。
	 * @param userId
	 * @return
	 */
	public User findByUserId(String userId);


	/**
	 * ユーザIDとパスワードをキーにユーザ情報Beanを取得します。
	 * @param userId
	 * @param password
	 * @return ユーザー情報オブジェクト
	 */
	public User findByUserIdAndPassword(String userId, String password);

}
