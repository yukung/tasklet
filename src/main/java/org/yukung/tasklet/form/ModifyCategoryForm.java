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

import org.apache.struts.validator.ValidatorForm;

/**
 * <p>
 * カテゴリ編集画面用アクションフォームです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class ModifyCategoryForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;

	/** カテゴリを選択するプルダウン */
	private Map<String, String> categories;

	/** カテゴリID */
	private String id;

	/** カテゴリ名 */
	private String name;

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

	/**
	 * カテゴリIDを取得します。
	 * 
	 * @return カテゴリID
	 */
	public String getId() {
		return id;
	}

	/**
	 * カテゴリIDを設定します。
	 * 
	 * @param id
	 *            カテゴリID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * カテゴリ名を取得します。
	 * 
	 * @return カテゴリ名
	 */
	public String getName() {
		return name;
	}

	/**
	 * カテゴリ名を設定します。
	 * 
	 * @param name
	 *            カテゴリ名
	 */
	public void setName(String name) {
		this.name = name;
	}

}
