package com.stpl.gtn.gtn20.ws.report.engine.mongo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoCommandException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsAttributeBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsProjectionBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsTreeNodeAttributeBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.service.GtnWsCommonCalculationService;

import net.sourceforge.jeval.EvaluationException;

public class GtnWsMongoService {

	private static GtnWsMongoService MONGO_SERVICE = null;
	private static final MongoDatabase MONGODB_INSTANCE = GtnWsMongoDBConnectionService.getDBInstance();
	private final GtnWsCommonCalculationService gtnWsCommonCalculation = GtnWsCommonCalculationService.getInstance();

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

	public MongoCollection<GtnWsProjectionBean> getCollectionForCustomClass(String collectionName) {
		return MONGODB_INSTANCE.getCollection(collectionName, GtnWsProjectionBean.class);
	}

	public void insertManyRecordsToMongoDbUsingCustomClass(String collectionName, Class<GtnWsProjectionBean> clazz, List<GtnWsProjectionBean> dataList) {
		MongoCollection<GtnWsProjectionBean> collection = getCollectionForCustomClass(collectionName);
		collection.insertMany(dataList);
	}

	public void dropCollections(List<String> collectionList) {
		for (String collectionName : collectionList) {
			MONGODB_INSTANCE.getCollection(collectionName).drop();
		}
	}

	public void updateFinalResultsToMongo(String collectionName, GtnWsReportEngineTreeNode output) {
		try {
			MongoCollection<Document> collection = getCollection(collectionName);
			insertIntoMongoCollection(collection, output);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}

	private void insertIntoMongoCollection(MongoCollection<Document> collection, GtnWsReportEngineTreeNode output) {
		for (GtnWsReportEngineTreeNode gtnWsTreeNode : output.getChildren()) {
			GtnWsTreeNodeAttributeBean nodeData = (GtnWsTreeNodeAttributeBean) gtnWsTreeNode.getNodeData();
			if (nodeData != null && !nodeData.getAttributeBeanList().isEmpty()) {
				List<Document> documentList = convertGtnWsTreeNodeAttributeToDocument(nodeData.getAttributeBeanList());
				if (documentList != null) {
					collection.insertMany(documentList);
				}
			}
			if (gtnWsTreeNode.getChildren() != null) {
				insertIntoMongoCollection(collection, gtnWsTreeNode);
			}
		}
	}

	public GtnWsTreeNodeAttributeBean topLevelAggregationSelectClause(GtnWsReportEngineTreeNode ccpNode,
			String collectionName) {
		GtnWsTreeNodeAttributeBean treeNodeAtrributeBean = new GtnWsTreeNodeAttributeBean();

		Document matchCondition = new Document("ccpId", new Document("$in", ccpNode.getCcpIds()));
		if (ccpNode.getRsIds() != null && !ccpNode.getRsIds().isEmpty()) {
			matchCondition.append("projectionDetailsValues.discountBean.rsId", new Document("$in", ccpNode.getRsIds()));
		}
		Document match = new Document("$match", matchCondition);
		Document groupFields = new Document("_id", new Document("semiAnnual", "$projectionDetailsValues.semiAnnual")
				.append("year", "$projectionDetailsValues.year"));
		groupFields.append("totalExfactoryActuals", new Document("$sum", "$projectionDetailsValues.exfactoryActual"));
		groupFields.append("totalExfactoryProjection",
				new Document("$sum", "$projectionDetailsValues.exfactoryProjection"));
		groupFields.append("totalContractSalesActual", new Document("$sum", "$projectionDetailsValues.salesActuals"));
		groupFields.append("totalContractSalesProjection",
				new Document("$sum", "$projectionDetailsValues.salesProjection"));

		Document selectClause = new Document("_id.semiAnnual", 1);
		selectClause.append("_id.year", 1);
		selectClause.append("totalExfactoryActuals", 1);
		selectClause.append("totalExfactoryProjection", 1);
		selectClause.append("totalContractSalesActuals", 1);
		selectClause.append("totalContractSalesProjection", 1);

		Document group = new Document("$group", groupFields);
		Document project = new Document("$project", selectClause);
		Document sort = new Document("$sort", new Document("_id.year", 1).append("_id.semiAnnual", 1));

		List<Document> conditions = new ArrayList<>();
		conditions.add(match);
		conditions.add(new Document("$unwind", "$projectionDetailsValues"));
		conditions.add(group);
		conditions.add(project);
		conditions.add(sort);
		AggregateIterable itr = getCollection(collectionName).aggregate(conditions).batchSize(1000);
		MongoCursor cr = itr.iterator();
		GtnWsAttributeBean attributeBean = new GtnWsAttributeBean();
		while (cr.hasNext()) {
			Document doc = (Document) cr.next();
			Document frequencyDocument = (Document) doc.remove("_id");
			if (frequencyDocument != null) {
				attributeBean.putAllAttributes(frequencyDocument);
			}
			attributeBean.putAllAttributes(doc);
		}
		treeNodeAtrributeBean.addAttributeBeanToList(attributeBean);
		return treeNodeAtrributeBean;
	}

	public void nodeAggregationSelectClause(GtnWsReportEngineTreeNode ccpNode, String collectionName,
			GtnWsTreeNodeAttributeBean rootNodeAtrributeBean) {
		GtnWsTreeNodeAttributeBean treeNodeAtrributeBean = new GtnWsTreeNodeAttributeBean();
		Document matchCondition = new Document("ccpId", new Document("$in", ccpNode.getCcpIds()));
		if (ccpNode.getRsIds() != null && !ccpNode.getRsIds().isEmpty()) {
			matchCondition.append("projectionDetailsValues.discountBean.rsId", new Document("$in", ccpNode.getRsIds()));
		}
		Document match = new Document("$match", matchCondition);
		Document groupFields = new Document("_id", new Document("semiAnnual", "$projectionDetailsValues.semiAnnual")
				.append("year", "$projectionDetailsValues.year"));
		groupFields.append("exfactoryActuals", new Document("$sum", "$projectionDetailsValues.exfactoryActuals"));
		groupFields.append("exfactoryProjection", new Document("$sum", "$projectionDetailsValues.exfactoryProjection"));
		groupFields.append("contractSalesActuals", new Document("$sum", "$projectionDetailsValues.salesActuals"));
		groupFields.append("contractSalesProjection", new Document("$sum", "$projectionDetailsValues.salesProjection"));
		groupFields.append("contractUnitsActuals", new Document("$sum", "$projectionDetailsValues.salesUnitsActuals"));
		groupFields.append("contractUnitsProjection",
				new Document("$sum", "$projectionDetailsValues.salesUnitsProjection"));
		groupFields.append("discountAmountActuals",
				new Document("$sum", "$projectionDetailsValues.discountBean.discountActuals"));
		groupFields.append("discountAmountProjection",
				new Document("$sum", "$projectionDetailsValues.discountBean.discountProjection"));

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
		selectClause.append("discountPercentActuals", dividedResult("$discountAmountActuals", "$contractSalesActuals"));
		selectClause.append("discountPercentProjection",
				dividedResult("$discountAmountProjection", "$contractSalesProjection"));
		selectClause.append("rpuActuals", dividedResult("$discountAmountActuals", "$contractUnitsActuals"));
		selectClause.append("rpuProjection", dividedResult("$discountAmountProjection", "$contractUnitsProjection"));
		selectClause.append("netContractSalesActuals",
				new Document("$subtract", Arrays.asList("$contractSalesActuals", "$discountAmountActuals")));
		selectClause.append("netContractSalesProjection",
				new Document("$subtract", Arrays.asList("$contractSalesProjection", "$discountAmountProjection")));
		selectClause.append("grossContractSalesPerExFactoryActuals",
				dividedResult("$contractSalesActuals", "$exfactoryActuals"));
		selectClause.append("grossContractSalesPerExFactoryProjection",
				dividedResult("$contractSalesProjection", "$exfactoryProjection"));
		selectClause.append("deductionPerExfactoryActuals",
				dividedResult("$discountAmountActuals", "$exfactoryActuals"));
		selectClause.append("deductionPerExfactoryProjection",
				dividedResult("$discountAmountProjection", "$exfactoryProjection"));
		selectClause.append("netContractSalesPerExfactoryActuals",
				dividedResult(
						new Document("$subtract", Arrays.asList("$contractSalesActuals", "$discountAmountActuals")),
						"$exfactoryActual"));
		selectClause.append("netContractSalesPerExfactoryProjection",
				dividedResult(
						new Document("$subtract",
								Arrays.asList("$contractSalesProjection", "$discountAmountProjection")),
						"$exfactoryProjection"));
		selectClause.append("netExfactorySalesActuals",
				new Document("$subtract", Arrays.asList("$exfactoryActuals", "$discountAmountActuals")));
		selectClause.append("netExfactorySalesProjection",
				new Document("$subtract", Arrays.asList("$exfactoryProjection", "$discountAmountProjection")));

		Document group = new Document("$group", groupFields);
		Document project = new Document("$project", selectClause);
		Document sort = new Document("$sort", new Document("_id.year", 1).append("_id.semiAnnual", 1));
	
		List<Document> conditions = new ArrayList<>();
		conditions.add(match);
		conditions.add(new Document("$unwind", "$projectionDetailsValues"));
		conditions.add(new Document("$unwind", "$projectionDetailsValues.discountBean"));
		conditions.add(group);
		conditions.add(project);
		conditions.add(sort);
		AggregateIterable<Document> itr = getCollection(collectionName).aggregate(conditions).batchSize(1000);
		MongoCursor cursor = itr.iterator();
		GtnWsAttributeBean attributeBean = new GtnWsAttributeBean();
		while (cursor.hasNext()) {
			Document doc = (Document) cursor.next();
			Document frequencyDocument = (Document) doc.remove("_id");
			if (frequencyDocument != null) {
				attributeBean.putAttributes("_id",
						ccpNode.getHierarchyNo() + frequencyDocument.get("semiAnnual") + frequencyDocument.get("year"));
				attributeBean.putAllAttributes(frequencyDocument);
			}
			attributeBean.putAllAttributes(doc);
		}
		List<GtnWsTreeNodeAttributeBean> nodeData = new ArrayList<>();
		treeNodeAtrributeBean.addAttributeBeanToList(attributeBean);
		nodeData.add(treeNodeAtrributeBean);
		totalLevelCalculation(rootNodeAtrributeBean, treeNodeAtrributeBean);
		if (ccpNode.getNodeData() == null) {
			ccpNode.setNodeData(nodeData);
		} else {
			List<GtnWsTreeNodeAttributeBean> updateNode = (List<GtnWsTreeNodeAttributeBean>) ccpNode.getNodeData();
			updateNode.add(treeNodeAtrributeBean);
		}

	}

	private void totalLevelCalculation(GtnWsTreeNodeAttributeBean rootNodeAtrributeBean,
			GtnWsTreeNodeAttributeBean currentNodeData) {
		try {
			for (int i = 0; i < rootNodeAtrributeBean.getAttributeBeanList().size(); i++) {
				GtnWsAttributeBean rootNodeDocument = rootNodeAtrributeBean.getAttributeBeanList().get(i);
				int topPeriod = rootNodeDocument.getIntegerAttribute("semiAnnual");
				int topyear = rootNodeDocument.getIntegerAttribute("year");
				for (int j = 0; j < currentNodeData.getAttributeBeanList().size(); j++) {
					GtnWsAttributeBean currentDoc = currentNodeData.getAttributeBeanList().get(j);
					int currentPeriod = currentDoc.getIntegerAttribute("semiAnnual");
					int currentyear = currentDoc.getIntegerAttribute("year");
					if (topPeriod == currentPeriod && topyear == currentyear) {
						currentDoc.putAttributes("totalExfactoryActuals",
								rootNodeDocument.getAttributes("totalExfactoryActuals"));
						currentDoc.putAttributes("totalExfactoryProjection",
								rootNodeDocument.getAttributes("totalExfactoryProjection"));
						currentDoc.putAttributes("contractSalesPerTotalContractSalesActuals",
								gtnWsCommonCalculation.getDividedValue(currentDoc.getAttributes("contractSalesActual"),
										rootNodeDocument.getAttributes("totalContractSalesActuals")));
						currentDoc.putAttributes("contractSalesPerTotalContractSalesProjection",
								gtnWsCommonCalculation.getDividedValue(
										currentDoc.getAttributes("contractSalesProjection"),
										rootNodeDocument.getAttributes("totalContractSalesProjection")));
						currentDoc.putAttributes("netExfactorySalesPerTotalExfactoryActuals",
								gtnWsCommonCalculation.getDividedValue(
										currentDoc.getAttributes("netExfactorySalesActuals"),
										currentDoc.getAttributes("exfactoryActuals")));
						currentDoc.putAttributes("netExfactorySalesPerTotalExfactoryProjection",
								gtnWsCommonCalculation.getDividedValue(
										currentDoc.getAttributes("netExfactorySalesProjection"),
										currentDoc.getAttributes("exfactoryProjection")));
						double weightedGtnActualDividedValue = gtnWsCommonCalculation.getDividedValue(
								currentDoc.getAttributes("exfactoryActuals"),
								currentDoc.getAttributes("totalExfactoryActuals"));
						currentDoc.putAttributes("weightedGtnActuals",
								gtnWsCommonCalculation.getMultipiedValue(weightedGtnActualDividedValue,
										currentDoc.getAttributes("deductionPerExfactoryActuals"), true));
						double weightedGtnProjectionDividedValue = gtnWsCommonCalculation.getDividedValue(
								currentDoc.getAttributes("exfactoryProjection"),
								currentDoc.getAttributes("totalExfactoryProjection"));
						currentDoc.putAttributes("weightedGtnProjection",
								gtnWsCommonCalculation.getMultipiedValue(weightedGtnProjectionDividedValue,
										currentDoc.getAttributes("deductionPerExfactoryProjection"), true));
						break;
					}
				}
			}
		} catch (EvaluationException ex) {
			ex.printStackTrace();
		}
	}

	public Document dividedResult(Object numerator, Object denominator) {
		List<Object> condition = new ArrayList<>();
		condition.add(new Document("$gt", Arrays.asList(denominator, 0)));
		condition.add(new Document("$multiply",
				Arrays.asList(new Document("$divide", Arrays.asList(numerator, denominator)), 100)));
		condition.add(0.0);
		return new Document("$cond", condition);
	}

	private List<Document> convertGtnWsTreeNodeAttributeToDocument(List<GtnWsAttributeBean> attributeBeanList) {
		List<Document> documentList = null;
		for (GtnWsAttributeBean gtnWsAttributeBean : attributeBeanList) {
			if (documentList == null) {
				documentList = new ArrayList<>();
			}
			documentList.add(new Document(gtnWsAttributeBean.getAttributeMap()));
		}
		return documentList;
	}

}
