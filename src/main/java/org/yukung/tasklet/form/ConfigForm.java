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
package org.yukung.tasklet.form;

import org.apache.struts.validator.ValidatorForm;

/**
 * <p>
 * 設定変更用アクションフォームです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class ConfigForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;

	/** ユーザID */
	private String userId;

	/** ユーザ名 */
	private String userName;

	/** 表示名 */
	private String displayName;

	/** メールアドレス */
	private String email;

	/** 古いパスワード */
	private String origin;

	/** 新パスワード */
	private String password;

	/** 新パスワード確認入力 */
	private String confirm;

	/**
	 * ユーザIDを取得します。
	 * 
	 * @return ユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザIDを設定します。
	 * 
	 * @param userId
	 *            ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * 古いパスワードを取得します。
	 * 
	 * @return 古いパスワード
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * 古いパスワードを設定します。
	 * 
	 * @param origin
	 *            古いパスワード
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * 新パスワードを取得します。
	 * 
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 新パスワードを設定します。
	 * 
	 * @param password
	 *            パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 新パスワード確認入力を取得します。
	 * 
	 * @return パスワード確認入力
	 */
	public String getConfirm() {
		return confirm;
	}

	/**
	 * 新パスワード確認入力を設定します。
	 * 
	 * @param confirm
	 *            パスワード確認入力
	 */
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
}
