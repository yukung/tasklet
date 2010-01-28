/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */

package tasklet.form;

import java.sql.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.struts.validator.ValidatorActionForm;

import tasklet.common.Priority;
import tasklet.util.DateConverter;
import tasklet.util.EnumConverter;

/**
 * タスク情報修正画面のActionFormです。
 *
 * @author Y.Ikeda
 *
 */
@SuppressWarnings("serial")
public class ModifyTaskForm extends ValidatorActionForm {

	/** タスクID */
	private String id;

	/** タスク名 */
	private String title;

	/** 優先度 */
	private Priority priority;

	/** 期限 */
	private String period;

	/** 見積時間 */
	private String estimatedTime;

	/**
	 * 文字列 → 列挙型へのコンバータを登録
	 */
	static {
		EnumConverter enumConverter = new EnumConverter();
		ConvertUtils.register(enumConverter, Priority.class);
	}

	/**
	 * 文字列 → Date型へのコンバータを登録
	 */
	static {
		DateConverter dateConverter = new DateConverter();
		ConvertUtils.register(dateConverter, Date.class);
	}

	/**
	 * タスクIDを取得します。
	 * @return タスクID
	 */
	public String getId() {
	    return id;
	}

	/**
	 * タスクIDを設定します。
	 * @param id タスクID
	 */
	public void setId(String id) {
	    this.id = id;
	}

	/**
	 * タスク名を取得します。
	 * @return タスク名
	 */
	public String getTitle() {
	    return title;
	}

	/**
	 * タスク名を設定します。
	 * @param title タスク名
	 */
	public void setTitle(String title) {
	    this.title = title;
	}

	/**
	 * 優先度を取得します。
	 * @return 優先度
	 */
	public Priority getPriority() {
	    return priority;
	}

	/**
	 * タスク情報修正画面の優先度プルダウンの要素を取得します。
	 * @return タスク情報修正画面の優先度プルダウンの要素
	 */
	public Priority[] getPriorities() {
		return Priority.values();
	}

	/**
	 * 優先度を設定します。
	 * @param priority 優先度
	 */
	public void setPriority(Priority priority) {
	    this.priority = priority;
	}

	/**
	 * 期限を取得します。
	 * @return 期限
	 */
	public String getPeriod() {
	    return period.replaceAll("-", "/");
	}

	/**
	 * 期限を設定します。
	 * @param period 期限
	 */
	public void setPeriod(String period) {
	    this.period = period;
	}

	/**
	 * 見積時間を取得します。
	 * @return 見積時間
	 */
	public String getEstimatedTime() {
	    return estimatedTime;
	}

	/**
	 * 見積時間を設定します。
	 * @param estimatedTime 見積時間
	 */
	public void setEstimatedTime(String estimatedTime) {
	    this.estimatedTime = estimatedTime;
	}
}
