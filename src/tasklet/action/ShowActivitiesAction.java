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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import tasklet.common.Constants;
import tasklet.entity.Activity;
import tasklet.entity.User;
import tasklet.service.ActivityService;
import tasklet.service.ActivityServiceImpl;
import tasklet.util.Pager;

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
		int userId = user.getId();
		int pageNo = 1; // 初期アクセス時は1ページ目

		ActivityService activityService = new ActivityServiceImpl();
		long count = activityService.getCount(userId);
		if (count == 0) {
			ActionMessages messages = new ActionMessages();
			ActionMessage message = new ActionMessage("messages.noactivity");
			messages.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, messages);
		} else {
			Pager pager = new Pager(count, pageNo, Constants.ACTIVITIES_MAX_LIMIT);
			List<Activity> activities = activityService.show(userId, pager.getOffset(), pager.getLimit());
			request.setAttribute("activities", activities);
			request.setAttribute("pager", pager);
		}

		saveToken(request);
		return mapping.findForward("success");
	}
}
