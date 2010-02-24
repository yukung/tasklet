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

import org.yukung.tasklet.entity.User;

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
	 */
	public Integer getUserCount(String userName);

	/**
	 * <p>
	 * ユーザ情報をusersテーブルに追加します。
	 * </p>
	 * 
	 * @param user
	 */
	public void addUser(User user);

	/**
	 * <p>
	 * カテゴリ「未分類」をcategoriesテーブルに登録します。
	 * </p>
	 * 
	 * @param userId
	 *            DBから払い出されたユーザID
	 */
	public void addDefaultCategory(int userId);
}
