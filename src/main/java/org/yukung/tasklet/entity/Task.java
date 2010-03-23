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
package org.yukung.tasklet.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.yukung.tasklet.common.Priority;
import org.yukung.tasklet.common.Status;

/**
 * <p>
 * カテゴリ情報を表すBeanクラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class Task {

	/** ID */
	private int id;

	/** アクティビティID */
	private int activityId;

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
	private double estimatedTime;

	/** 実績時間 */
	private double actualTime;

	/** メモ一覧 */
	private List<Memo> memos;

	/** 作成タイムスタンプ */
	private Timestamp createdOn;

	/** 更新タイムスタンプ */
	private Timestamp updatedOn;

	/**
	 * IDを取得します。
	 * 
	 * @return ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * IDを設定します。
	 * 
	 * @param id
	 *            ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * アクティビティIDを取得します。
	 * 
	 * @return アクティビティID
	 */
	public int getActivityId() {
		return activityId;
	}

	/**
	 * アクティビティIDを設定します。
	 * 
	 * @param activityId
	 *            アクティビティID
	 */
	public void setActivityId(int activityId) {
		this.activityId = activityId;
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

	public int getPriorityCode() {
		return priority.getCode();
	}

	public void setPriorityCode(int code) {
		for (Priority element : Priority.values()) {
			if (element.getCode() == code) {
				priority = element;
				return;
			}
		}
		throw new IllegalArgumentException("No such priority code.");
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

	public int getStatusCode() {
		return status.getCode();
	}

	public void setStatusCode(int code) {
		for (Status element : Status.values()) {
			if (element.getCode() == code) {
				status = element;
				return;
			}
		}
		throw new IllegalArgumentException("No such priority code.");
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
	public double getEstimatedTime() {
		return estimatedTime;
	}

	/**
	 * 見積時間を設定します。
	 * 
	 * @param estimatedTime
	 *            見積時間
	 */
	public void setEstimatedTime(double estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	/**
	 * 実績時間を取得します。
	 * 
	 * @return 実績時間
	 */
	public double getActualTime() {
		return actualTime;
	}

	/**
	 * 実績時間を設定します。
	 * 
	 * @param actualTime
	 *            実績時間
	 */
	public void setActualTime(double actualTime) {
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

	/**
	 * 作成タイムスタンプを取得します。
	 * 
	 * @return 作成タイムスタンプ
	 */
	public Timestamp getCreatedOn() {
		return createdOn;
	}

	/**
	 * 作成タイムスタンプを設定します。
	 * 
	 * @param createdOn
	 *            作成タイムスタンプ
	 */
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * 更新タイムスタンプを取得します。
	 * 
	 * @return 更新タイムスタンプ
	 */
	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * 更新タイムスタンプを設定します。
	 * 
	 * @param updatedOn
	 *            更新タイムスタンプ
	 */
	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}
}
