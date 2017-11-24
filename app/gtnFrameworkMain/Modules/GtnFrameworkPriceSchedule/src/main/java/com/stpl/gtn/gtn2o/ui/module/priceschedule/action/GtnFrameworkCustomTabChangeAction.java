package com.stpl.gtn.gtn2o.ui.module.priceschedule.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory.GtnFrameworkPriceProtectionValueChangeManager;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory.GtnFrameworkPriceTabValueChangeManager;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

public class GtnFrameworkCustomTabChangeAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
            return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		int position = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("tabSheet").getTabSheetSelectedTabIndex();
                
                if(position==3){
                       System.out.println("in position==3----------------------------------");
                     GtnUIFrameworkGlobalUI.getVaadinBaseComponent("psPriceProtectionMainButtonLayout").setVisible(true);
                }
                else{
                    GtnUIFrameworkGlobalUI.getVaadinBaseComponent("psPriceProtectionMainButtonLayout").setVisible(false);
                }
		
                   if (position == 2 || position == 3) {
                GtnFrameworkPriceProtectionValueChangeManager.setValueChangeAllowed(Boolean.TRUE);
                GtnFrameworkPriceTabValueChangeManager.setValueChangeAllowed(Boolean.TRUE);
                String previousTable = GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_RESULT_DATA_TABLE;
                String currentTable = GtnFrameworkCommonConstants.PS_PRICING_TAB_RESULT_DATA_TABLE;
                if (position == 3) {
                    previousTable = GtnFrameworkCommonConstants.PS_PRICING_TAB_RESULT_DATA_TABLE;
                    currentTable = GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_RESULT_DATA_TABLE;
                    
                }
                
                GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
                        .getVaadinComponentData(currentTable);
                GtnUIFrameworkPagedTableLogic currentTableLogic = componentData.getCurrentPageTableLogic();
                currentTableLogic.setCurrentPage(currentTableLogic.getCurrentPage());
                  componentData = GtnUIFrameworkGlobalUI
                        .getVaadinComponentData(previousTable);
                GtnUIFrameworkPagedTableLogic previousTableLogic = componentData.getCurrentPageTableLogic();
                currentTableLogic.setCheckedRecordCount( previousTableLogic.getCheckRecordCount());
                boolean checkValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(previousTable)
                        .getExtPagedTable().getColumnCheckBox(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
                GtnUIFrameworkGlobalUI.getVaadinBaseComponent(currentTable)
                        .getExtPagedTable().setColumnCheckBox(GtnFrameworkCommonConstants.CHECK_RECORD_ID, true, checkValue);

                return;
            }
                   
                System.out.println("ppTab------------------------------------------"+position);
               
		GtnFrameworkPriceProtectionValueChangeManager.setValueChangeAllowed(Boolean.FALSE);
		GtnFrameworkPriceTabValueChangeManager.setValueChangeAllowed(Boolean.FALSE);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
