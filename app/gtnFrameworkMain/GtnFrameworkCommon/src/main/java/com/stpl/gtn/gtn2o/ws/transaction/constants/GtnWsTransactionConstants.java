/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.transaction.constants;

/**
 *
 * @author Vinoth.Parthasarathy
 */
public class GtnWsTransactionConstants {
    private GtnWsTransactionConstants(){
        /**
         * empty constructor
         */
    }

	public static final String GTN_WS_TRANSACTION_SERVICE = "/gtnTransaction";

	public static final String GTN_WS_TRANSACTION_GETDATATYPE_SERVICE = "/getColumnDataType";

	public static final String GTN_WS_TRANSACTION_GETSEARCHRESULTS_SERVICE = "/getSearchResults";

	public static final String GTN_WS_TRANSACTION_GETVIEWRESULTS_SERVICE = "/getViewResults";
        
	public static final String GTN_WS_TRANSACTION_VALIDATION_SERVICE = "/getValidationRunning";

	public static final String GTN_WS_TRANSACTION_REPROCESS_SERVICE = "/reprocess";
}
