package com.stpl.app.security.businessRoleModuleMaster.dto;


import org.apache.commons.lang.StringUtils;

public class HelperDTO {	
	private int id;	
	private String description=StringUtils.EMPTY;
	
	
	public HelperDTO(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public HelperDTO() {
	}

	@Override
	public String toString() {		
		return description;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
