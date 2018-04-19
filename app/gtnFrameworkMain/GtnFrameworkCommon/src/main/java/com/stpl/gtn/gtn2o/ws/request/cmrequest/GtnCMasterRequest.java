package com.stpl.gtn.gtn2o.ws.request.cmrequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterValidationBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnWsCMasterQualifierBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

public class GtnCMasterRequest implements GtnWSRequestData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GtnCMasterRequest() {
		super();
	}

	private GtnCMasterBean gtnCMasterBean;
	private GtnWsCMasterQualifierBean gtnCMasterQualifierBean;
	private GtnCMasterValidationBean gtnCMasterValidationBean;
	private Object[] saveData;

	public Object[] getSaveData() {
		return saveData != null ? saveData.clone() : saveData;
	}

	public GtnCMasterValidationBean getGtnCMasterValidationBean() {
		return gtnCMasterValidationBean;
	}

	public void setGtnCMasterValidationBean(GtnCMasterValidationBean gtnCMasterValidationBean) {
		this.gtnCMasterValidationBean = gtnCMasterValidationBean;
	}

	public void setSaveData(Object[] saveData) {
		this.saveData = saveData != null ? saveData.clone() : saveData;
	}

	public GtnCMasterBean getGtnCMasterBean() {
		return gtnCMasterBean;
	}

	public void setGtnCMasterBean(GtnCMasterBean gtnCMasterBean) {
		this.gtnCMasterBean = gtnCMasterBean;
	}

	public GtnWsCMasterQualifierBean getGtnCMasterQualifierBean() {
		return gtnCMasterQualifierBean;
	}

	public void setGtnCMasterQualifierBean(GtnWsCMasterQualifierBean gtnCMasterQualifierBean) {
		this.gtnCMasterQualifierBean = gtnCMasterQualifierBean;
	}

}
