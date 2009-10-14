/**
 * Created 2009/09/03
 * Copyright (C) 2009  Yusuke Ikeda (ikeda.yusuke@gmail.com)
 *
 * This file is part of Tasklet.
 */
package tasklet.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 全てのアクションの基底クラスです。
 * <p>
 * アクションクラスを定義する際は、このクラスをオーバーライドする必要があります。
 * </p>
 * 
 * @autho r Y.Ikeda
 * 
 */
public abstract class AbstractAction extends Action {

	private static final Log LOG = LogFactory.getLog(AbstractAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {
			return doExecute(mapping, form, request, response);
		} catch (Exception e) {
			// 到達しない例外? struts-config.xmlのglobal扱いのエラー?
			LOG.error(e, e);
			throw e;
		}

	}

	/**
	 * 各アクションのエントリメソッドです。
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