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
package org.yukung.tasklet.form;

import java.sql.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.struts.validator.ValidatorForm;
import org.yukung.tasklet.util.DateConverter;

/**
 * <p>
 * タスク情報更新用アクションフォームです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class UpdateTaskForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;

	/** タスクID */
	private String taskId;

	/** 実績時間 */
	private String actualTime;

	/** 内容 */
	private String contents;

	/**
	 * 文字列 → Date型へのコンバータを登録
	 */
	static {
		DateConverter dateConverter = new DateConverter();
		ConvertUtils.register(dateConverter, Date.class);
	}

	/**
	 * タスクIDを取得します。
	 * 
	 * @return タスクID
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * タスクIDを設定します。
	 * 
	 * @param taskId
	 *            タスクID
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * 実績時間を取得します。
	 * 
	 * @return 実績時間
	 */
	public String getActualTime() {
		return actualTime;
	}

	/**
	 * 実績時間を設定します。
	 * 
	 * @param actualTime
	 *            実績時間
	 */
	public void setActualTime(String actualTime) {
		this.actualTime = actualTime;
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
}
