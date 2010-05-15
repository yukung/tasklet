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
 * カテゴリ情報を表すBeanクラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class Category {

	/** ID */
	private int id;

	/** ユーザID */
	private int userId;

	/** ソート順 */
	private int seq;

	/** カテゴリ名 */
	private String name;

	/** 作成タイムスタンプ */
	private Timestamp created_on;

	/** 更新タイムスタンプ */
	private Timestamp updated_on;

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
	 * ソート順を取得します。
	 * 
	 * @return ソート順
	 */
	public int getSeq() {
		return seq;
	}

	/**
	 * ソート順を設定します。
	 * 
	 * @param seq
	 *            ソート順
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}

	/**
	 * カテゴリ名を取得します。
	 * 
	 * @return カテゴリ名
	 */
	public String getName() {
		return name;
	}

	/**
	 * カテゴリ名を設定します。
	 * 
	 * @param name
	 *            カテゴリ名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 作成タイムスタンプを取得します。
	 * 
	 * @return 作成タイムスタンプ
	 */
	public Timestamp getCreated_on() {
		return created_on;
	}

	/**
	 * 作成タイムスタンプを設定します。
	 * 
	 * @param created_on
	 *            作成タイムスタンプ
	 */
	public void setCreated_on(Timestamp created_on) {
		this.created_on = created_on;
	}

	/**
	 * 更新タイムスタンプを取得します。
	 * 
	 * @return 更新タイムスタンプ
	 */
	public Timestamp getUpdated_on() {
		return updated_on;
	}

	/**
	 * 更新タイムスタンプを設定します。
	 * 
	 * @param updated_on
	 *            更新タイムスタンプ
	 */
	public void setUpdated_on(Timestamp updated_on) {
		this.updated_on = updated_on;
	}
}
