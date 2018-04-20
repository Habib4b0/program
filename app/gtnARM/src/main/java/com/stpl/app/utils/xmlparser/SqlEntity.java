/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils.xmlparser;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import org.apache.commons.lang.StringUtils;

public class SqlEntity {

    private String sqlQuery = StringUtils.EMPTY;

    private String sqlID = StringUtils.EMPTY;

    public String getSqlQuery() {
        return sqlQuery;
    }

    @XmlElement(name="query")
    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public String getSqlID() {
        return sqlID;
    }

    @XmlAttribute(name = "id")
    public void setSqlID(String sqlID) {
        this.sqlID = sqlID;
    }

}
