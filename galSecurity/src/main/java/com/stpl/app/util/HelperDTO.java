package com.stpl.app.util;

//import lombok.Getter;
//import lombok.Setter;


public class HelperDTO {
//	@Getter
//	@Setter
	private int id;
//	@Getter
//	@Setter
	private String description=HelperUtils.EMPTY;
	
	
	
	public HelperDTO(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public HelperDTO(String description) {
		this.id = 0;
		this.description = description;
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
