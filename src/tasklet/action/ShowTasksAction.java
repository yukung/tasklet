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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import tasklet.entity.Activity;
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
		int activityId = Integer.parseInt(addTasksForm.getActivityId());

		TaskService taskService = new TaskServiceImpl();
		String title = taskService.getActivityTitle(activityId);
		List<Task> tasks = taskService.show(activityId);
		if (tasks.size() == 0) {
			ActionMessages messages = new ActionMessages();
			ActionMessage message = new ActionMessage("messages.notask");
			messages.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, messages);
		} else {
			request.setAttribute("tasks", tasks);
		}
		// アクティビティ情報の再取得
		Activity activity = new Activity();
		activity.setId(activityId);
		activity.setTitle(title);
		request.setAttribute("activity", activity);

		saveToken(request);
		return mapping.findForward("success");
	}

}
