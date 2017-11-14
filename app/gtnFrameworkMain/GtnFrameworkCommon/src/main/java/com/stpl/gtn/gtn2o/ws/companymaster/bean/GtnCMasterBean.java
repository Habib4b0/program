
package com.stpl.gtn.gtn2o.ws.companymaster.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GtnCMasterBean implements Serializable {

	private static final long serialVersionUID = 4463547126135870595L;

	public GtnCMasterBean() {
		super();
	}

	private GtnCMasterInformationBean gtnCMasterInformationBean;

	private GtnCMasterCompanyUdcInfoBean gtnCMasterCompanyUdcInfoBean;

	private List<GtnCMasterIdentifierInfoBean> gtnCMasterIdentifierInfoBeanList;

	private List<GtnCMasterCompanyTradeClassBean> gtnCMasterCompanyTradeClassBeanList;

	private List<GtnCMasterCompanyParentBean> gtnCMasterCompanyParentBeanList;

	private List<NotesTabBean> gtnCMasterCompanyNotesTabBeanList;

	private List<GtnCMasterFinancialCloseBean> gtnCMasterFinancialCloseBean;

	private boolean updateFlag;

	public GtnCMasterInformationBean getGtnCMasterInformationBean() {
		return gtnCMasterInformationBean;
	}

	public void setGtnCMasterInformationBean(GtnCMasterInformationBean gtnCMasterInformationBean) {
		this.gtnCMasterInformationBean = gtnCMasterInformationBean;
	}

	public GtnCMasterCompanyUdcInfoBean getGtnCMasterCompanyUdcInfoBean() {
		return gtnCMasterCompanyUdcInfoBean;
	}

	public void setGtnCMasterCompanyUdcInfoBean(GtnCMasterCompanyUdcInfoBean gtnCMasterCompanyUdcInfoBean) {
		this.gtnCMasterCompanyUdcInfoBean = gtnCMasterCompanyUdcInfoBean;
	}

	public List<GtnCMasterIdentifierInfoBean> getGtnCMasterIdentifierInfoBeanList() {
		return gtnCMasterIdentifierInfoBeanList != null ? new ArrayList<>(gtnCMasterIdentifierInfoBeanList)
				: gtnCMasterIdentifierInfoBeanList;
	}

	public void setGtnCMasterIdentifierInfoBeanList(
			List<GtnCMasterIdentifierInfoBean> gtnCMasterIdentifierInfoBeanList) {
		this.gtnCMasterIdentifierInfoBeanList = gtnCMasterIdentifierInfoBeanList != null
				? new ArrayList<>(gtnCMasterIdentifierInfoBeanList)
				: gtnCMasterIdentifierInfoBeanList;
	}

	public List<GtnCMasterCompanyTradeClassBean> getGtnCMasterCompanyTradeClassBeanList() {
		return gtnCMasterCompanyTradeClassBeanList != null ? new ArrayList<>(gtnCMasterCompanyTradeClassBeanList)
				: gtnCMasterCompanyTradeClassBeanList;
	}

	public void setGtnCMasterCompanyTradeClassBeanList(
			List<GtnCMasterCompanyTradeClassBean> gtnCMasterCompanyTradeClassBeanList) {
		this.gtnCMasterCompanyTradeClassBeanList = (gtnCMasterCompanyTradeClassBeanList != null
				? new ArrayList<>(gtnCMasterCompanyTradeClassBeanList)
				: gtnCMasterCompanyTradeClassBeanList);
	}

	public List<GtnCMasterCompanyParentBean> getGtnCMasterCompanyParentBeanList() {
		return gtnCMasterCompanyParentBeanList != null ? new ArrayList<>(gtnCMasterCompanyParentBeanList)
				: gtnCMasterCompanyParentBeanList;
	}

	public void setGtnCMasterCompanyParentBeanList(List<GtnCMasterCompanyParentBean> gtnCMasterCompanyParentBeanList) {
		this.gtnCMasterCompanyParentBeanList = (gtnCMasterCompanyParentBeanList != null
				? new ArrayList<>(gtnCMasterCompanyParentBeanList)
				: gtnCMasterCompanyParentBeanList);
	}

	public List<NotesTabBean> getGtnCMasterCompanyNotesTabBeanList() {
		return gtnCMasterCompanyNotesTabBeanList != null ? new ArrayList<>(gtnCMasterCompanyNotesTabBeanList)
				: gtnCMasterCompanyNotesTabBeanList;
	}

	public void setGtnCMasterCompanyNotesTabBeanList(List<NotesTabBean> gtnCMasterCompanyNotesTabBeanList) {
		this.gtnCMasterCompanyNotesTabBeanList = gtnCMasterCompanyNotesTabBeanList != null
				? new ArrayList<>(gtnCMasterCompanyNotesTabBeanList)
				: gtnCMasterCompanyNotesTabBeanList;
	}

	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public List<GtnCMasterFinancialCloseBean> getGtnCMasterFinancialCloseBean() {
		return gtnCMasterFinancialCloseBean != null ? new ArrayList<>(gtnCMasterFinancialCloseBean)
				: gtnCMasterFinancialCloseBean;
	}

	public void setGtnCMasterFinancialCloseBean(List<GtnCMasterFinancialCloseBean> gtnCMasterFinancialCloseBean) {
		this.gtnCMasterFinancialCloseBean = gtnCMasterFinancialCloseBean != null
				? new ArrayList<>(gtnCMasterFinancialCloseBean)
				: gtnCMasterFinancialCloseBean;
	}
}
