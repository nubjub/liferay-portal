/**
 * Copyright (c) 2000-2006 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.WebKeys;
import com.liferay.util.Validator;

import javax.portlet.RenderResponse;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * <a href="SectionTag.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class SectionTag extends TagSupport {

	public int doStartTag() throws JspException {
		TabsTag tabsTag = (TabsTag)findAncestorWithClass(this, TabsTag.class);

		if (tabsTag == null) {
			throw new JspException();
		}

		try {
			ServletRequest req = pageContext.getRequest();

			RenderResponse renderResponse = (RenderResponse)req.getAttribute(
				WebKeys.JAVAX_PORTLET_RESPONSE);

			String namespace = StringPool.BLANK;

			if (renderResponse != null) {
				namespace = renderResponse.getNamespace();
			}

			String sectionParam = tabsTag.getParam();
			String sectionName = tabsTag.getSectionName();
			Boolean sectionSelected = new Boolean(tabsTag.getSectionSelected());
			String sectionScroll = namespace + sectionParam + "TabsScroll";
			String sectionRedirectParams =
				"&scroll=" + sectionScroll + "&" + sectionParam + "=" +
					sectionName;

			tabsTag.incrementSection();

			req.setAttribute("liferay-ui:section:param", sectionParam);
			req.setAttribute("liferay-ui:section:name", sectionName);
			req.setAttribute("liferay-ui:section:selected", sectionSelected);
			req.setAttribute("liferay-ui:section:scroll", sectionScroll);

			pageContext.setAttribute("sectionSelected", sectionSelected);
			pageContext.setAttribute("sectionParam", sectionParam);
			pageContext.setAttribute("sectionName", sectionName);
			pageContext.setAttribute("sectionScroll", sectionScroll);
			pageContext.setAttribute(
				"sectionRedirectParams", sectionRedirectParams);

			if (!tabsTag.isRefresh() || sectionSelected.booleanValue()) {
				if (!tabsTag.isRefresh()) {
					pageContext.include(getStartPage());
				}

				return EVAL_BODY_INCLUDE;
			}
			else {
				return EVAL_PAGE;
			}
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	public int doEndTag() throws JspException {
		try {
			pageContext.include(getEndPage());

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	public String getStartPage() {
		if (Validator.isNull(_startPage)) {
			return _START_PAGE;
		}
		else {
			return _startPage;
		}
	}

	public void setStartPage(String startPage) {
		_startPage = startPage;
	}

	public String getEndPage() {
		if (Validator.isNull(_endPage)) {
			return _END_PAGE;
		}
		else {
			return _endPage;
		}
	}

	public void setEndPage(String endPage) {
		_endPage = endPage;
	}

	private static final String _START_PAGE =
		"/html/taglib/ui/section/start.jsp";

	private static final String _END_PAGE = "/html/taglib/ui/section/end.jsp";

	private String _startPage;
	private String _endPage;

}