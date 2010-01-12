/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.service;


import tasklet.TaskletException;
import tasklet.entity.User;

/**
 * ユーザアカウント関連のビジネスロジックを規定するインタフェースです。
 *
 * @author Y.Ikeda
 *
 */
public interface AccountService {

	/**
	 * ユーザIDとパスワードをキーにログインします。
	 *
	 * @param userName
	 * @param password
	 * @return ユーザーオブジェクト
	 */
	public User login(String userName, String password);

	/**
	 * ユーザ情報をDBに登録します。
	 *
	 * @param user
	 * @throws TaskletException アプリ例外
	 */
	public void register(User user) throws TaskletException;

}
