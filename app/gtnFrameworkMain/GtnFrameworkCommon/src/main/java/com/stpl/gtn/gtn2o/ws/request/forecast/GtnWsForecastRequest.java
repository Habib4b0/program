package com.stpl.gtn.gtn2o.ws.request.forecast;

import java.io.Serializable;

import com.stpl.gtn.gtn2o.ws.forecast.bean.DataSelectionBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnWsForecastRequest implements Serializable {

	private static final long serialVersionUID = -6998704930458645747L;

	public GtnWsForecastRequest() {
		super();
	}

	private GtnForecastBean gtnForecastBean;

	private DataSelectionBean dataSelectionBean;

	private int inputSize;

	private Object[] inputs;

	public DataSelectionBean getDataSelectionBean() {
		return dataSelectionBean;
	}

	public void setDataSelectionBean(DataSelectionBean dataSelectionBean) {
		this.dataSelectionBean = dataSelectionBean;
	}

	public Object[] getInputs() {
		return inputs != null ? inputs.clone() : inputs;
	}

	public void setInputs(Object[] inputs) {
		this.inputs = inputs != null ? inputs.clone() : inputs;
	}

	public GtnForecastBean getGtnForecastBean() {
		return gtnForecastBean;
	}

	public void setGtnForecastBean(GtnForecastBean gtnForecastBean) {
		this.gtnForecastBean = gtnForecastBean;
	}

	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
		s.defaultWriteObject();
		s.writeInt(inputSize);
		for (int i = 0; i < inputSize; i++) {
			s.writeObject(inputs[i]);
		}
	}

	private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
		s.defaultReadObject();
		int arrayLength = s.readInt();
		Object[] elemntArray = new Object[arrayLength];
		for (int i = 0; i < elemntArray.length; i++) {
			elemntArray[i] = s.readObject();
		}
		this.inputs = elemntArray;
	}

}
