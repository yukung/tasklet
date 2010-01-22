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
import tasklet.entity.Memo;
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
			statement.setInt(2, activityId);
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
				task.setMemoCount(rs.getInt("memo_count"));
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

	/*
	 * (非 Javadoc)
	 * @see tasklet.dao.TaskDao#addTasks(tasklet.entity.Task)
	 */
	public void addTasks(Task task) {

		Connection conn = null;
		PreparedStatement statement = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append("addTaskToTasks").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			// DB更新
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setInt(1, task.getActivityId());
			statement.setString(2, task.getTitle());
			statement.setInt(3, task.getPriority().getCode());
			statement.setDate(4, task.getPeriod());
			statement.setDouble(5, task.getEstimatedTime());

			statement.executeUpdate();

		} catch (SQLException e) {
			rollback(conn);
			throw new DataAccessException(e.getMessage(), e);
		} finally {
			close(statement);
			close(conn);
		}
	}

	/*
	 * (非 Javadoc)
	 * @see tasklet.dao.TaskDao#getTaskDetailAndMemos(int)
	 */
	public Task getTaskDetailAndMemos(int taskId) {

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append("getTaskDetailAndMemos").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			// DB検索
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setInt(1, taskId);
			rs = statement.executeQuery();

			if (!rs.next()) {
				throw new DataAccessException("errors.exist");
			}

			// 結果の取り出し
			Task task = new Task();
			task.setId(rs.getInt("id"));
			task.setActivityId(rs.getInt("activity_id"));
			task.setTitle(rs.getString("title"));
			task.setPriorityFromCode(rs.getInt("priority"));
			task.setStatusFromCode(rs.getInt("status"));
			task.setPeriod(rs.getDate("period"));
			task.setFinishedOn(rs.getDate("finished_on"));
			task.setEstimatedTime(rs.getDouble("estimated_time"));
			task.setActualTime(rs.getDouble("actual_time"));

			// メモIDがnullだった場合はtask.memosにnullを設定
			// （rs.getInt(column)はSQL NULLだった場合0を返すため、
			// memosプロパティにオブジェクトがセットされてしまう。
			// 結果としてJSPのカスタムタグでnotEmptyが判定できなくなるのを防ぐ）
			rs.getInt("memo_id");
			if (rs.wasNull()) {
				task.setMemos(null);
			} else {
				List<Memo> memos = new ArrayList<Memo>();
				// メモ一覧の取得
				do {
					Memo memo = new Memo();
					memo.setId(rs.getInt("memo_id"));
					memo.setTaskId(rs.getInt("task_id"));
					memo.setSeq(rs.getInt("seq"));
					memo.setContents(rs.getString("contents"));
					memos.add(memo);
				} while (rs.next());
				task.setMemos(memos);
			}

			return task;

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
	 * @see tasklet.dao.TaskDao#updateTask(tasklet.entity.Task)
	 */
	public void updateTask(Task task) {

		Connection conn = null;
		PreparedStatement statement = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append("updateTaskByTaskId").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			// DB更新
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setDouble(1, task.getActualTime());
			statement.setInt(2, task.getId());

			statement.executeUpdate();

		} catch (SQLException e) {
			rollback(conn);
			throw new DataAccessException(e.getMessage(), e);
		} finally {
			close(statement);
			close(conn);
		}
	}

	/*
	 * (非 Javadoc)
	 * @see tasklet.dao.TaskDao#getMaxSequenceOfMemos(int)
	 */
	public Integer getMaxSequenceOfMemos(int taskId) {

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append(
					"getMaxSequenceOfMemos").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			// SQLの実行
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setInt(1, taskId);
			rs = statement.executeQuery();

			if (!rs.next()) {
				return null;
			}

			return Integer.valueOf(rs.getInt("max_seq"));

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
	 * @see tasklet.dao.TaskDao#addMemos(tasklet.entity.Memo)
	 */
	public void addMemos(Memo memo) {

		Connection conn = null;
		PreparedStatement statement = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append("addMemoToMemos").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			// DB更新
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setInt(1, memo.getTaskId());
			statement.setInt(2, memo.getSeq());
			statement.setString(3, memo.getContents());

			statement.executeUpdate();

		} catch (SQLException e) {
			rollback(conn);
			throw new DataAccessException(e.getMessage(), e);
		} finally {
			close(statement);
			close(conn);
		}
	}

	/*
	 * (非 Javadoc)
	 * @see tasklet.dao.TaskDao#getActivityIdByTaskId(int)
	 */
	public int getActivityIdByTaskId(int taskId) {

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append(
					"getActivityIdByTaskId").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			// SQLの実行
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setInt(1, taskId);
			rs = statement.executeQuery();

			if (!rs.next()) {
				throw new DataAccessException("errors.exist");
			}

			return rs.getInt("activity_id");

		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		} finally {
			close(rs);
			close(statement);
			close(conn);
		}
	}
}
