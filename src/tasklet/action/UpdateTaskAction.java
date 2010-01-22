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
import tasklet.form.DetailTaskForm;
import tasklet.service.TaskService;
import tasklet.service.TaskServiceImpl;

/**
 * @author Y.Ikeda
 *
 */
public class UpdateTaskAction extends AbstractAction {

	/* (非 Javadoc)
	 * @see tasklet.action.AbstractAction#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
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

		boolean isCancelled = false;
		if (isCancelled(request)) {
			isCancelled = true;
		}

		DetailTaskForm detailTaskForm = (DetailTaskForm) form;
		Task task = new Task();
		Memo memo = new Memo();
		try {
			// 入力フォーム情報をエンティティにマッピング
			BeanUtils.copyProperty(task, "id", detailTaskForm.getTaskId());
			BeanUtils.copyProperties(task, detailTaskForm);
			BeanUtils.copyProperties(memo, detailTaskForm);
		} catch (Exception e) {
			// 不整合の場合はシステム例外としてStrutsに投げる
			throw new DataAccessException(e.getMessage(), e);
		}

		TaskService taskService = new TaskServiceImpl();
		if (!isCancelled) {
			try {
				taskService.update(task, memo);
			} catch (TaskletException e) {
				ActionMessages errors = new ActionMessages();
				ActionMessage error = new ActionMessage(e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				saveErrors(request, errors);
				return mapping.getInputForward();
			}
		}

		// タスク一覧を再取得
		List<Task> tasks = taskService.show(task.getActivityId());
		request.setAttribute("tasks", tasks);
		String title = taskService.getActivityTitle(task.getActivityId());
		Activity activity = new Activity();
		activity.setId(task.getActivityId());
		activity.setTitle(title);
		request.setAttribute("activity", activity);

		if (isCancelled) {
			return mapping.findForward("cancel");
		} else {
			saveToken(request);
			return mapping.findForward("success");
		}
	}

}
