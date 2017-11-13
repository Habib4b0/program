package com.stpl.gtn.gtn2o.ws.request.pagetreetable;

import com.stpl.gtn.gtn2o.ws.forecast.bean.DataSelectionBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnWsPagedTreeTableRequest {

	public GtnWsPagedTreeTableRequest() {
		super();
	}

	private GtnForecastBean gtnForecastBean;

	private DataSelectionBean dataSelectionBean;

	private Object[] inputs;

	public Object[] getInputs() {
		return inputs == null ? inputs : inputs.clone();
	}

	public void setInputs(Object[] inputs) {
		this.inputs = inputs == null ? inputs : inputs.clone();
	}

	public GtnForecastBean getGtnForecastBean() {
		return gtnForecastBean;
	}

	public void setGtnForecastBean(GtnForecastBean gtnForecastBean) {
		this.gtnForecastBean = gtnForecastBean;
	}

	public DataSelectionBean getDataSelectionBean() {
		return dataSelectionBean;
	}

	public void setDataSelectionBean(DataSelectionBean dataSelectionBean) {
		this.dataSelectionBean = dataSelectionBean;
	}
}
