package com.stpl.gtn.gtn20.ws.report.engine.mongo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoCommandException;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.stpl.gtn.gtn20.ws.report.engine.mongo.constants.MongoConstants;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsAttributeBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsTreeNodeAttributeBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.service.GtnWsCommonCalculationService;

import net.sourceforge.jeval.EvaluationException;

@Service
@Scope(value = "singleton")
public class GtnWsMongoService {

	@Autowired
	GtnWsMongoDBConnectionService mongoDBInstance;

	@Autowired
	GtnWsCommonCalculationService gtnWsCommonCalculation;

	public void createCollection(String collectionName) {
		try {
			mongoDBInstance.getDBInstance().createCollection(collectionName);
		} catch (MongoCommandException ex) {
			ex.printStackTrace();
			System.out.println(" Collection Already exists ");
		}
	}

	public MongoCollection<Document> getCollection(String collectionName) {
		return mongoDBInstance.getDBInstance().getCollection(collectionName);
	}

	public MongoCollection<?> getCollectionForCustomClass(String collectionName, Class<?> clazz) {
		return mongoDBInstance.getDBInstance().getCollection(collectionName, clazz);
	}

	public void insertManyRecordsToMongoDbUsingCustomClass(MongoCollection collection, List<?> dataList) {
		collection.insertMany(dataList);
	}

	public void dropCollections(List<String> collectionList) {
		for (String collectionName : collectionList) {
			mongoDBInstance.getDBInstance().getCollection(collectionName).drop();
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

	public void writeTreeToMongo(String collectionName, GtnWsReportEngineTreeNode output) {
		try {
			@SuppressWarnings("unchecked")
			MongoCollection<GtnWsReportEngineTreeNode> collection = (MongoCollection<GtnWsReportEngineTreeNode>) getCollectionForCustomClass(
					collectionName, GtnWsReportEngineTreeNode.class);
			collection.insertOne(output);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void insertIntoMongoCollection(MongoCollection<Document> collection, GtnWsReportEngineTreeNode output) {
		for (GtnWsReportEngineTreeNode gtnWsTreeNode : output.getChildren()) {
			GtnWsTreeNodeAttributeBean nodeData = (GtnWsTreeNodeAttributeBean) gtnWsTreeNode.getNodeData();
				List<Document> documentList = convertGtnWsTreeNodeAttributeToDocument(nodeData);
				if (documentList != null) {
					collection.insertMany(documentList);
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
		AggregateIterable<Document> itr = getCollection(collectionName).aggregate(conditions).batchSize(1000);
		MongoCursor<Document> cr = itr.iterator();
		GtnWsAttributeBean attributeBean = new GtnWsAttributeBean();
		while (cr.hasNext()) {
			Document doc = cr.next();
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
		GtnWsAttributeBean attributeBean = null;
		while (cursor.hasNext()) {
			attributeBean = new GtnWsAttributeBean();
			Document doc = (Document) cursor.next();
			Document frequencyDocument = (Document) doc.remove("_id");
			if (frequencyDocument != null) {
				attributeBean.putAttributes("_id",
						ccpNode.getHierarchyNo() + frequencyDocument.get("semiAnnual") + frequencyDocument.get("year"));
				attributeBean.putAllAttributes(frequencyDocument);
			}
			attributeBean.putAllAttributes(doc);
			treeNodeAtrributeBean.addAttributeBeanToList(attributeBean);
		}
		List<GtnWsTreeNodeAttributeBean> nodeData = new ArrayList<>();
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
		if (rootNodeAtrributeBean != null) {
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
									gtnWsCommonCalculation.getDividedValue(
											currentDoc.getAttributes("contractSalesActual"),
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

	}

	public Document dividedResult(Object numerator, Object denominator) {
		List<Object> condition = new ArrayList<>();
		condition.add(new Document("$gt", Arrays.asList(denominator, 0)));
		condition.add(new Document("$multiply",
				Arrays.asList(new Document("$divide", Arrays.asList(numerator, denominator)), 100)));
		condition.add(0.0);
		return new Document("$cond", condition);
	}

	private List<Document> convertGtnWsTreeNodeAttributeToDocument(GtnWsTreeNodeAttributeBean nodeData) {
            if(nodeData!=null){
                List<GtnWsAttributeBean> attributeBeanList=nodeData.getAttributeBeanList();
		List<Document> documentList = null;
		for (GtnWsAttributeBean gtnWsAttributeBean : attributeBeanList) {
			if (documentList == null) {
				documentList = new ArrayList<>();
			}
			documentList.add(new Document(gtnWsAttributeBean.getAttributeMap()));
		}
		return documentList;
            }
            return null;
	}

	// GtnWsReportEngineTreeNode
	public Object getTreeFromMongo(String collectionName, Class<?> className, String input[], Object values[]) {
		BasicDBObject whereQuery = new BasicDBObject();
		if (input != null && values != null && values.length == input.length) {
			for (int i = 0; i < input.length; i++) {
				System.out.println("i =input " + input[i]);
				System.out.println("i =values " + values[i]);
				whereQuery.put(input[i], values[i]);
			}
		}
		@SuppressWarnings("unchecked")
		FindIterable<GtnWsReportEngineTreeNode> itr = (FindIterable<GtnWsReportEngineTreeNode>) getCollectionForCustomClass(
				collectionName, className).find(whereQuery);
		MongoCursor<GtnWsReportEngineTreeNode> cursor = itr.iterator();
		GtnWsReportEngineTreeNode treeNode = null;
		while (cursor.hasNext()) {
			treeNode = cursor.next();
		}
		return treeNode;
	}

	public void kafkaToMongoData(String collectionName) {
		Document group1Criteria = new Document("_id",
				new Document("ccp", "$CCP_DETAILS_SID").append("pr", "$PERIOD_SID"));
		group1Criteria.put("values",
				new Document("$push",
						new Document("rsId", "$RS_CONTRACT_SID").append("discountActuals", "$ACTUAL_DISCOUNT")
								.append("discountProjection", "$PROJECTION_DISCOUNT")
								.append("accural", "$ACCURAL_DISCOUNT")));
		group1Criteria.put("salesActuals", new Document("$max", "$ACTUAL_SALES"));
		group1Criteria.put("salesProjection", new Document("$max", "$PROJECTION_SALES"));

		group1Criteria.put("periodSid", new Document("$max", "$PERIOD_SID"));
		group1Criteria.put("ccpId", new Document("$max", "$CCP_DETAILS_SID"));
		group1Criteria.put("salesUnitsActuals", new Document("$max", "$ACTUAL_UNITS"));
		group1Criteria.put("salesUnitsProjection", new Document("$max", "$PROJECTION_UNITS"));

		group1Criteria.put("year", new Document("$max", "$YEAR"));
		group1Criteria.put("quarter", new Document("$max", "$QUARTER"));
		group1Criteria.put("semiAnnual", new Document("$max", "$SEMI_ANNUAL"));

		group1Criteria.put("exfactoryActuals", new Document("$max", "$EXFACTORY_ACTUAL_SALES"));
		group1Criteria.put("exfactoryProjection", new Document("$max", "$EXFACTORY_PROJECTION_SALES"));

		group1Criteria.put("MONTH", new Document("$max", "$MONTH"));

		Document group1 = new Document("$group", group1Criteria);

		Document group2Criteria = new Document("_id", "$ccpId");
		group2Criteria.put("projectionDetailsValues", new Document("$push", new Document("periodSid", "$periodSid")
				.append("quarter", "$quarter").append("semiAnnual", "$semiAnnual").append("year", "$year")
				.append("salesActuals", "$salesActuals").append("salesProjection", "$salesProjection")
				.append("salesUnitsActuals", "$salesUnitsActuals")
				.append("salesUnitsProjection", "$salesUnitsProjection").append("exfactoryActuals", "$exfactoryActuals")
				.append("exfactoryProjection", "$exfactoryProjection").append("discountBean", "$values")));

		Document group2 = new Document("$group", group2Criteria);

		Document projections = new Document("$project",
				new Document("ccpId", "$_id").append("projectionDetailsValues", 1).append("_id", 0));

		List conditions = new ArrayList<>();
		conditions.add(group1);
		conditions.add(group2);
		conditions.add(projections);

		AggregateIterable itr = getCollection(collectionName).aggregate(conditions).batchSize(5000);

		MongoCursor cr = itr.iterator();
		while (cr.hasNext()) {
			Document doc = (Document) cr.next();
			getCollection(MongoConstants.KAFKA_COLLECTION_ID).insertOne(doc);
		}
	}

	public void createUserBasedCcpCollection(List ccpIdList, String uniqueId) {
		Document matchCondition = new Document("ccpId", new Document("$in", ccpIdList));
		FindIterable filteredResult = getCollection(MongoConstants.KAFKA_COLLECTION_ID).find(matchCondition);
		MongoCursor cr = filteredResult.iterator();
		while (cr.hasNext()) {
			Document doc = (Document) cr.next();
			getCollection(MongoConstants.USER_BASED_CCP_COLLECTION + uniqueId).insertOne(doc);
		}
	}

	public FindIterable<Document> fetchDataFromMongo(String collectionName, Object input[], Object values[]) {
		try {
			BasicDBObject whereQuery = new BasicDBObject();
			if (input != null && values != null && values.length == input.length) {
				for (int i = 0; i < input.length; i++) {
					System.out.println("fetchDataFromMongo =input " + input[i]);
					System.out.println("fetchDataFromMongo=values " + values[i]);
					if (!values[i].toString().equals(".*")) {
						whereQuery.put(input[i].toString(), values[i]);
					}
				}
			}
			@SuppressWarnings("unchecked")
			FindIterable<Document> itr = getCollection(collectionName).find(whereQuery);

			return itr;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

}
