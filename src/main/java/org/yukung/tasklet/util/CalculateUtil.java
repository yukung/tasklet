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
package org.yukung.tasklet.util;

import java.sql.Date;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;

import org.yukung.tasklet.common.Status;
import org.yukung.tasklet.entity.Task;

/**
 * <p>
 * 計算処理を行うユーティリティクラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public final class CalculateUtil {

	/**
	 * <p>
	 * コンストラクタ。状態を持つ必要がないユーティリティクラスであるため、インスタンス化できません。
	 * </p>
	 */
	private CalculateUtil() {
	}

	/**
	 * <p>
	 * 達成率を計算します。
	 * </p>
	 * 
	 * @param tasks
	 *            タスク一覧
	 * @return 達成率<br>
	 *         割り切れない場合は0.0を返します。
	 */
	public static String calcAchievementRatio(List<Task> tasks) {
		double complete = 0.0;
		int amount = tasks.size();
		for (Task task : tasks) {
			if (task.getStatus() == Status.FINISH) {
				complete++;
			}
		}
		double ratio = (complete / (double) amount);
		if (Double.isNaN(ratio)) {
			ratio = 0.0;
		}
		NumberFormat percent = NumberFormat.getPercentInstance();
		return percent.format(ratio);
	}

	/**
	 * <p>
	 * 未完了のタスク件数を取得します。
	 * </p>
	 * 
	 * @param tasks
	 *            タスク一覧
	 * @return タスク残数
	 */
	public static String calcRemainingAmount(List<Task> tasks) {
		int complete = 0;
		int amount = tasks.size();
		for (Task task : tasks) {
			if (task.getStatus() == Status.FINISH) {
				complete++;
			}
		}
		return Integer.toString(amount - complete);
	}

	/**
	 * <p>
	 * 期限超過タスク数を取得します。
	 * </p>
	 * 
	 * @param tasks
	 *            タスク一覧
	 * @return 期限超過タスク数
	 */
	public static String calcOverdue(List<Task> tasks) {
		int amount = 0;
		Calendar today = Calendar.getInstance();
		for (Task task : tasks) {
			// 期限設定がないタスクはカウントしない
			Date periodAsDate = task.getPeriod();
			if (periodAsDate == null) {
				continue;
			}
			// 完了タスクはカウントしない
			if (task.getStatus() == Status.FINISH) {
				continue;
			}
			Calendar period = Calendar.getInstance();
			period.setTime(periodAsDate);

			/*
			 * Calendar#compareTo(anotherCalendar)メソッドは，ミリ秒単位の比較を行うため，
			 * 期日と今日の日付が同じ日の場合は期日オーバーと判定されてしまう。 （期日はその日の午前0時で表されるため）
			 * これを防ぐため，期限を1日ずらすことで期日オーバーとされないようにする。
			 */
			period.add(Calendar.DAY_OF_MONTH, 1);

			int result = today.compareTo(period);
			if (result == 1) {
				// 戻り値が-1,0は期限内なので除外
				amount++;
			}
		}
		return Integer.toString(amount);
	}

	/**
	 * <p>
	 * 予実比を取得します。
	 * </p>
	 * 
	 * @param tasks
	 *            タスク一覧
	 * @return 予実比<br>
	 *         割り切れない場合は0.0を返します。
	 */
	public static String calcRatioOfEstimateAndActual(List<Task> tasks) {
		double estimateTime, actualTime = 0.0;
		estimateTime = actualTime = 0.0;
		for (Task task : tasks) {
			estimateTime += task.getEstimatedTime();
			actualTime += task.getActualTime();
		}
		double ratio = actualTime / estimateTime;
		if (Double.isNaN(ratio)) {
			ratio = 0.0;
		}
		NumberFormat percent = NumberFormat.getPercentInstance();
		return percent.format(ratio);
	}

	/**
	 * <p>
	 * 見積時間合計を取得します。
	 * </p>
	 * 
	 * @param tasks
	 *            タスク一覧
	 * @return 見積時間合計
	 */
	public static String calcEstimatedTimeTotal(List<Task> tasks) {
		double estimatedTimeTotal = 0.0;
		for (Task task : tasks) {
			estimatedTimeTotal += task.getEstimatedTime();
		}
		return Double.toString(estimatedTimeTotal);
	}

	/**
	 * <p>
	 * 実績時間合計を取得します。
	 * </p>
	 * 
	 * @param tasks
	 *            タスク一覧
	 * @return 実績時間合計
	 */
	public static String calcActualTimeTotal(List<Task> tasks) {
		double actualTimeTotal = 0.0;
		for (Task task : tasks) {
			actualTimeTotal += task.getActualTime();
		}
		return Double.toString(actualTimeTotal);
	}

}
