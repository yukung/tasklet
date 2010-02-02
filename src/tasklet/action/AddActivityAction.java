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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import tasklet.DataAccessException;
import tasklet.TaskletException;
import tasklet.common.Constants;
import tasklet.entity.Activity;
import tasklet.entity.User;
import tasklet.service.ActivityService;
import tasklet.service.ActivityServiceImpl;
import tasklet.util.Pager;

/**
 * アクティビティを追加するアクションです。
 *
 * @author Y.Ikeda
 *
 */
public class AddActivityAction extends AbstractAction {

	/*
	 * (非 Javadoc)
	 *
	 * @see tasklet.action.AbstractAction#doExecute(org.apache.struts.action.
	 * ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		if (!isTokenValid(request, true)) {
			return mapping.findForward("double");
		}

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return mapping.findForward("login");
		}

		// セッションからユーザIDを取得
		int userId = user.getId();

		DynaActionForm addActivityForm = (DynaActionForm)form;

		Activity activity = new Activity();
		try {
			// 入力フォーム情報をユーザエンティティにマッピング
			BeanUtils.setProperty(activity, "title", addActivityForm.get("activityName"));
		} catch (Exception e) {
			// 不整合の場合はシステム例外としてStrutsに投げる
			throw new DataAccessException(e.getMessage(), e);
		}

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
			return mapping.getInputForward();
		}

		// アクティビティ一覧を再取得
		long count = activityService.getCount(userId);
		int pageNo = 1; // 先頭に新規タスクが追加されるため1ページ目を再表示
		Pager pager = new Pager(count, pageNo, Constants.ACTIVITIES_MAX_LIMIT);
		List<Activity> activities = activityService.show(userId, pager.getOffset(), pager.getLimit());
		request.setAttribute("activities", activities);
		request.setAttribute("pager", pager);
		addActivityForm.set("activityName", "");

		saveToken(request);
		return mapping.findForward("success");
	}
}
