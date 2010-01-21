/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */

package tasklet.entity;

import java.sql.Timestamp;

/**
 * メモ情報を表すBeanクラスです。
 *
 * @author Y.Ikeda
 *
 */
public class Memo {

	/** ID */
	private int id;

	/** 紐づくタスクID */
	private int taskId;

	/** ソート順 */
	private int seq;

	/** 内容 */
	private String contents;

	/** 作成タイムスタンプ */
	private Timestamp createdOn;

	/** 更新タイムスタンプ */
	private Timestamp updatedOn;

	/**
	 * IDを取得します。
	 * @return ID
	 */
	public int getId() {
	    return id;
	}

	/**
	 * IDを設定します。
	 * @param id ID
	 */
	public void setId(int id) {
	    this.id = id;
	}

	/**
	 * 紐づくタスクIDを取得します。
	 * @return 紐づくタスクID
	 */
	public int getTaskId() {
	    return taskId;
	}

	/**
	 * 紐づくタスクIDを設定します。
	 * @param taskId 紐づくタスクID
	 */
	public void setTaskId(int taskId) {
	    this.taskId = taskId;
	}

	/**
	 * ソート順を取得します。
	 * @return ソート順
	 */
	public int getSeq() {
	    return seq;
	}

	/**
	 * ソート順を設定します。
	 * @param seq ソート順
	 */
	public void setSeq(int seq) {
	    this.seq = seq;
	}

	/**
	 * 内容を取得します。
	 * @return 内容
	 */
	public String getContents() {
	    return contents;
	}

	/**
	 * 内容を設定します。
	 * @param contents 内容
	 */
	public void setContents(String contents) {
	    this.contents = contents;
	}

	/**
	 * 作成タイムスタンプを取得します。
	 * @return 作成タイムスタンプ
	 */
	public Timestamp getCreatedOn() {
	    return createdOn;
	}

	/**
	 * 作成タイムスタンプを設定します。
	 * @param createdOn 作成タイムスタンプ
	 */
	public void setCreatedOn(Timestamp createdOn) {
	    this.createdOn = createdOn;
	}

	/**
	 * 更新タイムスタンプを取得します。
	 * @return 更新タイムスタンプ
	 */
	public Timestamp getUpdatedOn() {
	    return updatedOn;
	}

	/**
	 * 更新タイムスタンプを設定します。
	 * @param updatedOn 更新タイムスタンプ
	 */
	public void setUpdatedOn(Timestamp updatedOn) {
	    this.updatedOn = updatedOn;
	}

}
