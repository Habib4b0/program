/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.portlets;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import com.vaadin.ui.UI;
/**
 *
 * @author Karthik.Raja
 */
@Component(service = UI.class, property = {
    "com.liferay.portlet.display-category=Transaction Management",
    "javax.portlet.name=VwAdjustDemandForecastAct",
    "javax.portlet.display-name=Demand",
   
    "com.liferay.portlet.instanceable=true",
    "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class VwAdjustDemandForecastAct  extends com.stpl.gtn.gtn2o.ui.portlets.GtnFrameworkTransactionPortlet  {
    
}
