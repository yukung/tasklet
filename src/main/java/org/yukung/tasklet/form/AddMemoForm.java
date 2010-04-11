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

import org.apache.struts.validator.ValidatorForm;

/**
 * <p>
 * 新規メモ追加画面用アクションフォームです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class AddMemoForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;

	/** タスクID */
	private String taskId;

	/** メモ内容 */
	private String contents;

	/**
	 * タスクIDを取得します。
	 * @return タスクID
	 */
	public String getTaskId() {
	    return taskId;
	}

	/**
	 * タスクIDを設定します。
	 * @param taskId タスクID
	 */
	public void setTaskId(String taskId) {
	    this.taskId = taskId;
	}

	/**
	 * メモ内容を取得します。
	 * @return メモ内容
	 */
	public String getContents() {
	    return contents;
	}

	/**
	 * メモ内容を設定します。
	 * @param contents メモ内容
	 */
	public void setContents(String contents) {
	    this.contents = contents;
	}

}
