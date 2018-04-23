/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.itemmaster;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsBrandMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemPricingQualifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemQualifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsValidationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

public class GtnWsItemMasterRequest implements GtnWSRequestData {

	private GtnWsValidationBean gtnWsValidationBean;

	public GtnWsItemMasterRequest() {
		super();
	}

	private GtnWsItemQualifierBean gtnWsItemQualifierBean;
	private GtnWsItemPricingQualifierBean gtnWsItemPricingQualifierBean;
	private String userId;
	private GtnWsItemMasterBean gtnWsItemMasterBean;
	private GtnWsBrandMasterBean gtnWsBrandMasterBean;

	public GtnWsValidationBean getGtnWsValidationBean() {
		return gtnWsValidationBean;
	}

	public void setGtnWsValidationBean(GtnWsValidationBean gtnWsValidationBean) {
		this.gtnWsValidationBean = gtnWsValidationBean;
	}

	public GtnWsItemQualifierBean getGtnWsItemQualifierBean() {
		return gtnWsItemQualifierBean;
	}

	public void setGtnWsItemQualifierBean(GtnWsItemQualifierBean gtnWsItemQualifierBean) {
		this.gtnWsItemQualifierBean = gtnWsItemQualifierBean;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public GtnWsItemPricingQualifierBean getGtnWsItemPricingQualifierBean() {
		return gtnWsItemPricingQualifierBean;
	}

	public void setGtnWsItemPricingQualifierBean(GtnWsItemPricingQualifierBean gtnWsItemPricingQualifierBean) {
		this.gtnWsItemPricingQualifierBean = gtnWsItemPricingQualifierBean;
	}

	public GtnWsItemMasterBean getGtnWsItemMasterBean() {
		return gtnWsItemMasterBean;
	}

	public void setGtnWsItemMasterBean(GtnWsItemMasterBean gtnWsItemMasterBean) {
		this.gtnWsItemMasterBean = gtnWsItemMasterBean;
	}

	public GtnWsBrandMasterBean getGtnWsBrandMasterBean() {
		return gtnWsBrandMasterBean;
	}

	public void setGtnWsBrandMasterBean(GtnWsBrandMasterBean gtnWsBrandMasterBean) {
		this.gtnWsBrandMasterBean = gtnWsBrandMasterBean;
	}
        private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
	}

}
