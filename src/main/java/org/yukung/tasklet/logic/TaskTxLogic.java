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
import org.yukung.tasklet.common.Status;
import org.yukung.tasklet.dao.ActivityDao;
import org.yukung.tasklet.dao.MemoDao;
import org.yukung.tasklet.dao.TaskDao;
import org.yukung.tasklet.entity.Memo;
import org.yukung.tasklet.entity.Task;
import org.yukung.tasklet.exception.TaskletException;
import org.yukung.tasklet.factory.DaoFactory;

/**
 * <p>
 * タスク関連のトランザクション単位を規定するクラスです。<br>
 * 1メソッドが1トランザクションを表現します。
 * </p>
 * 
 * @author yukung
 * 
 */
public class TaskTxLogic {

	/** アクティビティ情報DAO */
	private ActivityDao activityDao;

	/** タスク情報DAO */
	private TaskDao taskDao;

	/** メモ情報DAO */
	private MemoDao memoDao;

	/**
	 * <p>
	 * デフォルトコンストラクタ。
	 * </p>
	 * <p>
	 * リソース有効利用の観点から、各Serviceクラスから利用する場合は<br>
	 * 極力Service自身が持つDAOを渡す形で利用してください。
	 * </p>
	 */
	public TaskTxLogic() {
		this(DaoFactory.getInstance().createTaskDao(), DaoFactory.getInstance()
				.createMemoDao());
	}

	/**
	 * <p>
	 * コンストラクタ。
	 * </p>
	 * 
	 * @param taskDao
	 * @param memoDao
	 */
	public TaskTxLogic(TaskDao taskDao, MemoDao memoDao) {
		this.activityDao = null;
		this.taskDao = taskDao;
		this.memoDao = memoDao;
	}

	/**
	 * <p>
	 * コンストラクタ。
	 * </p>
	 * 
	 * @param activityDao
	 * @param taskDao
	 */
	public TaskTxLogic(ActivityDao activityDao, TaskDao taskDao) {
		this.activityDao = activityDao;
		this.taskDao = taskDao;
		this.memoDao = null;
	}

	/**
	 * @param activityDao
	 * @param taskDao
	 * @param memoDao
	 */
	public TaskTxLogic(ActivityDao activityDao, TaskDao taskDao, MemoDao memoDao) {
		this.activityDao = activityDao;
		this.taskDao = taskDao;
		this.memoDao = memoDao;
	}

	/**
	 * <p>
	 * タスク情報を更新します。
	 * </p>
	 * 
	 * @param task
	 *            タスク情報Entity
	 * @param memo
	 *            メモ情報Entity
	 * @exception DB更新時のエラー
	 */
	public void update(Task task, Memo memo) throws TaskletException {
		Connection conn = null;
		try {
			conn = DaoFactory.getInstance().getConnection();
			conn.setAutoCommit(false);

			if (task.getStatus() == Status.FINISH) {
				// 完了チェックがある場合は完了処理
				taskDao.completeTask(conn, task);
				int count = taskDao.getIncompleteCount(task.getActivityId())
						.intValue();
				if (count == 0) {
					activityDao.completeActivity(conn, task.getActivityId());
				}
			} else {
				// tasksテーブルへタスクを更新
				taskDao.updateTask(conn, task);
				activityDao.updateActivity(conn, task.getActivityId());
			}

			// メモ欄の入力があった場合はメモテーブルにデータを追加
			if (!memo.getContents().equals("")) {
				// メモテーブルの最新ソート順を追加
				int seq = getSeq(task);
				memo.setSeq(seq);

				// タスク情報のIDとメモ情報のIDを紐付け
				memo.setTaskId(task.getId());

				// memosテーブルへタスクを更新
				memoDao.addMemoToMemos(conn, memo);
			}

			DbUtils.commitAndCloseQuietly(conn);
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			throw new TaskletException(e.getMessage(), e);
		}
	}

	/**
	 * <p>
	 * メモの最新順を取得します。
	 * </p>
	 * 
	 * @param task
	 * @return ソート順の最大値
	 */
	private int getSeq(Task task) {
		// ソート順の取得
		Integer seq = memoDao.getMaxSeqOfMemos(task.getId());
		if (seq == null) {
			return 0;
		}
		return seq.intValue() + 1;
	}

	/**
	 * <p>
	 * タスクを完了します。
	 * </p>
	 * 
	 * @param activityId
	 * @param checked
	 *            完了対象のタスクIDの配列
	 * @throws TaskletException
	 *             DB更新時のエラー
	 */
	public void complete(int activityId, String[] checked)
			throws TaskletException {
		Connection conn = null;
		try {
			conn = DaoFactory.getInstance().getConnection();
			conn.setAutoCommit(false);

			taskDao.completeTasks(conn, checked);
			int count = taskDao.getIncompleteCount(activityId).intValue();
			if (count == 0) {
				activityDao.completeActivity(conn, activityId);
			}

			DbUtils.commitAndCloseQuietly(conn);
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			throw new TaskletException(e.getMessage(), e);
		}
	}

	/**
	 * <p>
	 * タスクを削除します。
	 * </p>
	 * 
	 * @param checked
	 *            削除対象のタスクIDの配列
	 * @exception TaskletException
	 *                DB更新時のエラー
	 */
	public void remove(String[] checked) throws TaskletException {
		Connection conn = null;
		try {
			conn = DaoFactory.getInstance().getConnection();
			conn.setAutoCommit(false);

			memoDao.removeMemos(conn, checked);
			taskDao.removeTasks(conn, checked);

			DbUtils.commitAndCloseQuietly(conn);
		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			throw new TaskletException(e.getMessage(), e);
		}
	}

	/**
	 * <p>
	 * アクティビティとそれに紐づくタスク、メモを全て削除します。
	 * </p>
	 * 
	 * @param activityId
	 * @throws TaskletException
	 *             DB更新時のエラー
	 */
	public void delete(int activityId) throws TaskletException {
		Connection conn = null;
		try {
			conn = DaoFactory.getInstance().getConnection();
			conn.setAutoCommit(false);

			deleteMemos(conn, activityId);
			deleteTasks(conn, activityId);
			activityDao.deleteIndexes(conn, activityId);
			activityDao.deleteActivities(conn, activityId);

			DbUtils.commitAndCloseQuietly(conn);

		} catch (SQLException e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			throw new TaskletException(e.getMessage(), e);
		}
	}

	/**
	 * <p>
	 * アクティビティIDに紐づくタスク全てを削除します。
	 * </p>
	 * 
	 * @param conn
	 *            DB接続
	 * @param activityId
	 *            アクティビティID
	 * 
	 * @throws SQLException
	 *             DB更新エラー
	 */
	private void deleteTasks(Connection conn, int activityId)
			throws SQLException {
		List<Object[]> tasks = taskDao.findTaskIdByActivityId(activityId);
		if (tasks.size() != 0) {
			String[] taskIds = new String[tasks.size()];
			int idx = 0;
			for (Object[] array : tasks) {
				for (Object param : array) {
					taskIds[idx] = String.valueOf(param);
				}
				idx++;
			}
			taskDao.deleteTasksFromActivity(conn, taskIds);
		}
	}

	/**
	 * <p>
	 * アクティビティIDに紐づくメモ全てを削除します。
	 * </p>
	 * 
	 * @param conn
	 *            DB接続
	 * @param activityId
	 *            アクティビティID
	 * 
	 * @throws SQLException
	 *             DB更新エラー
	 */
	private void deleteMemos(Connection conn, int activityId)
			throws SQLException {
		List<Object[]> memos = memoDao.findMemoIdByActivityId(activityId);
		if (memos.size() != 0) {
			String[] memoIds = new String[memos.size()];
			int idx = 0;
			for (Object[] array : memos) {
				for (Object param : array) {
					memoIds[idx] = String.valueOf(param);
				}
				idx++;
			}
			memoDao.deleteMemosFromActivity(conn, memoIds);
		}
	}
}
