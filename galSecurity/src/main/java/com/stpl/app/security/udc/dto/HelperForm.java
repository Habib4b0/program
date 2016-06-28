package com.stpl.app.security.udc.dto;

//import lombok.Data;

import org.apache.commons.lang.StringUtils;
//@Data
public class HelperForm {

	private int helperTableSid;
	private String description=StringUtils.EMPTY;
	private String listName=StringUtils.EMPTY;
	private String category;
	private Boolean add;
	private Boolean edit;

    public int getHelperTableSid() {
        return helperTableSid;
    }

    public void setHelperTableSid(int helperTableSid) {
        this.helperTableSid = helperTableSid;
    }
	private Boolean delete;   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getAdd() {
        return add;
    }

    public void setAdd(Boolean add) {
        this.add = add;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
	
	
}
