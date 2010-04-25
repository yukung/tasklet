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
 * 文字列処理を行うユーティリティクラスです。
 * </p>
 * 
 * @author yukung
 * 
 */
public final class StringUtil {

	/**
	 * <p>
	 * コンストラクタ。状態を持つ必要がないユーティリティクラスであるため、インスタンス化できません。
	 * </p>
	 */
	private StringUtil() {
	}

	/**
	 * <p>
	 * SQLのIN句に用いるバインド変数文字列を動的に生成します。
	 * </p>
	 * <p>
	 * 編集対象のIN句はSQLの最後である必要があります。
	 * </p>
	 * 
	 * @param params
	 *            バインド変数のパラメータ配列
	 * @param sql
	 *            編集対象のSQL
	 * @return 生成したSQL
	 */
	public static String createBindVariables(String[] params, String sql) {
		StringBuilder buffer = new StringBuilder(sql);
		buffer.append('(');
		for (int i = 0; i < params.length; i++) {
			buffer.append("?,");
		}
		// 最後のカンマは消す
		int index = buffer.lastIndexOf(",");
		buffer.deleteCharAt(index);
		buffer.append(')');
		return buffer.toString();
	}

}
