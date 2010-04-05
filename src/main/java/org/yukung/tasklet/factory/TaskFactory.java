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
package org.yukung.tasklet.factory;

import java.util.List;

import org.yukung.tasklet.dao.MemoDao;
import org.yukung.tasklet.dao.TaskDao;
import org.yukung.tasklet.entity.Memo;
import org.yukung.tasklet.entity.Task;

/**
 * <p>
 * タスク情報の関連を構築するFactoryクラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class TaskFactory {

	private TaskDao taskDao = DaoFactory.getInstance().createTaskDao();

	private MemoDao memoDao = DaoFactory.getInstance().createMemoDao();

	/**
	 * <p>
	 * メモ情報を格納したタスクの一覧を取得します。
	 * </p>
	 * 
	 * @param activityId
	 * @return メモ情報を持ったタスクのList
	 */
	public List<Task> getTasksByActivityId(int activityId) {
		List<Task> tasks = taskDao.findTasksByActivityId(activityId);

		// タスクが存在しない場合はnullを返す
		if (tasks.size() == 0) {
			return null;
		}

		for (Task task : tasks) {
			List<Memo> memos = memoDao.getMemosByTaskId(task.getId());
			task.setMemos(memos);
		}
		return tasks;
	}

	/**
	 * <p>
	 * メモ情報を格納したタスク詳細情報を取得します。
	 * </p>
	 * 
	 * @param taskId
	 * @return メモ情報を持ったタスクのEntity
	 */
	public Task getTask(int taskId) {
		Task task = taskDao.getTask(taskId);

		// タスクが存在しない場合はnullを返す
		if (task == null) {
			return null;
		}
		task.setMemos(memoDao.getMemosByTaskId(taskId));

		return task;
	}

}
