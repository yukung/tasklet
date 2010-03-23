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
package org.yukung.tasklet.dto;

import java.util.Calendar;
import java.util.Date;

import org.yukung.tasklet.common.Priority;
import org.yukung.tasklet.common.Status;

/**
 * <p>
 * タスク一覧画面の表示用Beanクラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class TaskDto {

	/** ID */
	private String id;

	/** タスク名 */
	private String title;

	/** 優先度 */
	private Priority priority;

	/** タスクの状態 */
	private Status status;

	/** 期限 */
	private Date period;

	/** 完了日 */
	private Date finishedOn;

	/** 見積時間 */
	private String estimatedTime;

	/** 実績時間 */
	private String actualTime;

	/** メモ件数 */
	private String memoCount;

	/**
	 * IDを取得します。
	 * 
	 * @return ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * IDを設定します。
	 * 
	 * @param id
	 *            ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * タスク名を取得します。
	 * 
	 * @return タスク名
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * タスク名を設定します。
	 * 
	 * @param title
	 *            タスク名
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 優先度を取得します。
	 * 
	 * @return 優先度
	 */
	public Priority getPriority() {
		return priority;
	}

	/**
	 * 優先度を設定します。
	 * 
	 * @param priority
	 *            優先度
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	/**
	 * タスクの状態を取得します。
	 * 
	 * @return タスクの状態
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * タスクの状態を設定します。
	 * 
	 * @param status
	 *            タスクの状態
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * 期限を取得します。
	 * 
	 * @return 期限
	 */
	public Date getPeriod() {
		return period;
	}

	/**
	 * 期限を設定します。
	 * 
	 * @param period
	 *            期限
	 */
	public void setPeriod(Date period) {
		this.period = period;
	}

	public boolean isOverdue() {
		if (getPeriod() == null) {
			return false;
		}
		Calendar today = Calendar.getInstance();
		Calendar period = Calendar.getInstance();
		period.setTime(getPeriod());

		// Calendar#compareTo(anotherCalendar)メソッドは、ミリ秒単位の比較を行うため、
		// 期日と今日の日付が同じ日の場合は期日オーバーと判定されてしまう。
		// （期日はその日の午前0時で表されるため）
		// これを防ぐため、期限を1日ずらすことで今日が期限のタスクを期限オーバーに含めないようにする。
		period.add(Calendar.DAY_OF_MONTH, 1);

		int result = today.compareTo(period);
		// 戻り値が -1,0 は期限内
		return result == 1 ? true : false;
	}

	/**
	 * 完了日を取得します。
	 * 
	 * @return 完了日
	 */
	public Date getFinishedOn() {
		return finishedOn;
	}

	/**
	 * 完了日を設定します。
	 * 
	 * @param finishedOn
	 *            完了日
	 */
	public void setFinishedOn(Date finishedOn) {
		this.finishedOn = finishedOn;
	}

	/**
	 * 見積時間を取得します。
	 * 
	 * @return 見積時間
	 */
	public String getEstimatedTime() {
		return estimatedTime;
	}

	/**
	 * 見積時間を設定します。
	 * 
	 * @param estimatedTime
	 *            見積時間
	 */
	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	/**
	 * 実績時間を取得します。
	 * 
	 * @return 実績時間
	 */
	public String getActualTime() {
		return actualTime;
	}

	/**
	 * 実績時間を設定します。
	 * 
	 * @param actualTime
	 *            実績時間
	 */
	public void setActualTime(String actualTime) {
		this.actualTime = actualTime;
	}

	/**
	 * メモ件数を取得します。
	 * 
	 * @return メモ件数
	 */
	public String getMemoCount() {
		return memoCount;
	}

	/**
	 * メモ件数を設定します。
	 * 
	 * @param memoCount
	 *            メモ件数
	 */
	public void setMemoCount(String memoCount) {
		this.memoCount = memoCount;
	}
}
