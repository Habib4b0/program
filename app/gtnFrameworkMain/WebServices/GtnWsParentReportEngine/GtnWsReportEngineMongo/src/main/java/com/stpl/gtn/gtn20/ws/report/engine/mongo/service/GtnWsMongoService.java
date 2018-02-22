package com.stpl.gtn.gtn20.ws.report.engine.mongo.service;

import com.mongodb.MongoCommandException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.service.GtnWsCommonCalculation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.sourceforge.jeval.EvaluationException;
import org.bson.Document;

public class GtnWsMongoService {

    private static GtnWsMongoService MONGO_SERVICE = null;
    private static final MongoDatabase MONGODB_INSTANCE = GtnWsMongoDBConnection.getDBInstance();
    private final GtnWsCommonCalculation gtnWsCommonCalculation = GtnWsCommonCalculation.getInstance();

    private GtnWsMongoService() {
        super();
    }

    public static GtnWsMongoService getInstance() {
        if (MONGO_SERVICE == null) {
            MONGO_SERVICE = new GtnWsMongoService();
        }
        return MONGO_SERVICE;
    }

    public void createCollection(String collectionName) {
        try {
            MONGODB_INSTANCE.createCollection(collectionName);
        } catch (MongoCommandException ex) {
            ex.printStackTrace();
            System.out.println(" Collection Already exists ");
        }
    }

    public MongoCollection<Document> getCollection(String collectionName) {
        return MONGODB_INSTANCE.getCollection(collectionName);
    }

    public synchronized static void dropCollections(List<String> collectionList) {
        for (String collectionName : collectionList) {
            MONGODB_INSTANCE.getCollection(collectionName).drop();
        }
    }

    public void updateFinalResultsToMongo(String collectionName, GtnWsReportEngineTreeNode output) {
        try {
            MongoCollection collection = getCollection(collectionName);
            insertIntoMongoCollection(collection, output);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    private void insertIntoMongoCollection(MongoCollection collection, GtnWsReportEngineTreeNode output) {
        for (GtnWsReportEngineTreeNode gtnWsTreeNode : output.getChildren()) {
            List<Document> nodeData = (List<Document>) gtnWsTreeNode.getNodeData();
            if (nodeData != null && !nodeData.isEmpty()) {
                collection.insertMany(nodeData);
            }
            if (gtnWsTreeNode.getChildren() != null) {
                insertIntoMongoCollection(collection, gtnWsTreeNode);
            }
        }
    }

    public List<Document> setTopLevelAggregatedValue(GtnWsReportEngineTreeNode ccpNode, MongoCollection<Document> collection) {
        List<Document> nodeDataList = new ArrayList();

        Document matchCondition = new Document("ccpId", new Document("$in", ccpNode.getCcpIds()));
        if (ccpNode.getRsIds() != null && !ccpNode.getRsIds().isEmpty()) {
            matchCondition.append("projectionDetailsValues.discountBean.rsId", new Document("$in", ccpNode.getRsIds()));
        }
        Document match = new Document("$match", matchCondition);
        Document groupFields = new Document("_id", new Document("semiAnnual", "$projectionDetailsValues.semiAnnual").append("year", "$projectionDetailsValues.year"));
        groupFields.append("totalExfactoryActuals", new Document("$sum", "$projectionDetailsValues.exfactoryActual"));
        groupFields.append("totalExfactoryProjection", new Document("$sum", "$projectionDetailsValues.exfactoryProjection"));
        groupFields.append("totalContractSalesActual", new Document("$sum", "$projectionDetailsValues.salesActuals"));
        groupFields.append("totalContractSalesProjection", new Document("$sum", "$projectionDetailsValues.salesProjection"));

        Document selectClause = new Document("_id.semiAnnual", 1);
        selectClause.append("_id.year", 1);
        selectClause.append("totalExfactoryActuals", 1);
        selectClause.append("totalExfactoryProjection", 1);
        selectClause.append("totalContractSalesActuals", 1);
        selectClause.append("totalContractSalesProjection", 1);

        Document group = new Document("$group", groupFields);
        Document project = new Document("$project", selectClause);
        Document sort = new Document("$sort", new Document("_id.year", 1).append("_id.semiAnnual", 1));

        List conditions = new ArrayList<>();
        conditions.add(match);
        conditions.add(new Document("$unwind", "$projectionDetailsValues"));
        conditions.add(group);
        conditions.add(project);
        conditions.add(sort);
        AggregateIterable itr = collection.aggregate(conditions).batchSize(1000);
        MongoCursor cr = itr.iterator();
        while (cr.hasNext()) {
            Document doc = (Document) cr.next();
            nodeDataList.add(doc);
        }
        return nodeDataList;
    }

    public void setAggregatedValue(GtnWsReportEngineTreeNode ccpNode, MongoCollection<Document> collection, List<Document> topLevelDocument) {
        List<Document> nodeDataList = new ArrayList();
        Document matchCondition = new Document("ccpId", new Document("$in", ccpNode.getCcpIds()));
        if (ccpNode.getRsIds() != null && !ccpNode.getRsIds().isEmpty()) {
            matchCondition.append("projectionDetailsValues.discountBean.rsId", new Document("$in", ccpNode.getRsIds()));
        }
        Document match = new Document("$match", matchCondition);
        Document groupFields = new Document("_id", new Document("semiAnnual", "$projectionDetailsValues.semiAnnual").append("year", "$projectionDetailsValues.year"));
        groupFields.append("exfactoryActuals", new Document("$sum", "$projectionDetailsValues.exfactoryActuals"));
        groupFields.append("exfactoryProjection", new Document("$sum", "$projectionDetailsValues.exfactoryProjection"));
        groupFields.append("contractSalesActuals", new Document("$sum", "$projectionDetailsValues.salesActuals"));
        groupFields.append("contractSalesProjection", new Document("$sum", "$projectionDetailsValues.salesProjection"));
        groupFields.append("contractUnitsActuals", new Document("$sum", "$projectionDetailsValues.salesUnitsActuals"));
        groupFields.append("contractUnitsProjection", new Document("$sum", "$projectionDetailsValues.salesUnitsProjection"));
        groupFields.append("discountAmountActuals", new Document("$sum", "$projectionDetailsValues.discountBean.discountActuals"));
        groupFields.append("discountAmountProjection", new Document("$sum", "$projectionDetailsValues.discountBean.discountProjection"));

        Document selectClause = new Document("_id.semiAnnual", 1);
        selectClause.append("_id.year", 1);
        selectClause.append("exfactoryActuals", 1);
        selectClause.append("exfactoryProjection", 1);
        selectClause.append("contractSalesActuals", 1);
        selectClause.append("contractSalesProjection", 1);
        selectClause.append("contractUnitsActuals", 1);
        selectClause.append("contractUnitsProjection", 1);
        selectClause.append("discountAmountActuals", 1);
        selectClause.append("discountAmountProjection", 1);
        selectClause.append("discountPercentActuals", getDivideDocument("$discountAmountActuals", "$contractSalesActuals"));
        selectClause.append("discountPercentProjection", getDivideDocument("$discountAmountProjection", "$contractSalesProjection"));
        selectClause.append("rpuActuals", getDivideDocument("$discountAmountActuals", "$contractUnitsActuals"));
        selectClause.append("rpuProjection", getDivideDocument("$discountAmountProjection", "$contractUnitsProjection"));
        selectClause.append("netContractSalesActuals", new Document("$subtract", Arrays.asList("$contractSalesActuals", "$discountAmountActuals")));
        selectClause.append("netContractSalesProjection", new Document("$subtract", Arrays.asList("$contractSalesProjection", "$discountAmountProjection")));
        selectClause.append("grossContractSalesPerExFactoryActuals", getDivideDocument("$contractSalesActuals", "$exfactoryActuals"));
        selectClause.append("grossContractSalesPerExFactoryProjection", getDivideDocument("$contractSalesProjection", "$exfactoryProjection"));
        selectClause.append("deductionPerExfactoryActuals", getDivideDocument("$discountAmountActuals", "$exfactoryActuals"));
        selectClause.append("deductionPerExfactoryProjection", getDivideDocument("$discountAmountProjection", "$exfactoryProjection"));
        selectClause.append("netContractSalesPerExfactoryActuals", getDivideDocument(new Document("$subtract", Arrays.asList("$contractSalesActuals", "$discountAmountActuals")), "$exfactoryActual"));
        selectClause.append("netContractSalesPerExfactoryProjection", getDivideDocument(new Document("$subtract", Arrays.asList("$contractSalesProjection", "$discountAmountProjection")), "$exfactoryProjection"));
        selectClause.append("netExfactorySalesActuals", new Document("$subtract", Arrays.asList("$exfactoryActuals", "$discountAmountActuals")));
        selectClause.append("netExfactorySalesProjection", new Document("$subtract", Arrays.asList("$exfactoryProjection", "$discountAmountProjection")));

        Document group = new Document("$group", groupFields);
        Document project = new Document("$project", selectClause);
        Document sort = new Document("$sort", new Document("_id.year", 1).append("_id.semiAnnual", 1));

        List conditions = new ArrayList<>();
        conditions.add(match);
        conditions.add(new Document("$unwind", "$projectionDetailsValues"));
        conditions.add(new Document("$unwind", "$projectionDetailsValues.discountBean"));
        conditions.add(group);
        conditions.add(project);
        conditions.add(sort);
        AggregateIterable itr = collection.aggregate(conditions).batchSize(1000);
        MongoCursor cr = itr.iterator();
        while (cr.hasNext()) {
            Document doc = (Document) cr.next();
            nodeDataList.add(doc);
        }
        List<List<Document>> finalNodes = new ArrayList<>();
        finalNodes.add(nodeDataList);
        setTotalLevelValues(topLevelDocument, nodeDataList);
        if (ccpNode.getNodeData() == null) {
            ccpNode.setNodeData(finalNodes);
        } else {
            List<List<Document>> updateNode = (List<List<Document>>) ccpNode.getNodeData();
            updateNode.add(nodeDataList);
        }

    }

    private void setTotalLevelValues(List<Document> topLevelDocument, List<Document> currentDocument) {
        try {
            for (int i = 0; i < topLevelDocument.size(); i++) {
                Document topDocument = topLevelDocument.get(i);
                Document topYearPeriodDocument = (Document) topDocument.get("_id");
                int topPeriod = topYearPeriodDocument.getInteger("semiAnnual");
                int topyear = topYearPeriodDocument.getInteger("year");
                for (int j = 0; j < currentDocument.size(); j++) {
                    Document currentDoc = currentDocument.get(j);
                    Document currentYearPeriodDoc = (Document) currentDoc.get("_id");
                    int currentPeriod = currentYearPeriodDoc.getInteger("semiAnnual");
                    int currentyear = currentYearPeriodDoc.getInteger("year");
                    if (topPeriod == currentPeriod && topyear == currentyear) {
                        currentDoc.append("totalExfactoryActuals", topLevelDocument.get(i).get("totalExfactoryActuals"));
                        currentDoc.append("totalExfactoryProjection", topLevelDocument.get(i).get("totalExfactoryProjection"));
                        currentDoc.append("contractSalesPerTotalContractSalesActuals",
                                gtnWsCommonCalculation.getDividedValue(currentDoc.get("contractSalesActual"), topDocument.get("totalContractSalesActuals")));
                        currentDoc.append("contractSalesPerTotalContractSalesProjection",
                                gtnWsCommonCalculation.getDividedValue(currentDoc.get("contractSalesProjection"), topDocument.get("totalContractSalesProjection")));
                        currentDoc.append("netExfactorySalesPerTotalExfactoryActuals",
                                gtnWsCommonCalculation.getDividedValue(currentDoc.get("netExfactorySalesActuals"), currentDoc.get("exfactoryActuals")));
                        currentDoc.append("netExfactorySalesPerTotalExfactoryProjection",
                                gtnWsCommonCalculation.getDividedValue(currentDoc.get("netExfactorySalesProjection"), currentDoc.get("exfactoryProjection")));
                        break;
                    }
                }
            }
        } catch (EvaluationException ex) {
            ex.printStackTrace();
        }
    }

    public Document getDivideDocument(Object numerator, Object denominator) {
        List condition = new ArrayList();
        condition.add(new Document("$gt", Arrays.asList(denominator, 0)));
        condition.add(new Document("$multiply", Arrays.asList(new Document("$divide", Arrays.asList(numerator, denominator)), 100)));
        condition.add(0.0);
        Document divide = new Document("$cond", condition);
        return divide;
    }

}
