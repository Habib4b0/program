/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.util;

/**
 *
 * @author karthikraja.k
 */
public class TableResultCustom {
	
	/** The obj result. */
	private Object[] objResult;
	
	/** The obj result header. */
	private String[] objResultHeader;
	
		/**
         * Getter for objResult.
	 * @return the objResult Object array.
	 */
	public Object[] getObjResult() {
		final Object[] copyValue=objResult;
		
		return copyValue;
		 
	}
	
	/**
	 * Setter for objResult .
	 *
	 * @param objResult the objResult to set
	 */
	public void setObjResult(final Object...objResult) {
		this.objResult = objResult;
	}
	/**
         * Getter for objResultHeader.
	 * @return the objResultHeader String array.
	 */
	public String[] getObjResultHeader() {
		final String[] cpyResultHeader=objResultHeader;

		return cpyResultHeader;
		
	}
	/**
         * Setter for objResultHeader.
	 * @param objResultHeader the objResultHeader to set
	 */
	public void setObjResultHeader(final String... objResultHeader) {
		this.objResultHeader = objResultHeader;
	}

}

