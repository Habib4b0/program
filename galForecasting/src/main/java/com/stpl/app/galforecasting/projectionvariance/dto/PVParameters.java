/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.projectionvariance.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author gopinath
 */
public class PVParameters implements Serializable, Cloneable {

    private String projectionId = StringUtils.EMPTY;
    private String userId = StringUtils.EMPTY;
    private String sessionId = StringUtils.EMPTY;
    private String discountLevel = StringUtils.EMPTY;
    private String frequency = StringUtils.EMPTY;
    private String viewIndicator = StringUtils.EMPTY;
    private String groupFilter = StringUtils.EMPTY;
    private String groupFilterValue = StringUtils.EMPTY;
    private String hierarchyNo = StringUtils.EMPTY;
    private String levelNo = StringUtils.EMPTY;
    private String viewName = StringUtils.EMPTY;
    private String discountId = StringUtils.EMPTY;
    private int customViewMasterSid = 0;
    List<Integer> pirorProjectionIds = new ArrayList();
     private String view = StringUtils.EMPTY;
//    private boolean viewName = StringUtils.EMPTY;

    public String getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(String projectionId) {
        this.projectionId = projectionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDiscountLevel() {
        return discountLevel;
    }

    public void setDiscountLevel(String discountLevel) {
        this.discountLevel = discountLevel;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getViewIndicator() {
        return viewIndicator;
    }

    public void setViewIndicator(String viewIndicator) {
        this.viewIndicator = viewIndicator;
    }

    public String getGroupFilter() {
        return groupFilter;
    }

    public void setGroupFilter(String groupFilter) {
        this.groupFilter = groupFilter;
    }

    public String getGroupFilterValue() {
        return groupFilterValue;
    }

    public void setGroupFilterValue(String groupFilterValue) {
        this.groupFilterValue = groupFilterValue;
    }

    public String getHierarchyNo() {
        return hierarchyNo;
    }

    public void setHierarchyNo(String hierarchyNo) {
        this.hierarchyNo = hierarchyNo;
    }

    public String getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(String levelNo) {
        this.levelNo = levelNo;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public int getCustomViewMasterSid() {
        return customViewMasterSid;
    }

    public void setCustomViewMasterSid(int customViewMasterSid) {
        this.customViewMasterSid = customViewMasterSid;
    }

    public List<Integer> getPirorProjectionIds() {
        return pirorProjectionIds;
    }

    public void setPirorProjectionIds(List<Integer> pirorProjectionIds) {
        this.pirorProjectionIds = pirorProjectionIds;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.projectionId);
        hash = 53 * hash + Objects.hashCode(this.userId);
        hash = 53 * hash + Objects.hashCode(this.sessionId);
        hash = 53 * hash + Objects.hashCode(this.discountLevel);
        hash = 53 * hash + Objects.hashCode(this.frequency);
        hash = 53 * hash + Objects.hashCode(this.viewIndicator);
        hash = 53 * hash + Objects.hashCode(this.groupFilter);
        hash = 53 * hash + Objects.hashCode(this.groupFilterValue);
        hash = 53 * hash + Objects.hashCode(this.hierarchyNo);
        hash = 53 * hash + Objects.hashCode(this.levelNo);
        hash = 53 * hash + Objects.hashCode(this.viewName);
        hash = 53 * hash + Objects.hashCode(this.discountId);
        hash = 53 * hash + this.customViewMasterSid;
        hash = 53 * hash + Objects.hashCode(this.pirorProjectionIds);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PVParameters other = (PVParameters) obj;
        if (this.customViewMasterSid != other.customViewMasterSid) {
            return false;
        }
        if (!Objects.equals(this.projectionId, other.projectionId)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.sessionId, other.sessionId)) {
            return false;
        }
        if (!Objects.equals(this.discountLevel, other.discountLevel)) {
            return false;
        }
        if (!Objects.equals(this.frequency, other.frequency)) {
            return false;
        }
        if (!Objects.equals(this.viewIndicator, other.viewIndicator)) {
            return false;
        }
        if (!Objects.equals(this.groupFilter, other.groupFilter)) {
            return false;
        }
        if (!Objects.equals(this.groupFilterValue, other.groupFilterValue)) {
            return false;
        }
        if (!Objects.equals(this.hierarchyNo, other.hierarchyNo)) {
            return false;
        }
        if (!Objects.equals(this.levelNo, other.levelNo)) {
            return false;
        }
        if (!Objects.equals(this.viewName, other.viewName)) {
            return false;
        }
        if (!Objects.equals(this.discountId, other.discountId)) {
            return false;
        }
        if (this.pirorProjectionIds.size() == other.pirorProjectionIds.size()) {
            return false;
        } 

            for (Integer pirorProjectionId : other.pirorProjectionIds) {
                if (!this.pirorProjectionIds.contains(pirorProjectionId)) {
                    return false;
                }
            }

        return true;
    }

    @Override
    public PVParameters clone() {

        try {
            return (PVParameters) super.clone(); //To change body of generated methods, choose Tools | Templates.
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(PVParameters.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

}
