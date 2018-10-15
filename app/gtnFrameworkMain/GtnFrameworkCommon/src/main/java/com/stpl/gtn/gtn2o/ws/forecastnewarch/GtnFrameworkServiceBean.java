/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecastnewarch;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class GtnFrameworkServiceBean {
    
    public static final List<String> PROJECTION_SEARCH_VALUES = Arrays.asList("Commercial Forecasting_projectionName", "Commercial Forecasting_projectionDescription",
			"Commercial Forecasting_company", "Commercial Forecasting_businessUnit", "forecastLandingScreen_customerHierarchy",
                        "Commercial Forecasting_prodhierarchyName","Commercial Forecasting_customerGroup","Commercial Forecasting_productGroup","Commercial Forecasting_from","Commercial Forecasting_to",
                        "projactionName","description","customerHierarchy","customerLevel","productHierarchy","productLevel","createdBy","createdDate","modifiedDate","Company","businessUnit"
    );
    public static final List<String> KEY_MAP_LIST = Arrays.asList("privatePublic", "BusinessUnitGLcomp","CompanyMasterGLcomp",
			"frequency", "dataSelectionDeduction", "CustomerGroup","ProductGroup","salesCustomView","deductionCustomView");
    public static final List<String> VIEW_LIST = Arrays.asList("VIEW_NAME", "VIEW_TYPE","CREATED_BY",
			"CREATED_DATE", "MODIFIED_BY", "MODIFIED_DATE","VIEW_DATA","VIEW_ID");
    public static final List<String> VIEW_MAP = Arrays.asList("VIEW_NAME", "VIEW_TYPE","CREATED_BY",
			"CREATED_DATE", "MODIFIED_BY", "MODIFIED_DATE","VIEW_DATA","VIEW_ID");
    
}
