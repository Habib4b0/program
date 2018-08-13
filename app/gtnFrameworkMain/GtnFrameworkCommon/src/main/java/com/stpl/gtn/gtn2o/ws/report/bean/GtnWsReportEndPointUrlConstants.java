package com.stpl.gtn.gtn2o.ws.report.bean;

import com.stpl.gtn.gtn2o.ws.exception.AccessDeniedException;

public class GtnWsReportEndPointUrlConstants {
    private GtnWsReportEndPointUrlConstants() {
        throw new AccessDeniedException("Can not create object for this class " + GtnWsReportEndPointUrlConstants.class.getName());
    }
	public static final String LOAD_HIERARCHY = "/loadHierarchy";
	public static final String SAVE_CUSTOM_TREE = "/saveCustomView";
	public static final String LOAD_CUSTOM_VIEW = "/loadCustomViewName";
	public static final String LOAD_CUSTOM_VIEW_DATA = "/loadCustomView";
	public static final String DELETE_CUSTOM_VIEW = "/deleteCustomView";
	public static final String LOAD_DEDUCTION_HIERARCHY = "/loadDeductionHierarchy";
}
