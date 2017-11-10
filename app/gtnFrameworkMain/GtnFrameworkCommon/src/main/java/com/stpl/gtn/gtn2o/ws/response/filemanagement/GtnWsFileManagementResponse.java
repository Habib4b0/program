/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.response.filemanagement;

import com.stpl.gtn.gtn2o.ws.filemanagement.bean.GtnWsFileManagementBean;
import com.stpl.gtn.gtn2o.ws.response.GtnWSResponseData;

public class GtnWsFileManagementResponse implements GtnWSResponseData {
	public GtnWsFileManagementResponse() {
		/**
		 * empty constructor
		 */
	}

	private GtnWsFileManagementBean gtnWsFileManagementBean;

	public GtnWsFileManagementBean getGtnWsFileManagementBean() {
		return gtnWsFileManagementBean;
	}

	public void setGtnWsFileManagementBean(GtnWsFileManagementBean gtnWsFileManagementBean) {
		this.gtnWsFileManagementBean = gtnWsFileManagementBean;
	}

}
