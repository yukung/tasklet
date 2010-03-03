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
import org.yukung.tasklet.dto.DtoConverter;
import org.yukung.tasklet.entity.Activity;
import org.yukung.tasklet.factory.ActivityFactory;
import org.yukung.tasklet.factory.DaoFactory;
import org.yukung.tasklet.service.ActivityService;

/**
 * <p>
 * アクティビティ関連のビジネスロジックを実行するServiceの実装クラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class ActivityServiceImpl implements ActivityService {

	/** アクティビティ情報DAO */
	private ActivityDao activityDao = DaoFactory.getInstance()
			.createActivityDao();

	/** タスク情報DAO */
	private TaskDao taskDao = DaoFactory.getInstance().createTaskDao();

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.service.ActivityService#getCount(int)
	 */
	@Override
	public long getCount(int userId) {
		return activityDao.getCountByUserId(userId);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.service.ActivityService#show(int, int, int)
	 */
	@Override
	public List<ActivityDto> show(int userId, int limit, int offset) {
		ActivityFactory activityFactory = new ActivityFactory();
		List<Activity> activities = activityFactory.getActivitiesByUserId(
				userId, limit, offset);
		// アクティビティが1件も登録されていない場合、空のListを返す
		if (activities == null) {
			return new ArrayList<ActivityDto>();
		}
		return DtoConverter.convert(activities);
	}

}
