/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.companygroup;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGroupBean;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpDataBean;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpValidationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnCompanyGroupRequest implements GtnWSRequestData {

	public GtnCompanyGroupRequest() {
		super();
	}

	private GtnCompanyGroupBean gtnCompanyGroupBean;

	private List<GtnCompanyGrpDataBean> gtnCompanyGrpDataBeanList;
	private GtnCompanyGrpValidationBean gtnCompanyGrpValidationBean;

	public GtnCompanyGroupBean getGtnCompanyGroupBean() {
		return gtnCompanyGroupBean;
	}

	public void setGtnCompanyGroupBean(GtnCompanyGroupBean gtnCompanyGroupBean) {
		this.gtnCompanyGroupBean = gtnCompanyGroupBean;
	}

	public List<GtnCompanyGrpDataBean> getGtnCompanyGrpDataBeanList() {
		return gtnCompanyGrpDataBeanList != null ? Collections.unmodifiableList(gtnCompanyGrpDataBeanList)
				: gtnCompanyGrpDataBeanList;
	}

	public void setGtnCompanyGrpDataBeanList(List<GtnCompanyGrpDataBean> gtnCompanyGrpDataBeanList) {
		this.gtnCompanyGrpDataBeanList = gtnCompanyGrpDataBeanList != null
				? Collections.unmodifiableList(gtnCompanyGrpDataBeanList)
				: gtnCompanyGrpDataBeanList;
	}

	public GtnCompanyGrpValidationBean getGtnCompanyGrpValidationBean() {
		return gtnCompanyGrpValidationBean;
	}

	public void setGtnCompanyGrpValidationBean(GtnCompanyGrpValidationBean gtnCompanyGrpValidationBean) {
		this.gtnCompanyGrpValidationBean = gtnCompanyGrpValidationBean;
	}

	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
	}
}
