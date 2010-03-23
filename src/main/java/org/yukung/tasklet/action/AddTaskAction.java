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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.yukung.tasklet.entity.Task;
import org.yukung.tasklet.exception.DataAccessException;
import org.yukung.tasklet.form.AddTaskForm;
import org.yukung.tasklet.service.TaskService;
import org.yukung.tasklet.service.impl.TaskServiceImpl;

/**
 * <p>
 * 新規タスク登録アクションです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class AddTaskAction extends AbstractAction {

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

		// ダブルポストのチェック
		if (!isTokenValid(request, true)) {
			return mapping.findForward("double");
		}

		// ActionFormをEntityにマッピング
		AddTaskForm addTaskForm = (AddTaskForm) form;
		Task task = new Task();
		try {
			BeanUtils.copyProperties(task, addTaskForm);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}

		TaskService taskService = new TaskServiceImpl();
		taskService.add(task);
		return null;
	}

}
