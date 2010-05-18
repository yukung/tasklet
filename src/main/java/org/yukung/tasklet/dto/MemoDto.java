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
package org.yukung.tasklet.dto;

import java.util.Date;

/**
 * <p>
 * メモ一覧画面の表示用Beanクラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class MemoDto {

	/** メモID */
	private String id;

	/** メモ内容 */
	private String contents;

	/** 作成タイムスタンプ */
	private Date createdOn;

	/**
	 * メモIDを取得します。
	 * 
	 * @return メモID
	 */
	public String getId() {
		return id;
	}

	/**
	 * メモIDを設定します。
	 * 
	 * @param id
	 *            メモID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * メモ内容を取得します。
	 * 
	 * @return メモ内容
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * メモ内容を設定します。
	 * 
	 * @param contents
	 *            メモ内容
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * 作成タイムスタンプを取得します。
	 * 
	 * @return 作成タイムスタンプ
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * 作成タイムスタンプを設定します。
	 * 
	 * @param createdOn
	 *            作成タイムスタンプ
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
