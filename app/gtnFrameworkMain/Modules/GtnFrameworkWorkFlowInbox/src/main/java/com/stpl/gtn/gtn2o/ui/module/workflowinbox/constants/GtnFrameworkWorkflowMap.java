package com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants;

public enum GtnFrameworkWorkflowMap {

	CF(GtnFrameworkWorkflowInboxClassConstants.WEB_COMMERCIAL), GF(
			GtnFrameworkWorkflowInboxClassConstants.WEB_GOVERNMENT), BR(
					GtnFrameworkWorkflowInboxClassConstants.WEB_BASERATE), FD(
							GtnFrameworkWorkflowInboxClassConstants.WEB_FIXEDDOLLAR), ARF(
									GtnFrameworkWorkflowInboxClassConstants.WEB_ARP), CMF(
											GtnFrameworkWorkflowInboxClassConstants.WEB_CONTRACT_DASHBOARD), REF(
													GtnFrameworkWorkflowInboxClassConstants.WEB_FORECAST_RETURNS), ARM(
															GtnFrameworkWorkflowInboxClassConstants.WEB_FIXEDDOLLAR_ARMFLOW);

	private GtnFrameworkWorkflowMap(String input) {
		this.input = input;
	}

	public String getInput() {
		return input;
	}

	private final String input;
}
