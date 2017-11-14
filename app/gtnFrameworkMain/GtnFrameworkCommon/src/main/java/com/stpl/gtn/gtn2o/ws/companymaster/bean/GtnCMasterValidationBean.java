/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.companymaster.bean;

import java.util.Collections;
import java.util.List;

public class GtnCMasterValidationBean {

	public GtnCMasterValidationBean() {
		super();
	}

	private List<GtnCMasterIdentifierInfoBean> gtnWsCompanyMasterIdentifierBeanList;
	private GtnCMasterCompanyTradeClassBean gntWsCompanyTradeBean;
	private GtnCMasterCompanyParentBean gtnWsCompanyParentBean;
	private boolean companyNoExist;
	private boolean companyIdExist;

	public List<GtnCMasterIdentifierInfoBean> getGtnWsCompanyMasterIdentifierBeanList() {
		return gtnWsCompanyMasterIdentifierBeanList != null ? Collections.unmodifiableList(gtnWsCompanyMasterIdentifierBeanList) : null;
	}

	public void setGtnWsCompanyMasterIdentifierBeanList(
			List<GtnCMasterIdentifierInfoBean> gtnWsCompanyMasterIdentifierBeanList) {
		this.gtnWsCompanyMasterIdentifierBeanList = gtnWsCompanyMasterIdentifierBeanList != null ? Collections.unmodifiableList(gtnWsCompanyMasterIdentifierBeanList) : null;
	}

	public GtnCMasterCompanyTradeClassBean getGntWsCompanyTradeBean() {
		return gntWsCompanyTradeBean;
	}

	public void setGntWsCompanyTradeBean(GtnCMasterCompanyTradeClassBean gntWsCompanyTradeBean) {
		this.gntWsCompanyTradeBean = gntWsCompanyTradeBean;
	}

	public GtnCMasterCompanyParentBean getGtnWsCompanyParentBean() {
		return gtnWsCompanyParentBean;
	}

	public void setGtnWsCompanyParentBean(GtnCMasterCompanyParentBean gtnWsCompanyParentBean) {
		this.gtnWsCompanyParentBean = gtnWsCompanyParentBean;
	}

	public boolean isCompanyNoExist() {
		return companyNoExist;
	}

	public void setCompanyNoExist(boolean companyNoExist) {
		this.companyNoExist = companyNoExist;
	}

	public boolean isCompanyIdExist() {
		return companyIdExist;
	}

	public void setCompanyIdExist(boolean companyIdExist) {
		this.companyIdExist = companyIdExist;
	}

}
