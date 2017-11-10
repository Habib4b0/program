package com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean;

import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class GtnIFamilyPlanBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public GtnIFamilyPlanBean() {
		super();
	}

	private GtnIFamilyPlanInformationBean ifpInfo;

	private List<NotesTabBean> notesTabList;

	private GtnIFamilyPlanCommonUpdateBean updateBean;

	public GtnIFamilyPlanInformationBean getIfpInfo() {
		return ifpInfo;
	}

	public void setIfpInfo(GtnIFamilyPlanInformationBean ifpInfo) {
		this.ifpInfo = ifpInfo;
	}

	public List<NotesTabBean> getNotesTabList() {
		return notesTabList != null ? Collections.unmodifiableList(notesTabList) : notesTabList;
	}

	public void setNotesTabList(List<NotesTabBean> notesTabList) {
		this.notesTabList = (notesTabList != null ? Collections.unmodifiableList(notesTabList) : notesTabList);
	}

	public GtnIFamilyPlanCommonUpdateBean getUpdateBean() {
		return updateBean;
	}

	public void setUpdateBean(GtnIFamilyPlanCommonUpdateBean updateBean) {
		this.updateBean = updateBean;
	}

}