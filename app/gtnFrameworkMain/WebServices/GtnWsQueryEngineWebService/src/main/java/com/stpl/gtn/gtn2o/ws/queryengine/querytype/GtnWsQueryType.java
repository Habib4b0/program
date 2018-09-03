package com.stpl.gtn.gtn2o.ws.queryengine.querytype;

import com.stpl.gtn.gtn2o.ws.queryengine.querytypeimpl.GtnExecuteSelectQueryImpl;
import com.stpl.gtn.gtn2o.ws.queryengine.querytypeimpl.GtnWsExecuteCountQueryImpl;
import com.stpl.gtn.gtn2o.ws.queryengine.querytypeimpl.GtnWsExecuteInsertUpdateParamsQueryImpl;
import com.stpl.gtn.gtn2o.ws.queryengine.querytypeimpl.GtnWsExecuteInsertUpdateQueryImpl;
import com.stpl.gtn.gtn2o.ws.queryengine.querytypeimpl.GtnWsExecuteSelectParamsQueryImpl;

public enum GtnWsQueryType {

	SELECT(new GtnExecuteSelectQueryImpl()), COUNT(new GtnWsExecuteCountQueryImpl()), SELECTWITHPARAMS(
			new GtnWsExecuteSelectParamsQueryImpl()), INSERTORUPDATE(
					new GtnWsExecuteInsertUpdateQueryImpl()),  INSERTORUPDATEWITHPARAMS(
									new GtnWsExecuteInsertUpdateParamsQueryImpl());

	private final GtnWsQueryTypeInterface gtnWsQueryTypeInterface;

	private GtnWsQueryType(GtnWsQueryTypeInterface gtnWsQueryTypeInterface) {
		this.gtnWsQueryTypeInterface = gtnWsQueryTypeInterface;
	}

	public GtnWsQueryTypeInterface getGtnWsQueryTypeInterface() {
		return gtnWsQueryTypeInterface;
	}

}
