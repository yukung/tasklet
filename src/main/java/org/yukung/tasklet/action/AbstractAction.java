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
package org.yukung.tasklet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * 全てのアクションの基底クラスです。<br>
 * アクションクラスを定義する際は、このクラスを継承する必要があります。
 * </p>
 * 
 * @author yukung
 * 
 */
public abstract class AbstractAction extends Action {

	private static final Log LOG = LogFactory.getLog(AbstractAction.class);

	/** アクションが成功した場合に使われるforward先の論理名 */
	public static final String SUCCESS = "success";

	/** アクションがキャンセルされた場合に使われるforward先の論理名 */
	public static final String CANCEL = "cancel";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			return doExecute(mapping, form, request, response);
		} catch (Exception e) {
			LOG.error(e, e);
			throw e;
		}
	}

	/**
	 * <p>
	 * 各アクションのエントリメソッドです。<br>
	 * アクションを定義する際は、このメソッドを必ず実装する必要があります。
	 * </p>
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return Forwardの定義名
	 */
	public abstract ActionForward doExecute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response);

	protected Log getLog() {
		return LOG;
	}
}
