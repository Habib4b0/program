
package com.stpl.gtn.gtn2o.ws.bean.sql;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sql")
public class GtnWsSqlRootElement {

	public GtnWsSqlRootElement() {
		super();
	}

	private List<GtnWsSqlEntityElement> sqlEntity;

	public List<GtnWsSqlEntityElement> getSqlEntity() {
		return sqlEntity != null ? new ArrayList<>(sqlEntity) : sqlEntity;
	}

	@XmlElement(name = "entity")
	public void setSqlEntity(List<GtnWsSqlEntityElement> sqlEntity) {
		this.sqlEntity = sqlEntity != null ? new ArrayList<>(sqlEntity) : sqlEntity;
	}

}
