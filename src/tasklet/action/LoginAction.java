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
import org.apache.struts.action.DynaActionForm;

import tasklet.bean.User;
import tasklet.dao.UserDao;
import tasklet.dao.UserDaoImpl;

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

		/* ログインモデルクラスを呼び出してログイン処理する（↓の処理を委譲する） */

		UserDao dao = new UserDaoImpl();
		User user = dao.findUser(userId);

		boolean isLogined = (user != null && userId.equals(user.getUserId()) && password.equals(user.getPassword()));
		HttpSession session = request.getSession();
		session.setAttribute("isLogined", isLogined);

		if (isLogined) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

}
