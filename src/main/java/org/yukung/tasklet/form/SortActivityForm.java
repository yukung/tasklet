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
 * アクティビティソート用アクションフォームです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class SortActivityForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;

	/** ソートされたアクティビティID（カンマ区切り） */
	private String sortId;

	/**
	 * ソートされたアクティビティID（カンマ区切り）を取得します。
	 * 
	 * @return ソートされたアクティビティID（カンマ区切り）
	 */
	public String getSortId() {
		return sortId;
	}

	/**
	 * ソートされたアクティビティID（カンマ区切り）を設定します。
	 * 
	 * @param sortId
	 *            ソートされたアクティビティID（カンマ区切り）
	 */
	public void setSortId(String sortId) {
		this.sortId = sortId;
	}
}
