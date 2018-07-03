/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils.xmlparser;

import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Sql {

    private List<SqlEntity> sqlEntity;

    public Sql() {
        super();
    }

    public List<SqlEntity> getSqlEntity() {
        return sqlEntity == null ? sqlEntity : Collections.unmodifiableList(sqlEntity);
    }

    @XmlElement(name ="entity")
    public void setSqlEntity(List<SqlEntity> sqlEntity) {
        this.sqlEntity = sqlEntity == null ? sqlEntity : Collections.unmodifiableList(sqlEntity);
    }

}
