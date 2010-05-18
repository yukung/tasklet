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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.EventDispatchAction;
import org.yukung.tasklet.entity.Category;
import org.yukung.tasklet.exception.TaskletException;
import org.yukung.tasklet.form.ModifyCategoryForm;
import org.yukung.tasklet.service.ActivityService;
import org.yukung.tasklet.service.impl.ActivityServiceImpl;

/**
 * <p>
 * カテゴリ操作アクションです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class OperateCategoryAction extends EventDispatchAction {

	/** アクションが成功した場合に使われるforward先の論理名 */
	private static final String SUCCESS = "success";
	/** アクションがエラーとなった場合に使われるforward先の論理名 */
	private static final String ERROR = "error";
	/** アクションが2度呼ばれた場合に使われるforward先の論理名 */
	private static final String DOUBLE = "double";

	/**
	 * <p>
	 * カテゴリ名をリネームします。
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

		// ダブルポストのチェック
		if (!isTokenValid(request, true)) {
			return mapping.findForward(DOUBLE);
		}
		// ActionFormをEntityにマッピング
		ModifyCategoryForm modifyCategoryForm = (ModifyCategoryForm) form;
		Category category = new Category();
		BeanUtils.copyProperties(category, modifyCategoryForm);

		// サービス呼び出し
		ActivityService activityService = new ActivityServiceImpl();
		try {
			activityService.updateCategory(category);
			ActionMessages messages = new ActionMessages();
			ActionMessage message = new ActionMessage("messages.update");
			messages.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, messages);
		} catch (TaskletException e) {
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage("errors.update");
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			return mapping.getInputForward();
		}

		saveToken(request);
		return mapping.findForward(SUCCESS);
	}

	/**
	 * <p>
	 * カテゴリを削除します。
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

		return null;
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
