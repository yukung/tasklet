/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import tasklet.common.DataAccessException;
import tasklet.common.TaskletException;
import tasklet.entity.Activity;
import tasklet.entity.User;
import tasklet.service.ActivityService;
import tasklet.service.ActivityServiceImpl;

/**
 * アクティビティを追加するアクションです。
 *
 * @author Y.Ikeda
 *
 */
public class addActivityAction extends AbstractAction {

	/* (非 Javadoc)
	 * @see tasklet.action.AbstractAction#doExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		if (!isTokenValid(request, true)) {
			return mapping.findForward("double");
		}

		DynaActionForm addActivityForm = (DynaActionForm)form;

		Activity activity = new Activity();
		try {
			PropertyUtils.setSimpleProperty(activity, "title", addActivityForm.get("activityName"));
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}

		// セッションからユーザIDを取得
		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getId();

		// アクティビティを登録
		ActivityService activityService = new ActivityServiceImpl();
		try {
			activity = activityService.setAcitivity(activity, userId);
			activityService.add(activity, userId);
		} catch (TaskletException e) {
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage(e.getMessage());
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			return mapping.findForward("success");
		}
		return mapping.findForward("success");
	}
}
