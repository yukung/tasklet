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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.yukung.tasklet.dto.ActivityDto;
import org.yukung.tasklet.dto.TaskDto;
import org.yukung.tasklet.form.ToggleTasksForm;
import org.yukung.tasklet.service.TaskService;
import org.yukung.tasklet.service.impl.TaskServiceImpl;
import org.yukung.tasklet.utils.CalculateUtil;

/**
 * <p>
 * タスク一覧表示切替アクションです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class ToggleTasksAction extends AbstractAction {

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

		ToggleTasksForm toggleTasksForm = (ToggleTasksForm) form;
		int activityId = Integer.parseInt(toggleTasksForm.getActivityId());

		boolean onlyIncompleted = toggleOnlyIncompleted(request);

		// アクティビティIDとアクティビティ名の取得
		TaskService taskService = new TaskServiceImpl();
		ActivityDto activity = taskService.getActivityInfo(activityId);
		request.setAttribute("activity", activity);

		// タスク情報の取得
		List<TaskDto> tasks = taskService.show(activityId);
		int completed = CalculateUtil.countCompleted(tasks);
		if (tasks.size() == 0
				|| (onlyIncompleted && tasks.size() == completed)) {
			ActionMessages messages = new ActionMessages();
			ActionMessage message = new ActionMessage("messages.notask");
			messages.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, messages);
		} else {
			request.setAttribute("tasks", tasks);
		}

		return mapping.findForward(SUCCESS);
	}

	/**
	 * <p>
	 * 完了タスクの表示モードをトグルします。
	 * </p>
	 * 
	 * @param request
	 * @return 表示モード。Trueの場合完了タスクを非表示にします。<br>
	 *         Falseの場合完了タスクを表示します。
	 */
	private boolean toggleOnlyIncompleted(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Object onlyIncompleted = session.getAttribute("onlyIncompleted");
		if (onlyIncompleted != null && onlyIncompleted.equals(Boolean.TRUE)) {
			session.setAttribute("onlyIncompleted", Boolean.FALSE);
			return false;
		} else {
			session.setAttribute("onlyIncompleted", Boolean.TRUE);
			return true;
		}
	}
}
