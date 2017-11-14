/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.itemmaster.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnWsItemMasterBean implements Serializable {

	public GtnWsItemMasterBean() {
		super();
	}

	private static final long serialVersionUID = 1L;
	private GtnWsItemMasterInfoBean gtnWsItemMasterInfoBean;
	private List<GtnWsItemIdentifierBean> gtnWsItemIdentifierBeanList;
	private GntWsItemPricingBean gntWsItemPricingBean;
	private List<NotesTabBean> noteBeanList;

	public GtnWsItemMasterInfoBean getGtnWsItemMasterInfoBean() {
		return gtnWsItemMasterInfoBean;
	}

	public void setGtnWsItemMasterInfoBean(GtnWsItemMasterInfoBean gtnWsItemMasterInfoBean) {
		this.gtnWsItemMasterInfoBean = gtnWsItemMasterInfoBean;
	}

	public List<GtnWsItemIdentifierBean> getGtnWsItemIdentifierBeanList() {
		return gtnWsItemIdentifierBeanList != null ? new ArrayList<>(gtnWsItemIdentifierBeanList)
				: gtnWsItemIdentifierBeanList;
	}

	public void setGtnWsItemIdentifierBeanList(List<GtnWsItemIdentifierBean> gtnWsItemIdentifierBeanList) {
		this.gtnWsItemIdentifierBeanList = gtnWsItemIdentifierBeanList != null
				? new ArrayList<>(gtnWsItemIdentifierBeanList)
				: gtnWsItemIdentifierBeanList;
	}

	public GntWsItemPricingBean getGntWsItemPricingBean() {
		return gntWsItemPricingBean;
	}

	public void setGntWsItemPricingBean(GntWsItemPricingBean gntWsItemPricingBean) {
		this.gntWsItemPricingBean = gntWsItemPricingBean;
	}

	public List<NotesTabBean> getNoteBeanList() {
		return noteBeanList != null ? new ArrayList<>(noteBeanList) : noteBeanList;
	}

	public void setNoteBeanList(List<NotesTabBean> noteBeanList) {
		this.noteBeanList = noteBeanList != null ? new ArrayList<>(noteBeanList) : noteBeanList;
	}

}
