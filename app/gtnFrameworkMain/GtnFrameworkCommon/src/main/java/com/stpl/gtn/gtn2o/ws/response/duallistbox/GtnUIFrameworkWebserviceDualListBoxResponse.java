
package com.stpl.gtn.gtn2o.ws.response.duallistbox;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

public class GtnUIFrameworkWebserviceDualListBoxResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	public GtnUIFrameworkWebserviceDualListBoxResponse() {
		super();
	}

	private List<GtnWsRecordBean> dualListBoxTableDataList;

	private String fileName;

	public List<GtnWsRecordBean> getDualListBoxTableDataList() {
		return dualListBoxTableDataList == null ? dualListBoxTableDataList
				: Collections.unmodifiableList(dualListBoxTableDataList);
	}

	public void setDualListBoxTableDataList(List<GtnWsRecordBean> dualListBoxTableDataList) {
		this.dualListBoxTableDataList = dualListBoxTableDataList == null ? dualListBoxTableDataList
				: Collections.unmodifiableList(dualListBoxTableDataList);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
