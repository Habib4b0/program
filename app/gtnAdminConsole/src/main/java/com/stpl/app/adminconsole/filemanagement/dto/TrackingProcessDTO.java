/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.filemanagement.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohamed.hameed
 */
public class TrackingProcessDTO {
    private String fileType = StringUtils.EMPTY;
    private String processIdentifier = StringUtils.EMPTY;
    private String startTime = StringUtils.EMPTY;
    private String endTime = StringUtils.EMPTY;
    private String noOfProjections = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;
    private String processType = StringUtils.EMPTY;
    private String sequence = StringUtils.EMPTY;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getProcessIdentifier() {
        return processIdentifier;
    }

    public void setProcessIdentifier(String processIdentifier) {
        this.processIdentifier = processIdentifier;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getNoOfProjections() {
        return noOfProjections;
    }

    public void setNoOfProjections(String noOfProjections) {
        this.noOfProjections = noOfProjections;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
    
}
