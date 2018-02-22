package com.stpl.gtn.gtn20.ws.report.engine.mongo.test;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.in;

import com.stpl.gtn.gtn20.ws.report.engine.mongo.service.GtnWsMongoDBConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import static com.mongodb.client.model.Filters.in;

public class SampleMongoTest {

    public static void main(String[] args) throws InterruptedException {
        SampleMongoTest test = new SampleMongoTest();
        MongoDatabase instance = GtnWsMongoDBConnection.getDBInstance();
        System.out.println(instance.getName());
//        instance.createCollection("sample");
        MongoCollection<Document> ins = instance.getCollection("test1");
//        test.insert(ins);
//        test.searchAll(ins);
//        test.delete(ins);
        test.aggregation(ins);
    }

    public void insert(MongoCollection<Document> instance) {
        Document add = new Document("ccpid", 1);
        add.append("projectiondetails", new Document("periodSid", 601)
                .append("exfactoryactuals", 10000).append("exfactoryprojection", 20000));
        instance.insertOne(add);
        add = new Document("ccpid", 2);
        add.append("projectiondetails", new Document("periodSid", 601)
                .append("exfactoryactuals", 10000).append("exfactoryprojection", 20000));
        instance.insertOne(add);
    }

    public void searchAll(MongoCollection<Document> instance) {
        FindIterable itr = instance.find();
        MongoCursor cr = itr.iterator();
        while (cr.hasNext()) {
            Document doc = (Document) cr.next();
            System.out.println(doc.toJson());
        }
    }

    public void delete(MongoCollection<Document> instance) {
//        instance.deleteMany(eq("ccpid",1));
        List list = new ArrayList();
        list.add(2);
        instance.deleteMany(in("ccpid", list));
    }

    public void aggregation(MongoCollection<Document> instance) {

        Document groupFields = new Document();
        groupFields.put("_id", new Document("Semi Annual", "$projectionDetailsValues.semiAnnual").append("Year", "$projectionDetailsValues.year"));
        groupFields.put("contractSalesActuals", new Document("$sum", "$projectionDetailsValues.contractSalesActuals"));
        Document group = new Document("$group", groupFields);
        Document project = new Document("$project", new Document("_id", 1).append("Total", new Document("$add", "$contractSalesActuals,$contractSalesProjection")));
        Document sort = new Document("$sort", new Document("_id.Year", 1).append("_id.Semi Annual", 1));
        List conditions = new ArrayList<>();
        conditions.add(Aggregates.match(Filters.in("ccpId", Arrays.asList(10516, 10515))));
        conditions.add(Aggregates.unwind("$projectionDetailsValues"));
        conditions.add(group);
        conditions.add(project);
        conditions.add(sort);
        AggregateIterable itr = instance.aggregate(conditions).batchSize(1000);
        MongoCursor cr = itr.iterator();
        while (cr.hasNext()) {
            Document doc = (Document) cr.next();
            System.out.println("Year = " + doc.get("_id"));
            System.out.println(doc.toJson());
        }
    }
}
