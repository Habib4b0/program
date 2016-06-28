/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author maheshj
 */
public class IdDescriptionDTO {

    Integer id = new Integer(0);
    String description = StringUtils.EMPTY;

    public IdDescriptionDTO(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
