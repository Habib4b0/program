package com.stpl.gtn.gtn20.ws.report.engine.mongo.service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.stpl.gtn.gtn20.ws.report.engine.mongo.bean.GtnWsMongodbConnectionBean;

public class GtnWsMongoDBConnectionService {

    private static MongoDatabase dataBase = null;

    private GtnWsMongoDBConnectionService() {
        super();
    }

    static {
        GtnWsMongodbConnectionBean connectionBean = new GtnWsMongodbConnectionBean();
        connectionBean.setHost("10.4.75.117");
        connectionBean.setPortNo("27017");
        connectionBean.setDatabase("GTN_MONGO_APP");
        getDataBaseInstance(connectionBean);
    }

    private static MongoDatabase getDataBaseInstance(GtnWsMongodbConnectionBean connectionBean) {
        if (dataBase == null) {
            MongoClientURI connectionString = new MongoClientURI("mongodb://" + connectionBean.getHost() + ":" + connectionBean.getPortNo());
            MongoClient mongoClient = new MongoClient(connectionString);
            dataBase = mongoClient.getDatabase(connectionBean.getDatabase());
        }
        return dataBase;
    }

    public static MongoDatabase getDBInstance() {
        return dataBase;
    }

}
