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
import org.yukung.tasklet.dto.TaskDto;
import org.yukung.tasklet.exception.DataAccessException;

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
	List<TaskDto> show(int activityId);

	/**
	 * <p>
	 * アクティビティID及びアクティビティ名を取得します。
	 * </p>
	 * 
	 * @param activityId
	 * @return アクティビティ情報
	 * @throws DataAccessException
	 *             アクティビティが見つからない場合
	 */
	ActivityDto getActivityInfo(int activityId) throws DataAccessException;

}
