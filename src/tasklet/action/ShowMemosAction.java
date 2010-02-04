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
import org.apache.struts.action.DynaActionForm;

import tasklet.entity.Memo;
import tasklet.entity.User;
import tasklet.service.TaskService;
import tasklet.service.TaskServiceImpl;

/**
 * メモ詳細を表示するアクションです。
 *
 * @author Y.Ikeda
 *
 */
public class ShowMemosAction extends AbstractAction {

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

		DynaActionForm showMemosForm = (DynaActionForm) form;
		int taskId = Integer.parseInt(showMemosForm.get("taskId").toString());

		TaskService taskService = new TaskServiceImpl();
		List<Memo> memos = taskService.getMemos(taskId);
		String title = taskService.getTask(taskId).getTitle();
		request.setAttribute("memos", memos);
		request.setAttribute("taskId", String.valueOf(taskId));
		request.setAttribute("title", title);

		saveToken(request);
		return mapping.findForward("success");
	}

}
