package com.stpl.gtn.gtn2o.ws.processscheduler.bean;

/**
 *
 * @author deepak.kumar
 */
public class GtnWsFtpPropertiesBean {
    private String scripts;
    private String interfaceName;

    public GtnWsFtpPropertiesBean(){
    	super();
    }
    
    public String getScripts() {
        return scripts;
    }

    public void setScripts(String scripts) {
        this.scripts = scripts;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

}