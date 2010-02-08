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
import org.apache.struts.actions.EventDispatchAction;

/**
 * タスクに対する操作を行うアクションです。
 *
 * @author Y.Ikeda
 *
 */
public class OperateTaskAction extends EventDispatchAction {

	/**
	 * 完了ボタンを押した時のアクションです。
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward done(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null;
	}

	/**
	 * 削除ボタンを押した時のアクションです。
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("success");
	}

	/**
	 * リクエストパラメータが存在しない時のアクションです。
	 *
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("success");
	}
}
