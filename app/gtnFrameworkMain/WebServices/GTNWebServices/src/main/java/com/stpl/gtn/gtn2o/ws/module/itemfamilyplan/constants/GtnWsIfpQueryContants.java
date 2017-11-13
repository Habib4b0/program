/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.constants;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnWsIfpQueryContants {

	private GtnWsIfpQueryContants() {
	}

	public static final String GTN_IFP_SEARCH_QUERY_SELECT = " SELECT COUNT(DISTINCT IFP.IFP_MODEL_SID)  ";
	public static final String GTN_IFP_SEARCH_QUERY_COUNT = " FROM  IFP_MODEL IFP JOIN IFP_DETAILS IFD ON IFP.IFP_MODEL_SID=IFD.IFP_MODEL_SID "
			+ "JOIN DBO.ITEM_MASTER IM ON IFD.ITEM_MASTER_SID=IM.ITEM_MASTER_SID";
	public static final String GTN_IFP_SEARCH_QUERY = " FROM IFP_MODEL IFP JOIN IFP_DETAILS IFD ON IFP.IFP_MODEL_SID=IFD.IFP_MODEL_SID "
			+ "JOIN ITEM_MASTER IM ON IFD.ITEM_MASTER_SID=IM.ITEM_MASTER_SID";
	public static final String GTN_IFP_SEARCH_QUERY_WHERE = " IFP.INBOUND_STATUS <> 'D' ";

}
