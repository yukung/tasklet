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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.yukung.tasklet.exception.DataAccessException;

/**
 * パスワードに関する変換処理を行うユーティリティクラスです。
 * 
 * @author yukung
 * 
 */
public final class PasswordUtil {

	/**
	 * <p>
	 * コンストラクタ。状態を持つ必要がないユーティリティクラスであるため、インスタンス化できません。
	 * </p>
	 */
	private PasswordUtil() {
	}

	/**
	 * <p>
	 * パスワードを暗号化します。
	 * </p>
	 * 
	 * @param password
	 * @return MD5でダイジェストされたパスワード<br>
	 *         引数がnullもしくは空文字の場合は空文字を返します。
	 */
	public static String encrypt(String password) {
		if (password == null || (password.length() == 0)) {
			return "";
		} else {
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				throw new DataAccessException(e.getMessage(), e);
			}
			byte[] digest = md.digest(password.getBytes());
			return toHexString(digest);
		}
	}

	/**
	 * <p>
	 * MD5でダイジェストした値を、16進数の文字列に変換します。
	 * </p>
	 * 
	 * @param digest
	 * @return 16進数に変換したMD5ダイジェスト文字列
	 */
	private static String toHexString(byte[] digest) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < digest.length; i++) {
			int n = digest[i] & 0xff;
			if (n < 16) {
				// 16以下の数の場合は頭に0を付ける
				sb.append("0");
			}
			sb.append(Integer.toHexString(n).toUpperCase());
		}
		return sb.toString();
	}
}
