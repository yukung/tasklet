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
	 */
	public long getCountByUserId(int userId);

	/**
	 * @param userId
	 * @param offset
	 *            取得開始ページ
	 * @param limit
	 *            最大取得件数
	 * @return アクティビティ一覧を格納した配列
	 */
	public List<Activity> findActivitiesByUserId(int userId, int offset,
			int limit);
}
