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

import tasklet.entity.Activity;
import tasklet.entity.User;
import tasklet.service.ActivityService;
import tasklet.service.ActivityServiceImpl;

/**
 * アクティビティ一覧を表示するアクションです。
 * 
 * @author Y.Ikeda
 * 
 */
public class ShowActivitiesAction extends AbstractAction {

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

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			// TODO ログインしていないので、トップページに戻ってログインさせるように遷移する。
		}

		ActivityService activityService = new ActivityServiceImpl();
		Activity[] activityArray = activityService.show(user.getId());
		request.setAttribute("activities", activityArray);

		return mapping.findForward("success");
	}

}
