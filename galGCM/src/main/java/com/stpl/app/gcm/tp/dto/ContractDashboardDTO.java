/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.dto;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Lokeshwari
 */
public class ContractDashboardDTO implements Serializable {

    private String component = StringUtils.EMPTY;
    private String id = StringUtils.EMPTY;
    private String number = StringUtils.EMPTY;
    private String name = StringUtils.EMPTY;

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
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

}
