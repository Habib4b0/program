package com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants;

public enum GtnFrameworkWorkflowIdMap {

	GF(GtnFrameworkWorkflowInboxClassConstants.PROJECTIONIDWORKFLOW), CF(
			GtnFrameworkWorkflowInboxClassConstants.PROJECTIONIDWORKFLOW), ARF(
					GtnFrameworkWorkflowInboxClassConstants.PROJECTIONIDWORKFLOW), REF(
							GtnFrameworkWorkflowInboxClassConstants.PROJECTIONIDWORKFLOW), BR(
									GtnFrameworkWorkflowInboxClassConstants.ACCCLOSUREIDMASTERSID), FD(
											GtnFrameworkWorkflowInboxClassConstants.ACCCLOSUREIDMASTERSID), CMF(
													GtnFrameworkWorkflowInboxClassConstants.CONTRACT_MASTERSID), ARM(
															GtnFrameworkWorkflowInboxClassConstants.ARM_MASTERSID), CM(
													GtnFrameworkWorkflowInboxClassConstants.CONTRACT_MASTERSID);
	private GtnFrameworkWorkflowIdMap(String input) {
		this.input = input;
	}

	public String getInput() {
		return input;
	}

	private final String input;
}
