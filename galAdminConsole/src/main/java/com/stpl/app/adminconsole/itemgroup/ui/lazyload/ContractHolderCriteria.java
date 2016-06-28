/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.itemgroup.ui.lazyload;

import org.vaadin.addons.lazycontainer.AbstractSearchCriteria;

// TODO: Auto-generated Javadoc
/**
 * The Class ContractHolderCriteria.
 *
 * @author shrihariharan
 */
public class ContractHolderCriteria extends AbstractSearchCriteria {

	
	private String searchText;

	
	private String description;

	
	public String getDescription() {
		return description;
	}

	
	public void setDescription(final String description) {
		this.description = description;
	}

	
	public String getSearchText() {
		return searchText;
	}

	
	public void setSearchText(final String searchText) {
		this.searchText = searchText;
	}
}
