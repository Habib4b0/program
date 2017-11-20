/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractheader.constants;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnWsContractHeaderQueryContants {

	private GtnWsContractHeaderQueryContants() {
	}

	public static final String GTN_CONTRACT_HEADER_SEARCH_QUERY = " FROM	CONTRACT_MASTER CON LEFT JOIN COMPANY_MASTER COMPTRADE ON COMPTRADE.COMPANY_MASTER_SID = CON.CONT_HOLD_COMPANY_MASTER_SID ";
	public static final String GTN_CONTRACT_HEADER_SELECT_CLAUSE = " SELECT COUNT(DISTINCT CONTRACT_MASTER_SID)  ";
	public static final String GTN_CONTRACT_HEADER_WHERE_CLAUSE = " CON.INBOUND_STATUS <> 'D' ";

}
