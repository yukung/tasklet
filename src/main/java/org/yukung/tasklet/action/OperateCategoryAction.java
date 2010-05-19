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
package org.yukung.tasklet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.EventDispatchAction;

/**
 * <p>
 * カテゴリ操作アクションです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class OperateCategoryAction extends EventDispatchAction {

	/** 名前変更アクションを呼び出す場合のforward先の論理名 */
	private static final String RENAME = "rename";
	/** 削除アクションを呼び出す場合のforward先の論理名 */
	private static final String DELETE = "delete";
	/** GETメソッドで直接呼ばれた場合のforward先の論理名 */
	private static final String ERROR = "error";

	/**
	 * <p>
	 * リネームアクションにフォワードします。
	 * </p>
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return Forwardの定義名
	 * @throws Exception
	 */
	public ActionForward rename(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward(RENAME);
	}

	/**
	 * <p>
	 * 削除アクションにフォワードします。
	 * </p>
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return Forwardの定義名
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward(DELETE);
	}

	/**
	 * <p>
	 * エラー画面に遷移します。
	 * </p>
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return エラー画面
	 * @throws Exception
	 */
	public ActionForward error(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward(ERROR);
	}
}
