package com.stpl.gtn.gtn2o.ws.components.duallistbox;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

public interface GtnWsDualListBoxFilter {

	public GtnWsRecordBean applyFilter(GtnWsRecordBean dualListData);

	public int getFilterIndex();
}
