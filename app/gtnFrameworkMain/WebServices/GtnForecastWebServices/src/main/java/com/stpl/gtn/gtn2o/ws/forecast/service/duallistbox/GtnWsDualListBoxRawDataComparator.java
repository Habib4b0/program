/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service.duallistbox;

import java.util.Comparator;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

/**
 *
 * @author STPL
 */
public class GtnWsDualListBoxRawDataComparator implements Comparator<GtnWsRecordBean> {

	private final int comparisonIndex;

	GtnWsDualListBoxRawDataComparator(int comparisonIndex) {
		this.comparisonIndex = comparisonIndex;
	}

	@Override
	public int compare(GtnWsRecordBean o1, GtnWsRecordBean o2) {
		Integer first = Integer.valueOf(o1.getRawObjectArray()[this.comparisonIndex].toString());
		Integer second = Integer.valueOf(o2.getRawObjectArray()[this.comparisonIndex].toString());
		return first - second;
	}

}
