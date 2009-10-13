/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.entity;

/**
 * ユーザ情報を表すBeanクラスです。
 * 
 * @author Y.Ikeda
 * 
 */
public class User {

	/** プライマリーキー */
	private int id;

	/** ユーザID */
	private String userId;

	/** パスワード */
	private String password;

	/** 姓 */
	private String firstName;

	/** 名 */
	private String lastName;

	/** メールアドレス */
	private String email;

	/**
	 * メールアドレスを取得します。
	 * 
	 * @return メールアドレス
	 */
	public String getEmail() {
		return email;
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
	 * プライマリーキーを取得します。
	 * 
	 * @return プライマリーキー
	 */
	public int getId() {
		return id;
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
	 * パスワードを取得します。
	 * 
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * ユーザIDを取得します。
	 * 
	 * @return ユーザID
	 */
	public String getUserId() {
		return userId;
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
	 * 姓を設定します。
	 * 
	 * @param firstName
	 *            姓
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * プライマリーキーを設定します。
	 * 
	 * @param id
	 *            プライマリーキー
	 */
	public void setId(int id) {
		this.id = id;
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
	 * パスワードを設定します。
	 * 
	 * @param password
	 *            パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
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

}
