/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.pojo;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Sathya.Seelan
 */
public class ComponentPojo {

    private String componentName;
    private String propertyName;
    private String listName = StringUtils.EMPTY;
    private String parentLayout;
    private List<GtnUIFrameWorkActionConfig> actionConfigList;

    public ComponentPojo(String componentName, String propertyName, String listName, String parentLayout) {
        this.componentName = componentName;
        this.propertyName = propertyName;
        this.listName = listName;
        this.parentLayout = parentLayout;
    }

    public ComponentPojo(String componentName, String propertyName, String parentLayout) {
        this.componentName = componentName;
        this.propertyName = propertyName;
        this.parentLayout = parentLayout;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getParentLayout() {
        return parentLayout;
    }

    public void setParentLayout(String parentLayout) {
        this.parentLayout = parentLayout;
    }

    public List<GtnUIFrameWorkActionConfig> getActionConfigList() {
        return actionConfigList;
    }

    public void setActionConfigList(List<GtnUIFrameWorkActionConfig> actionConfigList) {
        this.actionConfigList = actionConfigList;
    }

}
