/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.service;

import java.util.ArrayList;
import java.util.List;

import tasklet.dao.TaskDao;
import tasklet.entity.Task;
import tasklet.factory.DaoFactory;

/**
 * タスク関連のビジネスロジックを実行するServiceの実装クラスです。
 *
 * @author Y.Ikeda
 *
 */
public class TaskServiceImpl implements TaskService {

	private TaskDao taskDao = DaoFactory.getInstance().createTaskDao();

	/* (非 Javadoc)
	 * @see tasklet.service.TaskService#show(int)
	 */
	public List<Task> show(int activityId) {

		List<Task> tasks = taskDao.findTasksByActivityId(activityId);

		// タスクが1件も登録されていない場合、空のListを返す
		if (tasks == null) {
			tasks = new ArrayList<Task>();
		}
		return tasks;
	}

	/*
	 * (非 Javadoc)
	 * @see tasklet.service.TaskService#getActivityTitle(int)
	 */
	public String getActivityTitle(int activityId) {
		return taskDao.getActivityTitle(activityId);
	}

}
