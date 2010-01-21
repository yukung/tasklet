/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.dao;

import java.util.List;

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
}
