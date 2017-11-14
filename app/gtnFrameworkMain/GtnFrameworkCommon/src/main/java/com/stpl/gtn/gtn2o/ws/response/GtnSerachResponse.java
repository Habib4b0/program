
package com.stpl.gtn.gtn2o.ws.response;

import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;

public class GtnSerachResponse implements GtnWSResponseData {

	public GtnSerachResponse() {
		super();
	}

	private int count;
	private GtnUIFrameworkDataTable resultSet;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public GtnUIFrameworkDataTable getResultSet() {
		return resultSet;
	}

	public void setResultSet(GtnUIFrameworkDataTable resultSet) {
		this.resultSet = resultSet;
	}

}
