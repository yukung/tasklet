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
package org.yukung.tasklet.entity;

import java.sql.Timestamp;

/**
 * <p>
 * ユーザ情報を表すBeanクラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class User {

	/** ID */
	private int id;

	/** ユーザ名 */
	private String userName;

	/** メールアドレス */
	private String email;

	/** パスワード */
	private String password;

	/** 表示名 */
	private String displayName;

	/** 登録タイムスタンプ */
	private Timestamp createdOn;

	/** 更新タイムスタンプ */
	private Timestamp updatedOn;

	/**
	 * IDを取得します。
	 * 
	 * @return ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * IDを設定します。
	 * 
	 * @param id
	 *            ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * ユーザ名を取得します。
	 * 
	 * @return ユーザ名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * ユーザ名を設定します。
	 * 
	 * @param userName
	 *            ユーザ名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * メールアドレスを取得します。
	 * 
	 * @return メールアドレス
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * メールアドレスを設定します。
	 * 
	 * @param email
	 *            メールアドレス
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * パスワードを取得します。
	 * 
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * パスワードを設定します。
	 * 
	 * @param password
	 *            パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 表示名を取得します。
	 * 
	 * @return 表示名
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * 表示名を設定します。
	 * 
	 * @param displayName
	 *            表示名
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * 登録タイムスタンプを取得します。
	 * 
	 * @return 登録タイムスタンプ
	 */
	public Timestamp getCreatedOn() {
		return createdOn;
	}

	/**
	 * 登録タイムスタンプを設定します。
	 * 
	 * @param createdOn
	 *            登録タイムスタンプ
	 */
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * 更新タイムスタンプを取得します。
	 * 
	 * @return 更新タイムスタンプ
	 */
	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * 更新タイムスタンプを設定します。
	 * 
	 * @param updatedOn
	 *            更新タイムスタンプ
	 */
	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}
}
