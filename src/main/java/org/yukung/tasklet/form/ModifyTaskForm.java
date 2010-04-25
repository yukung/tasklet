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
import org.apache.struts.validator.ValidatorActionForm;
import org.yukung.tasklet.common.Priority;
import org.yukung.tasklet.utils.DateConverter;
import org.yukung.tasklet.utils.EnumConverter;

/**
 * <p>
 * タスク修正画面用アクションフォームです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class ModifyTaskForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	/** タスクID */
	private String id;

	/** タスク名 */
	private String title;

	/** 優先度 */
	private Priority priority;

	/** 期限 */
	private String period;

	/** 見積時間 */
	private String estimatedTime;

	/**
	 * 文字列 → 列挙型へのコンバータを登録
	 */
	static {
		EnumConverter enumConverter = new EnumConverter();
		ConvertUtils.register(enumConverter, Priority.class);
	}

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
	public String getId() {
		return id;
	}

	/**
	 * タスクIDを設定します。
	 * 
	 * @param id
	 *            タスクID
	 */
	public void setId(String id) {
		this.id = id;
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
	 * タスク情報修正画面の優先度プルダウンの要素を取得します。
	 * 
	 * @return タスク情報修正画面の優先度プルダウンの要素
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
		return period.replaceAll("-", "/");
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
}
