/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */

package tasklet.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import tasklet.DataAccessException;
import tasklet.TaskletException;
import tasklet.entity.Activity;
import tasklet.entity.Memo;
import tasklet.entity.Task;
import tasklet.entity.User;
import tasklet.form.AddMemoForm;
import tasklet.service.TaskService;
import tasklet.service.TaskServiceImpl;

/**
 * メモを追加するアクションです。
 *
 * @author Y.Ikeda
 *
 */
public class AddMemoAction extends AbstractAction {

	/*
	 * (非 Javadoc)
	 *
	 * @see tasklet.action.AbstractAction#doExecute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return mapping.findForward("login");
		}

		if (!isTokenValid(request, true)) {
			return mapping.findForward("double");
		}

		AddMemoForm addMemoForm = (AddMemoForm) form;
		Memo memo = new Memo();
		try {
			BeanUtils.copyProperties(memo, addMemoForm);
		} catch (Exception e) {
			// 不整合の場合はシステム例外としてStrutsに投げる
			throw new DataAccessException(e.getMessage(), e);
		}

		TaskService taskService = new TaskServiceImpl();
		saveToken(request);

		if (!isCancelled(request)) {
			try {
				taskService.add(memo);
			} catch (TaskletException e) {
				ActionMessages errors = new ActionMessages();
				ActionMessage error = new ActionMessage(e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				saveErrors(request, errors);
				return mapping.getInputForward();
			}
			List<Memo> memos = taskService.getMemos(memo.getTaskId());
			String title = taskService.getTask(memo.getTaskId()).getTitle();
			request.setAttribute("memos", memos);
			request.setAttribute("taskId", String.valueOf(memo.getTaskId()));
			request.setAttribute("title", title);
			addMemoForm.setContents(""); // メモ入力欄を初期化

			return mapping.findForward("success");
		} else {
			// キャンセルボタン押下の場合はタスク一覧を再取得
			int activityId = taskService.getActivityId(memo.getTaskId());
			List<Task> tasks = taskService.show(activityId);
			request.setAttribute("tasks", tasks);
			String title = taskService.getActivityTitle(activityId);
			Activity activity = new Activity();
			activity.setId(activityId);
			activity.setTitle(title);
			request.setAttribute("activity", activity);

			return mapping.findForward("cancel");
		}
	}

}
