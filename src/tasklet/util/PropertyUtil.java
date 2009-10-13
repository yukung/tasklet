/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.util;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * プロジェクト固有のプロパティファイルを扱うユーティリティクラスです。
 * 
 * @author Y.Ikeda
 * 
 */
public class PropertyUtil {

	/** プロジェクト固有ベースネーム定数 */
	private static final String PROJECT_PROPERTY_BASE_NAME = "tasklet";

	/** プロパティ情報 */
	private static PropertyUtil property = null;

	/** リソースバンドル */
	private ResourceBundle resource = null;

	private PropertyUtil(String baseName) {
		resource = ResourceBundle.getBundle(baseName);
	}

	/**
	 * 当クラスのインスタンス取得時はこのメソッドを利用する。
	 * 
	 * @return PropertyUtilのシングルトンオブジェクト
	 */
	public static PropertyUtil getInstance(String baseName) {
		if (property == null) {
			property = new PropertyUtil(baseName);
		}
		return property;
	}

	/**
	 * 指定されたキーのプロパティ値を取得します。
	 * 
	 * @param キー
	 * @return プロパティ値
	 */
	public String getString(String key) {
		return resource.getString(key);
	}

	/**
	 * 指定されたキーのプロパティ値を取得します。
	 * 
	 * @param キー
	 * @return プロパティ値の文字列配列
	 */
	public String[] getStringArray(String key) {
		return resource.getStringArray(key);
	}

	/**
	 * プロパティのキーリストを取得します。
	 * 
	 * @return プロパティのキーリスト
	 */
	public Enumeration<String> getKeys() {
		return resource.getKeys();
	}

}
