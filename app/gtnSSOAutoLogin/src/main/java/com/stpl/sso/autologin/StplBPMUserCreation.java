/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.sso.autologin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jayaram.LeelaRam
 */
public class StplBPMUserCreation {

    private static final Logger LOGGER = LoggerFactory.getLogger(StplBPMUserCreation.class);
    public static final String FWD_SLASH = "\\";
    public static final String BWD_SLASH = "/";
    private String encryptedValue;
    

    public boolean createBPMUser(String userName, String sessionID) {
        boolean loginStatus = false;
        DataSource dataSource = null;
        Connection connection = null;
        PreparedStatement prepareStmt = null;
        getAESEncryptedValueUsingJS(userName);
        if (  "".equals(this.getEncryptedValue()) || "null".equals(this.getEncryptedValue())) {
            return loginStatus;
        } else {
            try {
                Context initialContext = new InitialContext();
                dataSource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
                connection = dataSource.getConnection();
                prepareStmt = connection.prepareStatement("insert into STPL_BPM_SSO(USERNAME,PASSWORD,SESSIONID) values(?,?,?)");
                
                prepareStmt.setString(1, this.getEncryptedValue());
                prepareStmt.setString(2, this.getEncryptedValue());
                prepareStmt.setString(3, sessionID);
                int update = prepareStmt.executeUpdate();
                loginStatus = update > 0;
            } catch (NamingException | SQLException ex) {
                LOGGER.error(ex.getMessage(), ex);
            } finally {
                try {
                    if (prepareStmt != null) {
                        prepareStmt.close();
                    }
                    connection.close();
                } catch (SQLException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        }
        return loginStatus;
    }

    private void getAESEncryptedValueUsingJS(String username) {
    	
        String scriptFile = System.getProperty("jboss.home.dir");
        if (!"null".equals(scriptFile)) {
            try {
                scriptFile = scriptFile.replace(FWD_SLASH, BWD_SLASH) + "/standalone/deployments/ROOT.war/html/js/aes.js";
                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName("javascript");
                engine.eval(new FileReader(new File(scriptFile)));
                engine.put("bpmUserObject", this);
                engine.eval(" var encryptedValue = CryptoJS.AES.encrypt('" + username + "', 'Secret Passphrase'); ");
                engine.eval(" bpmUserObject.setEncryptedValue(this.encryptedValue); ");
                LOGGER.info("Encrypted Text  = " + this.getEncryptedValue());
            } catch (FileNotFoundException ex) {
                LOGGER.error("aes.js file doesn't exists");
                LOGGER.error(ex.getMessage());
            } catch (ScriptException ex) {
                LOGGER.error(ex.getMessage());
            }
        } else {
            LOGGER.error(" Jboss home is not yet set");
        }
    }

    public String getEncryptedValue() {
        return encryptedValue;
    }

    public void setEncryptedValue(String encryptedValue) {
        this.encryptedValue = encryptedValue;
    }

}
