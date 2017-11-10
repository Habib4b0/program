package com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants;

public enum GtnFrameworkWorkflowPortletMap {

	GF(GtnFrameworkWorkflowInboxClassConstants.GOVERNMENT), CF(GtnFrameworkWorkflowInboxClassConstants.COMMERCIAL), ARF(
			GtnFrameworkWorkflowInboxClassConstants.ACCRUAL_RATE_PROJ), REF(
					GtnFrameworkWorkflowInboxClassConstants.RETURNS), ARM(
							GtnFrameworkWorkflowInboxClassConstants.FIXEDDOLLAR_ADJUSTMENT), CMF(
									GtnFrameworkWorkflowInboxClassConstants.CONTRACT_DASHBOARD);
	private GtnFrameworkWorkflowPortletMap(String input) {
		this.input = input;

	}

	public String getInput() {
		return input;
	}

	private final String input;
}