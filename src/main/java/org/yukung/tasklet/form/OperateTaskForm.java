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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * タスク操作画面用アクションフォームです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class OperateTaskForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	/** アクティビティID */
	private String activityId;

	/** 選択したタスク */
	private String[] checked;

	/**
	 * アクティビティIDを取得します。
	 * 
	 * @return アクティビティID
	 */
	public String getActivityId() {
		return activityId;
	}

	/**
	 * アクティビティIDを設定します。
	 * 
	 * @param activityId
	 *            アクティビティID
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	/**
	 * 選択したタスクを取得します。
	 * 
	 * @return 選択したタスク
	 */
	public String[] getChecked() {
		return checked;
	}

	/**
	 * 選択したタスクを設定します。
	 * 
	 * @param checked
	 *            選択したタスク
	 */
	public void setChecked(String[] checked) {
		this.checked = checked;
	}

	/**
	 * プロパティを初期化します。
	 */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		checked = null;
	}
}
