/**
 *
 */
package com.stpl.app.security.permission.model;

import java.io.Serializable;

/**
 * The Class AppPermission to maintaining the permissions.
 *
 * @author satheesh
 */
public class AppPermission implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7936862903782151735L;

	/**
	 * The module name.
	 */
	private String moduleName;

	/**
	 * The property name.
	 */
	private String propertyName;

	/**
	 * The add flag.
	 */
	private boolean addFlag;

	/**
	 * The edit flag.
	 */
	private boolean editFlag;

	/**
	 * The view flag.
	 */
	private boolean viewFlag;

	/**
	 * The function flag.
	 */
	private boolean functionFlag;

	/**
	 * The tab flag.
	 */
	private boolean tabFlag;

	/**
	 * The search flag.
	 */
	private boolean searchFlag;

	/**
	 * Gets the module name.
	 *
	 * @return the module name
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * Sets the module name.
	 *
	 * @param moduleName
	 *            the module name
	 */
	public void setModuleName(final String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * Gets the property name.
	 *
	 * @return the property name
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * Sets the property name.
	 *
	 * @param propertyName
	 *            the property name
	 */
	public void setPropertyName(final String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * Checks if is add flag.
	 *
	 * @return true, if checks if is add flag
	 */
	public boolean isAddFlag() {
		return addFlag;
	}

	/**
	 * Sets the add flag.
	 *
	 * @param addFlag
	 *            the adds the flag
	 */
	public void setAddFlag(final boolean addFlag) {
		this.addFlag = addFlag;
	}

	/**
	 * Checks if is edit flag.
	 *
	 * @return true, if checks if is edit flag
	 */
	public boolean isEditFlag() {
		return editFlag;
	}

	/**
	 * Sets the edit flag.
	 *
	 * @param editFlag
	 *            the edits the flag
	 */
	public void setEditFlag(final boolean editFlag) {
		this.editFlag = editFlag;
	}

	/**
	 * Checks if is view flag.
	 *
	 * @return true, if checks if is view flag
	 */
	public boolean isViewFlag() {
		return viewFlag;
	}

	/**
	 * Sets the view flag.
	 *
	 * @param viewFlag
	 *            the view flag
	 */
	public void setViewFlag(final boolean viewFlag) {
		this.viewFlag = viewFlag;
	}

	/**
	 * Checks if is function flag.
	 *
	 * @return true, if checks if is function flag
	 */
	public boolean isFunctionFlag() {
		return functionFlag;
	}

	/**
	 * Sets the function flag.
	 *
	 * @param functionFlag
	 *            the function flag
	 */
	public void setFunctionFlag(final boolean functionFlag) {
		this.functionFlag = functionFlag;
	}

	/**
	 * Checks if is tab flag.
	 *
	 * @return true, if checks if is tab flag
	 */
	public boolean isTabFlag() {
		return tabFlag;
	}

	/**
	 * Sets the tab flag.
	 *
	 * @param tabFlag
	 *            the tab flag
	 */
	public void setTabFlag(final boolean tabFlag) {
		this.tabFlag = tabFlag;
	}

	/**
	 * Checks if is search flag.
	 *
	 * @return true, if checks if is search flag
	 */
	public boolean isSearchFlag() {
		return searchFlag;
	}

	/**
	 * Sets the search flag.
	 *
	 * @param searchFlag
	 *            the search flag
	 */
	public void setSearchFlag(final boolean searchFlag) {
		this.searchFlag = searchFlag;
	}

}
