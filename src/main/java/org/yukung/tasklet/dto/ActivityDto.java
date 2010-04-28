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

import java.util.List;
import java.util.Map;

/**
 * <p>
 * アクティビティ一覧画面の表示用Beanクラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class ActivityDto {

	/** アクティビティID */
	private String id;

	/** アクティビティ名 */
	private String title;

	/** カテゴリ名 */
	private String categoryName;

	/** 未完了フラグ */
	private boolean isIncomplete;

	/** 達成率 */
	private String achievementRatio;

	/** タスク残数 */
	private String remainingAmount;

	/** 期限超過タスク数 */
	private String overdue;

	/** 予実比 */
	private String ratioOfEstimateAndActual;

	/** 見積時間合計 */
	private String estimatedTimeTotal;

	/** 実績時間合計 */
	private String actualTimeTotal;

	/** 他のアクティビティID */
	private List<Map<String, Object>> moreActivities;

	/**
	 * アクティビティIDを取得します。
	 * 
	 * @return アクティビティID
	 */
	public String getId() {
		return id;
	}

	/**
	 * アクティビティIDを設定します。
	 * 
	 * @param id
	 *            アクティビティID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * アクティビティ名を取得します。
	 * 
	 * @return アクティビティ名
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * アクティビティ名を設定します。
	 * 
	 * @param title
	 *            アクティビティ名
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * カテゴリ名を取得します。
	 * 
	 * @return カテゴリ名
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * カテゴリ名を設定します。
	 * 
	 * @param categoryName
	 *            カテゴリ名
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 未完了フラグを取得します。
	 * 
	 * @return 未完了フラグ
	 */
	public boolean isIncomplete() {
		return isIncomplete;
	}

	/**
	 * 未完了フラグを設定します。
	 * 
	 * @param isIncomplete
	 *            未完了フラグ
	 */
	public void setIncomplete(boolean isIncomplete) {
		this.isIncomplete = isIncomplete;
	}

	/**
	 * 達成率を取得します。
	 * 
	 * @return 達成率
	 */
	public String getAchievementRatio() {
		return achievementRatio;
	}

	/**
	 * 達成率を設定します。
	 * 
	 * @param achievementRatio
	 *            達成率
	 */
	public void setAchievementRatio(String achievementRatio) {
		this.achievementRatio = achievementRatio;
	}

	/**
	 * タスク残数を取得します。
	 * 
	 * @return タスク残数
	 */
	public String getRemainingAmount() {
		return remainingAmount;
	}

	/**
	 * タスク残数を設定します。
	 * 
	 * @param remainingAmount
	 *            タスク残数
	 */
	public void setRemainingAmount(String remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	/**
	 * 期限超過タスク数を取得します。
	 * 
	 * @return 期限超過タスク数
	 */
	public String getOverdue() {
		return overdue;
	}

	/**
	 * 期限超過タスク数を設定します。
	 * 
	 * @param overdue
	 *            期限超過タスク数
	 */
	public void setOverdue(String overdue) {
		this.overdue = overdue;
	}

	/**
	 * 予実比を取得します。
	 * 
	 * @return 予実比
	 */
	public String getRatioOfEstimateAndActual() {
		return ratioOfEstimateAndActual;
	}

	/**
	 * 予実比を設定します。
	 * 
	 * @param ratioOfEstimateAndActual
	 *            予実比
	 */
	public void setRatioOfEstimateAndActual(String ratioOfEstimateAndActual) {
		this.ratioOfEstimateAndActual = ratioOfEstimateAndActual;
	}

	/**
	 * 見積時間合計を取得します。
	 * 
	 * @return 見積時間合計
	 */
	public String getEstimatedTimeTotal() {
		return estimatedTimeTotal;
	}

	/**
	 * 見積時間合計を設定します。
	 * 
	 * @param estimatedTimeTotal
	 *            見積時間合計
	 */
	public void setEstimatedTimeTotal(String estimatedTimeTotal) {
		this.estimatedTimeTotal = estimatedTimeTotal;
	}

	/**
	 * 実績時間合計を取得します。
	 * 
	 * @return 実績時間合計
	 */
	public String getActualTimeTotal() {
		return actualTimeTotal;
	}

	/**
	 * 実績時間合計を設定します。
	 * 
	 * @param actualTimeTotal
	 *            実績時間合計
	 */
	public void setActualTimeTotal(String actualTimeTotal) {
		this.actualTimeTotal = actualTimeTotal;
	}

	/**
	 * 他のアクティビティIDを取得します。
	 * 
	 * @return 他のアクティビティID
	 */
	public List<Map<String, Object>> getMoreActivities() {
		return moreActivities;
	}

	/**
	 * 他のアクティビティIDを設定します。
	 * 
	 * @param moreActivities
	 *            他のアクティビティID
	 */
	public void setMoreActivities(List<Map<String, Object>> moreActivities) {
		this.moreActivities = moreActivities;
	}

}
