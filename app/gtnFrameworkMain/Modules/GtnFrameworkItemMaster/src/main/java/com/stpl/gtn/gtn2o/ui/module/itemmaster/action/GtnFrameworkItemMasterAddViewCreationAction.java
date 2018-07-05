package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.constants.GtnFrameworkItemMasterStringContants;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.util.GtnFrameworkItemMasterArmUdc1Utility;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkItemMasterAddViewCreationAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass  {
	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkItemMasterAddViewCreationAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		/*
		 * No Implementation Needed
		 */
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("Inside GtnFrameworkItemMasterAddViewCreationAction --> doAction... start");
		checkUDC1Type();
		String udc1Type=(String) GtnUIFrameworkGlobalUI.getSessionProperty("UDC1");
		
		if(udc1Type!=null && udc1Type.equalsIgnoreCase(GtnFrameworkItemMasterStringContants.ARM_UDC_1)) {
			activeCheckedComboBoxUdc1();
			return;
		}
		activeComboBoxUdc1();
		
		gtnLogger.debug("Inside GtnFrameworkItemMasterAddViewCreationAction --> doAction... end");
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
	
	private void checkUDC1Type() {
		String udc1Type = System.getProperty(GtnFrameworkItemMasterStringContants.UDC1+"gjh");
		GtnUIFrameworkGlobalUI.addSessionProperty(GtnFrameworkItemMasterStringContants.UDC1, udc1Type);
	}
	
	private void activeComboBoxUdc1() {
		/*
		 * Visibility Of Udc1 Component
		 */
		
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkItemMasterStringContants.ITEM_INFORMATION_TAB_UDC_1_LAYOUT)
			.setVisible(Boolean.TRUE);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkItemMasterStringContants.ITEM_INFORMATION_TAB_UDC_1_CHECKED_COMBO_BOX_LAYOUT)
			.setVisible(Boolean.FALSE);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkItemMasterStringContants.ITEM_INFORMATION_TAB_UDC_1_TEXT_BOX_LAYOUT)
			.setVisible(Boolean.FALSE);
	}

	private void activeCheckedComboBoxUdc1() {
		try {
			List<String> itemValueList=new ArrayList<>(GtnFrameworkItemMasterArmUdc1Utility.getArmUdc1ValueList());
			List<String> itemCodeList=new ArrayList<>(GtnFrameworkItemMasterArmUdc1Utility.getArmUdc1CodeList());
			
			for(int i=0;i<itemValueList.size();i++) {
				if (itemValueList.get(i).contains(GtnFrameworkItemMasterStringContants.COMMA)) {
					itemValueList.remove(i);
					itemCodeList.remove(i);
				}
			}
			/*
			 * Setting Values in checked combo box for udc1
			 */
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkItemMasterStringContants.ITEM_INFORMATION_TAB_UDC_1_CHECKED_COMBO_BOX)
				.loadCheckedCombobox(GtnFrameworkItemMasterStringContants.SELECT_VALUE, itemCodeList, itemValueList);
			/*
			 * Visibility Of Udc1 Component
			 */
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkItemMasterStringContants.ITEM_INFORMATION_TAB_UDC_1_CHECKED_COMBO_BOX_LAYOUT)
				.setVisible(Boolean.TRUE);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkItemMasterStringContants.ITEM_INFORMATION_TAB_UDC_1_LAYOUT)
				.setVisible(Boolean.FALSE);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkItemMasterStringContants.ITEM_INFORMATION_TAB_UDC_1_TEXT_BOX_LAYOUT)
				.setVisible(Boolean.FALSE);
		} catch (GtnFrameworkValidationFailedException e) {
			gtnLogger.error("Error in GtnFrameworkItemMasterAddViewCreationAction --> activeCheckedComboBoxUdc1", e);
		}
	}

}
