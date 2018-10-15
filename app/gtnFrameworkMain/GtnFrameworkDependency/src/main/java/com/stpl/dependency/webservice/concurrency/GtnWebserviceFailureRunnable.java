/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.dependency.webservice.concurrency;

import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClientCallOnFailure;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class GtnWebserviceFailureRunnable {
    long staticTime = System.currentTimeMillis();
    
    public Runnable createRunnable(final Object... inputs) {
        Runnable runnable = () -> {
            GtnUIFrameworkWebServiceClientCallOnFailure gtnWebServiceClientCallOnFailure = new GtnUIFrameworkWebServiceClientCallOnFailure();
            gtnWebServiceClientCallOnFailure.setStaticTime((long)inputs[1]);
            gtnWebServiceClientCallOnFailure.callGtnWebServiceUrlOnFailureOnDeploy();
            ((GtnCommonWebServiceImplClass) inputs[0]).initCallOnFailure();
        };
        return runnable;
    }
    
}
