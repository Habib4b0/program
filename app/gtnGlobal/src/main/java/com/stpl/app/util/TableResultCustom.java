package com.stpl.app.util;

/**
 * Class that holds the headers and visible column for tables.
 * 
 * @author 
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
		return objResult;
		 
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
		return objResultHeader;
		
	}
	/**
         * Setter for objResultHeader.
	 * @param objResultHeader the objResultHeader to set
	 */
	public void setObjResultHeader(final String... objResultHeader) {
		this.objResultHeader = objResultHeader;
	}

}
