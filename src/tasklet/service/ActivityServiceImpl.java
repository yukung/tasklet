/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.service;

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
	public Activity[] show(int userId) {
		Activity[] activities = activityDao.findActivitiesByUserId(userId);
		if (activities == null) {
			activities = new Activity[0];
		}
		return activities;
	}

}
