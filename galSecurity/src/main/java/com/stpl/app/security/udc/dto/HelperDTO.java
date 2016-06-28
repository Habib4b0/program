package com.stpl.app.security.udc.dto;

//import lombok.Getter;
//import lombok.Setter;

import org.apache.commons.lang.StringUtils;

public class HelperDTO {
//	@Getter
//	@Setter
	private int id;
//	@Getter
//	@Setter
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
