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

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import org.yukung.tasklet.utils.DateConverter;

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
	private String id;

	/** 実績時間 */
	private String actualTime;

	/** 完了フラグ */
	private boolean finished;

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
	 * 完了フラグを取得します。
	 * 
	 * @return 完了フラグ
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * 完了フラグを設定します。
	 * 
	 * @param finished
	 *            完了フラグ
	 */
	public void setFinished(boolean isFinished) {
		this.finished = isFinished;
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

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action
	 * .ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		finished = false;
	}
}
