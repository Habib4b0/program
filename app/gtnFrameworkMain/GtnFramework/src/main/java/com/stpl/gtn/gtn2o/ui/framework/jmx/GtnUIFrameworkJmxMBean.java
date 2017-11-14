/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.jmx;

/**
 *
 * @author Nadhiya.Jayaram
 */
public interface GtnUIFrameworkJmxMBean {

	public int getVaadinComponentsCount();

	public int returnTextFieldCount();

	public int returnComboBoxCount();

	public int returnPopUpTextFieldCount();

	public int returnPopDateFieldCount();

	public int returnExtPagedTableCount();

	public int returnButtonCount();

	public int returnCustomMenuBarCount();

	public int returnExtFilterTableCount();

	public int returnTextAreaCount();

	public int returnOptionGroupCount();

	public int returnFreezePagedTreeTableCount();

	public int objectListCount();


	public void clearMap();

	public boolean isEnableJMX();

	public void getEnableJMX(boolean jmxSwitchFlag);

	public int returnFieldFactoryTextFieldCount();

	public int returnFieldFactoryComboBoxCount();

	public int returnFieldFactoryCustomTextFieldCount();

	public int returnFieldFactoryPopupDateFieldCount();

	public int returnFieldFactoryButtonCount();

	public int returnFieldFactoryTextAreaCount();
}
