/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.sso.autologin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
            try {
                InputStream in = getClass().getResourceAsStream("/aes.js"); 
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                ScriptEngineManager manager = new ScriptEngineManager(ClassLoader.getSystemClassLoader());
                ScriptEngine engine = manager.getEngineByName("nashorn");
                engine.eval(reader);
                engine.put("bpmUserObject", this);	
                engine.eval(" var encryptedValue = CryptoJS.AES.encrypt('" + username + "', 'Secret Passphrase'); ");
                engine.eval(" bpmUserObject.setEncryptedValue(this.encryptedValue); ");
                LOGGER.info("Encrypted Text  = " + this.getEncryptedValue());
            }  catch (ScriptException ex) {
                LOGGER.error(ex.getMessage());
            }
        
    }

    public String getEncryptedValue() {
        return encryptedValue;
    }

    public void setEncryptedValue(String encryptedValue) {
        this.encryptedValue = encryptedValue;
    }

}
