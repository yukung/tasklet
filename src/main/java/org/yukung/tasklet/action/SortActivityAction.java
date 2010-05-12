/**
 * Copyright 2009-2010 Yusuke Ikeda. (@yukung) <yukung.i@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.yukung.tasklet.action;

import static org.yukung.tasklet.common.Constants.ACTIVITIES_MAX_LIMIT;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.yukung.tasklet.dto.ActivityDto;
import org.yukung.tasklet.entity.Activity;
import org.yukung.tasklet.entity.User;
import org.yukung.tasklet.exception.TaskletException;
import org.yukung.tasklet.form.SortActivityForm;
import org.yukung.tasklet.service.ActivityService;
import org.yukung.tasklet.service.impl.ActivityServiceImpl;
import org.yukung.tasklet.utils.Pager;

/**
 * <p>
 * アクティビティのソートアクションです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class SortActivityAction extends AbstractAction {

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.action.AbstractAction#doExecute(org.apache.struts.
	 * action.ActionMapping, org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		// ActionFormをEntityにマッピング
		SortActivityForm sortActivityForm = (SortActivityForm) form;
		String sortId = sortActivityForm.getSortId();
		List<Activity> sortedActivities = doMappingToEntity(sortId);

		// アクティビティ追加処理
		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getId();
		ActivityService activityService = new ActivityServiceImpl();
		try {
			activityService.sort(sortedActivities, userId);
		} catch (TaskletException e) {
			ActionMessages errors = new ActionMessages();
			ActionMessage error = new ActionMessage("errors.update");
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			saveErrors(request, errors);
			return mapping.getInputForward();
		}

		// アクティビティ一覧再表示
		long count = activityService.getCount(userId);
		if (count == 0) {
			ActionMessages messages = new ActionMessages();
			ActionMessage message = new ActionMessage("messages.noactivity");
			messages.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, messages);
		} else {
			int pageNo = 1; // ソート直後は１ページ目を表示する
			Pager pager = new Pager(count, pageNo, ACTIVITIES_MAX_LIMIT);
			List<ActivityDto> activities = activityService.show(userId, pager
					.getLimit(), pager.getOffset());
			request.setAttribute("activities", activities);
			request.setAttribute("pager", pager);
		}
		return mapping.findForward(SUCCESS);
	}

	/**
	 * <p>
	 * リクエストパラメータのアクティビティIDをEntityにマッピングします。
	 * </p>
	 * 
	 * @param sortId
	 * @return アクティビティEntityのList
	 */
	private List<Activity> doMappingToEntity(String sortId) {
		String[] sortIdArray = sortId.split(",");
		List<Activity> sortedActivities = new ArrayList<Activity>(
				sortIdArray.length);
		int index = sortIdArray.length; // 逆順からソートする
		for (int i = 0; i < sortIdArray.length; i++) {
			Activity activity = new Activity();
			activity.setId(Integer.parseInt(sortIdArray[i]));
			activity.setSeq(index); // 配列 → ArrayList への詰め直しは前後を入れ替える
			sortedActivities.add(activity);
			index--;
		}
		return sortedActivities;
	}
}
