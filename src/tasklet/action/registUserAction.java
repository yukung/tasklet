/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
			HttpServletRequest request, HttpServletResponse response) {
		// ActionFormの取得
		// DB登録サービスのインスタンス化
		// DB登録
		// ActionForward("success")
		// 重複ユーザーIDの時はActionMessageにエラーメッセージ
		// ActionInputForward
	}

}
