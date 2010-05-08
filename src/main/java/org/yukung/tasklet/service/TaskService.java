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
package org.yukung.tasklet.service;

import java.util.List;

import org.yukung.tasklet.dto.ActivityDto;
import org.yukung.tasklet.dto.DetailDto;
import org.yukung.tasklet.dto.MemoDto;
import org.yukung.tasklet.dto.TaskDto;
import org.yukung.tasklet.entity.Memo;
import org.yukung.tasklet.entity.Task;
import org.yukung.tasklet.exception.DataAccessException;
import org.yukung.tasklet.exception.TaskletException;

/**
 * <p>
 * タスク関連のビジネスロジックを規定するインタフェースです。
 * </p>
 * 
 * @author yukung
 * 
 */
public interface TaskService {

	/**
	 * <p>
	 * アクティビティIDに紐づいたタスク一覧を取得します。
	 * </p>
	 * 
	 * @param activityId
	 * @return タスク一覧画面表示DTOを格納したList
	 */
	public List<TaskDto> show(int activityId);

	/**
	 * <p>
	 * アクティビティID及びアクティビティ名を取得します。
	 * </p>
	 * 
	 * @param activityId
	 * @param userId
	 *            TODO
	 * @return アクティビティ情報
	 * @throws DataAccessException
	 *             アクティビティが見つからない場合
	 */
	public ActivityDto getActivityInfo(int activityId, int userId)
			throws DataAccessException;

	/**
	 * <p>
	 * 新規タスクを追加します。
	 * </p>
	 * 
	 * @param task
	 *            タスク情報Entity
	 * @exception TaskletException
	 *                DB登録エラー
	 */
	public void add(Task task) throws TaskletException;

	/**
	 * <p>
	 * タスク詳細情報を取得します。
	 * </p>
	 * 
	 * @param taskId
	 * @return タスク詳細情報
	 */
	public DetailDto getTask(int taskId);

	/**
	 * <p>
	 * アクティビティIDを取得します。
	 * </p>
	 * 
	 * @param taskId
	 * @return アクティビティID
	 */
	public int getActivityId(int taskId);

	/**
	 * <p>
	 * タスク情報を更新します。
	 * </p>
	 * 
	 * @param task
	 *            タスク情報Entity
	 * @param memo
	 *            メモ情報Entity
	 * @throws TaskletException
	 *             DB更新エラー
	 */
	public void update(Task task, Memo memo) throws TaskletException;

	/**
	 * <p>
	 * タスクIDに紐づいたメモ一覧を取得します。
	 * </p>
	 * 
	 * @param taskId
	 * @return メモ一覧画面表示用DTOを格納したList
	 */
	public List<MemoDto> getMemos(int taskId);

	/**
	 * <p>
	 * 新規メモを追加します。
	 * </p>
	 * 
	 * @param memo
	 *            メモ情報Entity
	 * @exception TaskletException
	 *                DB登録エラー
	 */
	public void add(Memo memo) throws TaskletException;

	/**
	 * <p>
	 * タスク情報を修正します。
	 * </p>
	 * 
	 * @param task
	 * @throws TaskletException
	 *             DB更新エラー
	 */
	public void modify(Task task) throws TaskletException;

	/**
	 * <p>
	 * タスクを完了します。
	 * </p>
	 * 
	 * @param activityId
	 * @param checked
	 *            選択されたタスクID
	 * @throws TaskletException
	 *             DB更新エラー
	 */
	public void complete(int activityId, String[] checked)
			throws TaskletException;

	/**
	 * <p>
	 * タスクを削除します。
	 * </p>
	 * 
	 * @param checked
	 *            選択されたタスクID
	 * @throws TaskletException
	 *             DB更新エラー
	 */
	public void remove(String[] checked) throws TaskletException;

	/**
	 * <p>
	 * アクティビティとそれに紐づくタスク、メモを全て削除します。
	 * </p>
	 * 
	 * @param activityId
	 *            アクティビティID
	 * @throws TaskletException
	 *             DB更新エラー
	 */
	public void deleteAll(int activityId) throws TaskletException;

}
