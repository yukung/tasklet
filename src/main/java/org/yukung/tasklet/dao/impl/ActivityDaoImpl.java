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
package org.yukung.tasklet.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.yukung.tasklet.dao.AbstractDao;
import org.yukung.tasklet.dao.ActivityDao;
import org.yukung.tasklet.entity.Activity;
import org.yukung.tasklet.exception.DataAccessException;

/**
 * <p>
 * アクティビティ情報DAOインタフェースの実装クラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class ActivityDaoImpl extends AbstractDao implements ActivityDao {

	/**
	 * <p>
	 * デフォルトコンストラクタ。
	 * </p>
	 */
	public ActivityDaoImpl() {
		super();
	}

	/**
	 * <p>
	 * コンストラクタ。DataSource経由での接続はこちらを使用します。
	 * </p>
	 * 
	 * @param ds
	 *            データソース
	 */
	public ActivityDaoImpl(DataSource ds) {
		super(ds);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.ActivityDao#getCountByUserId(int)
	 */
	@Override
	public Long getCountByUserId(int userId) {
		String sql = getSQLFromPropertyFile("getCountByUserId");
		ResultSetHandler<Object> rsh = new ScalarHandler(1);
		try {
			Integer count = (Integer) runner.query(sql, rsh, Integer
					.valueOf(userId));
			return Long.valueOf(count.longValue());
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.ActivityDao#findActivitiesByUserId(int, int,
	 * int)
	 */
	@Override
	public List<Activity> findActivitiesByUserId(int userId, int limit,
			int offset) {
		String sql = getSQLFromPropertyFile("findActivitiesByUserId");
		ResultSetHandler<List<Activity>> rsh = new BeanListHandler<Activity>(
				Activity.class);
		try {
			return runner.query(sql, rsh, Integer.valueOf(userId), Integer
					.valueOf(limit), Integer.valueOf(offset));
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @seeorg.yukung.tasklet.dao.ActivityDao#addActivityToActivities(java.sql.
	 * Connection, org.yukung.tasklet.entity.Activity)
	 */
	@Override
	public void addActivityToActivities(Connection conn, Activity activity)
			throws SQLException {
		String sql = getSQLFromPropertyFile("addActivityToActivities");
		Object[] params = { activity.getTitle(),
				Integer.valueOf(activity.getSeq()) };
		runner.update(conn, sql, params);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.ActivityDao#getMaxSeqOfActivities(int)
	 */
	@Override
	public Integer getMaxSeqOfActivities(int userId) {
		String sql = getSQLFromPropertyFile("getMaxSeqOfActivities");
		ResultSetHandler<Object> rsh = new ScalarHandler(1);
		try {
			return (Integer) runner.query(sql, rsh, Integer.valueOf(userId));
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.ActivityDao#getLastInsertId(java.lang.String)
	 */
	@Override
	public Integer getLastInsertId(String title) {
		String sql = getSQLFromPropertyFile("getLastInsertId");
		ResultSetHandler<Object> rsh = new ScalarHandler(1);
		try {
			return (Integer) runner.query(sql, rsh, title);
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.dao.ActivityDao#addActivityToIndexes(java.sql.Connection
	 * , org.yukung.tasklet.entity.Activity, int)
	 */
	@Override
	public void addActivityToIndexes(Connection conn, Activity activity,
			int userId) throws SQLException {
		String sql = getSQLFromPropertyFile("addActivityToIndexes");
		Object[] params = { Integer.valueOf(userId),
				Integer.valueOf(activity.getCategory().getId()),
				Integer.valueOf(activity.getId()) };
		runner.update(conn, sql, params);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.ActivityDao#getActivityTitle(int)
	 */
	@Override
	public Activity getActivityInfo(int activityId) {
		String sql = getSQLFromPropertyFile("getActivityInfo");
		ResultSetHandler<Activity> rsh = new BeanHandler<Activity>(
				Activity.class);
		try {
			return runner.query(sql, rsh, Integer.valueOf(activityId));
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.dao.ActivityDao#completeActivity(java.sql.Connection,
	 * int)
	 */
	@Override
	public void completeActivity(Connection conn, int activityId)
			throws SQLException {
		String sql = getSQLFromPropertyFile("completeActivity");
		runner.update(conn, sql, Integer.valueOf(activityId));
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.ActivityDao#getMoreActivities(int)
	 */
	@Override
	public List<Map<String, Object>> getMoreActivities(int userId) {
		String sql = getSQLFromPropertyFile("getMoreActivities");
		ResultSetHandler<List<Map<String, Object>>> rsh = new MapListHandler();
		try {
			return runner.query(sql, rsh, Integer.valueOf(userId));
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.ActivityDao#getSeqByAscending(int)
	 */
	@Override
	public List<Activity> getSeqByAscending(int userId) {
		String sql = getSQLFromPropertyFile("getSeqByAscending");
		ResultSetHandler<List<Activity>> rsh = new BeanListHandler<Activity>(
				Activity.class);
		try {
			return runner.query(sql, rsh, Integer.valueOf(userId));
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.ActivityDao#updateSeq(java.sql.Connection,
	 * org.yukung.tasklet.entity.Activity)
	 */
	@Override
	public void updateSeq(Connection conn, Activity activity)
			throws SQLException {
		String sql = getSQLFromPropertyFile("updateSeq");
		Object[] params = { Integer.valueOf(activity.getSeq()),
				Integer.valueOf(activity.getId()) };
		runner.update(conn, sql, params);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.ActivityDao#getSeqByDescending(int)
	 */
	@Override
	public List<Activity> getSeqByDescending(int userId) {
		String sql = getSQLFromPropertyFile("getSeqByDescending");
		ResultSetHandler<List<Activity>> rsh = new BeanListHandler<Activity>(
				Activity.class);
		try {
			return runner.query(sql, rsh, Integer.valueOf(userId));
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.dao.ActivityDao#deleteIndexes(java.sql.Connection,
	 * int)
	 */
	@Override
	public void deleteIndexes(Connection conn, int activityId)
			throws SQLException {
		String sql = getSQLFromPropertyFile("deleteIndexes");
		runner.update(conn, sql, Integer.valueOf(activityId));
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see
	 * org.yukung.tasklet.dao.ActivityDao#deleteActivities(java.sql.Connection,
	 * int)
	 */
	@Override
	public void deleteActivities(Connection conn, int activityId)
			throws SQLException {
		String sql = getSQLFromPropertyFile("deleteActivities");
		runner.update(conn, sql, Integer.valueOf(activityId));
	}
}
