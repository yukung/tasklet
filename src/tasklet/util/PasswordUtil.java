/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */

package tasklet.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import tasklet.DataAccessException;

/**
 * パスワードに関する変換処理を行うユーティリティクラスです。
 *
 * @author Y.Ikeda
 *
 */
public class PasswordUtil {

	/**
	 * コンストラクタ。<br>
	 * 状態を持つ必要がないユーティリティクラスであるため、インスタンス化させないようにする。
	 */
	private PasswordUtil() {
	}

	/**
	 * パスワードを暗号化します。
	 * @param password
	 * @return MD5でダイジェストされたパスワード
	 */
	public static String encrypt(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
		byte[] digest = md.digest(password.getBytes());
		return byteArraytoHexString(digest);
	}

	/**
	 * MD5でダイジェストした値を、16進数の文字列に変換します。
	 *
	 * @param digest
	 * @return 16進数に変換したMD5ダイジェスト文字列
	 */
	private static String byteArraytoHexString(byte[] digest) {
		StringBuilder md5 = new StringBuilder();
		for (int i = 0; i < digest.length; i++) {
			int n = digest[i] & 0xff;
			if (n < 16) {
				// 16以下の数の場合は頭に0を付ける
				md5.append("0");
			}
			md5.append(Integer.toHexString(n).toUpperCase());
		}
		return md5.toString();
	}
}
