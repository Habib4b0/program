
package com.stpl.gtn.gtn2o.ws.request.balancesummaryrequest;

import java.io.Serializable;

public class GtnBSDataSelectionLookUpRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String hierarchyType = "";
	private String hierarchyName = "";
	private String lookUpName = "";

	public String getHierarchyType1() {
		return hierarchyType;
	}

	public void setHierarchyType(String hierarchyType) {
		this.hierarchyType = hierarchyType;
	}

	public String getHierarchyName() {
		return hierarchyName;
	}

	public void setHierarchyName(String hierarchyName) {
		this.hierarchyName = hierarchyName;
	}

	public String getLookUpName() {
		return lookUpName;
	}

	public void setLookUpName(String lookUpName) {
		this.lookUpName = lookUpName;
	}

}
