package com.stpl.gtn.gtn2o.ws.report.engine.calculation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn20.ws.report.engine.mongo.service.GtnWsMongoService;
import com.stpl.gtn.gtn2o.ws.report.engine.bean.GtnWsCalculationType;
import com.stpl.gtn.gtn2o.ws.report.engine.bean.GtnWsVariableCategoryBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsAttributeBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineBean;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsTreeNodeAttributeBean;

@Service
@Scope(value = "singleton")
public class GtnWsMongoCalculation {

	@Autowired
	GtnWsMongoService gtnMongoService;

	private GtnWsReportEngineBean engineBean;

	boolean index = false;

	public void nodeData(GtnWsReportEngineBean engineBean) {
		this.engineBean = engineBean;
		List<String> collection = engineBean.getComparisonTableName();
		GtnWsReportEngineTreeNode reportEngineTreeNode = engineBean.getInput();
		for (String collectionName : collection) {
			nodeDataRecursion(reportEngineTreeNode, collectionName, null);
		}
	}

	private void nodeDataRecursion(GtnWsReportEngineTreeNode ccpNode, String collection,
			GtnWsTreeNodeAttributeBean rootNodeAtrributeBean) {
		if (ccpNode.getLevelNumber() == 0 || rootNodeAtrributeBean == null) {
			rootNodeAtrributeBean = gtnMongoService.topLevelAggregationSelectClause(ccpNode, collection);
		}
		for (GtnWsReportEngineTreeNode gtnWsTreeNode : ccpNode.getChildren()) {
			if (gtnWsTreeNode.getChildren() != null) {
				gtnMongoService.nodeAggregationSelectClause(gtnWsTreeNode, collection, rootNodeAtrributeBean);
				nodeDataRecursion(gtnWsTreeNode, collection, rootNodeAtrributeBean);
			} else {
				gtnMongoService.nodeAggregationSelectClause(gtnWsTreeNode, collection, rootNodeAtrributeBean);
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
					System.out.println("document for current " + i + " = " + doc.getAttributes());
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
				callVariableCategoryLogic(currentAttributes, newAttributes, finalNode, categoryBean);
				calculatedAttributes.addAllAttributeBeanToList(newAttributes);
				gtnWsTreeNode.setNodeData(calculatedAttributes);
			}

			if (gtnWsTreeNode.getChildren() != null) {
				variableCategoryCalculationRecursion(gtnWsTreeNode);
			}
		}
	}

	private void callVariableCategoryLogic(GtnWsTreeNodeAttributeBean currentAttributes,
			List<GtnWsAttributeBean> newAttributes, List<GtnWsTreeNodeAttributeBean> finalNode,
			GtnWsVariableCategoryBean categoryBean) {
		if (currentAttributes.getAttributeBeanList() != null) {
			for (GtnWsAttributeBean currectProjection : currentAttributes.getAttributeBeanList()) {
				categoryBean.setCurrentNodeAttribute(currectProjection);
				for (int i = 0; i < finalNode.size(); i++) {
					if (!"Latest Projection".equals(categoryBean.getComparisonBasis())) {
						GtnWsTreeNodeAttributeBean priorAttributes = finalNode.get(i);
						categoryBean.setProjectionId(i);
						if (priorAttributes.getAttributeBeanList() != null) {
							for (GtnWsAttributeBean priorProjection : priorAttributes.getAttributeBeanList()) {
								GtnWsAttributeBean newDoc = new GtnWsAttributeBean();
								categoryBean.setPriorNodeAttribute(priorProjection);
								categoryBean.setVariableCategory("Value");
								categoryBean.setCalculationType(GtnWsCalculationType.VALUE);
								boolean added = calculateVariableCategory(currectProjection, priorProjection,
										categoryBean, newDoc);
								categoryBean.setVariableCategory("Variance");
								categoryBean.setCalculationType(GtnWsCalculationType.VARIANCE);
								calculateVariableCategory(currectProjection, priorProjection, categoryBean, newDoc);
								categoryBean.setVariableCategory("PerChange");
								categoryBean.setCalculationType(GtnWsCalculationType.PER_CHANGE);
								calculateVariableCategory(currectProjection, priorProjection, categoryBean, newDoc);
								if (added) {
									newAttributes.add(newDoc);
								}
							}
						}
					}
				}
			}
		}
	}

	private boolean calculateVariableCategory(GtnWsAttributeBean currentProjection, GtnWsAttributeBean priorProjection,
			GtnWsVariableCategoryBean categoryBean, GtnWsAttributeBean newAttributes) {
		int currentPeriod = ((Long) currentProjection.getAttribute("semiAnnual")).intValue();
		int currentyear = ((Long) currentProjection.getAttribute("year")).intValue();
		int priorPeriod = ((Long) priorProjection.getAttribute("semiAnnual")).intValue();
		int prioryear = ((Long) priorProjection.getAttribute("year")).intValue();
		if (currentPeriod == priorPeriod && currentyear == prioryear) {
			newAttributes.putAttributes("semiAnnual", priorPeriod);
			newAttributes.putAttributes("year", prioryear);
			categoryBean.setCalculatedNodeAttribute(newAttributes);
			GtnWsCalculationInterface calculationType = categoryBean.getCalculationType().getCalculation();
			calculationType.initiateCalculation(categoryBean);
			return true;
		}
		return false;
	}

}