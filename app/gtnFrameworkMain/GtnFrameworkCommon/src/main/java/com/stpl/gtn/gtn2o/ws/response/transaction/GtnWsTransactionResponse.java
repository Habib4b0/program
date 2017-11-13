/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.response.transaction;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vinoth.Parthasarathy
 */
public class GtnWsTransactionResponse {

	public GtnWsTransactionResponse() {
		super();
	}

	private Map<String, Class<?>> columnDataTypeMap = new HashMap<>();
	private Object[] viewResults;

	public Map<String, Class<?>> getColumnDataTypeMap() {
		return columnDataTypeMap;
	}

	public void setColumnDataTypeMap(Map<String, Class<?>> columnDataTypeMap) {
		this.columnDataTypeMap = columnDataTypeMap;
	}

	public Object[] getViewResults() {
		return viewResults != null ?  viewResults.clone() : viewResults;
	}

	public void setViewResults(Object[] viewResults) {
		this.viewResults = viewResults != null ?  viewResults.clone() : viewResults;
	}

}
