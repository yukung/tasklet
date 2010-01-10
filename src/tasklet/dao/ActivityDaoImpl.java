/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.dao;

import static tasklet.Constants.PROPERTY_KEY_SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import tasklet.common.DataAccessException;
import tasklet.common.TaskletException;
import tasklet.entity.Activity;
import tasklet.util.PropertyUtil;

/**
 * アクティビティ情報DAO実装クラスです。
 *
 * @author Y.Ikeda
 *
 */
public class ActivityDaoImpl extends AbstractDao implements ActivityDao {

	/**
	 * 新しいActivityDaoのインスタンスを生成します。
	 *
	 * @param source
	 */
	public ActivityDaoImpl(DataSource source) {
		this.source = source;
	}

	/*
	 * (非 Javadoc)
	 * @see tasklet.dao.ActivityDao#findActivitiesByUserId(int, int, int)
	 */
	public List<Activity> findActivitiesByUserId(int userId, int offset, int limit) {

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append(
					"findActivitiesByUserId").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			// SQLの実行
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setInt(1, userId);
			statement.setInt(2, userId);
			statement.setInt(3, limit); // パラメータの順序に注意！！
			statement.setInt(4, offset);
			rs = statement.executeQuery();

			// 結果の取り出し
			List<Activity> activities = new ArrayList<Activity>();
			while (rs.next()) {
				Activity activity = new Activity();
				activity.setId(rs.getInt("id"));
				activity.setTitle(rs.getString("title"));
				activity.setDescription(rs.getString("description"));
				activity.setSeq(rs.getInt("seq"));
				activity.setCategory(rs.getString("name"));
				activity.setIncomplete(rs.getBoolean("is_incomplete"));
				activity.setCreatedOn(rs.getTimestamp("created_on"));
				activity.setUpdatedOn(rs.getTimestamp("updated_on"));
				activities.add(activity);
			}

			return activities;

		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		} catch (ArrayStoreException e) {
			throw new DataAccessException(e.getMessage(), e);
		} finally {
			close(rs);
			close(statement);
			close(conn);
		}
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see tasklet.dao.ActivityDao#getMaxSequenceOfActivities(int)
	 */
	public Integer getMaxSequenceOfActivities(int userId) {

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append(
					"getMaxSequenceOfActivities").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			// SQLの実行
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setInt(1, userId);
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
	 *
	 * @see tasklet.dao.ActivityDao#addActivities(tasklet.entity.Activity)
	 */
	public void addActivities(Activity activity) {

		Connection conn = null;
		PreparedStatement statement = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append(
					"addActivityToActivities").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			// DB更新
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, activity.getTitle());
			statement.setInt(2, activity.getSeq());
			// 未完了フラグは追加時は必ずtrueで登録される（SQL側で固定値登録）

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
	 * @see tasklet.dao.ActivityDao#addIndexes(int, int, int)
	 */
	public void addIndexes(int userId, int categoryId, int activityId) {

		Connection conn = null;
		PreparedStatement statement = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append(
					"addActivityToIndexes").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			// DB更新
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setInt(1, userId);
			statement.setInt(2, categoryId);
			statement.setInt(3, activityId);

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
	 * @see tasklet.dao.ActivityDao#getLastActivityId(tasklet.entity.Activity)
	 */
	public int getLastActivityId(Activity activity) throws TaskletException {

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append(
					"getLastActivityId").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			// DB検索
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, activity.getTitle());
			rs = statement.executeQuery();

			if (!rs.next()) {
				throw new TaskletException("errors.insert");
			}

			return rs.getInt("last_activity_id");

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
	 *
	 * @see tasklet.dao.ActivityDao#getLastCategoryId(int)
	 */
	public int getLastCategoryId(int userId) throws TaskletException {

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append(
					"getLastCategoryId").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			// DB検索
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setInt(1, userId);
			rs = statement.executeQuery();

			if (!rs.next()) {
				throw new DataAccessException("errors.insert");
			}

			return rs.getInt("last_category_id");

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
	 * @see tasklet.dao.ActivityDao#getActivityCountByUserId(int)
	 */
	public long getActivityCountByUserId(int userId) {
		// TODO 自動生成されたメソッド・スタブ
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			// SQLの取得
			String propertyKey = new StringBuilder(PROPERTY_KEY_SQL).append("getActivityCountByUserId").toString();
			PropertyUtil property = PropertyUtil.getInstance("sql");
			String sql = property.getString(propertyKey);

			// DB検索
			conn = source.getConnection();
			statement = conn.prepareStatement(sql);
			statement.setInt(1, userId);
			rs = statement.executeQuery();

			if (!rs.next()) {
				return 0;
			}

			return rs.getLong("activities_count");

		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		} finally {
			close(rs);
			close(statement);
			close(conn);
		}
	}
}
