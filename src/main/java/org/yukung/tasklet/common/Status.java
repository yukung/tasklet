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
 * タスクの状態を定義する列挙型です。
 * </p>
 * 
 * @author yukung
 * 
 */
public enum Status {

	/** 未着手 */
	YET(0, "未着手"),
	/** 着手 */
	PROCEED(1, "着手"),
	/** 完了 */
	FINISH(2, "完了");

	final int code;
	final String statusName;

	private Status(int code, String statusName) {
		this.code = code;
		this.statusName = statusName;
	}

	/**
	 * <p>
	 * DB定義と対応するコード値を取得します。
	 * </p>
	 * 
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * <p>
	 * 状態の名称を取得します。
	 * </p>
	 * 
	 * @return 状態の名称
	 */
	public String getStatusName() {
		return statusName;
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
