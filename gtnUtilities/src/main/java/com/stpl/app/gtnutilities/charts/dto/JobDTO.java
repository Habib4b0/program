/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnutilities.charts.dto;

/**
 *
 * @author Karthik.Raja
 */
public class JobDTO {
    
    //Dont Change existing order of Fields (Used Reflection to Set Data)
    
    private String jobname;
    private String enabled;
    private String description;
    private String occurs;
    private String occurs_detail;
    private String frequency;
    private String execution_Time;
    private String last_Execution_Day;
    private String status;
    
   // Add New Fields here 
    
    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }
    

    public String getOccurs_detail() {
        return occurs_detail;
    }

    public void setOccurs_detail(String occurs_detail) {
        this.occurs_detail = occurs_detail;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOccurs() {
        return occurs;
    }

    public void setOccurs(String occurs) {
        this.occurs = occurs;
    }


    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getExecution_Time() {
        return execution_Time;
    }

    public void setExecution_Time(String execution_Time) {
        this.execution_Time = execution_Time;
    }

    public String getLast_Execution_Day() {
        return last_Execution_Day;
    }

    public void setLast_Execution_Day(String last_Execution_Day) {
        this.last_Execution_Day = last_Execution_Day;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

 

    
}
