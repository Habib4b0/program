package com.stpl.gtn.gtn2o.ws.itemgroup.bean;

import java.io.Serializable;

public class GtnWsItemGrpValidationBean implements Serializable {

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	public GtnWsItemGrpValidationBean() {
		super();
	}

	private int imtdCount;

	private boolean isGroupNameExists;

	public int getImtdCount() {
		return imtdCount;
	}

	public void setImtdCount(int imtdCount) {
		this.imtdCount = imtdCount;
	}

	public boolean isGroupNameExists() {
		return isGroupNameExists;
	}

	public void setGroupNameExists(boolean isGroupNameExists) {
		this.isGroupNameExists = isGroupNameExists;
	}

	@Override
	public String toString() {
		return "GtnWsItemGrpValidationBean{" + "imtdCount=" + imtdCount + '}';
	}

}
