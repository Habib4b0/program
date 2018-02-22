package com.stpl.gtn.gtn2o.ws.report.engine.calculation;

import com.mongodb.client.MongoCollection;
import com.stpl.gtn.gtn20.ws.report.engine.mongo.service.GtnWsMongoService;
import com.stpl.gtn.gtn2o.ws.report.engine.bean.GtnWsVariableCategoryBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class GtnWsMongoCalculation {

    private static final GtnWsMongoService MONGO_SERVICE = GtnWsMongoService.getInstance();
    boolean index = false;

    public MongoCollection<Document> getCollection(String collectionName) {
        return MONGO_SERVICE.getCollection(collectionName);
    }

    public void setNodeData(GtnWsReportEngineTreeNode ccpNode, GtnWsReportEngineBean bean) {
        List<String> collection = bean.getCollection();
        for (String collectionName : collection) {
            MongoCollection<Document> mongoCollection = getCollection(collectionName);
            setRowValue(ccpNode, mongoCollection, null);
        }
    }

    private void setRowValue(GtnWsReportEngineTreeNode ccpNode, MongoCollection<Document> collection, List<Document> topLevelDocument) {
        if (ccpNode.getLevelNumber() == 0 || topLevelDocument == null) {
            topLevelDocument = MONGO_SERVICE.setTopLevelAggregatedValue(ccpNode, collection);
        }
        for (GtnWsReportEngineTreeNode gtnWsTreeNode : ccpNode.getChildren()) {
            if (gtnWsTreeNode.getChildren() != null) {
                MONGO_SERVICE.setAggregatedValue(gtnWsTreeNode, collection, topLevelDocument);
                setRowValue(gtnWsTreeNode, collection, topLevelDocument);
            } else {
                MONGO_SERVICE.setAggregatedValue(gtnWsTreeNode, collection, topLevelDocument);
            }
        }
    }

    public void displayNodeValues(GtnWsReportEngineTreeNode ccpNode) {
        for (GtnWsReportEngineTreeNode gtnWsTreeNode : ccpNode.getChildren()) {
            System.out.println(gtnWsTreeNode.toString());
            if (gtnWsTreeNode.getNodeData() != null) {
                List<List<Document>> finalNode = (List<List<Document>>) gtnWsTreeNode.getNodeData();
                for (int i = 0; i < finalNode.size(); i++) {
                    List<Document> doc = (List<Document>) finalNode.get(i);
                    for (Document document : doc) {
                        System.out.println("document for current " + i + " = " + document.toJson());
                    }
                }
            }
            if (gtnWsTreeNode.getChildren() != null) {
                displayNodeValues(gtnWsTreeNode);
            }
        }
    }

    public void setVariableCategory(final GtnWsReportEngineTreeNode ccpNode, GtnWsReportEngineBean engineBean) {
        for (GtnWsReportEngineTreeNode gtnWsTreeNode : ccpNode.getChildren()) {
            if (gtnWsTreeNode.getNodeData() != null) {
                GtnWsVariableCategoryBean categoryBean = new GtnWsVariableCategoryBean();
                categoryBean.setComparisonBasis(engineBean.getComparisonBasis());
                List<List<Document>> finalNode = (List<List<Document>>) gtnWsTreeNode.getNodeData();
                List<Document> current = (List<Document>) finalNode.get(engineBean.getSelectedProjectionId());
                List<Document> prior = null;
                List<Document> finalDocument = new ArrayList<>();

                Document newDoc = new Document("hierarchyNo", gtnWsTreeNode.getHierarchyNo());
                newDoc.append("parentHierarchyNo", gtnWsTreeNode.getParent().getHierarchyNo());
                newDoc.append("RsId", gtnWsTreeNode.getRsIds());
                newDoc.append("ccpId", gtnWsTreeNode.getCcpIds());

                for (Document currectProjection : current) {
                    categoryBean.setCurrentDocument(currectProjection);
                    for (int i = 0; i < finalNode.size(); i++) {
                        if (!"Latest Projection".equals(categoryBean.getComparisonBasis())) {
                            prior = (List<Document>) finalNode.get(i);
                            categoryBean.setProjectionId(i);
                            for (Document priorProjection : prior) {
                                categoryBean.setPriorDocument(priorProjection);
                                categoryBean.setVariableCategory("Value");
                                categoryBean.setCalculationType(GtnWsCalculationType.VALUE);
                                calculateVariableCategory(currectProjection, priorProjection, categoryBean, newDoc);
                                categoryBean.setVariableCategory("Variance");
                                categoryBean.setCalculationType(GtnWsCalculationType.VARIANCE);
                                calculateVariableCategory(currectProjection, priorProjection, categoryBean, newDoc);
                                categoryBean.setVariableCategory("PerChange");
                                categoryBean.setCalculationType(GtnWsCalculationType.PER_CHANGE);
                                calculateVariableCategory(currectProjection, priorProjection, categoryBean, newDoc);
                            }
                        }
                    }
                }
                finalDocument.add(newDoc);
                gtnWsTreeNode.setNodeData(finalDocument);
            }
            if (gtnWsTreeNode.getChildren() != null) {
                setVariableCategory(gtnWsTreeNode, engineBean);
            }
        }
    }

    private void calculateVariableCategory(Document currentProjection, Document priorProjection,
            GtnWsVariableCategoryBean categoryBean, Document newDoc) {
        Document currentDocument = (Document) currentProjection.get("_id");
        int currentPeriod = currentDocument.getInteger("semiAnnual");
        int currentyear = currentDocument.getInteger("year");
        Document priorDocument = (Document) priorProjection.get("_id");
        int priorPeriod = priorDocument.getInteger("semiAnnual");
        int prioryear = priorDocument.getInteger("year");
        if (currentPeriod == priorPeriod && currentyear == prioryear) {
            newDoc.append("semiAnnual", priorPeriod);
            newDoc.append("year", prioryear);
            categoryBean.setCalculatedDocument(newDoc);
            GtnWsCalculation calculationType = categoryBean.getCalculationType().getCalculation();
            calculationType.initiateCalculation(categoryBean);
        }
    }

}
