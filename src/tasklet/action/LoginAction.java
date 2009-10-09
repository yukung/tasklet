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
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import tasklet.bean.User;
import tasklet.service.accountService;
import tasklet.service.accountServiceImpl;

/**
 * ログイン処理を行うActionです。
 * @author Y.Ikeda
 */
public class LoginAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaActionForm loginForm = (DynaActionForm) form;

		String userId = (String) loginForm.get("userId");
		String password = (String) loginForm.get("password");

		accountService accountService = new accountServiceImpl();
		User user = accountService.login(userId, password);
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
