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
package org.yukung.tasklet.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.yukung.tasklet.entity.Activity;

/**
 * <p>
 * アクティビティ情報DAOインタフェースです。
 * </p>
 * 
 * @author yukung
 * 
 */
public interface ActivityDao {

	/**
	 * <p>
	 * 引数のユーザIDに紐づくアクティビティの件数を取得します。
	 * </p>
	 * 
	 * @param userId
	 * @return アクティビティの件数
	 *         <p>
	 *         見つからない場合は0を返します。<br>
	 *         COUNT文のため、nullを考慮する必要はありません。
	 *         </p>
	 */
	public Long getCountByUserId(int userId);

	/**
	 * <p>
	 * ユーザIDをキーにアクティビティの一覧を取得します。
	 * </p>
	 * 
	 * @param userId
	 * @param limit
	 *            取得開始ページ
	 * @param offset
	 *            最大取得件数
	 * @return アクティビティ一覧を格納したList
	 */
	public List<Activity> findActivitiesByUserId(int userId, int limit,
			int offset);

	/**
	 * <p>
	 * 新規アクティビティを追加します。
	 * </p>
	 * 
	 * @param conn
	 *            DB接続
	 * @param activity
	 *            アクティビティ情報Entity
	 * @throws SQLException
	 *             DB更新エラー
	 */
	public void addActivityToActivities(Connection conn, Activity activity)
			throws SQLException;

	/**
	 * <p>
	 * ユーザIDをキーにアクティビティのソート順の最大値を取得します。
	 * </p>
	 * 
	 * @param userId
	 * @return アクティビティのソート順最大値<br>
	 *         アクティビティが存在しない場合はnullを返します。
	 */
	public Integer getMaxSeqOfActivities(int userId);

	/**
	 * <p>
	 * 払い出されたアクティビティIDを取得します。
	 * </p>
	 * 
	 * @param title
	 * @return DBから払い出されたアクティビティID<br>
	 *         アクティビティが存在しない場合はnullを返します。
	 */
	public Integer getLastInsertId(String title);

	/**
	 * <p>
	 * indexesテーブルにアクティビティ情報を追加します。
	 * </p>
	 * 
	 * @param conn
	 *            DB接続
	 * @param activity
	 *            アクティビティ情報Entity
	 * @param userId
	 * @throws SQLException
	 *             DB更新エラー
	 */
	public void addActivityToIndexes(Connection conn, Activity activity,
			int userId) throws SQLException;

	/**
	 * <p>
	 * アクティビティ名を取得します。
	 * </p>
	 * 
	 * @param activityId
	 * @return アクティビティ情報
	 */
	public Activity getActivityInfo(int activityId);

}
