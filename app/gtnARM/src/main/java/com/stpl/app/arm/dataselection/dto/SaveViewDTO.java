/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author
 */
public class SaveViewDTO {

    private int viewId;

    /**
     * The view name.
     */
    private String viewName = StringUtils.EMPTY;

    /**
     * The view type.
     */
    private String viewType = "Private";

    private int createdBy = 0;
    private String createdDate = StringUtils.EMPTY;
    private String modifyBy = StringUtils.EMPTY;
    private String modifiDate = StringUtils.EMPTY;
    private int projectionSid = 0;

    /**
     * Gets the view id.
     *
     * @return the viewId
     */
    public int getViewId() {
        return viewId;
    }

    /**
     * Sets the view id.
     *
     * @param viewId the viewId to set
     */
    public void setViewId(final int viewId) {
        this.viewId = viewId;
    }

    /**
     * Gets the view name.
     *
     * @return the viewName
     */
    public String getViewName() {
        return viewName;
    }

    /**
     * Sets the view name.
     *
     * @param viewName the viewName to set
     */
    public void setViewName(final String viewName) {
        this.viewName = viewName;
    }

    /**
     * Gets the view type.
     *
     * @return the viewType
     */
    public String getViewType() {
        return viewType;
    }

    /**
     * Sets the view type.
     *
     * @param viewType the viewType to set
     */
    public void setViewType(final String viewType) {
        this.viewType = viewType;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifiDate() {
        return modifiDate;
    }

    public void setModifiDate(String modifiDate) {
        this.modifiDate = modifiDate;
    }

    public int getProjectionSid() {
        return projectionSid;
    }

    public void setProjectionSid(int projectionSid) {
        this.projectionSid = projectionSid;
    }

}
