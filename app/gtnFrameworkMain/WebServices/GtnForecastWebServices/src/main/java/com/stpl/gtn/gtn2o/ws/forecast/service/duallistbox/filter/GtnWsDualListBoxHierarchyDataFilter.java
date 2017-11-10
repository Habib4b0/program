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
public class GtnWsDualListBoxHierarchyDataFilter implements GtnWsDualListBoxFilter {

	private final List<String> filterDataList;
	private int filterIndex;
	private final boolean isGetAllRows;

	public GtnWsDualListBoxHierarchyDataFilter(int filterIndex, List<String> filterDataList, boolean isGetAllRows) {
		this.filterDataList = filterDataList;
		this.filterIndex = filterIndex;
		this.isGetAllRows = isGetAllRows;
	}

	public void setFilterIndex(int filterIndex) {
		this.filterIndex = filterIndex;
	}

	@Override
	public int getFilterIndex() {
		return filterIndex;
	}

	@Override
	public GtnWsRecordBean applyFilter(GtnWsRecordBean dualListData) {
		Object[] dataObj = dualListData.getRawObjectArray();
		if (dataObj != null) {
			Object obj = dataObj[filterIndex];
			String val = String.valueOf(obj == null ? StringUtils.EMPTY : obj.toString());

			if (isGetAllRows) {
				return dualListData;
			}
			for (String filterData : filterDataList) {
				if (checkHigherLevelData(filterData, val) || checkLowerLevelData(filterData, val)) {
					return dualListData;
				}
			}
		}
		dualListData.setRawObjectArray(GtnFrameworkCommonConstants.EMPTY_ARRAY);
		return dualListData;
	}

	private boolean checkHigherLevelData(final String filterValue, final String actualData) {
		return filterValue.startsWith(actualData);
	}

	private boolean checkLowerLevelData(final String filterValue, final String actualData) {
		return actualData.startsWith(filterValue);
	}

}
