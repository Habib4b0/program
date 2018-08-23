<%--
/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
--%>

<%@ include file="/init.jsp" %>
<style>
.login-logo {
	margin: 15px auto;
}

.sign-in-panel-col {
	margin-top: 15%;
}

.sign-in-panel-col label {
	font-weight: 400;
	font-size: 13px;
}

.sign-in-panel-col .panel-heading {
	background: linear-gradient(to bottom, #7fb9ef 0px, #2e85d8 100%);
	color: #f4f4f4;
	font-size: 22px;
	padding-left: 10px;
	text-shadow: 1px 1px #686868;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
}

.sign-in-panel-col .panel-heading .panel-title {
	font-size: 22px;
}

.sign-in-panel-col .panel {
	border: 0px;
	box-shadow: 0 7px 7px #ddd;
	padding: 0px 0;
}

.sign-in-panel-col .btn {
	background: linear-gradient(to bottom, #aeb9c1 0px, #6d808e 100%) repeat;
	border-bottom: 2px solid #657682;
	color: #fff;
	font-size: 16px;
	margin-top: 30px;
	width: 100%;
}

.sign-in-panel-col .btn:hover {
	background: linear-gradient(to bottom, #6d808e 0px, #86949f 100%) repeat;
	border-bottom: 2px solid #657682;
}

.sign-in-panel-col .input-group-addon {
	background: #fff;
}

.iconE {
	left: auto;
	right: 3px;
	padding: 0 3px;
	z-index: 2;
	position: absolute;
	bottom: 1px;
	line-height: 28px;
	display: inline-block;
	color: #909090;
	font-size: 16px;
	margin-right: 16px;
}

.iconM {
	top: 25px;
}

.iconL {
	top: 94px;
}

.sizeable>span {
	font-size: 18px;
}

.sizeable {
	margin-top: 10px !important;
}

.button-holder {
	margin: 0px !important;
}
</style>
<c:choose>
	<c:when test="<%= themeDisplay.isSignedIn() %>">

		<%
		String signedInAs = HtmlUtil.escape(user.getFullName());

		if (themeDisplay.isShowMyAccountIcon() && (themeDisplay.getURLMyAccount() != null)) {
			String myAccountURL = String.valueOf(themeDisplay.getURLMyAccount());

			signedInAs = "<a class=\"signed-in\" href=\"" + HtmlUtil.escape(myAccountURL) + "\">" + signedInAs + "</a>";
		}
		%>

		<liferay-ui:message arguments="<%= signedInAs %>" key="you-are-signed-in-as-x" translateArguments="<%= false %>" />
	</c:when>
	<c:otherwise>

		<%
		String redirect = ParamUtil.getString(request, "redirect");

		String login = LoginUtil.getLogin(request, "login", company);
		String password = StringPool.BLANK;
		boolean rememberMe = ParamUtil.getBoolean(request, "rememberMe");

		if (Validator.isNull(authType)) {
			authType = company.getAuthType();
		}
		%>

		<portlet:actionURL name="/login/login" secure="<%= PropsValues.COMPANY_SECURITY_AUTH_REQUIRES_HTTPS || request.isSecure() %>" var="loginURL" />

		<aui:form action="<%= loginURL %>" autocomplete='<%= PropsValues.COMPANY_SECURITY_LOGIN_FORM_AUTOCOMPLETE ? "on" : "off" %>' cssClass="sign-in-form" method="post" name="fm" onSubmit="event.preventDefault();">
			<aui:input name="saveLastPath" type="hidden" value="<%= false %>" />
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="doActionAfterLogin" type="hidden" value="<%= portletName.equals(PortletKeys.FAST_LOGIN) ? true : false %>" />

			<c:choose>
				<c:when test='<%= SessionMessages.contains(request, "userAdded") %>'>

					<%
					String userEmailAddress = (String)SessionMessages.get(request, "userAdded");
					String userPassword = (String)SessionMessages.get(request, "userAddedPassword");
					%>

					<div class="alert alert-success">
						<c:choose>
							<c:when test="<%= company.isStrangersVerify() || Validator.isNull(userPassword) %>">
								<liferay-ui:message key="thank-you-for-creating-an-account" />

								<c:if test="<%= company.isStrangersVerify() %>">
									<liferay-ui:message arguments="<%= userEmailAddress %>" key="your-email-verification-code-has-been-sent-to-x" translateArguments="<%= false %>" />
								</c:if>
							</c:when>
							<c:otherwise>
								<liferay-ui:message arguments="<%= userPassword %>" key="thank-you-for-creating-an-account.-your-password-is-x" translateArguments="<%= false %>" />
							</c:otherwise>
						</c:choose>

						<c:if test="<%= PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.ADMIN_EMAIL_USER_ADDED_ENABLED) %>">
							<liferay-ui:message arguments="<%= userEmailAddress %>" key="your-password-has-been-sent-to-x" translateArguments="<%= false %>" />
						</c:if>
					</div>
				</c:when>
				<c:when test='<%= SessionMessages.contains(request, "userPending") %>'>

					<%
					String userEmailAddress = (String)SessionMessages.get(request, "userPending");
					%>

					<div class="alert alert-success">
						<liferay-ui:message arguments="<%= userEmailAddress %>" key="thank-you-for-creating-an-account.-you-will-be-notified-via-email-at-x-when-your-account-has-been-approved" translateArguments="<%= false %>" />
					</div>
				</c:when>
			</c:choose>

			<liferay-ui:error exception="<%= AuthException.class %>" message="authentication-failed" />
			<liferay-ui:error exception="<%= CompanyMaxUsersException.class %>" message="unable-to-log-in-because-the-maximum-number-of-users-has-been-reached" />
			<liferay-ui:error exception="<%= CookieNotSupportedException.class %>" message="authentication-failed-please-enable-browser-cookies" />
			<liferay-ui:error exception="<%= NoSuchUserException.class %>" message="authentication-failed" />
			<liferay-ui:error exception="<%= PasswordExpiredException.class %>" message="your-password-has-expired" />
			<liferay-ui:error exception="<%= UserEmailAddressException.MustNotBeNull.class %>" message="please-enter-an-email-address" />
			<liferay-ui:error exception="<%= UserLockoutException.LDAPLockout.class %>" message="this-account-is-locked" />

			<liferay-ui:error exception="<%= UserLockoutException.PasswordPolicyLockout.class %>">

				<%
				UserLockoutException.PasswordPolicyLockout ule = (UserLockoutException.PasswordPolicyLockout)errorException;
				%>

				<liferay-ui:message arguments="<%= ule.user.getUnlockDate() %>" key="this-account-is-locked-until-x" translateArguments="<%= false %>" />
			</liferay-ui:error>

			<liferay-ui:error exception="<%= UserPasswordException.class %>" message="authentication-failed" />
			<liferay-ui:error exception="<%= UserScreenNameException.MustNotBeNull.class %>" message="the-screen-name-cannot-be-blank" />
				<div class="row">
					<div class="col-md-3 col-sm-6 col-md-offset-4 col-sm-offset-3 sign-in-panel-col">
							<div class="panel panel-default">
									<div class="panel-heading">
									  <h2 class="panel-title">Sign In</h2>
									</div>
									<div class="panel-body">
											<div class="row">
											  <div class="col-md-12">
			<aui:fieldset>

				<%
				String loginLabel = null;

				if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
					loginLabel = "email-address";
				}
				else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
					loginLabel = "screen-name";
				}
				else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
					loginLabel = "id";
				}
				%>

				<aui:input autoFocus="<%= windowState.equals(LiferayWindowState.EXCLUSIVE) || windowState.equals(WindowState.MAXIMIZED) %>" cssClass="clearable" icon="icon-upload-alt" iconAlign="right" label="<%= loginLabel %>" name="login" showRequiredLabel="<%= false %>" type="text" value="<%= login %>">
					<aui:validator name="required" />
					
				</aui:input>
				<i class="icon-user iconM iconE"></i>

				<aui:input name="password" showRequiredLabel="<%= false %>" type="password" value="<%= password %>">
					<aui:validator name="required" />
				</aui:input>
				<i class="icon-lock iconE iconL"></i>

				<span id="<portlet:namespace />passwordCapsLockSpan" style="display: none;"><liferay-ui:message key="caps-lock-is-on" /></span>

				<c:if test="<%= company.isAutoLogin() && !PropsValues.SESSION_DISABLED %>">
					<aui:input checked="<%= rememberMe %>" name="rememberMe" type="checkbox" />
				</c:if>
			</aui:fieldset>

			<aui:button-row>
				<aui:button cssClass="sizeable" type="submit" icon="icon-key" iconAlign="left" value="sign-in" />
			</aui:button-row>
		</div></div></div></div></div></div>
		</aui:form>

		<liferay-util:include page="/navigation.jsp" servletContext="<%= application %>" />

		<aui:script sandbox="<%= true %>">
			var form = AUI.$(document.<portlet:namespace />fm);

			form.on(
				'submit',
				function(event) {
					var redirect = form.fm('redirect');

					if (redirect) {
						var redirectVal = redirect.val();

						redirect.val(redirectVal + window.location.hash);
					}

					submitForm(form);
				}
			);

			form.fm('password').on(
				'keypress',
				function(event) {
					Liferay.Util.showCapsLock(event, '<portlet:namespace />passwordCapsLockSpan');
				}
			);
		</aui:script>
	</c:otherwise>
</c:choose>