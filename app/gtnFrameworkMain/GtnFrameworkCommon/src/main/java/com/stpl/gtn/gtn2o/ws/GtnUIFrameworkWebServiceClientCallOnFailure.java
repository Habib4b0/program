/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class GtnUIFrameworkWebServiceClientCallOnFailure {

    private String url;
    private String moduleName;
    private GtnUIFrameworkWebserviceRequest request;
    private GtnWsSecurityToken securityToken;
    private GtnUIFrameworkWebServiceClient wsClient;
    private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameworkWebServiceClient.class);
    private long staticTime;


    public void callGtnWebServiceUrlOnFailure() {
        try {
            logger.info("staticTime****----------------" +staticTime);
            if((System.currentTimeMillis() - staticTime)/1000 < 150){
                Long startTime = System.currentTimeMillis();
                TimeUnit.SECONDS.sleep(10);
                logger.info("Waiting Time in milliseconds----------------" + (System.currentTimeMillis() - startTime));
                wsClient.callGtnWebServiceUrl(url, moduleName, request, securityToken);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(GtnUIFrameworkWebServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public GtnUIFrameworkWebserviceRequest getRequest() {
        return request;
    }

    public void setRequest(GtnUIFrameworkWebserviceRequest request) {
        this.request = request;
    }

    public GtnWsSecurityToken getSecurityToken() {
        return securityToken;
    }

    public void setSecurityToken(GtnWsSecurityToken securityToken) {
        this.securityToken = securityToken;
    }

    public GtnUIFrameworkWebServiceClient getWsClient() {
        return wsClient;
    }

    public void setWsClient(GtnUIFrameworkWebServiceClient wsClient) {
        this.wsClient = wsClient;
    }

    public long getStaticTime() {
        return staticTime;
    }

    public void setStaticTime(long staticTime) {
        this.staticTime = staticTime;
    }
    

}
