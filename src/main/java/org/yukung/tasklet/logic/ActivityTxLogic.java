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
import org.yukung.tasklet.dao.CategoryDao;
import org.yukung.tasklet.entity.Activity;
import org.yukung.tasklet.entity.Category;
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

	/** カテゴリ情報DAO */
	private CategoryDao categoryDao;

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
		this(DaoFactory.getInstance().createActivityDao(), DaoFactory
				.getInstance().createCategoryDao());
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
		this.categoryDao = null;
	}

	/**
	 * <p>
	 * コンストラクタ。
	 * </p>
	 * 
	 * @param categoryDao
	 */
	public ActivityTxLogic(CategoryDao categoryDao) {
		this.activityDao = null;
		this.categoryDao = categoryDao;
	}

	/**
	 * <p>
	 * コンストラクタ。
	 * </p>
	 * 
	 * @param activityDao
	 * @param categoryDao
	 */
	public ActivityTxLogic(ActivityDao activityDao, CategoryDao categoryDao) {
		this.activityDao = activityDao;
		this.categoryDao = categoryDao;
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
			int index = origin.size();
			for (Activity activity : origin) {
				activity.setSeq(index);
				activityDao.updateSeq(conn, activity);
				index--;
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
			int index = origin.size();
			for (Activity activity : origin) {
				activity.setSeq(index);
				activityDao.updateSeq(conn, activity);
				index--;
			}

			DbUtils.commitAndCloseQuietly(conn);
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			throw new TaskletException(e.getMessage(), e);
		}
	}

	/**
	 * <p>
	 * 渡されたアクティビティのソート順で更新します。
	 * </p>
	 * 
	 * @param sortedActivities
	 *            ソートされたアクティビティEntity
	 * @throws TaskletException
	 *             DB更新時のエラー
	 */
	public void sort(List<Activity> sortedActivities) throws TaskletException {
		Connection conn = null;
		try {
			conn = DaoFactory.getInstance().getConnection();
			for (Activity activity : sortedActivities) {
				activityDao.updateSeq(conn, activity);
			}

			DbUtils.commitAndCloseQuietly(conn);
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			throw new TaskletException(e.getMessage(), e);
		}
	}

	/**
	 * <p>
	 * 最新のソート順を取得して、カテゴリを追加します。
	 * </p>
	 * 
	 * @param category
	 *            カテゴリ情報Entity
	 * @throws TaskletException
	 *             DB更新エラー
	 */
	public void addCategory(Category category) throws TaskletException {
		Connection conn = null;
		try {
			conn = DaoFactory.getInstance().getConnection();
			conn.setAutoCommit(false);

			// カテゴリの最新ソート順を取得
			Integer count = categoryDao.getMaxSeqOfCategories(category
					.getUserId());
			category.setSeq(getSeq(count));
			// categoriesテーブルへカテゴリを追加
			categoryDao.add(conn, category);

			DbUtils.commitAndCloseQuietly(conn);
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			throw new TaskletException(e.getMessage(), e);
		}
	}

	/**
	 * <p>
	 * ユーザIDに紐づくカテゴリの最新順を取得します。
	 * </p>
	 * 
	 * @param count
	 * @return カテゴリのソート順最大値
	 */
	private int getSeq(Integer count) {
		if (count == null) {
			count = 0;
		}
		return count.intValue() + 1;
	}
}
