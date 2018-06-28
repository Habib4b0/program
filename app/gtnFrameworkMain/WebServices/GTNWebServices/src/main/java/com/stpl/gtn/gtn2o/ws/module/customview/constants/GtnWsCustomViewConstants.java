/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.customview.constants;

public class GtnWsCustomViewConstants {

    private GtnWsCustomViewConstants() {
        super();
    }
    
    public static final String GTN_CUSTOM_VIEW_SEARCH_QUERY =  " FROM CUST_VIEW_MASTER CVM join CUST_VIEW_DETAILS CVD on  CVD.CUSTOM_VIEW_MASTER_SID=CVM.CUST_VIEW_MASTER_SID JOIN RELATIONSHIP_BUILDER RBC ON RBC.RELATIONSHIP_BUILDER_SID = CVM.CUSTOMER_RELATIONSHIP_SID JOIN RELATIONSHIP_BUILDER RBP ON RBP.RELATIONSHIP_BUILDER_SID = CVM.PRODUCT_RELATIONSHIP_SID "
            + " LEFT JOIN HELPER_TABLE customVwScreen on CVM.MODULE_TYPE = customVwScreen.HELPER_TABLE_SID ";
    public static final String CVM = "CVM";
    public static final String RELATIONSHIP_NAME = "RELATIONSHIP_NAME";
    public static final String RELATIONSHIP_BUILDER_SID = "RELATIONSHIP_BUILDER_SID";
}
