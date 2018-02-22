package com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants;

public enum GtnFrameworkWorkflowuserTypeMap {

	GF(GtnFrameworkWorkflowInboxClassConstants.APPROVER), CF(GtnFrameworkWorkflowInboxClassConstants.APPROVER), CMF(
			GtnFrameworkWorkflowInboxClassConstants.APPROVER), ARF(
					GtnFrameworkWorkflowInboxClassConstants.APPROVER), REF(
							GtnFrameworkWorkflowInboxClassConstants.APPROVER), ARM_TRX(
									GtnFrameworkWorkflowInboxClassConstants.APPROVER), BR(
											GtnFrameworkWorkflowInboxClassConstants.YES), FD(
													GtnFrameworkWorkflowInboxClassConstants.YES), CM(
			GtnFrameworkWorkflowInboxClassConstants.APPROVER);

	private GtnFrameworkWorkflowuserTypeMap(String input) {
		this.input = input;
	}

	public String getInput() {
		return input;
	}

	private final String input;
}
