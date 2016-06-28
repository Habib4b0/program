/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galworkflow.dto;
import com.vaadin.ui.Image;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
/**
 *
 * @author Santanukumar
 */
public class WorkFlowHistoryLookupDTO implements Serializable{
    
    private String company = StringUtils.EMPTY;
    private String segment = StringUtils.EMPTY;
    private String segmentGroup = StringUtils.EMPTY;
    private String marketType = StringUtils.EMPTY;
    private String discAcctType = StringUtils.EMPTY;
    private String brand = StringUtils.EMPTY;
    private String item = StringUtils.EMPTY;
    private String status = StringUtils.EMPTY;
    private String creationDate = StringUtils.EMPTY;
    private String createdBy = StringUtils.EMPTY;
    private String startDate = StringUtils.EMPTY;
    private String modifiedBy = StringUtils.EMPTY;
    private Date modifiedDate;
    private String notes = StringUtils.EMPTY;
    private Boolean checkbox = false;
    private Image attachmentLink;
    private String fileName=StringUtils.EMPTY;

    public Boolean getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(Boolean checkbox) {
        this.checkbox = checkbox;
    }

    public Image getAttachmentLink() {
        return attachmentLink;
    }

    public void setAttachmentLink(Image attachmentLink) {
        this.attachmentLink = attachmentLink;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getSegmentGroup() {
        return segmentGroup;
    }

    public void setSegmentGroup(String segmentGroup) {
        this.segmentGroup = segmentGroup;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getDiscAcctType() {
        return discAcctType;
    }

    public void setDiscAcctType(String discAcctType) {
        this.discAcctType = discAcctType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
}
