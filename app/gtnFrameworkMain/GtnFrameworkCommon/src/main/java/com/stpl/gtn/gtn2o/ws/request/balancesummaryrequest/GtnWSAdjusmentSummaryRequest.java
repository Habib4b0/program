
package com.stpl.gtn.gtn2o.ws.request.balancesummaryrequest;

import java.util.Map;

public class GtnWSAdjusmentSummaryRequest {

	private Map<String, Object> helperTableSidAndValueMap;

	public Map<String, Object> getHelperTableSidAndValueMap() {
		return helperTableSidAndValueMap;
	}

	public void setHelperTableSidAndValueMap(Map<String, Object> helperTableSidAndValueMap) {
		this.helperTableSidAndValueMap = helperTableSidAndValueMap;
	}

	public GtnWSAdjusmentSummaryRequest() {
		super();
	}

}
