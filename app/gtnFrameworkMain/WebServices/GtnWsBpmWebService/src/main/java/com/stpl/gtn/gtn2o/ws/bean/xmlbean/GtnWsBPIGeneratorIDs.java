package com.stpl.gtn.gtn2o.ws.bean.xmlbean;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GtnWsBPIGeneratorIDs {
	private List<GtnWsRowConfig> rowList;

	public GtnWsBPIGeneratorIDs() {
		/**
		 * Default Constructor
		 */
	}

	@XmlElement(name = "row")
	public List<GtnWsRowConfig> getRowList() {
		return rowList == null ? null : Collections.unmodifiableList(rowList);
	}

	public void setRowList(List<GtnWsRowConfig> rowList) {
		this.rowList = Collections.unmodifiableList(rowList);
	}

}
