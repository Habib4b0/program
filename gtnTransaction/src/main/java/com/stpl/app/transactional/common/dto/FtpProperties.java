/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.dto;

/**
 *
 * @author Manasa
 */
public class FtpProperties {
    
    /**
     * *
     * the Ftp Properties File Path
     */
    public static final String FTP_CONFIGURATION_PATH = "/opt/bpigtn/conf/BPI Configuration/FTPConfiguration.properties";  
    private String scripts;
    private String interfaceName;

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
