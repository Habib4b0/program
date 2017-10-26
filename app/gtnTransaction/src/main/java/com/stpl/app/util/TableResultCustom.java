package com.stpl.app.util;


/**
 * The Class TableResultCustom.
 */
public class TableResultCustom {

	/** The obj result. */
	private Object[] objResult;

	/** The obj result header. */
	private String[] objResultHeader;

	/**
	 * Gets the obj result.
	 *
	 * @return the obj result
	 */
	public Object[] getObjResult() {
		return objResult;

	}

	/**
	 * Sets the obj result.
	 *
	 * @param objResult
	 *            the obj result
	 */
	public void setObjResult(final Object... objResult) {
		this.objResult = objResult;
	}

	/**
	 * Gets the obj result header.
	 *
	 * @return the obj result header
	 */
	public String[] getObjResultHeader() {
		return objResultHeader;

	}

	/**
	 * Sets the obj result header.
	 *
	 * @param objResultHeader
	 *            the obj result header
	 */
	public void setObjResultHeader(final String... objResultHeader) {
		this.objResultHeader = objResultHeader;
	}

}
