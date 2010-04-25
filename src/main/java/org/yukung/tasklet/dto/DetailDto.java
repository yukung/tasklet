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

import java.util.Date;
import java.util.List;

import org.yukung.tasklet.common.Priority;
import org.yukung.tasklet.common.Status;
import org.yukung.tasklet.entity.Memo;
import org.yukung.tasklet.utils.CalculateUtil;

/**
 * <p>
 * タスク詳細画面の表示用Beanクラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class DetailDto {

	/** タスクID */
	private String id;

	/** タスク名 */
	private String title;

	/** 優先度 */
	private Priority priority;

	/** 状態 */
	private Status status;

	/** 期限 */
	private Date period;

	/** 完了日 */
	private Date finishedOn;

	/** 見積時間 */
	private String estimatedTime;

	/** 実績時間 */
	private String actualTime;

	/** メモ一覧 */
	private List<Memo> memos;

	/**
	 * タスクIDを取得します。
	 * 
	 * @return タスクID
	 */
	public String getId() {
		return id;
	}

	/**
	 * タスクIDを設定します。
	 * 
	 * @param id
	 *            タスクID
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
	 * 状態を取得します。
	 * 
	 * @return 状態
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * 状態を設定します。
	 * 
	 * @param status
	 *            状態
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * 期限切れかどうかを返します。
	 * 
	 * @return 期限切れの場合 true<br>
	 *         そうでない場合 false
	 */
	public boolean isOverdue() {
		return CalculateUtil.isOverdue((java.sql.Date) getPeriod());
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

	public long getDaysRemaining() {
		return CalculateUtil.calcDaysRemaining((java.sql.Date) getPeriod());
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
	 * メモ一覧を取得します。
	 * 
	 * @return メモ一覧
	 */
	public List<Memo> getMemos() {
		return memos;
	}

	/**
	 * メモ一覧を設定します。
	 * 
	 * @param memos
	 *            メモ一覧
	 */
	public void setMemos(List<Memo> memos) {
		this.memos = memos;
	}
}
