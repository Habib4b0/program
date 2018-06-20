/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.itemaster.constants;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnWsItemMasterQueryContants {

	private GtnWsItemMasterQueryContants() {
	}

	public static final String GTN_ITEM_MASTER_SEARCH_QUERY = " FROM ITEM_MASTER IM LEFT JOIN BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID  LEFT JOIN dbo.ITEM_IDENTIFIER IQ ON IM.ITEM_MASTER_SID=IQ.ITEM_MASTER_SID  LEFT JOIN ITEM_MASTER IM1 ON CONVERT (VARCHAR(100),IM1.ITEM_MASTER_SID)=IM.NDC9 LEFT JOIN HELPER_TABLE HELPERTABLETHERAPETIC ON HELPERTABLETHERAPETIC.HELPER_TABLE_SID=IM.THERAPEUTIC_CLASS"
                +" LEFT JOIN HELPER_TABLE itemStatusHelper on IM.ITEM_STATUS = itemStatusHelper.HELPER_TABLE_SID"
                +" LEFT JOIN HELPER_TABLE itemTypeHelper on IM.ITEM_TYPE = itemTypeHelper.HELPER_TABLE_SID"
                +" LEFT JOIN HELPER_TABLE formHelper on IM.FORM = formHelper.HELPER_TABLE_SID"
                +" LEFT JOIN HELPER_TABLE strengthHelper on IM.STRENGTH = strengthHelper.HELPER_TABLE_SID";
	public static final String GTN_ITEM_MASTER_SEARCH_WHERE_CLAUSE = " IM.INBOUND_STATUS <> 'D' ";
	public static final String GTN_ITEM_MASTER_QUALIFIER_SEARCH_QUERY = "  FROM DBO.ITEM_QUALIFIER IQ  ";
	public static final String GTN_ITEM_MASTER_QUALIFIER_SEARCH_QUERY_WHERE = " IQ.INBOUND_STATUS <> 'D' ";
	public static final String GTN_ITEM_MASTER_PRICING_SEARCH_QUERY = "  FROM DBO.ITEM_PRICING_QUALIFIER IQ  ";
	public static final String GTN_ITEM_MASTER_PRICING_SEARCH_QUERY_WHERE = " IQ.INBOUND_STATUS <> 'D' ";
	public static final String GTN_ITEM_MASTER_NEW_FORMULATION_SEARCH_QUERY_WHERE = " IM.INBOUND_STATUS <> 'D' AND IM.ITEM_TYPE =(SELECT HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME='ITEM_TYPE' AND DESCRIPTION like '%NDC-9%') ";
	public static final String GTN_ITEM_MASTER_SEARCH_QUERY_FORMULATION = " FROM ITEM_MASTER IM LEFT JOIN BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID  JOIN dbo.ITEM_IDENTIFIER IQ ON IM.ITEM_MASTER_SID=IQ.ITEM_MASTER_SID  LEFT JOIN COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID=IM.ORGANIZATION_KEY LEFT JOIN ITEM_MASTER IM1 ON CONVERT (VARCHAR(100),IM1.ITEM_MASTER_SID)=IM.NDC9";

}
