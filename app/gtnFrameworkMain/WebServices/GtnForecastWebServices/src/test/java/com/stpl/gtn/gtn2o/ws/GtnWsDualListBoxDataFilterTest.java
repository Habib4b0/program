/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws;

import org.junit.Ignore;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.duallistbox.GtnWsDualListBoxFilter;

/**
 *
 * @author Nimisha.Rakesh
 */
@Ignore
public class GtnWsDualListBoxDataFilterTest implements GtnWsDualListBoxFilter {

	@Override
	public int getFilterIndex() {
		return 0;
	}

	@Override
	public GtnWsRecordBean applyFilter(GtnWsRecordBean dualListData) {

		return dualListData;
	}
}
