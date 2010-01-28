/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */

package tasklet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tasklet.DataAccessException;
import tasklet.entity.Task;
import tasklet.entity.User;
import tasklet.form.ModifyTaskForm;
import tasklet.service.TaskService;
import tasklet.service.TaskServiceImpl;

/**
 * タスク情報修正画面を表示するアクションです。
 *
 * @author Y.Ikeda
 *
 */
public class ModifyAction extends AbstractAction {

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

		ModifyTaskForm modifyTaskForm = (ModifyTaskForm) form;
		int taskId = Integer.parseInt(modifyTaskForm.getId());
		TaskService taskService = new TaskServiceImpl();
		Task task = taskService.getTask(taskId);

		try {
			// ActionFormにDBのデータをマッピングする
			BeanUtils.copyProperties(modifyTaskForm, task);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}

		saveToken(request);
		return mapping.findForward("success");
	}

}
