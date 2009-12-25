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
	 * 
	 * @see tasklet.dao.ActivityDao#findActivitiesByUserId(int)
	 */
	public List<Activity> findActivitiesByUserId(int userId) {

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
			throw new TaskletException(e.getMessage(), e);
		} catch (ArrayStoreException e) {
			throw new TaskletException(e.getMessage(), e);
		} finally {
			close(rs);
			close(statement);
			close(conn);
		}
	}
}
