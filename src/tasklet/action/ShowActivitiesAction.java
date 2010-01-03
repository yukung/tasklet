/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.action;

import java.util.List;

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
			return mapping.findForward("login");
		}

		ActivityService activityService = new ActivityServiceImpl();
		List<Activity> activities = activityService.show(user.getId());
		request.setAttribute("activities", activities);

		saveToken(request);
		return mapping.findForward("success");
	}
}
