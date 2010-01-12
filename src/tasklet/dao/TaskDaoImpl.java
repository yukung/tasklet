/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.dao;

import static tasklet.common.Constants.PROPERTY_KEY_SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import tasklet.DataAccessException;
import tasklet.entity.Task;
import tasklet.util.PropertyUtil;

/**
 * タスク情報DAO実装クラスです。
 *
 * @author Y.Ikeda
 *
 */
public class TaskDaoImpl extends AbstractDao implements TaskDao {

	/**
	 * 新しいTaskDaoのインスタンスを生成します。
	 *
	 * @param source
	 */
	public TaskDaoImpl(DataSource source) {
		this.source = source;
	}

	/* (非 Javadoc)
	 * @see tasklet.dao.TaskDao#findTasksByActivityId(int)
	 */
	public List<Task> findTasksByActivityId(int activityId) {

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append("findTasksByActivityId").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			// SQLの実行
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setInt(1, activityId);
			rs = statement.executeQuery();

			// 結果の取り出し
			List<Task> tasks = new ArrayList<Task>();
			while (rs.next()) {
				Task task = new Task();
				task.setId(rs.getInt("id"));
				task.setActivityId(rs.getInt("activity_id"));
				task.setTitle(rs.getString("title"));
				task.setPriorityFromCode((rs.getInt("priority")));
				task.setStatusFromCode(rs.getInt("status"));
				task.setPeriod(rs.getDate("period"));
				task.setFinishedOn(rs.getDate("finished_on"));
				task.setEstimatedTime(rs.getDouble("estimated_time"));
				task.setActualTime(rs.getDouble("actual_time"));
				task.setCreatedOn(rs.getTimestamp("created_on"));
				task.setUpdatedOn(rs.getTimestamp("updated_on"));
				tasks.add(task);
			}

			return tasks;

		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		} finally {
			close(rs);
			close(statement);
			close(conn);
		}
	}

	/*
	 * (非 Javadoc)
	 * @see tasklet.dao.TaskDao#getActivityTitle(int)
	 */
	public String getActivityTitle(int activityId) {

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append("getActivityTitle").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			// SQLの実行
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setInt(1, activityId);
			rs = statement.executeQuery();

			if (!rs.next()) {
				throw new DataAccessException("errors.exist");
			}

			return rs.getString("title");

		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		} finally {
			close(rs);
			close(statement);
			close(conn);
		}

	}

}
