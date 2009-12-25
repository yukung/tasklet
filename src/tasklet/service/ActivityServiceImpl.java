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
import tasklet.entity.Activity;
import tasklet.factory.DaoFactory;

/**
 * アクティビティ関連のビジネスロジックを実行するServiceの実装クラスです。
 * 
 * @author Y.Ikeda
 * 
 */
public class ActivityServiceImpl implements ActivityService {

	private ActivityDao activityDao = DaoFactory.getInstance().creActivityDao();

	/*
	 * (非 Javadoc)
	 * 
	 * @see tasklet.service.ActivityService#show(int)
	 */
	public List<Activity> show(int userId) {
		List<Activity> activities = activityDao.findActivitiesByUserId(userId);
		if (activities == null) {
			activities = new ArrayList<Activity>();
		}
		return activities;
	}
}
