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
import org.apache.struts.action.DynaActionForm;

/**
 * タスク追加画面を表示するアクションです。
 *
 * @author Y.Ikeda
 *
 */
public class EntryAction extends AbstractAction {

	/* (非 Javadoc)
	 * @see tasklet.action.AbstractAction#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO 自動生成されたメソッド・スタブ
		DynaActionForm showTaskForm = (DynaActionForm) form;
		String id = showTaskForm.get("activityId").toString();
		System.out.println(id);

		saveToken(request);
		return mapping.findForward("success");
	}

}
