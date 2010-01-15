/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */

package tasklet.util;

import org.apache.commons.beanutils.Converter;

/**
 * ActionFormからEntityへの変換を行う際、列挙型の項目の文字列→列挙型への
 * コンバートを行うユーティリティクラスです。
 *
 * @author Y.Ikeda
 *
 */
public class EnumConverter implements Converter {

	/* (非 Javadoc)
	 * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public Object convert(Class type, Object value) {
		return Enum.valueOf(type, value.toString());
	}

}
