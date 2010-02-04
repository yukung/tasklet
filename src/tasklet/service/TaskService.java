/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.service;

import java.util.List;

import tasklet.TaskletException;
import tasklet.entity.Memo;
import tasklet.entity.Task;

/**
 * タスク関連のビジネスロジックを規定するインタフェースです。
 *
 * @author Y.Ikeda
 *
 */
public interface TaskService {

	/**
	 * アクティビティIDに紐づいたタスク一覧を取得します。
	 *
	 * @param activityId
	 * @return タスク一覧を格納したList
	 */
	public List<Task> show(int activityId);

	/**
	 * アクティビティIDに対応したアクティビティ名を取得します。
	 *
	 * @param activityId
	 * @return アクティビティのタイトル
	 */
	public String getActivityTitle(int activityId);

	/**
	 * タスクを登録します。
	 *
	 * @param task
	 * @throws TaskletException タスク登録時のエラー
	 */
	public void add(Task task) throws TaskletException;

	/**
	 * タスクIDをキーにタスク情報オブジェクトを取得します。
	 *
	 * @param taskId
	 * @return タスク情報オブジェクト
	 */
	public Task getTask(int taskId);

	/**
	 * タスク情報オブジェクトとメモ情報オブジェクトを元に、タスクを更新します。
	 *
	 * @param task
	 * @param memo
	 * @throws TaskletException タスク更新時のエラー
	 */
	public void update(Task task, Memo memo) throws TaskletException;

	/**
	 * タスクIDから紐づくアクティビティIDを取得します。
	 * @param taskId
	 * @return タスクIDに紐づくアクティビティID
	 */
	public int getActivityId(int taskId);

	/**
	 * タスクを修正します。
	 *
	 * @param task
	 * @throws TaskletException タスク修正時のエラー
	 */
	public void modify(Task task) throws TaskletException;

	/**
	 * タスクIDから紐づくメモ一覧を取得します。
	 *
	 * @param taskId
	 * @return タスクIDに紐づくメモ一覧
	 */
	public List<Memo> getMemos(int taskId);

	/**
	 * メモを登録します。
	 *
	 * @param memo
	 * @throws TaskletException メモ追加時のエラー
	 */
	public void add(Memo memo) throws TaskletException;

}
