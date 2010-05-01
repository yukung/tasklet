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
package org.yukung.tasklet.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.yukung.tasklet.dao.ActivityDao;
import org.yukung.tasklet.entity.Activity;
import org.yukung.tasklet.exception.TaskletException;
import org.yukung.tasklet.factory.DaoFactory;

/**
 * <p>
 * アクティビティ関連のトランザクション単位を規定するクラスです。<br>
 * 1メソッドが1トランザクションを表現します。
 * </p>
 * 
 * @author yukung
 * 
 */
public class ActivityTxLogic {

	/** アクティビティ情報DAO */
	private ActivityDao activityDao;

	/**
	 * <p>
	 * デフォルトコンストラクタ。
	 * </p>
	 * <p>
	 * リソース有効利用の観点から、各Serviceクラスから利用する場合は<br>
	 * 極力Service自身が持つDAOを渡す形で利用してください。
	 * </p>
	 */
	public ActivityTxLogic() {
		this(DaoFactory.getInstance().createActivityDao());
	}

	/**
	 * <p>
	 * コンストラクタ。
	 * </p>
	 * 
	 * @param activityDao
	 */
	public ActivityTxLogic(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	/**
	 * <p>
	 * 新規アクティビティを追加します。
	 * </p>
	 * 
	 * @param activity
	 *            アクティビティ情報Entity
	 * @param userId
	 * @throws TaskletException
	 *             DB更新時のエラー
	 */
	public void add(Activity activity, int userId) throws TaskletException {
		Connection conn = null;
		try {
			conn = DaoFactory.getInstance().getConnection();
			conn.setAutoCommit(false);

			// activitiesテーブルへアクティビティを追加
			activityDao.addActivityToActivities(conn, activity);
			// 払い出されたアクティビティIDを取得
			activity.setId(activityDao.getLastInsertId(activity.getTitle())
					.intValue());
			// indexesテーブルへ関連を追加
			activityDao.addActivityToIndexes(conn, activity, userId);

			DbUtils.commitAndCloseQuietly(conn);
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			throw new TaskletException(e.getMessage(), e);
		}

	}

	/**
	 * <p>
	 * アクティビティを昇順にソートします。
	 * </p>
	 * 
	 * @param userId
	 *            ユーザID
	 * @throws TaskletException
	 *             DB更新時のエラー
	 */
	public void ascend(int userId) throws TaskletException {
		Connection conn = null;
		try {
			conn = DaoFactory.getInstance().getConnection();
			conn.setAutoCommit(false);

			List<Activity> origin = activityDao.getSeqByAscending(userId);
			// ソート順の書き換え
			int index = 0;
			for (Activity activity : origin) {
				activity.setSeq(index);
				activityDao.updateSeq(conn, activity);
				index++;
			}

			DbUtils.commitAndCloseQuietly(conn);
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			throw new TaskletException(e.getMessage(), e);
		}
	}

	/**
	 * <p>
	 * アクティビティを降順にソートします。
	 * 
	 * @param userId
	 *            ユーザID
	 * @throws TaskletException
	 *             DB更新時のエラー
	 */
	public void descend(int userId) throws TaskletException {
		Connection conn = null;
		try {
			conn = DaoFactory.getInstance().getConnection();
			conn.setAutoCommit(false);

			List<Activity> origin = activityDao.getSeqByDescending(userId);
			// ソート順の書き換え
			int index = 0;
			for (Activity activity : origin) {
				activity.setSeq(index);
				activityDao.updateSeq(conn, activity);
				index++;
			}

			DbUtils.commitAndCloseQuietly(conn);
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			throw new TaskletException(e.getMessage(), e);
		}
	}
}
