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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.yukung.tasklet.dto.ActivityDto;
import org.yukung.tasklet.entity.Category;
import org.yukung.tasklet.entity.User;
import org.yukung.tasklet.form.ModifyActivityForm;
import org.yukung.tasklet.service.ActivityService;
import org.yukung.tasklet.service.TaskService;
import org.yukung.tasklet.service.impl.ActivityServiceImpl;
import org.yukung.tasklet.service.impl.TaskServiceImpl;

/**
 * <p>
 * アクティビティ修正画面を表示するアクションです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class ReviseAction extends AbstractAction {

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

		// ユーザ情報の取得
		User user = (User) request.getSession().getAttribute("user");

		// カテゴリ一覧の取得
		ActivityService activityService = new ActivityServiceImpl();
		Map<String, String> categories = activityService
				.getCategoriesWithUncategorized(user.getId());

		// カテゴリ一覧のセット
		ModifyActivityForm modifyActivityForm = (ModifyActivityForm) form;
		modifyActivityForm.setCategories(categories);
		int activityId = Integer.parseInt(modifyActivityForm.getActivityId());
		Category category = activityService.getCategory(activityId);
		modifyActivityForm.setCategoryId(String.valueOf(category.getId())); // カテゴリの既定値

		// アクティビティ名のセット
		TaskService taskService = new TaskServiceImpl();
		ActivityDto activity = taskService.getActivityInfo(activityId, user
				.getId());
		modifyActivityForm.setTitle(activity.getTitle());

		saveToken(request);
		return mapping.findForward(SUCCESS);
	}

}
