/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */

package tasklet.form;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author Y.Ikeda
 *
 */
@SuppressWarnings("serial")
public class AddMemoForm extends ValidatorForm {

	private String taskId;

	private String contents;

	/**
	 * taskIdを取得します。
	 * @return taskId
	 */
	public String getTaskId() {
	    return taskId;
	}

	/**
	 * taskIdを設定します。
	 * @param taskId taskId
	 */
	public void setTaskId(String taskId) {
	    this.taskId = taskId;
	}

	/**
	 * contentsを取得します。
	 * @return contents
	 */
	public String getContents() {
	    return contents;
	}

	/**
	 * contentsを設定します。
	 * @param contents contents
	 */
	public void setContents(String contents) {
	    this.contents = contents;
	}

}
