/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.service;

import tasklet.entity.User;

/**
 * ユーザーアカウント関連のビジネスロジックを規定するインタフェースです。
 * @author Y.Ikeda
 *
 */
public interface accountService {

	/**
	 * @param userId
	 * @param password
	 * @return ユーザーオブジェクト
	 */
	public User login(String userId, String password);

}
