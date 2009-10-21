/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.entity;

import java.util.Date;

/**
 * ユーザ情報を表すBeanクラスです。
 * 
 * @author Y.Ikeda
 * 
 */
public class User {

	/** ユーザID */
	private int userId;

	/** ユーザ名 */
	private String userName;

	/** パスワード */
	private String password;

	/** 表示名 */
	private String displayName;

	/** メールアドレス */
	private String email;

	/** 登録日 */
	private Date registeredDate;

	/**
	 * 表示名を取得します。
	 * 
	 * @return 表示名
	 */
	public String getDisplayName() {
		return displayName;
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
	 * パスワードを取得します。
	 * 
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 登録日を取得します。
	 * 
	 * @return 登録日
	 */
	public Date getRegisteredDate() {
		return registeredDate;
	}

	/**
	 * ユーザIDを取得します。
	 * 
	 * @return ユーザID
	 */
	public int getUserId() {
		return userId;
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
	 * 表示名を設定します。
	 * 
	 * @param displayName
	 *            表示名
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
	 * パスワードを設定します。
	 * 
	 * @param password
	 *            パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 登録日を設定します。
	 * 
	 * @param registeredDate
	 *            登録日
	 */
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	/**
	 * ユーザIDを設定します。
	 * 
	 * @param userId
	 *            ユーザID
	 */
	public void setUserId(int userId) {
		this.userId = userId;
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

}
