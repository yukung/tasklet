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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import tasklet.entity.Task;
import tasklet.entity.User;
import tasklet.form.AddTaskForm;
import tasklet.service.TaskService;
import tasklet.service.TaskServiceImpl;

/**
 * タスク一覧を表示するアクションです。
 *
 * @author Y.Ikeda
 *
 */
public class ShowTasksAction extends AbstractAction {

	/*
	 * (非 Javadoc)
	 *
	 * @seetasklet.action.AbstractAction#doExecute(org.apache.struts.action.
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

		AddTaskForm addTasksForm = (AddTaskForm) form;
		int activityId = Integer.parseInt(addTasksForm.getActivityId().toString());

		TaskService taskService = new TaskServiceImpl();
		List<Task> tasks = taskService.show(activityId);
		request.setAttribute("tasks", tasks);
		String title = taskService.getActivityTitle(activityId);
		addTasksForm.setActivityId(Integer.toString(activityId));
		addTasksForm.setActivityTitle(title);

		saveToken(request);
		return mapping.findForward("success");
	}

}
