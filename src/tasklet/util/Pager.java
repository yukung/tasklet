/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.util;

/**
 * 一覧画面のページング処理を実現するPagerクラスです。
 *
 * @author Y.Ikeda
 *
 */
public class Pager {

	/** 前のページ,次のページが存在するか */
	private final boolean prev, next;

	/** 前のページ番号,次のページ番号 */
	private final int prevPageNo, nextPageNo;

	/** 現在のページ位置,ページ内の件数 */
	private final int offset, limit;

	/** ページングするデータの全件数 */
	private final long count;

	/**
	 * ページングするデータのPagerオブジェクトを生成します。
	 *
	 * @param count ページングデータの最大件数
	 * @param pageNo 現在のページ番号
	 * @param limit ページングするデータの件数
	 */
	public Pager(long count, int pageNo, int limit) {
		this.prev = pageNo > 1;
		this.next = pageNo * limit < count;
		if (prev) {
			prevPageNo = pageNo - 1;
		} else {
			prevPageNo = 1;
		}
		if (next) {
			nextPageNo = pageNo + 1;
		} else {
			nextPageNo = (int) (count / limit);
		}

		int offset = (pageNo - 1) * limit;
		if (count < offset) {
			this.offset = 0;
		} else {
			this.offset = offset;
		}
		this.limit = limit;
		this.count = count;
	}

	/**
	 * 前のページが存在するかを返します。
	 * @return prev
	 */
	public boolean isPrev() {
	    return prev;
	}

	/**
	 * 次のページが存在するかを返します。
	 * @return next
	 */
	public boolean isNext() {
	    return next;
	}

	/**
	 * 前のページ番号を取得します。
	 * @return prevPageNo
	 */
	public int getPrevPageNo() {
	    return prevPageNo;
	}

	/**
	 * 次のページ番号を取得します。
	 * @return nextPageNo
	 */
	public int getNextPageNo() {
	    return nextPageNo;
	}

	/**
	 * 現在のページ位置を取得します。
	 * @return offset
	 */
	public int getOffset() {
	    return offset;
	}

	/**
	 * ページ内の件数を取得します。
	 * @return limit
	 */
	public int getLimit() {
	    return limit;
	}

	/**
	 * ページングするデータの全件数を取得します。
	 * @return ページングするデータの全件数
	 */
	public long getCount() {
	    return count;
	}

}