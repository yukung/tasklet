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

import java.util.Map;

import org.apache.struts.action.ActionForm;

/**
 * <p>
 * アクティビティ修正用アクションフォームです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class ModifyActivityForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	/** アクティビティID */
	private String activityId;

	/** アクティビティ名 */
	private String title;

	/** カテゴリID */
	private String categoryId;

	/** カテゴリを選択するプルダウン */
	private Map<String, String> categories;

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
	 * カテゴリIDを取得します。
	 * 
	 * @return カテゴリID
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * カテゴリIDを設定します。
	 * 
	 * @param categoryId
	 *            カテゴリID
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * カテゴリを選択するプルダウンを取得します。
	 * 
	 * @return カテゴリを選択するプルダウン
	 */
	public Map<String, String> getCategories() {
		return categories;
	}

	/**
	 * カテゴリを選択するプルダウンを設定します。
	 * 
	 * @param categories
	 *            カテゴリを選択するプルダウン
	 */
	public void setCategories(Map<String, String> categories) {
		this.categories = categories;
	}
}
