/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.schedule.dto;

/**
 *
 * @author hazi.s
 */
public class FtpProperties {

    public static final String FTP_CONFIGURATION_PATH = "/BPI Configuration/FTPConfiguration.properties";
    public static final String GL_POSTING_SH = "GL_Posting_Intf.sh";

    private String scripts;

    public String getScripts() {
        return scripts;
    }

    public void setScripts(String scripts) {
        this.scripts = scripts;
    }

}
