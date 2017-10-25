/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.serverlogging.dto;

/**
 *
 * @author Karthik.Raja
 */
public class LoggingDto {
    String logDestination;
    String messagesSelected;
    String active;

    public String getLogDestination() {
        return logDestination;
    }

    public void setLogDestination(String logDestination) {
        this.logDestination = logDestination;
    }

    public String getMessagesSelected() {
        return messagesSelected;
    }

    public void setMessagesSelected(String messagesSelected) {
        this.messagesSelected = messagesSelected;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
