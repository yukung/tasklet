/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */

package tasklet.form;

import java.sql.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.struts.validator.ValidatorForm;

import tasklet.util.DateConverter;

/**
 * タスク情報詳細画面のActionFormです。
 *
 * @author Y.Ikeda
 *
 */
@SuppressWarnings("serial")
public class DetailTaskForm extends ValidatorForm {

	/** 紐づくタスクID */
	private String taskId;

	/** 今回の実績時間 */
	private String actualTime;

	/** 追加するメモの内容 */
	private String memo;

	/**
	 * 文字列 → Date型へのコンバータを登録
	 */
	static {
		DateConverter dateConverter = new DateConverter();
		ConvertUtils.register(dateConverter, Date.class);
	}

	/**
	 * 紐づくタスクIDを取得します。
	 * @return 紐づくタスクID
	 */
	public String getTaskId() {
	    return taskId;
	}

	/**
	 * 紐づくタスクIDを設定します。
	 * @param taskId 紐づくタスクID
	 */
	public void setTaskId(String taskId) {
	    this.taskId = taskId;
	}

	/**
	 * 今回の実績時間を取得します。
	 * @return 今回の実績時間
	 */
	public String getActualTime() {
	    return actualTime;
	}

	/**
	 * 今回の実績時間を設定します。
	 * @param actualTime 今回の実績時間
	 */
	public void setActualTime(String actualTime) {
	    this.actualTime = actualTime;
	}

	/**
	 * 追加するメモの内容を取得します。
	 * @return 追加するメモの内容
	 */
	public String getMemo() {
	    return memo;
	}

	/**
	 * 追加するメモの内容を設定します。
	 * @param memo 追加するメモの内容
	 */
	public void setMemo(String memo) {
	    this.memo = memo;
	}

}
