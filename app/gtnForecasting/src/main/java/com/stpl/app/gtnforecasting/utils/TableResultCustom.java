package com.stpl.app.gtnforecasting.utils;

// TODO: Auto-generated Javadoc
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
		final Object[] copyValue=objResult;
		
		return copyValue;
		 
	}
	
	/**
	 * Setter for objResult .
	 *
	 * @param objResult the objResult to set
	 */
	public void setObjResult(final Object...objResult) {
		this.objResult = objResult == null ? objResult : objResult.clone();
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
		this.objResultHeader = objResultHeader == null ? objResultHeader : objResultHeader.clone();
	}

}
