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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.yukung.tasklet.dto.ActivityDto;
import org.yukung.tasklet.dto.DetailDto;
import org.yukung.tasklet.dto.TaskDto;
import org.yukung.tasklet.entity.Memo;
import org.yukung.tasklet.entity.Task;
import org.yukung.tasklet.exception.DataAccessException;
import org.yukung.tasklet.exception.TaskletException;
import org.yukung.tasklet.form.UpdateTaskForm;
import org.yukung.tasklet.service.TaskService;
import org.yukung.tasklet.service.impl.TaskServiceImpl;
import org.yukung.tasklet.utils.CalculateUtil;

/**
 * <p>
 * タスク情報更新アクションです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class UpdateTaskAction extends AbstractAction {

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
			return mapping.findForward("double");
		}

		// ActionFormをEntityにマッピング
		UpdateTaskForm updateTaskForm = (UpdateTaskForm) form;
		Task task = new Task();
		Memo memo = new Memo();
		try {
			BeanUtils.copyProperties(task, updateTaskForm);
			BeanUtils.copyProperty(memo, "taskId", updateTaskForm.getId());
			BeanUtils.copyProperties(memo, updateTaskForm);
		} catch (Exception e) {
			// 不整合の場合はシステム例外としてStrutsに投げる
			throw new DataAccessException(e.getMessage(), e);
		}

		TaskService taskService = new TaskServiceImpl();
		if (!isCancelled(request)) {
			try {
				taskService.update(task, memo);
			} catch (TaskletException e) {
				ActionMessages errors = new ActionMessages();
				ActionMessage error = new ActionMessage("errors.update");
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				saveErrors(request, errors);
				DetailDto detail = taskService.getTask(task.getId());
				request.setAttribute("task", detail);
				saveToken(request);
				return mapping.getInputForward();
			}
		}

		// アクティビティIDとアクティビティ名の取得
		int activityId = taskService.getActivityId(task.getId());
		ActivityDto activity = taskService.getActivityInfo(activityId);
		request.setAttribute("activity", activity);

		// タスク一覧を再取得
		List<TaskDto> tasks = taskService.show(activityId);
		int completed = CalculateUtil.countCompleted(tasks);
		Boolean showsCompleted = (Boolean) request.getSession(true)
				.getAttribute("showsCompleted");
		if (tasks.size() == 0
				|| (showsCompleted.booleanValue() && tasks.size() == completed)) {
			ActionMessages messages = new ActionMessages();
			ActionMessage message = new ActionMessage("messages.notask");
			messages.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, messages);
		} else {
			request.setAttribute("tasks", tasks);
		}

		return mapping.findForward(SUCCESS);
	}

}
