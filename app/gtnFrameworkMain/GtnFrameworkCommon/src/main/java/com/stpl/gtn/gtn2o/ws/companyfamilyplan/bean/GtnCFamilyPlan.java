package com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean;

import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class GtnCFamilyPlan implements Serializable {

	private static final long serialVersionUID = 1L;

	public GtnCFamilyPlan() {
		super();
	}

	private GtnCFamilyPlanInformation cfpInfo;

	private List<NotesTabBean> notesTabList;

	private GtnCFamilyPlanCommonUpdateBean updateBean;

	public GtnCFamilyPlanInformation getCfpInfo() {
		return cfpInfo;
	}

	public void setCfpInfo(GtnCFamilyPlanInformation cfpInfo) {
		this.cfpInfo = cfpInfo;
	}

	public List<NotesTabBean> getNotesTabList() {
		return notesTabList != null ? Collections.unmodifiableList(notesTabList) : notesTabList;
	}

	public void setNotesTabList(List<NotesTabBean> notesTabList) {
		this.notesTabList = notesTabList != null ? Collections.unmodifiableList(notesTabList) : notesTabList;
	}

	public GtnCFamilyPlanCommonUpdateBean getUpdateBean() {
		return updateBean;
	}

	public void setUpdateBean(GtnCFamilyPlanCommonUpdateBean updateBean) {
		this.updateBean = updateBean;
	}

}