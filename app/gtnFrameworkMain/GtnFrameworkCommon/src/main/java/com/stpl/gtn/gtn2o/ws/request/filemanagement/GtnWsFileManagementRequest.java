/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.filemanagement;

import com.stpl.gtn.gtn2o.ws.filemanagement.bean.GtnWsFileManagementBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

public class GtnWsFileManagementRequest implements GtnWSRequestData {
	public GtnWsFileManagementRequest() {
		/**
		 * empty constructor
		 */
	}

	private GtnWsFileManagementBean fileManagementBean;

	public GtnWsFileManagementBean getFileManagementBean() {
		return fileManagementBean;
	}

	public void setFileManagementBean(GtnWsFileManagementBean fileManagementBean) {
		this.fileManagementBean = fileManagementBean;
	}

}
