/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */

package tasklet.util;

import java.sql.Date;
import org.apache.commons.beanutils.Converter;

/**
 * ActionFormからEntityへの変換を行う際、Date型の項目の文字列→ Date型への
 * コンバートを行うユーティリティクラスです。
 *
 * @author Y.Ikeda
 *
 */
public class DateConverter implements Converter {

	/* (非 Javadoc)
	 * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public Object convert(Class type, Object value) {
		if (value.toString().equals("")) {
			return null;
		} else {
			return Date.valueOf(value.toString().replace("/", "-"));
		}
	}

}
