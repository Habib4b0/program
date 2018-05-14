/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.itemgroup;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnWsItemGroupBean;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnWsItemGrpDataBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnWsItemGroupRequest implements GtnWSRequestData {

	public GtnWsItemGroupRequest() {
		super();
	}

	private GtnWsItemGroupBean gtnWsItemGroupBean;
	private List<GtnWsItemGrpDataBean> gtnWsItemGrpDataBeanList;

	public GtnWsItemGroupBean getGtnWsItemGroupBean() {
		return gtnWsItemGroupBean;
	}

	public void setGtnWsItemGroupBean(GtnWsItemGroupBean gtnWsItemGroupBean) {
		this.gtnWsItemGroupBean = gtnWsItemGroupBean;
	}

	public List<GtnWsItemGrpDataBean> getGtnWsItemGrpDataBeanList() {
		return gtnWsItemGrpDataBeanList != null ? Collections.unmodifiableList(gtnWsItemGrpDataBeanList)
				: gtnWsItemGrpDataBeanList;
	}

	public void setGtnWsItemGrpDataBeanList(List<GtnWsItemGrpDataBean> gtnWsItemGrpDataBeanList) {
		this.gtnWsItemGrpDataBeanList = gtnWsItemGrpDataBeanList != null
				? Collections.unmodifiableList(gtnWsItemGrpDataBeanList)
				: gtnWsItemGrpDataBeanList;
	}
        private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
	}

}
