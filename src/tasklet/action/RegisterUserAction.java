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

import tasklet.common.DataAccessException;
import tasklet.common.TaskletException;
import tasklet.entity.User;
import tasklet.service.AccountService;
import tasklet.service.AccountServiceImpl;

/**
 * ユーザ登録を行うActionです。
 *
 * @author Y.Ikeda
 */
public class RegisterUserAction extends AbstractAction {

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

		DynaActionForm registerUserForm = (DynaActionForm) form;

		User user = new User();
		try {
			// 入力フォーム情報をユーザエンティティにマッピング
			BeanUtils.copyProperties(user, registerUserForm);
		} catch (Exception e) {
			// 不整合の場合はシステム例外としてStrutsに投げる
			throw new DataAccessException(e.getMessage(), e);
		}

		AccountService service = new AccountServiceImpl();
		try {
			service.register(user);
		} catch (TaskletException e) {
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(e.getMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			return mapping.getInputForward();
		}
		return mapping.findForward("success");

		// TODO ２重送信防止およびダブルポスト防止のロジックを入れる（アクションの基底クラスに入れるべき？）
	}
}
