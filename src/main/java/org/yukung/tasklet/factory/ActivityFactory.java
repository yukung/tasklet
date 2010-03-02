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
package org.yukung.tasklet.factory;

import java.util.List;

import org.yukung.tasklet.dao.ActivityDao;
import org.yukung.tasklet.dao.TaskDao;
import org.yukung.tasklet.entity.Activity;
import org.yukung.tasklet.entity.Task;

/**
 * <p>
 * アクティビティ情報の関連を構築するFactoryクラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class ActivityFactory {

	private ActivityDao activityDao = DaoFactory.getInstance()
			.createActivityDao();

	private TaskDao taskDao = DaoFactory.getInstance().createTaskDao();

	/**
	 * <p>
	 * タスク情報を格納したアクティビティの一覧を取得します。
	 * </p>
	 * 
	 * @param userId
	 * @param limit
	 * @param offset
	 * @return タスク情報を持ったアクティビティのList
	 */
	public List<Activity> getActivitiesByUserId(int userId, int limit,
			int offset) {
		List<Activity> activities = activityDao.findActivitiesByUserId(userId,
				limit, offset);

		// アクティビティが存在しない場合はnullを返す
		if (activities == null) {
			return null;
		}

		for (Activity activity : activities) {
			List<Task> tasks = taskDao.findTasksByActivityId(activity.getId());
			activity.setTasks(tasks);
		}
		return activities;
	}
}
