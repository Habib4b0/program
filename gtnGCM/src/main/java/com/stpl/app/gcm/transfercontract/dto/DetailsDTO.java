/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.dto;

import com.stpl.app.serviceUtils.ConstantUtil;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Harlin
 */
public class DetailsDTO {

    private Boolean check = Boolean.FALSE;
    private String id = StringUtils.EMPTY;
    private String number = StringUtils.EMPTY;
    private String name = StringUtils.EMPTY;
    private String match = StringUtils.EMPTY;
    private String add = StringUtils.EMPTY;
    private String remove = StringUtils.EMPTY;
    private String componentType = ConstantUtil.SELECT_ONE;
    private String searchField = ConstantUtil.SELECT_ONE;
    private String value = StringUtils.EMPTY;
    
    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getRemove() {
        return remove;
    }

    public void setRemove(String remove) {
        this.remove = remove;
    }

}
