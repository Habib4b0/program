
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request;

import java.io.Serializable;

/**
 *
 * @author Karthik.Raja
 */
public class GtnwsExcelRequest implements Serializable {

	private static final long serialVersionUID = -6998704930458645747L;

	public GtnwsExcelRequest() {
		super();
	}

	private Object[] inputs;
	private int inputSize;

	public Object[] getInputs() {
		return inputs != null ? inputs.clone() : inputs;

	}

	public void setInputs(Object[] inputs) {
		this.inputs = inputs != null ? inputs.clone() : inputs;
		this.inputSize = (inputs != null ? inputs.length : 0);
	}

	private void writeObject(java.io.ObjectOutputStream excelOutputStream) throws java.io.IOException {
		excelOutputStream.defaultWriteObject();
		excelOutputStream.writeInt(inputSize);
		for (int i = 0; i < inputSize; i++) {
			excelOutputStream.writeObject(inputs[i]);
		}
	}

	private void readObject(java.io.ObjectInputStream excelInputStream)
			throws java.io.IOException, ClassNotFoundException {
		excelInputStream.defaultReadObject();
		int arrayLength = excelInputStream.readInt();
		Object[] elemntArray = new Object[arrayLength];
		for (int i = 0; i < elemntArray.length; i++) {
			elemntArray[i] = excelInputStream.readObject();
		}
		this.inputs = elemntArray;
	}

}
