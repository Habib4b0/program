package com.stpl.gtn.gtn2o.ws.response.cmresponse;

import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterValidationBean;
import com.stpl.gtn.gtn2o.ws.response.GtnWSResponseData;

public class GtnCompanyMasterResponse implements GtnWSResponseData {

	public GtnCompanyMasterResponse() {
		super();
	}

	private GtnCMasterBean gtnCMasterBean;
	private boolean indentifierExist;
	private boolean indentifierQualfierUsed;
	private boolean indentifierQualfierValueExist;
	private boolean success;
	private String reasonForFailure;
	private GtnCMasterValidationBean gtnWsCMValidationBean;
	private int helperTableSid;

	public boolean isIndentifierExist() {
		return indentifierExist;
	}

	public void setIndentifierExist(boolean indentifierExist) {
		this.indentifierExist = indentifierExist;
	}

	public boolean isIndentifierQualfierValueExist() {
		return indentifierQualfierValueExist;
	}

	public void setIndentifierQualfierValueExist(boolean indentifierQualfierValueExist) {
		this.indentifierQualfierValueExist = indentifierQualfierValueExist;
	}

	public GtnCMasterValidationBean getGtnWsCMValidationBean() {
		return gtnWsCMValidationBean;
	}

	public void setGtnWsCMValidationBean(GtnCMasterValidationBean gtnWsCMValidationBean) {
		this.gtnWsCMValidationBean = gtnWsCMValidationBean;
	}

	public GtnCMasterBean getGtnCMasterBean() {
		return gtnCMasterBean;
	}

	public void setGtnCMasterBean(GtnCMasterBean gtnCMasterBean) {
		this.gtnCMasterBean = gtnCMasterBean;
	}

	public boolean isIndentifierQualfierUsed() {
		return indentifierQualfierUsed;
	}

	public void setIndentifierQualfierUsed(boolean indentifierQualfierUsed) {
		this.indentifierQualfierUsed = indentifierQualfierUsed;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getReasonForFailure() {
		return reasonForFailure;
	}

	public void setReasonForFailure(String reasonForFailure) {
		this.reasonForFailure = reasonForFailure;
	}

	public int getHelperTableSid() {
		return helperTableSid;
	}

	public void setHelperTableSid(int helperTableSid) {
		this.helperTableSid = helperTableSid;
	}

}
