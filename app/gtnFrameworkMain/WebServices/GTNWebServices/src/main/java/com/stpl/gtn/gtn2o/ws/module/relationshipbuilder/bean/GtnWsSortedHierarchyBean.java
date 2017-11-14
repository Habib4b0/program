/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.bean;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;

/**
 *
 * @author Jayaram.LeelaRam
 */
public class GtnWsSortedHierarchyBean implements Comparable<GtnWsSortedHierarchyBean> {

	public GtnWsSortedHierarchyBean() {
		/**
		 * empty constructor
		 */
	}

	private String hierarchyDetails;

	public String getHierarchyDetails() {
		return hierarchyDetails.split(GtnFrameworkWebserviceConstant.STRING_HASH)[1];
	}

	public void setHierarchyDetails(String hierarchyDetails) {
		this.hierarchyDetails = hierarchyDetails;
	}

	public String getSortedHierarchy() {
		return hierarchyDetails;
	}

	@Override
	public int compareTo(GtnWsSortedHierarchyBean o) {
		return this.getHierarchyDetails().compareTo(o.getHierarchyDetails());
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
