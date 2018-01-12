/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dto;

import com.stpl.ifs.ui.util.NumericConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author karthikeyans
 */
public class LevelMapKey implements Serializable, Comparable<LevelMapKey>,Cloneable {

    private String levelNo;
    private String levelValuesSid;
    private  List<Integer> ccpIds = new ArrayList();
    private String HierarchyNo;
    private int customViewDetailsSid;

    public LevelMapKey(String levelNo, String levelValuesSid) {
        this.levelNo = levelNo;
        this.levelValuesSid = levelValuesSid;

    }

    public String getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(String levelNo) {
        this.levelNo = levelNo;
    }

    public String getLevelValuesSid() {
        return levelValuesSid;
    }

    public void setLevelValuesSid(String levelValuesSid) {
        this.levelValuesSid = levelValuesSid;
    }

    @Override
    public int hashCode() {
        int hash = NumericConstants.FIVE;
        hash = NumericConstants.SEVENTEEN * hash + Objects.hashCode(this.levelNo);
        hash =  NumericConstants.SEVENTEEN * hash + Objects.hashCode(this.levelValuesSid);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LevelMapKey other = (LevelMapKey) obj;
        if (!Objects.equals(this.levelNo, other.levelNo)) {
            return false;
        }
        if (!Objects.equals(this.levelValuesSid, other.levelValuesSid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LevelMapKey{" + "levelNo=" + levelNo + ", levelValuesSid=" + levelValuesSid + ", ccpIds=" + ccpIds + ", HierarchyNo=" + HierarchyNo + ", customViewDetailsSid=" + customViewDetailsSid + '}';
    }

    
    
    @Override
    public int compareTo(LevelMapKey o) {
        return levelValuesSid.compareTo(o.getLevelValuesSid());
    }

    public List<Integer> getCcpIds() {
        return ccpIds == null ? ccpIds : new ArrayList<>(ccpIds);
    }

    public void setCcpIds(List<Integer> ccpIds) {
        this.ccpIds = ccpIds == null ? ccpIds : new ArrayList<>(ccpIds);
    }

    public String getHierarchyNo() {
        return HierarchyNo;
    }

    public void setHierarchyNo(String HierarchyNo) {
        this.HierarchyNo = HierarchyNo;
    }

    @Override
    public LevelMapKey clone() throws CloneNotSupportedException {
        return (LevelMapKey)super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCustomViewDetailsSid() {
        return customViewDetailsSid;
    }

    public void setCustomViewDetailsSid(int customViewDetailsSid) {
        this.customViewDetailsSid = customViewDetailsSid;
    }
    

}
