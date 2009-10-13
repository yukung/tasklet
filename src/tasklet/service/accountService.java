/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.service;

import tasklet.entity.User;

/**
 * ユーザアカウント関連のビジネスロジックを規定するインタフェースです。
 * 
 * @author Y.Ikeda
 * 
 */
public interface accountService {

	/**
	 * ユーザIDとパスワードをキーにログインします。
	 * 
	 * @param userId
	 * @param password
	 * @return ユーザーオブジェクト
	 */
	public User login(String userId, String password);

	/**
	 * ユーザ情報をDBに登録します。
	 * 
	 * @param user
	 * @return 登録結果
	 */
	public boolean regist(User user);

}
