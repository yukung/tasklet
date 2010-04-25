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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.EventDispatchAction;
import org.yukung.tasklet.dto.ActivityDto;
import org.yukung.tasklet.dto.TaskDto;
import org.yukung.tasklet.exception.TaskletException;
import org.yukung.tasklet.form.OperateTaskForm;
import org.yukung.tasklet.service.TaskService;
import org.yukung.tasklet.service.impl.TaskServiceImpl;

/**
 * <p>
 * タスク操作アクションです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class OperateTaskAction extends EventDispatchAction {

	/** アクションが成功した場合に使われるforward先の論理名 */
	private static final String SUCCESS = "success";

	/**
	 * <p>
	 * タスクの状態を完了にします。
	 * </p>
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return Forwardの定義名
	 * @throws Exception
	 */
	public ActionForward complete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		OperateTaskForm operateTaskForm = (OperateTaskForm) form;
		int activityId = Integer.parseInt(operateTaskForm.getActivityId());
		String[] checked = operateTaskForm.getChecked();

		TaskService taskService = new TaskServiceImpl();

		try {
			taskService.complete(activityId, checked);
		} catch (TaskletException e) {
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage("errors.update");
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
		}

		// アクティビティIDとアクティビティ名の取得
		ActivityDto activity = taskService.getActivityInfo(activityId);
		request.setAttribute("activity", activity);

		// タスク一覧を再取得
		List<TaskDto> tasks = taskService.show(activityId);
		if (tasks.size() == 0) {
			ActionMessages messages = new ActionMessages();
			ActionMessage message = new ActionMessage("messages.notask");
			messages.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, messages);
		} else {
			request.setAttribute("tasks", tasks);
		}

		// チェックボックスの初期化
		operateTaskForm.setChecked(null);

		saveToken(request);
		return mapping.findForward(SUCCESS);
	}

	/**
	 * <p>
	 * タスクを削除します。
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
	 * タスク一覧を表示します。
	 * </p>
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return Forwardの定義名
	 * @throws Exception
	 */
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		OperateTaskForm operateTaskForm = (OperateTaskForm) form;
		int activityId = Integer.parseInt(operateTaskForm.getActivityId());
		TaskService taskService = new TaskServiceImpl();

		// アクティビティIDとアクティビティ名の取得
		ActivityDto activity = taskService.getActivityInfo(activityId);
		request.setAttribute("activity", activity);

		// タスク一覧を再取得
		List<TaskDto> tasks = taskService.show(activityId);
		if (tasks.size() == 0) {
			ActionMessages messages = new ActionMessages();
			ActionMessage message = new ActionMessage("messages.notask");
			messages.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, messages);
		} else {
			request.setAttribute("tasks", tasks);
		}

		saveToken(request);
		return mapping.findForward(SUCCESS);
	}

}
