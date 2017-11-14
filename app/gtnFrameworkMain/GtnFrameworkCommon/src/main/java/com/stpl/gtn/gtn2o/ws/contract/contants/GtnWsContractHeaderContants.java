/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.contract.contants;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnWsContractHeaderContants {
    private GtnWsContractHeaderContants(){
        /**
         * empty constructor
         */
    }

	public static final String GTN_WS_CONTRACT_HEADER_SERVICE = "/GtnWsContractHeader";
	public static final String GTN_WS_CONTRACT_HEADER_FETCH_INFORMATION_SERVICE = "/fetchContractHeaderInformation";
	public static final String GTN_WS_CONTRACT_HEADER_DELETE_SERVICE = "/deleteContractHeaderModel";
	public static final String GTN_WS_CONTRACT_HEADER_SAVE_SERVICE = "/saveService";
	public static final String GTN_WS_CONTRACT_HEADER_ID_NO_VALIDATION_SERVICE = "/contractHeaderIdNoValidation";

	public static final String GTN_WS_CONTRACT_ALIAS_DELETE_SERVICE = "/deleteContractAliasModel";
}
