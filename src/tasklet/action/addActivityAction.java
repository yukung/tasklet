/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.action;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import tasklet.common.DataAccessException;
import tasklet.entity.Activity;
import tasklet.service.ActivityService;
import tasklet.service.ActivityServiceImpl;
import tasklet.util.PropertyUtil;

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

		String userId = (String) request.getSession().getAttribute("userId");
		ActivityService activityService = new ActivityServiceImpl();
		activity = activityService.setAcitivity(activity, userId);
		activityService.add(activity);
		// TODO Activityテーブル、Indexesテーブルの登録処理
		// TODO 例外処理
		// TODO 次画面へフォワード
		return null;
	}

}
