package com.stpl.gtn.gtn2o.ws.itemmaster.bean;

public class GtnWsBrandMasterBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public GtnWsBrandMasterBean() {
		super();
	}

	private int brandMasterSid;
	private String brandId;
	private String brandName;
	private String displayBrand;
	private String brandDesc;

	public int getBrandMasterSid() {
		return brandMasterSid;
	}

	public void setBrandMasterSid(int brandMasterSid) {
		this.brandMasterSid = brandMasterSid;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getDisplayBrand() {
		return displayBrand;
	}

	public void setDisplayBrand(String displayBrand) {
		this.displayBrand = displayBrand;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

}
