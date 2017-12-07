package com.stpl.gtn.gtn2o.ws.transaction.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GtnWSTransactionModuleBean {

	public GtnWSTransactionModuleBean() {
		super();
	}

	private List<GtnWSTransactionColumnBean> module = new ArrayList<>();
	private List<GtnWSTransactionColumnBean> moduleDetails = new ArrayList<>();

	public List<GtnWSTransactionColumnBean> getModule() {
		return module != null ? Collections.unmodifiableList(module) : module;
	}

	public void setModule(List<GtnWSTransactionColumnBean> module) {
		this.module = module != null ? new ArrayList<>(module) : module;
	}

	public List<GtnWSTransactionColumnBean> getModuleDetails() {
		return moduleDetails != null ? Collections.unmodifiableList(moduleDetails) : moduleDetails;
	}

	public void setModuleDetails(List<GtnWSTransactionColumnBean> moduleDetails) {
		this.moduleDetails = moduleDetails != null ? new ArrayList<>(moduleDetails) : moduleDetails;
	}

}
