/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.common.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
public class SessionDTO {
    
    private int systemId;
    private String mode;
    private String fromViewPage;
    private int versionNo;
    private String logic;
    private String userId;
    private String sessionId;
    private int selectedHierarchyCategory;
    private int selectedHierarchySessionId;
    private int hierarchyVersion;
    private String arpSessionId;
    private List<String> selectedItems= new ArrayList<>();
    
    
    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getFromViewPage() {
        return fromViewPage;
    }

    public void setFromViewPage(String fromViewPage) {
        this.fromViewPage = fromViewPage;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    public String getLogic() {
        return logic;
    }

    public void setLogic(String logic) {
        this.logic = logic;
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

    public int getSelectedHierarchyCategory() {
        return selectedHierarchyCategory;
    }

    public void setSelectedHierarchyCategory(int selectedHierarchyCategory) {
        this.selectedHierarchyCategory = selectedHierarchyCategory;
    }

    public int getSelectedHierarchySessionId() {
        return selectedHierarchySessionId;
    }

    public void setSelectedHierarchySessionId(int selectedHierarchySessionId) {
        this.selectedHierarchySessionId = selectedHierarchySessionId;
    }

    public int getHierarchyVersion() {
        return hierarchyVersion;
    }

    public void setHierarchyVersion(int hierarchyVersion) {
        this.hierarchyVersion = hierarchyVersion;
    }

    public String getArpSessionId() {
        return arpSessionId;
    }

    public void setArpSessionId(String arpSessionId) {
        this.arpSessionId = arpSessionId;
    }

    public List<String> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<String> selectedItems) {
        this.selectedItems = selectedItems;
    }
    
}
