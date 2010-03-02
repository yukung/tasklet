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
package org.yukung.tasklet.common;

/**
 * <p>
 * タスクの優先度を定義する列挙型です。
 * </p>
 * 
 * @author yukung
 * 
 */
public enum Priority {

	/** 優先度 高 */
	HIGH(1, "高"),
	/** 優先度 普通 */
	NORMAL(2, "普通"),
	/** 優先度 低 */
	LOW(3, "低"),
	/** 優先度なし */
	NOTHING(9, "なし");

	final int code;
	final String priorityName;

	private Priority(int code, String priorityName) {
		this.code = code;
		this.priorityName = priorityName;
	}

	/**
	 * <p>
	 * DB定義と対応するコード値を取得します。
	 * </p>
	 * 
	 * @return 優先度コード
	 */
	public int getCode() {
		return code;
	}

	/**
	 * <p>
	 * 優先度の名称を取得します。
	 * </p>
	 * 
	 * @return 優先度の名称
	 */
	public String getPriorityName() {
		return priorityName;
	}

	/**
	 * <p>
	 * 列挙要素自身の文字列表現を取得します。
	 * </p>
	 * 
	 * @return 列挙要素名
	 */
	public String getValue() {
		return name();
	}
}
