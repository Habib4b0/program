/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.itemmaster.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnWsValidationBean {

	public GtnWsValidationBean() {
		super();
	}

	private List<GtnWsItemIdentifierBean> gtnWsItemMasterIdentifierBeanList;
	private GntWsItemPricingBean gntWsItemPricingBean;
	private GtnWsItemQualifierBean gtnWsItemQualifierBean;
	private GtnWsItemPricingQualifierBean gtnWsItemPricingQualifierBean;
	private boolean itemNoExist;
	private boolean itemIdExist;
	private boolean ndc8Exist;
	private String validationMsg;

	public List<GtnWsItemIdentifierBean> getGtnWsItemMasterIdentifierBeanList() {
		return gtnWsItemMasterIdentifierBeanList != null ? new ArrayList<>(gtnWsItemMasterIdentifierBeanList)
				: gtnWsItemMasterIdentifierBeanList;
	}

	public void setGtnWsItemMasterIdentifierBeanList(List<GtnWsItemIdentifierBean> gtnWsItemMasterIdentifierBeanList) {
		this.gtnWsItemMasterIdentifierBeanList = gtnWsItemMasterIdentifierBeanList != null
				? new ArrayList<>(gtnWsItemMasterIdentifierBeanList)
				: gtnWsItemMasterIdentifierBeanList;
	}

	public GntWsItemPricingBean getGntWsItemPricingBean() {
		return gntWsItemPricingBean;
	}

	public void setGntWsItemPricingBean(GntWsItemPricingBean gntWsItemPricingBean) {
		this.gntWsItemPricingBean = gntWsItemPricingBean;
	}

	public GtnWsItemQualifierBean getGtnWsItemQualifierBean() {
		return gtnWsItemQualifierBean;
	}

	public void setGtnWsItemQualifierBean(GtnWsItemQualifierBean gtnWsItemQualifierBean) {
		this.gtnWsItemQualifierBean = gtnWsItemQualifierBean;
	}

	public GtnWsItemPricingQualifierBean getGtnWsItemPricingQualifierBean() {
		return gtnWsItemPricingQualifierBean;
	}

	public void setGtnWsItemPricingQualifierBean(GtnWsItemPricingQualifierBean gtnWsItemPricingQualifierBean) {
		this.gtnWsItemPricingQualifierBean = gtnWsItemPricingQualifierBean;
	}

	public boolean isItemNoExist() {
		return itemNoExist;
	}

	public void setItemNoExist(boolean itemNoExist) {
		this.itemNoExist = itemNoExist;
	}

	public boolean isItemIdExist() {
		return itemIdExist;
	}

	public void setItemIdExist(boolean itemIdExist) {
		this.itemIdExist = itemIdExist;
	}

	public boolean isNdc8Exist() {
		return ndc8Exist;
	}

	public void setNdc8Exist(boolean ndc8Exist) {
		this.ndc8Exist = ndc8Exist;
	}

	public String getValidationMsg() {
		return validationMsg;
	}

	public void setValidationMsg(String validationMsg) {
		this.validationMsg = validationMsg;
	}

}
