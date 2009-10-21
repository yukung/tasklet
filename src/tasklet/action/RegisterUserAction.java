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

import tasklet.common.TaskletException;
import tasklet.entity.User;
import tasklet.service.accountService;
import tasklet.service.accountServiceImpl;

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
			// 入力情報をユーザーエンティティにマッピング
			BeanUtils.copyProperties(user, registerUserForm);
		} catch (Exception e) {
			// 入力項目とDB項目が合わない場合はエラー画面へ
			throw new TaskletException(e.getMessage(), e);
		}

		accountService service = new accountServiceImpl();
		int resultCount = service.register(user);
		if (resultCount > 0) {
			return mapping.findForward("success");
		} else if (resultCount == -1) {
			// ユーザID重複
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage("errors.already");
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			return mapping.getInputForward();
		} else if (resultCount == -2) {
			// DB項目あふれ
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage("errors.overflow");
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			return mapping.getInputForward();
		} else {
			// その他SQLエラー
			throw new TaskletException();
		}
	}
}
