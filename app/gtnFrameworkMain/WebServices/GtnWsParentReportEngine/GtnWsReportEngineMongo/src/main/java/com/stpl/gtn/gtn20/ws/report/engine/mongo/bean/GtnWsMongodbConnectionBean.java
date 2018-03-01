package com.stpl.gtn.gtn20.ws.report.engine.mongo.bean;

import org.bson.types.ObjectId;

public class GtnWsMongodbConnectionBean {

	private ObjectId id;
	private String host;
	private String portNo;
	private String database;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPortNo() {
		return portNo;
	}

	public void setPortNo(String portNo) {
		this.portNo = portNo;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public ObjectId getId() {
        return id;
    }

    public void setId(final ObjectId id) {
        this.id = id;
    }

	@Override
	public String toString() {
		return "GtnWsMongodbConnectionBean [_id=" + id + ", host=" + host + ", portNo=" + portNo + ", database="
				+ database + "]";
	}


}
