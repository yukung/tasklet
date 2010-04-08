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

import org.apache.struts.action.ActionForm;

/**
 * <p>
 * タスク詳細表示用アクションフォームです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class DetailForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	/** タスクID */
	private String id;

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
}
