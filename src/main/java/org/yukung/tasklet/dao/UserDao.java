/**
 * Copyright 2009-2010 Yusuke Ikeda. (@yukung) <yukung.i@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.yukung.tasklet.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.yukung.tasklet.entity.User;
import org.yukung.tasklet.exception.TaskletException;

/**
 * <p>
 * ユーザ情報DAOインタフェースです。
 * </p>
 * 
 * @author yukung
 * 
 */
public interface UserDao {

	/**
	 * <p>
	 * ユーザ名をキーにユーザ情報Entityを取得します。
	 * </p>
	 * 
	 * @param userName
	 * @return ユーザ情報Entity
	 */
	public User findUserByUserName(String userName);

	/**
	 * <p>
	 * ユーザ名とパスワードをキーにユーザ情報Entityを取得します。
	 * </p>
	 * 
	 * @param userName
	 * @param password
	 * @return ユーザ情報Entity
	 */
	public User findUserByUserNameAndPassword(String userName, String password);

	/**
	 * <p>
	 * ユーザ名をキーに登録されている件数を取得します。
	 * </p>
	 * 
	 * @param userName
	 * @return 登録されている件数
	 *         <p>
	 *         見つからない場合は0を返します。<br>
	 *         COUNT文のため、nullを考慮する必要はありません。
	 *         </p>
	 */
	public Integer getUserCount(String userName);

	/**
	 * <p>
	 * ユーザ情報をusersテーブルに追加します。
	 * </p>
	 * 
	 * @param conn
	 *            DB接続
	 * @param user
	 *            ユーザ情報
	 * @throws SQLException
	 *             DB更新エラー
	 */
	public void addUser(Connection conn, User user) throws SQLException;

	/**
	 * @param userName
	 *            ユーザ名
	 * @return Userテーブルに格納されているパスワード
	 */
	public String getPassword(String userName);

	/**
	 * <p>
	 * ユーザ情報を更新します。
	 * </p>
	 * 
	 * @param user
	 *            ユーザEntity
	 * @throws TaskletException
	 *             DB更新エラー
	 */
	public void updateUser(User user) throws TaskletException;
}
