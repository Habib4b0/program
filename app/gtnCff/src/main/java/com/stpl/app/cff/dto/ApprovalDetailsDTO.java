/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.cff.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author shrihariharan
 */
public class ApprovalDetailsDTO {
  private String approvedBy=StringUtils.EMPTY;
  private Date approvedDate;
  private String approvalSequence=StringUtils.EMPTY;
  private String approvalStatus=StringUtils.EMPTY;

    public ApprovalDetailsDTO() {
        super();
    }
  
    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getApprovedDate() {
        return approvedDate == null ? null : (Date) approvedDate.clone();
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate == null ? null : (Date) approvedDate.clone();
    }

    public String getApprovalSequence() {
        return approvalSequence;
    }

    public void setApprovalSequence(String approvalSequence) {
        this.approvalSequence = approvalSequence;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
