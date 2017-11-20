/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.contract;

import java.util.List;

import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnWsContractMasterBean;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnwsContractAliasMasterBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;
import java.util.ArrayList;
import java.util.Collections;

public class GtnWsContractHeaderRequest implements GtnWSRequestData {

	public GtnWsContractHeaderRequest() {
		super();
	}

	private GtnWsContractMasterBean gtnWsContractMasterBean;
	private String userId;
	private List<GtnwsContractAliasMasterBean> gtnwsContractAliasMasterBeanList;
	private List<NotesTabBean> noteBeanList;

	public GtnWsContractMasterBean getGtnWsContractMasterBean() {
		return gtnWsContractMasterBean;
	}

	public void setGtnWsContractMasterBean(GtnWsContractMasterBean gtnWsContractMasterBean) {
		this.gtnWsContractMasterBean = gtnWsContractMasterBean;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<NotesTabBean> getNoteBeanList() {
		return noteBeanList != null ? Collections.unmodifiableList(noteBeanList) : noteBeanList;
	}

	public void setNoteBeanList(List<NotesTabBean> noteBeanList) {
		this.noteBeanList = noteBeanList != null ? Collections.unmodifiableList(noteBeanList) : noteBeanList;
	}

	public List<GtnwsContractAliasMasterBean> getGtnwsContractAliasMasterBeanList() {
		return gtnwsContractAliasMasterBeanList != null ? new ArrayList<>(gtnwsContractAliasMasterBeanList) : gtnwsContractAliasMasterBeanList;
	}

	public void setGtnwsContractAliasMasterBeanList(
			List<GtnwsContractAliasMasterBean> gtnwsContractAliasMasterBeanList) {
		this.gtnwsContractAliasMasterBeanList = gtnwsContractAliasMasterBeanList != null ? new ArrayList<>(gtnwsContractAliasMasterBeanList) : gtnwsContractAliasMasterBeanList;
	}

}
