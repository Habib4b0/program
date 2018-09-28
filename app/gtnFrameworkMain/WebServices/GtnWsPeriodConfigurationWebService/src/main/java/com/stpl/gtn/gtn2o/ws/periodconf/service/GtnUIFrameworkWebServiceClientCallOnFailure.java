/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.periodconf.service;

import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class GtnUIFrameworkWebServiceClientCallOnFailure {
    
    
    private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameworkWebServiceClient.class);
    private long staticTime;
    private GtnWsPeriodConfigurationService periodConfigurationService;

   
    
    public GtnUIFrameworkWebServiceClientCallOnFailure(GtnWsPeriodConfigurationService periodConfigurationService) {
        this.periodConfigurationService = periodConfigurationService;
    }

    public void callGtnWebServiceUrlOnFailure() {
        try {
            logger.info("staticTime****----------------" +staticTime);
            if((System.currentTimeMillis() - staticTime)/1000 < 300){
                Long startTime = System.currentTimeMillis();
                TimeUnit.SECONDS.sleep(10);
                logger.info("Waiting Time in milliseconds----------------" + (System.currentTimeMillis() - startTime));
                 periodConfigurationService.init();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(GtnUIFrameworkWebServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public long getStaticTime() {
        return staticTime;
    }

    public void setStaticTime(long staticTime) {
        this.staticTime = staticTime;
    }
    
    

    
}
