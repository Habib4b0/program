package com.stpl.app.security.userGrpBsnsRoleMgmt.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

public class UserGrpBsnsRoleDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5315806772770200824L;

    public HashMap<String, Long> getUserGroupMap() {
        return userGroupMap;
    }

    public void setUserGroupMap(HashMap<String, Long> userGroupMap) {
        this.userGroupMap = userGroupMap;
    }

    public HashMap<String, Integer> getBusinessRoleMap() {
        return businessRoleMap;
    }

    public void setBusinessRoleMap(HashMap<String, Integer> businessRoleMap) {
        this.businessRoleMap = businessRoleMap;
    }

    public List<String> getUserGroup() {
        return userGroup == null ? userGroup : new ArrayList<>(userGroup);
    }

    public void setUserGroup(List<String> userGroup) {
        this.userGroup = userGroup == null ? userGroup : new ArrayList<>(userGroup);
    }

    public List<String> getBusinessRole() {
        return businessRole == null ? businessRole : new ArrayList<>(businessRole);
    }

    public void setBusinessRole(List<String> businessRole) {
        this.businessRole = businessRole == null ? businessRole : new ArrayList<>(businessRole);
    }

    public List<String> getSelectedBusinessRole() {
        return selectedBusinessRole == null ? selectedBusinessRole : new ArrayList<>(selectedBusinessRole);
    }

    public void setSelectedBusinessRole(List<String> selectedBusinessRole) {
        this.selectedBusinessRole = selectedBusinessRole == null ? selectedBusinessRole : new ArrayList<>(selectedBusinessRole);
    }

    public Set<String> getSelectedBusinessRole1() {
        return selectedBusinessRole1 == null ? selectedBusinessRole1 : Collections.unmodifiableSet(selectedBusinessRole1);
    }

    public void setSelectedBusinessRole1(Set<String> selectedBusinessRole1) {
        this.selectedBusinessRole1 = selectedBusinessRole1 == null ? selectedBusinessRole1 : Collections.unmodifiableSet(selectedBusinessRole1);
    }

    public String getSelUserGroupId() {
        return selUserGroupId;
    }

    public void setSelUserGroupId(String selUserGroupId) {
        this.selUserGroupId = selUserGroupId;
    }
	private HashMap<String,Long> userGroupMap;
	private HashMap<String,Integer> businessRoleMap;
	private List<String> userGroup;
	private List<String> businessRole;
	private List<String> selectedBusinessRole;
	private Set<String> selectedBusinessRole1;
	private String  selUserGroupId = StringUtils.EMPTY;
	
}
