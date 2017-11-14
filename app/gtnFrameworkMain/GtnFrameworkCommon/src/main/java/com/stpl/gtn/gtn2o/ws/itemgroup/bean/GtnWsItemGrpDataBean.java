package com.stpl.gtn.gtn2o.ws.itemgroup.bean;

import java.io.Serializable;

public class GtnWsItemGrpDataBean implements Serializable {

	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	public GtnWsItemGrpDataBean() {
		super();
	}

	private int itemGroupSid;
	private int itemMasterSid;
	private int versionNo;

	public int getItemMasterSid() {
		return itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		this.itemMasterSid = itemMasterSid;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	public int getItemGroupSid() {
		return itemGroupSid;
	}

	public void setItemGroupSid(int itemGroupSid) {
		this.itemGroupSid = itemGroupSid;
	}

	@Override
	public String toString() {
		return "GtnWsItemGrpDataBean{" + "itemGroupSid=" + itemGroupSid + ", itemMasterSid=" + itemMasterSid
				+ ", versionNo=" + versionNo + '}';
	}

}
