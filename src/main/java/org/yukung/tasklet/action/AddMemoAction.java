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
import org.yukung.tasklet.dto.MemoDto;
import org.yukung.tasklet.dto.TaskDto;
import org.yukung.tasklet.entity.Memo;
import org.yukung.tasklet.exception.DataAccessException;
import org.yukung.tasklet.exception.TaskletException;
import org.yukung.tasklet.form.AddMemoForm;
import org.yukung.tasklet.service.TaskService;
import org.yukung.tasklet.service.impl.TaskServiceImpl;
import org.yukung.tasklet.utils.CalculateUtil;

/**
 * <p>
 * 新規タスク登録アクションです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class AddMemoAction extends AbstractAction {

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
		AddMemoForm addMemoForm = (AddMemoForm) form;
		Memo memo = new Memo();
		try {
			BeanUtils.copyProperties(memo, addMemoForm);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}

		TaskService taskService = new TaskServiceImpl();
		saveToken(request);
		if (!isCancelled(request)) {
			try {
				taskService.add(memo);
			} catch (TaskletException e) {
				ActionMessages errors = new ActionMessages();
				ActionMessage error = new ActionMessage("errors.insert");
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				saveErrors(request, errors);
				return mapping.getInputForward();
			}
			DetailDto task = taskService.getTask(memo.getTaskId());
			request.setAttribute("task", task);
			List<MemoDto> memos = taskService.getMemos(memo.getTaskId());
			request.setAttribute("memos", memos);
			addMemoForm.setContents(""); // メモ入力欄を初期化
			return mapping.findForward(SUCCESS);
		} else {
			// アクティビティIDとアクティビティ名の取得
			int activityId = taskService.getActivityId(memo.getTaskId());
			ActivityDto activity = taskService.getActivityInfo(activityId);
			request.setAttribute("activity", activity);

			// タスク一覧を再取得
			List<TaskDto> tasks = taskService.show(activityId);
			int completed = CalculateUtil.countCompleted(tasks);
			Boolean onlyIncompleted = (Boolean) request.getSession(true)
					.getAttribute("onlyIncompleted");
			if (tasks.size() == 0
					|| (onlyIncompleted.booleanValue() && tasks.size() == completed)) {
				ActionMessages messages = new ActionMessages();
				ActionMessage message = new ActionMessage("messages.notask");
				messages.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, messages);
			} else {
				request.setAttribute("tasks", tasks);
			}
			return mapping.findForward(CANCEL);
		}
	}
}
