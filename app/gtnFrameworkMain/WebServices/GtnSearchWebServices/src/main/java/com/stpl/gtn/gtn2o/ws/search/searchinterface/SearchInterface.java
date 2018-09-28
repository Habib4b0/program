/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.search.searchinterface;

import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.search.sqlservice.GtnSearchwebServiceSqlService;

/**
 *
 * @author anandh.karuppusamy
 */

public interface SearchInterface {
    public GtnUIFrameworkWebserviceResponse getSearchResults(GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest,
                                                                                                           String query,GtnSearchwebServiceSqlService gtnSearchSqlService);
    
}
