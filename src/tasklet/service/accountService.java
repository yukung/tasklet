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
	 * @param userName
	 * @param password
	 * @return ユーザーオブジェクト
	 */
	public User login(String userName, String password);

	/**
	 * ユーザ情報をDBに登録します。
	 * 
	 * @param user
	 * @return 登録件数<br>
	 *         例外発生時は以下<br>
	 *         <ul>
	 *         <li>ユーザーID重複：-1</li>
	 *         <li>DB項目あふれ：-2</li>
	 *         <li>その他のDBエラー：0</li>
	 *         </ul>
	 */
	public int register(User user);

}
