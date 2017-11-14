package com.stpl.gtn.gtn2o.ws.bean.xmlbean;

import javax.xml.bind.annotation.XmlElement;

public class GtnWsRowConfig {

	private String moduleName;
	private String counterValue;
	private String counterUpdatedDate;

	public GtnWsRowConfig() {
		/**
		 * Default Constructor
		 */
	}

	@XmlElement
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@XmlElement
	public String getCounterValue() {
		return counterValue;
	}

	public void setCounterValue(String counterValue) {
		this.counterValue = counterValue;
	}

	@XmlElement
	public String getCounterUpdatedDate() {
		return counterUpdatedDate;
	}

	public void setCounterUpdatedDate(String counterUpdatedDate) {
		this.counterUpdatedDate = counterUpdatedDate;
	}

}
