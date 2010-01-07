/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.service;

import java.util.ArrayList;
import java.util.List;

import tasklet.common.DataAccessException;
import tasklet.common.TaskletException;
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

		// アクティビティが1件も登録されていない場合、空のListを返す
		if (activities == null) {
			activities = new ArrayList<Activity>();
		} else {
			for (Activity activity : activities) {
				activity.setTasks(taskDao.findTasksByActivityId(activity.getId()));
			}
		}
		return activities;
	}

	public Activity setAcitivity(Activity activity, int userId) {

		activity.setDescription("これはテストです。"); // TODO 概要はとりあえず固定値登録
		// ソート順の取得
		Integer count = activityDao.getMaxSequenceOfActivities(userId);
		if (count == null) {
			count = Integer.valueOf(0);
		} else {
			count = count.intValue() + 1;
		}
		activity.setSeq(count.intValue());
		// 未完了フラグはSQLで固定値trueで登録

		return activity;
	}

	public void add(Activity activity, int userId) throws TaskletException {

		// アクティビティ追加処理
		try {
			activityDao.addActivities(activity);
			int activityId = activityDao.getLastActivityId(activity);
			int categoryId = activityDao.getLastCategoryId(userId);
			activityDao.addIndexes(userId, categoryId, activityId);
		} catch (DataAccessException e) {
			// DB登録エラー
			throw new TaskletException("errors.insert", e);
		}
	}
}
