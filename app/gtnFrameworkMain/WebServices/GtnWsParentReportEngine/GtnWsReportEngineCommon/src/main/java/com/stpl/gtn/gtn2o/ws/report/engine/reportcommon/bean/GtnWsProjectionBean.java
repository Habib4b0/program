package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GtnWsProjectionBean implements Serializable {

	private int ccpId;

	private List<GtnWsProjectionDetailsValuesBean> projectionDetailsValues;

	public GtnWsProjectionBean() {
		super();
	}

	public int getCcpId() {
		return ccpId;
	}

	public void setCcpId(int ccpId) {
		this.ccpId = ccpId;
	}

	public List<GtnWsProjectionDetailsValuesBean> getProjectionDetailsValues() {
		return projectionDetailsValues;
	}

	public void addProjectionDetailsValues(GtnWsProjectionDetailsValuesBean projectionDetailsValues) {
		if (this.projectionDetailsValues == null) {
			this.projectionDetailsValues = new ArrayList<>();
		}
		this.projectionDetailsValues.add(projectionDetailsValues);
	}

	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
		s.defaultWriteObject();
	}

	// Dont delete. this Method is called during Serialization
	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
	}

}
