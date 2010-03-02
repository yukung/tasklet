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
 * メモ情報を表すBeanクラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class Memo {

	/** ID */
	private int id;

	/** タスクID */
	private int taskId;

	/** ソート順 */
	private int seq;

	/** 内容 */
	private String contents;

	/** 作成タイムスタンプ */
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
	 * タスクIDを取得します。
	 * 
	 * @return タスクID
	 */
	public int getTaskId() {
		return taskId;
	}

	/**
	 * タスクIDを設定します。
	 * 
	 * @param taskId
	 *            タスクID
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
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
	 * 内容を取得します。
	 * 
	 * @return 内容
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * 内容を設定します。
	 * 
	 * @param contents
	 *            内容
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * 作成タイムスタンプを取得します。
	 * 
	 * @return 作成タイムスタンプ
	 */
	public Timestamp getCreatedOn() {
		return createdOn;
	}

	/**
	 * 作成タイムスタンプを設定します。
	 * 
	 * @param createdOn
	 *            作成タイムスタンプ
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
