/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.deductioncalendar.bean;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mahesh.James
 */
public class GtnWsDeductionCalendarBean {

	public GtnWsDeductionCalendarBean() {
		super();
	}

	private boolean isAddAll;

	private List<Integer> idList;

	public List<Integer> getIdList() {
		return idList != null ? Collections.unmodifiableList(idList) : idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList != null ? Collections.unmodifiableList(idList) : idList;
	}

	public boolean isIsAddAll() {
		return isAddAll;
	}

	public void setIsAddAll(boolean isAddAll) {
		this.isAddAll = isAddAll;
	}

}
