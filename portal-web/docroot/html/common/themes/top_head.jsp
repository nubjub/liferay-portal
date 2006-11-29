<%
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
%>

<%@ include file="/html/common/init.jsp" %>

<%@ include file="/html/common/themes/top_meta.jsp" %>
<%@ include file="/html/common/themes/top_meta-ext.jsp" %>

<link rel="Shortcut Icon" href="<%= themeDisplay.getPathThemeImage() %>/liferay.ico" />

<link href="<%= themeDisplay.getPathMain() %>/portal/css_cached?themeId=<%= themeDisplay.getTheme().getThemeId() %>&colorSchemeId=<%= themeDisplay.getColorScheme().getColorSchemeId() %>" type="text/css" rel="stylesheet" />
<link href="<%= themeDisplay.getPathJavaScript() %>/calendar/skins/aqua/theme.css" rel="stylesheet" type="text/css" />

<%
List portlets = null;

if ((layout != null) && layout.getType().equals(LayoutImpl.TYPE_PORTLET)) {
	portlets = layoutTypePortlet.getPortlets();
}
%>

<style type="text/css">
	<liferay-theme:include page="css.jsp" />
</style>

<c:if test="<%= portlets != null %>">

	<%
	for (int i = 0; i < portlets.size(); i++) {
		Portlet portlet = (Portlet)portlets.get(i);

		List headerCssList = portlet.getHeaderCss();

		for (int j = 0; j < headerCssList.size(); j++) {
			String headerCss = (String)headerCssList.get(j);
	%>

			<link href="<%= Validator.isNotNull(portlet.getServletContextName()) ? StringPool.SLASH + portlet.getServletContextName() : StringPool.BLANK %><%= headerCss %>" rel="stylesheet" type="text/css" />

	<%
		}
	}
	%>

</c:if>

<%@ include file="/html/common/themes/top_js.jsp" %>
<%@ include file="/html/common/themes/top_js-ext.jsp" %>

<c:if test="<%= portlets != null %>">

	<%
	for (int i = 0; i < portlets.size(); i++) {
		Portlet portlet = (Portlet)portlets.get(i);

		List headerJavaScriptList = portlet.getHeaderJavaScript();

		for (int j = 0; j < headerJavaScriptList.size(); j++) {
			String headerJavaScript = (String)headerJavaScriptList.get(j);
	%>

			<script src="<%= Validator.isNotNull(portlet.getServletContextName()) ? StringPool.SLASH + portlet.getServletContextName() : StringPool.BLANK %><%= headerJavaScript %>" type="text/javascript"></script>

	<%
		}
	}
	%>

</c:if>