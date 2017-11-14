/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.table.newpagedtreetable;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnUIFrameworkNewPagedTableConfig {

	private String countUrl=null;
	private int itemPerPage = 15;
	private boolean sinkItemPerPageWithPageLength = true;

	public String getCountUrl() {
		return countUrl;
	}

	public void setCountUrl(String countUrl) {
		this.countUrl = countUrl;
	}

	public int getItemPerPage() {
		return itemPerPage;
	}

	public void setItemPerPage(int itemPerPage) {
		this.itemPerPage = itemPerPage;
	}

	public boolean isSinkItemPerPageWithPageLength() {
		return sinkItemPerPageWithPageLength;
	}

	public void setSinkItemPerPageWithPageLength(boolean sinkItemPerPageWithPageLength) {
		this.sinkItemPerPageWithPageLength = sinkItemPerPageWithPageLength;
	}

}
