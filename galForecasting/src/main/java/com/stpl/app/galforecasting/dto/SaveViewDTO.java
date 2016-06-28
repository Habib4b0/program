/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.dto;

import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class SaveViewDTO.
 *
 * @author lokeshwari
 */
public class SaveViewDTO {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 7364905471337092671L;

    /**
     * The view id.
     */
    private int viewId;

    /**
     * The view name.
     */
    private String viewName = StringUtils.EMPTY;

    /**
     * The view type.
     */
    private String viewType = "Private";

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
}
