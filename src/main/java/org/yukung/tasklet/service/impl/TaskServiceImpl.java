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
package org.yukung.tasklet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.yukung.tasklet.dao.ActivityDao;
import org.yukung.tasklet.dao.TaskDao;
import org.yukung.tasklet.dto.ActivityDto;
import org.yukung.tasklet.dto.TaskDto;
import org.yukung.tasklet.dto.converter.DtoConverter;
import org.yukung.tasklet.entity.Activity;
import org.yukung.tasklet.entity.Task;
import org.yukung.tasklet.exception.DataAccessException;
import org.yukung.tasklet.factory.ConverterFactory;
import org.yukung.tasklet.factory.DaoFactory;
import org.yukung.tasklet.factory.TaskFactory;
import org.yukung.tasklet.service.TaskService;

/**
 * <p>
 * タスク関連のビジネスロジックを実行するServiceの実装クラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class TaskServiceImpl implements TaskService {

	/** アクティビティ情報DAO */
	private ActivityDao activityDao = DaoFactory.getInstance()
			.createActivityDao();

	/** タスク情報DAO */
	private TaskDao taskDao = DaoFactory.getInstance().createTaskDao();

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.service.TaskService#show(int)
	 */
	@Override
	public List<TaskDto> show(int activityId) {
		TaskFactory taskFactory = new TaskFactory();
		List<Task> tasks = taskFactory.getTasksByActivityId(activityId);

		// タスクが1件も登録されていない場合、空のListを返す
		if (tasks == null) {
			return new ArrayList<TaskDto>();
		}
		DtoConverter<Task, TaskDto> converter = ConverterFactory
				.createDtoConverter(Task.class);
		return converter.convert(tasks);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.service.TaskService#getActivityInfo(int)
	 */
	@Override
	public ActivityDto getActivityInfo(int activityId)
			throws DataAccessException {
		Activity activity = activityDao.getActivityInfo(activityId);

		if (activity == null) {
			throw new DataAccessException("errors.general");
		}
		DtoConverter<Activity, ActivityDto> converter = ConverterFactory
				.createDtoConverter(Activity.class);
		return converter.convert(activity);
	}
}
