/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.response.itemmaster;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsBrandMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemQualifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsValidationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

public class GtnWsItemMasterResponse implements GtnWSRequestData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GtnWsItemMasterResponse() {
		super();
	}

	private boolean indentifierExist;
	private boolean indentifierQualfierValueExist;
	private boolean indentifierQualfierNameExist;
	private boolean pricnigQualfierExist;
	private boolean pricnigQualfierNameExist;
	private String companyName;
	private GtnWsBrandMasterBean gtnWsBrandMasterBean;
	private GtnWsItemMasterBean gtnWsItemMasterBean;
	private GtnWsValidationBean gtnWsValidationBean;
	private GtnWsItemQualifierBean gtnWsItemQualifierBean;
	private boolean indentifierQualfierUsed;

	private boolean itemPresentInContract;
	private boolean itemPresentInIfp;

	private boolean pricingQualfierUsed;

	public boolean isIndentifierExist() {
		return indentifierExist;
	}

	public void setIndentifierExist(boolean indentifierExist) {
		this.indentifierExist = indentifierExist;
	}

	public boolean isIndentifierQualfierValueExist() {
		return indentifierQualfierValueExist;
	}

	public void setIndentifierQualfierValueExist(boolean indentifierQualfierValueExist) {
		this.indentifierQualfierValueExist = indentifierQualfierValueExist;
	}

	public boolean isQualfierNameExist() {
		return indentifierQualfierNameExist;
	}

	public void setQualfierNameExist(boolean qualfierNameExist) {
		this.indentifierQualfierNameExist = qualfierNameExist;
	}

	public boolean isIndentifierQualfierNameExist() {
		return indentifierQualfierNameExist;
	}

	public void setIndentifierQualfierNameExist(boolean indentifierQualfierNameExist) {
		this.indentifierQualfierNameExist = indentifierQualfierNameExist;
	}

	public boolean isPricnigQualfierExist() {
		return pricnigQualfierExist;
	}

	public void setPricnigQualfierExist(boolean pricnigQualfierExist) {
		this.pricnigQualfierExist = pricnigQualfierExist;
	}

	public boolean isPricnigQualfierNameExist() {
		return pricnigQualfierNameExist;
	}

	public void setPricnigQualfierNameExist(boolean pricnigQualfierNameExist) {
		this.pricnigQualfierNameExist = pricnigQualfierNameExist;
	}

	public GtnWsBrandMasterBean getGtnWsBrandMasterBean() {
		return gtnWsBrandMasterBean;
	}

	public void setGtnWsBrandMasterBean(GtnWsBrandMasterBean gtnWsBrandMasterBean) {
		this.gtnWsBrandMasterBean = gtnWsBrandMasterBean;
	}

	public GtnWsItemMasterBean getGtnWsItemMasterBean() {
		return gtnWsItemMasterBean;
	}

	public void setGtnWsItemMasterBean(GtnWsItemMasterBean gtnWsItemMasterBean) {
		this.gtnWsItemMasterBean = gtnWsItemMasterBean;
	}

	public GtnWsValidationBean getGtnWsValidationBean() {
		return gtnWsValidationBean;
	}

	public void setGtnWsValidationBean(GtnWsValidationBean gtnWsValidationBean) {
		this.gtnWsValidationBean = gtnWsValidationBean;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public boolean isItemPresentInContract() {
		return itemPresentInContract;
	}

	public void setItemPresentInContract(boolean itemPresentInContract) {
		this.itemPresentInContract = itemPresentInContract;
	}

	public boolean isItemPresentInIfp() {
		return itemPresentInIfp;
	}

	public void setItemPresentInIfp(boolean itemPresentInIfp) {
		this.itemPresentInIfp = itemPresentInIfp;
	}

	public boolean isIndentifierQualfierUsed() {
		return indentifierQualfierUsed;
	}

	public void setIndentifierQualfierUsed(boolean indentifierQualfierUsed) {
		this.indentifierQualfierUsed = indentifierQualfierUsed;
	}

	public GtnWsItemQualifierBean getGtnWsItemQualifierBean() {
		return gtnWsItemQualifierBean;
	}

	public void setGtnWsItemQualifierBean(GtnWsItemQualifierBean gtnWsItemQualifierBean) {
		this.gtnWsItemQualifierBean = gtnWsItemQualifierBean;
	}

	public boolean isPricingQualfierUsed() {
		return pricingQualfierUsed;
	}

	public void setPricingQualfierUsed(boolean pricingQualfierUsed) {
		this.pricingQualfierUsed = pricingQualfierUsed;
	}

	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
	}
}
