/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import tasklet.entity.User;
import tasklet.service.AccountService;
import tasklet.service.AccountServiceImpl;

/**
 * ログイン処理を行うActionです。
 *
 * @author Y.Ikeda
 */
public class LoginAction extends AbstractAction {

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

		DynaActionForm loginForm = (DynaActionForm) form;

		String userName = (String) loginForm.get("userName");
		String password = (String) loginForm.get("password");

		AccountService AccountService = new AccountServiceImpl();
		User user = AccountService.login(userName, password);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return mapping.findForward("success");
		} else {
			ActionMessages messages = new ActionMessages();
			ActionMessage message = new ActionMessage("errors.login");
			messages.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, messages);
			return mapping.getInputForward();
		}
	}
}
