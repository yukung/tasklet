/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.entity;

import java.sql.Date;

/**
 * ユーザ情報を表すBeanクラスです。
 * 
 * @author Y.Ikeda
 * 
 */
public class User {

	/** ユーザID */
	private int userId;

	/** ログイン名 */
	private String userName;

	/** パスワード */
	private String password;

	/** 姓 */
	private String firstName;

	/** 名 */
	private String lastName;

	/** 表示名 */
	private String displayName;

	/** メールアドレス */
	private String email;

	/** 登録日 */
	private Date registeredDate;

	/**
	 * ユーザIDを取得します。
	 * 
	 * @return ユーザID
	 */
	public int getUserId() {
		return userId;
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
	 * ログイン名を取得します。
	 * 
	 * @return ログイン名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * ログイン名を設定します。
	 * 
	 * @param userName
	 *            ログイン名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * 姓を取得します。
	 * 
	 * @return 姓
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 姓を設定します。
	 * 
	 * @param firstName
	 *            姓
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 名を取得します。
	 * 
	 * @return 名
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 名を設定します。
	 * 
	 * @param lastName
	 *            名
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * 登録日を取得します。
	 * 
	 * @return 登録日
	 */
	public Date getRegisteredDate() {
		return registeredDate;
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

}
