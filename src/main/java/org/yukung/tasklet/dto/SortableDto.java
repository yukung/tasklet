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

/**
 * <p>
 * タスク詳細画面の表示用Beanクラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class SortableDto {

	/** アクティビティID */
	private String id;

	/** アクティビティ名 */
	private String title;

	/** ソート順 */
	private String seq;

	/**
	 * アクティビティIDを取得します。
	 * 
	 * @return アクティビティID
	 */
	public String getId() {
		return id;
	}

	/**
	 * アクティビティIDを設定します。
	 * 
	 * @param id
	 *            アクティビティID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * アクティビティ名を取得します。
	 * 
	 * @return アクティビティ名
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * アクティビティ名を設定します。
	 * 
	 * @param title
	 *            アクティビティ名
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * ソート順を取得します。
	 * 
	 * @return ソート順
	 */
	public String getSeq() {
		return seq;
	}

	/**
	 * ソート順を設定します。
	 * 
	 * @param seq
	 *            ソート順
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
}
