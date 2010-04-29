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
 * ページ遷移処理用アクションフォームです。
 * </p>
 * </p>
 * 
 * @author yukung
 * 
 */
public class PaginationForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	/** 表示するページ番号 */
	private String pageNo;

	/**
	 * 表示するページ番号を取得します。
	 * 
	 * @return 表示するページ番号
	 */
	public String getPageNo() {
		return pageNo;
	}

	/**
	 * 表示するページ番号を設定します。
	 * 
	 * @param pageNo
	 *            表示するページ番号
	 */
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
}
