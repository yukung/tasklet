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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.yukung.tasklet.dto.DetailDto;
import org.yukung.tasklet.dto.MemoDto;
import org.yukung.tasklet.form.MemosForm;
import org.yukung.tasklet.service.TaskService;
import org.yukung.tasklet.service.impl.TaskServiceImpl;

/**
 * <p>
 * メモ一覧表示用アクションです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class MemosAction extends AbstractAction {

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

		MemosForm memosForm = (MemosForm) form;
		int taskId = Integer.parseInt(memosForm.getTaskId());

		// タスクIDとタスク名の取得
		TaskService taskService = new TaskServiceImpl();
		DetailDto task = taskService.getTask(taskId);
		request.setAttribute("task", task);

		// メモ情報の取得
		List<MemoDto> memos = taskService.getMemos(taskId);
		request.setAttribute("memos", memos);

		saveToken(request);
		return mapping.findForward(SUCCESS);
	}

}
