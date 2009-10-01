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
	 * ユーザーIDをキーにユーザ情報Beanを取得します。
	 * @param userId
	 * @return
	 */
	User findUser(String userId);

}
