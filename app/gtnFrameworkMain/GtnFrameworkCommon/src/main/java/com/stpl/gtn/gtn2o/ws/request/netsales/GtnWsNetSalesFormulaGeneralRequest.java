/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.request.netsales;

import java.util.List;

import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUIFrameworkNsfInfoBean;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUIFrameworkNsfSelectedDeductionsBean;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUiFrameworkNsfSelectedCustomerBean;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnWsNsfUpdateBean;
import java.util.Collections;

/**
 *
 * @author STPL
 */
public class GtnWsNetSalesFormulaGeneralRequest {

	public GtnWsNetSalesFormulaGeneralRequest() {
		super();
	}

	private GtnUIFrameworkNsfInfoBean nSfInfoBean;

	private List<GtnUiFrameworkNsfSelectedCustomerBean> nsCustomerInfoBeanList;

	private List<GtnUIFrameworkNsfSelectedDeductionsBean> selectedDeductionInfoBeanList;

	private GtnWsNsfUpdateBean nsfUpdateBean;

	public GtnUIFrameworkNsfInfoBean getnSfInfoBean() {
		return nSfInfoBean;
	}

	public void setnSfInfoBean(GtnUIFrameworkNsfInfoBean nSfInfoBean) {
		this.nSfInfoBean = nSfInfoBean;
	}

	public List<GtnUiFrameworkNsfSelectedCustomerBean> getNsCustomerInfoBeanList() {
		return nsCustomerInfoBeanList != null ? Collections.unmodifiableList(nsCustomerInfoBeanList) : nsCustomerInfoBeanList;
	}

	public void setNsCustomerInfoBeanList(List<GtnUiFrameworkNsfSelectedCustomerBean> nsCustomerInfoBeanList) {
		this.nsCustomerInfoBeanList = nsCustomerInfoBeanList != null ? Collections.unmodifiableList(nsCustomerInfoBeanList) : nsCustomerInfoBeanList;
	}

	public List<GtnUIFrameworkNsfSelectedDeductionsBean> getSelectedDeductionInfoBeanList() {
		return selectedDeductionInfoBeanList != null ? Collections.unmodifiableList(selectedDeductionInfoBeanList) : selectedDeductionInfoBeanList;
	}

	public void setSelectedDeductionInfoBeanList(
			List<GtnUIFrameworkNsfSelectedDeductionsBean> selectedDeductionInfoBeanList) {
		this.selectedDeductionInfoBeanList = selectedDeductionInfoBeanList != null ? Collections.unmodifiableList(selectedDeductionInfoBeanList) : selectedDeductionInfoBeanList;
	}

	public GtnWsNsfUpdateBean getNsfUpdateBean() {
		return nsfUpdateBean;
	}

	public void setNsfUpdateBean(GtnWsNsfUpdateBean nsfUpdateBean) {
		this.nsfUpdateBean = nsfUpdateBean;
	}

}
