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

import org.yukung.tasklet.entity.Task;
import org.yukung.tasklet.exception.TaskletException;

/**
 * <p>
 * タスク情報DAOインタフェースです。
 * </p>
 * 
 * @author yukung
 * 
 */
public interface TaskDao {

	/**
	 * <p>
	 * アクティビティIDをキーにタスクの一覧を取得します。
	 * </p>
	 * 
	 * @param activityId
	 * @return タスク一覧を格納したList
	 */
	public List<Task> findTasksByActivityId(int activityId);

	/**
	 * <p>
	 * 新規タスクを追加します。
	 * </p>
	 * 
	 * @param task
	 *            タスク情報Entity
	 * @throws TaskletException
	 *             DB更新エラー
	 */
	public void addTask(Task task) throws TaskletException;

	/**
	 * @param taskId
	 * @return タスク詳細情報を格納したEntity
	 */
	public Task getTask(int taskId);

	/**
	 * <p>
	 * タスクIDをキーにアクティビティIDを取得します。
	 * </p>
	 * 
	 * @param taskId
	 * @return アクティビティID
	 */
	public Integer getActivityIdByTaskId(int taskId);

	/**
	 * <p>
	 * タスクIDをキーに実績時間を取得します。
	 * </p>
	 * 
	 * @param taskId
	 * @return 実績時間
	 */
	public Double getActualTimeByTaskId(int taskId);

	/**
	 * <p>
	 * タスク情報を元にタスクを更新します。
	 * </p>
	 * 
	 * @param conn
	 *            DB接続
	 * @param task
	 *            タスク情報Entity
	 * @throws SQLException
	 *             DB更新エラー
	 */
	public void updateTask(Connection conn, Task task) throws SQLException;

	/**
	 * <p>
	 * タスク情報を元にタスクを修正します。
	 * </p>
	 * 
	 * @param task
	 *            タスク情報Entity
	 * @throws TaskletException
	 *             DB更新エラー
	 */
	public void modifyTask(Task task) throws TaskletException;

	/**
	 * <p>
	 * 選択されたタスクを完了します。
	 * </p>
	 * 
	 * @param conn
	 *            DB接続
	 * @param checked
	 *            選択されたタスクIDの配列
	 * @throws SQLException
	 *             DB更新エラー
	 */
	public void completeTasks(Connection conn, String[] checked)
			throws SQLException;

	/**
	 * <p>
	 * 未完了のタスク件数を取得します。
	 * </p>
	 * 
	 * @param activityId
	 * @return 未完了のタスク件数
	 */
	public Integer getIncompleteCount(int activityId);

	/**
	 * <p>
	 * 選択されたタスクを削除します。
	 * </p>
	 * 
	 * @param conn
	 * @param checked
	 * @exception SQLException
	 *                DB更新エラー
	 */
	public void removeTasks(Connection conn, String[] checked)
			throws SQLException;

}