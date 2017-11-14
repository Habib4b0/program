/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service.duallistbox.filter;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.duallistbox.GtnWsDualListBoxFilter;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;

/**
 *
 * @author STPL
 */
public class GtnWsDualListBoxDataFilter implements GtnWsDualListBoxFilter {

	private final String filterColumnName;
	private int filterIndex;
	private final String filterValue;

	public GtnWsDualListBoxDataFilter(List<String> recordHeader, String filterColumnName, String filterValue) {
		this.filterColumnName = filterColumnName;
		this.filterValue = filterValue;
		this.filterIndex = recordHeader.indexOf(filterColumnName);
	}

	public void setFilterIndex(int filterIndex) {
		this.filterIndex = filterIndex;
	}

	public String getFilterColumnName() {
		return filterColumnName;
	}

	@Override
	public int getFilterIndex() {
		return filterIndex;
	}

	@Override
	public GtnWsRecordBean applyFilter(GtnWsRecordBean gtnWsRecordBean) {
		Object[] dataObj = gtnWsRecordBean.getRawObjectArray();
		if (dataObj != null) {
			Object obj = dataObj[filterIndex];
			String val = String.valueOf(obj == null ? StringUtils.EMPTY : obj.toString());
			if (filterValue.contains(val)) {
				return gtnWsRecordBean;
			}
			gtnWsRecordBean.setRawObjectArray(GtnFrameworkCommonConstants.EMPTY_ARRAY);
			return gtnWsRecordBean;
		}
		return null;
	}

}
