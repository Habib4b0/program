package com.stpl.gtn.gtn2o.ws.report.engine.calculation;

import com.stpl.gtn.gtn20.ws.report.engine.mongo.service.GtnWsMongoService;
import com.stpl.gtn.gtn2o.ws.report.engine.bean.GtnWsCalculationType;
import com.stpl.gtn.gtn2o.ws.report.engine.bean.GtnWsVariableCategoryBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsAttributeBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsTreeNodeAttributeBean;
import java.util.ArrayList;
import java.util.List;

public class GtnWsMongoCalculation {

	private final GtnWsReportEngineBean engineBean;

	private static final GtnWsMongoService MONGO_SERVICE = GtnWsMongoService.getInstance();

	boolean index = false;

	public GtnWsMongoCalculation(GtnWsReportEngineBean engineBean) {
		this.engineBean = engineBean;
	}

	public void nodeData() {
		List<String> collection = engineBean.getComparisonTableName();
		GtnWsReportEngineTreeNode reportEngineTreeNode = engineBean.getInput();
		for (String collectionName : collection) {
			nodeDataRecursion(reportEngineTreeNode, collectionName, null);
		}
	}

	private void nodeDataRecursion(GtnWsReportEngineTreeNode ccpNode, String collection,
			GtnWsTreeNodeAttributeBean rootNodeAtrributeBean) {
		if (ccpNode.getLevelNumber() == 0 || rootNodeAtrributeBean == null) {
			rootNodeAtrributeBean = MONGO_SERVICE.topLevelAggregationSelectClause(ccpNode, collection);
		}
		for (GtnWsReportEngineTreeNode gtnWsTreeNode : ccpNode.getChildren()) {
			if (gtnWsTreeNode.getChildren() != null) {
				MONGO_SERVICE.nodeAggregationSelectClause(gtnWsTreeNode, collection, rootNodeAtrributeBean);
				nodeDataRecursion(gtnWsTreeNode, collection, rootNodeAtrributeBean);
			} else {
				MONGO_SERVICE.nodeAggregationSelectClause(gtnWsTreeNode, collection, rootNodeAtrributeBean);
			}
		}
	}

	public void displayNodeValues(GtnWsReportEngineTreeNode ccpNode) {
		for (GtnWsReportEngineTreeNode gtnWsTreeNode : ccpNode.getChildren()) {
			System.out.println(gtnWsTreeNode.toString());
			if (gtnWsTreeNode.getNodeData() != null) {
				GtnWsTreeNodeAttributeBean finalNode = (GtnWsTreeNodeAttributeBean) gtnWsTreeNode.getNodeData();
				for (int i = 0; i < finalNode.getAttributeBeanList().size(); i++) {
					GtnWsAttributeBean doc = finalNode.getAttributeBeanList().get(i);
					System.out.println("document for current " + i + " = " + doc.getAttributeMap());
				}
			}
			if (gtnWsTreeNode.getChildren() != null) {
				displayNodeValues(gtnWsTreeNode);
			}
		}
	}

	public GtnWsReportEngineTreeNode variableCategoryCalculation() {
		variableCategoryCalculationRecursion(engineBean.getInput());
		return engineBean.getInput();
	}

	private void variableCategoryCalculationRecursion(final GtnWsReportEngineTreeNode ccpNode) {
		for (GtnWsReportEngineTreeNode gtnWsTreeNode : ccpNode.getChildren()) {
			if (gtnWsTreeNode.getNodeData() != null) {
				GtnWsVariableCategoryBean categoryBean = new GtnWsVariableCategoryBean();
				categoryBean.setComparisonBasis(engineBean.getComparisonBasis());
				List<GtnWsTreeNodeAttributeBean> finalNode = (List<GtnWsTreeNodeAttributeBean>) gtnWsTreeNode
						.getNodeData();
				GtnWsTreeNodeAttributeBean currentAttributes = finalNode.get(engineBean.getSelectedProjectionId());
				GtnWsTreeNodeAttributeBean calculatedAttributes = new GtnWsTreeNodeAttributeBean();
				List<GtnWsAttributeBean> newAttributes = new ArrayList<>();
				callVariableCategoryLogic(currentAttributes, newAttributes, finalNode, categoryBean, gtnWsTreeNode);
				calculatedAttributes.addAllAttributeBeanToList(newAttributes);
				gtnWsTreeNode.setNodeData(calculatedAttributes);
			}
			if (gtnWsTreeNode.getChildren() != null) {
				variableCategoryCalculationRecursion(gtnWsTreeNode);
			}
		}
	}

        private void callVariableCategoryLogic(GtnWsTreeNodeAttributeBean currentAttributes,
                 List<GtnWsAttributeBean> newAttributesList, List<GtnWsTreeNodeAttributeBean> finalNode,
                 GtnWsVariableCategoryBean categoryBean, GtnWsReportEngineTreeNode gtnWsTreeNode) {
             for (int i = 0; i < currentAttributes.getAttributeBeanList().size(); i++) {
                 GtnWsAttributeBean currectProjection = currentAttributes.getAttributeBeanList().get(i);
                 GtnWsAttributeBean newAttributes = new GtnWsAttributeBean();
                 newAttributes.putAttributes("hierarchyNo", gtnWsTreeNode.getHierarchyNo());
                 newAttributes.putAttributes("parentHierarchyNo", gtnWsTreeNode.getParent().getHierarchyNo());
		 newAttributes.putAttributes("RsId", gtnWsTreeNode.getRsIds());
		 newAttributes.putAttributes("ccpId", gtnWsTreeNode.getCcpIds());
                 newAttributes.putAttributes("levelNumber", gtnWsTreeNode.getLevelNumber());
                 newAttributes.putAttributes("levelValue", gtnWsTreeNode.getLevelValue());
                 categoryBean.setCurrentNodeAttribute(currectProjection);
                 newAttributes.putAttributes("_id", currectProjection.getAttributes("_id"));
                 for (int j = 0; j < finalNode.size(); j++) {
                     if (!"Latest Projection".equals(categoryBean.getComparisonBasis())) {
                         GtnWsTreeNodeAttributeBean priorAttributes = finalNode.get(j);
                         categoryBean.setProjectionId(j);
                         for (GtnWsAttributeBean priorProjection : priorAttributes.getAttributeBeanList()) {
                             categoryBean.setPriorNodeAttribute(priorProjection);
                             categoryBean.setVariableCategory("Value");
                             categoryBean.setCalculationType(GtnWsCalculationType.VALUE);
                             calculateVariableCategory(currectProjection, priorProjection, categoryBean, newAttributes);
                             categoryBean.setVariableCategory("Variance");
                             categoryBean.setCalculationType(GtnWsCalculationType.VARIANCE);
                             calculateVariableCategory(currectProjection, priorProjection, categoryBean, newAttributes);
                             categoryBean.setVariableCategory("PerChange");
                             categoryBean.setCalculationType(GtnWsCalculationType.PER_CHANGE);
                             calculateVariableCategory(currectProjection, priorProjection, categoryBean, newAttributes);
                         }
                     }
                 }
                 newAttributesList.add(newAttributes);
             }
         }

	private void calculateVariableCategory(GtnWsAttributeBean currentProjection, GtnWsAttributeBean priorProjection,
			GtnWsVariableCategoryBean categoryBean, GtnWsAttributeBean newDoc) {
		int currentPeriod = currentProjection.getIntegerAttribute("semiAnnual");
		int currentyear = currentProjection.getIntegerAttribute("year");
		int priorPeriod = priorProjection.getIntegerAttribute("semiAnnual");
		int prioryear = priorProjection.getIntegerAttribute("year");
		if (currentPeriod == priorPeriod && currentyear == prioryear) {
			newDoc.putAttributes("semiAnnual", priorPeriod);
			newDoc.putAttributes("year", prioryear);
			categoryBean.setCalculatedNodeAttribute(newDoc);
			GtnWsCalculationInterface calculationType = categoryBean.getCalculationType().getCalculation();
			calculationType.initiateCalculation(categoryBean);
		}
	}

}
