/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

/**
 * ログイン処理を行うActionです。
 *
 * @author Yusuke
 *
 */
public class LoginAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		DynaActionForm loginForm = (DynaActionForm) form;

		String userId = (String) loginForm.get("userId");
		String password = (String) loginForm.get("password");
		System.out.println(userId);
		if (password.equals("12341234")) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

}
