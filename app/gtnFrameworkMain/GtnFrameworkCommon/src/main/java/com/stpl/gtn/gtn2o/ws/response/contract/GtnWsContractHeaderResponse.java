/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.response.contract;

import java.util.List;

import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnWsContractMasterBean;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnwsContractAliasMasterBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;
import java.util.ArrayList;
import java.util.Collections;

public class GtnWsContractHeaderResponse implements GtnWSRequestData {

	public GtnWsContractHeaderResponse() {
		super();
	}

	private boolean contractIdDuplicate;
	private boolean contractNoDuplicate;
	private boolean contractMasterUsed;
	private GtnWsContractMasterBean gtnWsContractMasterBean;
	private List<GtnwsContractAliasMasterBean> gtnwsContractAliasMasterBeanList;
	private List<NotesTabBean> notesTabList;

	public boolean isContractIdDuplicate() {
		return contractIdDuplicate;
	}

	public void setContractIdDuplicate(boolean contractIdDuplicate) {
		this.contractIdDuplicate = contractIdDuplicate;
	}

	public boolean isContractNoDuplicate() {
		return contractNoDuplicate;
	}

	public void setContractNoDuplicate(boolean contractNoDuplicate) {
		this.contractNoDuplicate = contractNoDuplicate;
	}

	public GtnWsContractMasterBean getGtnWsContractMasterBean() {
		return gtnWsContractMasterBean;
	}

	public void setGtnWsContractMasterBean(GtnWsContractMasterBean gtnWsContractMasterBean) {
		this.gtnWsContractMasterBean = gtnWsContractMasterBean;
	}

	public List<GtnwsContractAliasMasterBean> getGtnwsContractAliasMasterBeanList() {
		return gtnwsContractAliasMasterBeanList != null ? Collections.unmodifiableList(gtnwsContractAliasMasterBeanList)
				: gtnwsContractAliasMasterBeanList;
	}

	public void setGtnwsContractAliasMasterBeanList(
			List<GtnwsContractAliasMasterBean> gtnwsContractAliasMasterBeanList) {
		this.gtnwsContractAliasMasterBeanList = gtnwsContractAliasMasterBeanList != null
				? Collections.unmodifiableList(gtnwsContractAliasMasterBeanList) : gtnwsContractAliasMasterBeanList;
	}

	public List<NotesTabBean> getNotesTabList() {
		return notesTabList != null ? new ArrayList<>(notesTabList) : notesTabList;
	}

	public void setNotesTabList(List<NotesTabBean> notesTabList) {
		this.notesTabList = notesTabList != null ? new ArrayList<>(notesTabList) : notesTabList;
	}

	public boolean isContractMasterUsed() {
		return contractMasterUsed;
	}

	public void setContractMasterUsed(boolean contractMasterUsed) {
		this.contractMasterUsed = contractMasterUsed;
	}

}
