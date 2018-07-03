/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.constants;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnFrameworkAdjustmentDetailsStringConstants {

    private static final String[] CUSTOM_FILTER_PROPERTY_IDS = {GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_TYPE,
        GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_STATUS, "form", GtnFrameworkCommonConstants.STRENGTH};
    
    private static final String[] CUSTOM_FILTER_LIST_NAME_ARRAY = {"ITEM_TYPE", "STATUS", "FORM", "STRENGTH"};

    public static String[] getCustomFilterListNameArray() {
        return CUSTOM_FILTER_LIST_NAME_ARRAY.clone();
    }

    public static String[] getCustomFilterPropertyIds() {
        return CUSTOM_FILTER_PROPERTY_IDS.clone();
    }
}
