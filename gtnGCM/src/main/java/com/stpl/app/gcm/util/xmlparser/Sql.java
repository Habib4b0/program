
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.util.xmlparser;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author karthikeyans
 */
@XmlRootElement
public class Sql {

    private List<SqlEntity> sqlEntity;

    public List<SqlEntity> getSqlEntity() {
        return sqlEntity;
    }

    @XmlElement(name ="entity")
    public void setSqlEntity(List<SqlEntity> sqlEntity) {
        this.sqlEntity = sqlEntity;
    }

}

