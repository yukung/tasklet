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
package org.yukung.tasklet.utils;

/**
 * <p>
 * 一覧画面のページング処理を実現するPagerクラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public class Pager {

	/** 前のページ、次のページが存在するか */
	private final boolean prev, next;

	/** 前のページ番号、次のページ番号 */
	private final int prevPageNo, nextPageNo;

	/** 現在のページ位置、ページ内の件数 */
	private final int offset, limit;

	/** ページングするデータの全件数 */
	private final long count;

	/**
	 * <p>
	 * ページングするデータのPagerオブジェクトを生成します。
	 * </p>
	 * 
	 * @param count
	 *            ページングデータの最大件数
	 * @param pageNo
	 *            現在のページ番号
	 * @param limit
	 *            ページングするデータの件数
	 */
	public Pager(long count, int pageNo, int limit) {
		prev = pageNo > 1;
		next = pageNo * limit < count;
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
	 * 
	 * @return 前のページが存在する場合はtrue、そうでない場合はfalse
	 */
	public boolean isPrev() {
		return prev;
	}

	/**
	 * 次のページが存在するかを返します。
	 * 
	 * @return 次のページが存在する場合はtrue、そうでない場合はfalse
	 */
	public boolean isNext() {
		return next;
	}

	/**
	 * 前のページ番号を取得します。
	 * 
	 * @return 前のページ番号
	 */
	public int getPrevPageNo() {
		return prevPageNo;
	}

	/**
	 * 次のページ番号を取得します。
	 * 
	 * @return 次のページ番号
	 */
	public int getNextPageNo() {
		return nextPageNo;
	}

	/**
	 * 現在のページ位置を取得します。
	 * 
	 * @return 現在のページ位置
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * ページ内の件数を取得します。
	 * 
	 * @return ページ内の件数
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * ページングするデータの全件数を取得します。
	 * 
	 * @return ページングするデータの全件数
	 */
	public long getCount() {
		return count;
	}

}
