/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */

package tasklet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tasklet.entity.Task;
import tasklet.entity.User;
import tasklet.form.DetailTaskForm;
import tasklet.service.TaskService;
import tasklet.service.TaskServiceImpl;

/**
 * タスク詳細を表示するアクションです。
 *
 * @author Y.Ikeda
 *
 */
public class ShowDetailAction extends AbstractAction {

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

		DetailTaskForm detailTaskForm = (DetailTaskForm) form;
		int taskId = Integer.parseInt(detailTaskForm.getTaskId());

		TaskService taskService = new TaskServiceImpl();
		Task task = taskService.getTask(taskId);
		request.setAttribute("task", task);

		saveToken(request);
		return mapping.findForward("success");
	}

}
