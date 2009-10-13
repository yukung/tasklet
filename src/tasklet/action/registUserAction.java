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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import tasklet.entity.User;
import tasklet.service.accountService;
import tasklet.service.accountServiceImpl;

/**
 * ユーザ登録を行うActionです。
 * 
 * @author Y.Ikeda
 */
public class registUserAction extends AbstractAction {

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
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaActionForm registUserForm = (DynaActionForm) form;

		User user = new User();
		BeanUtils.copyProperties(user, registUserForm);

		accountService service = new accountServiceImpl();
		boolean isSuccess = service.regist(user);
		if (isSuccess) {
			return mapping.findForward("success");
			// ユーザー登録しました画面に遷移
		} else {
			ActionMessages messages = new ActionMessages();
			ActionMessage message = new ActionMessage("errors.cancel");
			messages.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, messages);
			return mapping.getInputForward();
		}
		// DB登録サービスのインスタンス化
		// DB登録
		// ActionForward("success")
		// 重複ユーザーIDの時はActionMessageにエラーメッセージ
		// ActionInputForward
	}

}
