/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.dao;

import java.util.List;

import tasklet.entity.Memo;
import tasklet.entity.Task;

/**
 * タスク情報DAOインタフェースです。
 *
 * @author Y.Ikeda
 *
 */
public interface TaskDao {

	/**
	 * アクティビティIDをキーにタスク情報エンティティを取得します。
	 *
	 * @param activityId
	 * @return タスク情報を格納したList
	 */
	public List<Task> findTasksByActivityId(int activityId);

	/**
	 * アクティビティIDをキーにアクティビティのタイトルを取得します。
	 *
	 * @param activityId
	 * @return アクティビティのタイトル
	 */
	public String getActivityTitle(int activityId);

	/**
	 * タスク情報をタスクテーブルに登録します。
	 *
	 * @param task タスク情報オブジェクト
	 */
	public void addTasks(Task task);

	/**
	 * タスクIDをキーにタスク情報エンティティを取得します。
	 *
	 * @param taskId
	 * @return タスク情報エンティティ
	 */
	public Task getTaskDetailAndMemos(int taskId);

	/**
	 * タスク情報を元にタスクを更新します。
	 *
	 * @param task
	 */
	public void updateTask(Task task);

	/**
	 * タスクIDをキーにメモテーブルのソート順を最大値を取得します。
	 * @param taskId
	 * @return ソート順の最大値
	 */
	public Integer getMaxSequenceOfMemos(int taskId);

	/**
	 * メモ情報をメモテーブルに追加します。
	 *
	 * @param memo
	 */
	public void addMemos(Memo memo);

	/**
	 * タスクIDをキーにアクティビティIDを取得します。
	 *
	 * @param taskId
	 * @return アクティビティID
	 */
	public int getActivityIdByTaskId(int taskId);
}
