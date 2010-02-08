/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */

package tasklet.form;

import org.apache.struts.validator.ValidatorForm;

/**
 * タスク操作用のActionFormです。
 *
 * @author Y.Ikeda
 *
 */
@SuppressWarnings("serial")
public class OperateTaskForm extends ValidatorForm {

	/** 選択されたタスク */
	private String[] selected;

	/**
	 * 選択されたタスクを取得します。
	 * @return 選択されたタスク
	 */
	public String[] getSelected() {
	    return selected;
	}

	/**
	 * 選択されたタスクを設定します。
	 * @param selected 選択されたタスク
	 */
	public void setSelected(String[] selected) {
	    this.selected = selected;
	}

}
