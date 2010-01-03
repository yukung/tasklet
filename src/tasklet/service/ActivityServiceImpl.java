/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.service;

import java.util.ArrayList;
import java.util.List;

import tasklet.dao.ActivityDao;
import tasklet.dao.TaskDao;
import tasklet.entity.Activity;
import tasklet.factory.DaoFactory;

/**
 * アクティビティ関連のビジネスロジックを実行するServiceの実装クラスです。
 *
 * @author Y.Ikeda
 *
 */
public class ActivityServiceImpl implements ActivityService {

	private ActivityDao activityDao = DaoFactory.getInstance().createActivityDao();

	private TaskDao taskDao = DaoFactory.getInstance().createTaskDao();

	/*
	 * (非 Javadoc)
	 *
	 * @see tasklet.service.ActivityService#show(int)
	 */
	public List<Activity> show(int userId) {
		List<Activity> activities = activityDao.findActivitiesByUserId(userId);
		if (activities == null) {
			activities = new ArrayList<Activity>();
		} else {
			for (Activity activity : activities) {
				activity.setTasks(taskDao.findTasksByActivityId(activity.getId()));
			}
		}
		return activities;
	}

	public Activity setAcitivity(Activity activity, String userId) {
		activity.setDescription("これはテストです。");
		Integer count = activityDao.getMaxSequenceOfActivities(Integer.valueOf(userId).intValue());
		if (count == null) {
			count = Integer.valueOf(0);
		} else {
			count = count.intValue() + 1;
		}
		activity.setSeq(count);
		activity.setIncomplete(true);

		return activity;
	}

	public void add(Activity activity) {
		// TODO 自動生成されたメソッド・スタブ
		int activityId = activityDao.addActivities(activity);
	}
}
