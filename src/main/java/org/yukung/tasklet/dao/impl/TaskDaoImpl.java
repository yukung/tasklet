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

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.yukung.tasklet.dao.AbstractDao;
import org.yukung.tasklet.dao.TaskDao;
import org.yukung.tasklet.entity.Task;
import org.yukung.tasklet.exception.DataAccessException;
import org.yukung.tasklet.exception.TaskletException;

/**
 * <p>
 * タスク情報DAOインタフェースの実装クラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class TaskDaoImpl extends AbstractDao implements TaskDao {

	/**
	 * <p>
	 * デフォルトコンストラクタ。
	 * </p>
	 */
	public TaskDaoImpl() {
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
	public TaskDaoImpl(DataSource ds) {
		super(ds);
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.TaskDao#findTasksByActivityId(int)
	 */
	@Override
	public List<Task> findTasksByActivityId(int activityId) {
		String sql = getSQLFromPropertyFile("findTasksByActivityId");
		ResultSetHandler<List<Task>> rsh = new BeanListHandler<Task>(Task.class);
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
	 * org.yukung.tasklet.dao.TaskDao#addTask(org.yukung.tasklet.entity.Task)
	 */
	@Override
	public void addTask(Task task) throws TaskletException {
		String sql = getSQLFromPropertyFile("addTask");
		Object[] param = { Integer.valueOf(task.getActivityId()),
				task.getTitle(), Integer.valueOf(task.getPriority().getCode()),
				task.getPeriod(), Double.valueOf(task.getEstimatedTime()) };
		try {
			runner.update(sql, param);
		} catch (SQLException e) {
			throw new TaskletException(e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.TaskDao#getTaskDetail(int)
	 */
	@Override
	public Task getTask(int taskId) {
		String sql = getSQLFromPropertyFile("getTask");
		ResultSetHandler<Task> rsh = new BeanHandler<Task>(Task.class);
		try {
			return runner.query(sql, rsh, Integer.valueOf(taskId));
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	/*
	 * (非 Javadoc)
	 * 
	 * @see org.yukung.tasklet.dao.TaskDao#getActivityIdByTaskId(int)
	 */
	@Override
	public Integer getActivityIdByTaskId(int taskId) {
		String sql = getSQLFromPropertyFile("getActivityIdByTaskId");
		ResultSetHandler<Object> rsh = new ScalarHandler(1);
		try {
			return (Integer) runner.query(sql, rsh, Integer.valueOf(taskId));
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}

	}
}
