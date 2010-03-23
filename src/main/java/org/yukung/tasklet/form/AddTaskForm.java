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

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.yukung.tasklet.common.Priority;

/**
 * <p>
 * 新規タスク追加画面用アクションフォームです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class AddTaskForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;

	/** アクティビティID */
	private String activityId;

	/** タスク名 */
	private String title;

	/** 優先度 */
	private Priority priority;

	/** 期限 */
	private String period;

	/** 見積時間 */
	private String estimatedTime;

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
	 * タスク名を取得します。
	 * 
	 * @return タスク名
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * タスク名を設定します。
	 * 
	 * @param title
	 *            タスク名
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 優先度を取得します。
	 * 
	 * @return 優先度
	 */
	public Priority getPriority() {
		return priority;
	}

	/**
	 * タスク追加画面の優先度プルダウンの要素を取得します。
	 * 
	 * @return 優先度プルダウンの要素
	 */
	public Priority[] getPriorities() {
		return Priority.values();
	}

	/**
	 * 優先度を設定します。
	 * 
	 * @param priority
	 *            優先度
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	/**
	 * 期限を取得します。
	 * 
	 * @return 期限
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * 期限を設定します。
	 * 
	 * @param period
	 *            期限
	 */
	public void setPeriod(String period) {
		this.period = period;
	}

	/**
	 * 見積時間を取得します。
	 * 
	 * @return 見積時間
	 */
	public String getEstimatedTime() {
		return estimatedTime;
	}

	/**
	 * 見積時間を設定します。
	 * 
	 * @param estimatedTime
	 *            見積時間
	 */
	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action
	 * .ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		setPriority(Priority.NOTHING); // 優先度を「なし」に設定
	}

}
