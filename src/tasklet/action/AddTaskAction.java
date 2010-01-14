/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */

package tasklet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tasklet.DataAccessException;
import tasklet.entity.Task;
import tasklet.entity.User;
import tasklet.form.AddTaskForm;

/**
 * @author Y.Ikeda
 *
 */
public class AddTaskAction extends AbstractAction {

	/* (非 Javadoc)
	 * @see tasklet.action.AbstractAction#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		if (!isTokenValid(request, true)) {
			return mapping.findForward("double");
		}

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return mapping.findForward("login");
		}

		AddTaskForm addTaskForm = (AddTaskForm) form;

		Task task = new Task();
		try {
			// 入力フォーム情報をタスクエンティティにマッピング
			PropertyUtils.copyProperties(task, addTaskForm);
		} catch (Exception e) {
			// 不整合の場合はシステム例外としてStrutsに投げる
			throw new DataAccessException(e.getMessage(), e);
		}

		// TODO TaskServiceの登録メソッドを呼んで処理する

		return null;
	}

}
