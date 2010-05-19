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
import org.yukung.tasklet.entity.Category;
import org.yukung.tasklet.exception.DataAccessException;
import org.yukung.tasklet.exception.TaskletException;
import org.yukung.tasklet.form.ModifyCategoryForm;
import org.yukung.tasklet.service.ActivityService;
import org.yukung.tasklet.service.impl.ActivityServiceImpl;

/**
 * <p>
 * カテゴリリネームアクションです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class RenameCategoryAction extends AbstractAction {

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.action.AbstractAction#doExecute(org.apache.struts.
	 * action.ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		// ダブルポストのチェック
		if (!isTokenValid(request, true)) {
			return mapping.findForward(DOUBLE);
		}
		// ActionFormをEntityにマッピング
		ModifyCategoryForm modifyCategoryForm = (ModifyCategoryForm) form;
		Category category = new Category();
		try {
			BeanUtils.copyProperties(category, modifyCategoryForm);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}

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

}
