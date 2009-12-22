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
	private Date createdOn;

	/** 更新タイムスタンプ */
	private Date updatedOn;

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
	 * 登録日を取得します。
	 * 
	 * @return 登録日
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * 登録日を設定します。
	 * 
	 * @param createdOn
	 *            登録日
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * 更新日を取得します。
	 * 
	 * @return 更新日
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * 更新日を設定します。
	 * 
	 * @param updatedOn
	 *            更新日
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}