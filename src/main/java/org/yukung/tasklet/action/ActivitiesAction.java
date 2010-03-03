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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.yukung.tasklet.dto.ActivityDto;
import org.yukung.tasklet.entity.User;
import org.yukung.tasklet.service.ActivityService;
import org.yukung.tasklet.service.impl.ActivityServiceImpl;
import org.yukung.tasklet.util.Pager;

/**
 * <p>
 * アクティビティ一覧表示アクションです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class ActivitiesAction extends AbstractAction {

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

		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getId();
		int pageNo = 1; // 初期アクセス時は1ページ目を表示する

		ActivityService activityService = new ActivityServiceImpl();
		long count = activityService.getCount(userId);
		if (count == 0) {
			ActionMessages messages = new ActionMessages();
			ActionMessage message = new ActionMessage("messages.noactivity");
			messages.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, messages);
		} else {
			Pager pager = new Pager(count, pageNo, ACTIVITIES_MAX_LIMIT);
			List<ActivityDto> activities = activityService.show(userId, pager
					.getLimit(), pager.getOffset());
			request.setAttribute("activities", activities);
			request.setAttribute("pager", pager);
		}

		saveToken(request);
		return mapping.findForward(SUCCESS);
	}
}
