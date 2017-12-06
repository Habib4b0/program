package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.serviceimpl.deduction;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipBuilder;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.service.GtnFrameworkWhereQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.module.relationshipbuilder.service.GtnWsRelationshipBuilderService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderConstants;
import com.stpl.gtn.gtn2o.ws.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Component("DeductionWhere")
@Scope(value = "singleton")
public class GtnFrameworkDedutionWhereServiceImpl implements GtnFrameworkWhereQueryGeneratorService {
	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnFrameworkHierarchyService hierarchyService;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;
	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnWsRelationshipBuilderService relationLogic;

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkDedutionWhereServiceImpl.class);

	public GtnFrameworkDedutionWhereServiceImpl() {
		super();
	}

	public void addWhereClause(GtnFrameworkQueryGeneratorBean querygeneratorBean,
			GtnWsRelationshipBuilderBean relationBean) throws GtnFrameworkGeneralException {
		int whereClauseSize = querygeneratorBean.getWhereClauseConfigList().size();
		querygeneratorBean.removeWhereClauseConfigListByIndex(1, whereClauseSize - 1);
		hierarchyService.getInboundRestrictionQueryForAutoUpdate(querygeneratorBean);
		try (Session session = sessionFactory.openSession()) {
			RelationshipBuilder productrelationshipBuilder = session.get(RelationshipBuilder.class,
					relationBean.getDeductionRelation());
			List<Integer> itemMastersidList = getItemMasterSidForProductRelation(productrelationshipBuilder);
			querygeneratorBean.addWhereClauseBean("RS_CONTRACT_DETAILS.ITEM_MASTER_SID", null,
					GtnFrameworkOperatorType.IN, GtnFrameworkDataType.LIST, itemMastersidList);
		} 
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getItemMasterSidForProductRelation(RelationshipBuilder productrelationshipBuilder)
			throws GtnFrameworkGeneralException {
		List<Integer> result = new ArrayList<>();
		try {
			List<HierarchyLevelDefinitionBean> hierarchyList = relationLogic.getRBHierarchyLevelDefinitionBySid(
					productrelationshipBuilder.getHierarchyDefinition().getHierarchyDefinitionSid(),
					productrelationshipBuilder.getHierarchyDefinition().getVersionNo(),
					productrelationshipBuilder.getRelationshipBuilderSid());

			int levelNo = HierarchyLevelDefinitionBean.getLastLinkedLevelNo(hierarchyList);
			HierarchyLevelDefinitionBean destinationHierarchyBean = HierarchyLevelDefinitionBean
					.getBeanByLevelNo(levelNo, hierarchyList);
			if (destinationHierarchyBean != null) {
				GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
						.getKeyRelationBeanUsingTableIdAndColumnName(destinationHierarchyBean.getTableName(),
								destinationHierarchyBean.getFieldName());
				List<String> whereClauseColumn = hierarchyService.getMappingColumns(keyBean);
				List<String> whereQueries = relationLogic.getRelationQueries(
						productrelationshipBuilder.getRelationshipBuilderSid(), hierarchyList.subList(0, levelNo),
						productrelationshipBuilder.getVersionNo());
				GtnFrameworkFileReadWriteService fileReadWriteService = new GtnFrameworkFileReadWriteService();
				GtnFrameworkHierarchyQueryBean queryBean = fileReadWriteService.getQueryFromFile(
						destinationHierarchyBean.getHierarchyDefinitionSid(),
						destinationHierarchyBean.getHierarchyLevelDefinitionSid(),
						destinationHierarchyBean.getVersionNo());
				GtnFrameworkQueryGeneratorBean finalQueryBean = queryBean.getQuery();
				finalQueryBean.removeSelectClauseByIndex(0);
				finalQueryBean.removeSelectClauseByIndex(0);
				finalQueryBean.addSelectClauseBean(GtnWsRelationshipBuilderConstants.ITEM_MASTER_TABLE_COLUMN_NAME,
						null, Boolean.TRUE, null);
				finalQueryBean.addWhereClauseBean(whereClauseColumn.get(1), null, GtnFrameworkOperatorType.IN,
						GtnFrameworkDataType.NULL_ALLOWED, "?");
				String finalQuery = finalQueryBean.generateQuery();
				result = (List<Integer>) gtnSqlQueryEngine
						.executeSelectQuery(gtnWsSqlService.getReplacedQuery(whereQueries, finalQuery));
			}
		} catch (Exception e) {
			
			logger.error(e.getMessage());
		}
		return result;

	}

}
