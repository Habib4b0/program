package com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.dataselectionedit.GtnWsForecastHierarchyInsertRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "singleton")
public class GtnWsDataselectionHierarchyUpdateService {

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public GtnWsDataselectionHierarchyUpdateService() {
		super();
	}

	public void checkAndInsertHierarchy(GtnWsForecastHierarchyInsertRequest gtnWshirarchyInsertRequest)
			throws GtnFrameworkGeneralException {
		int customerRelaitonVersionNo = gtnWshirarchyInsertRequest.getProjectionRelationshipCustVersionNo();
		int productRelaitonVersionNo = gtnWshirarchyInsertRequest.getProjectionRelationshipProdVersionNo();
		int customerRelaitonBuilderSid = gtnWshirarchyInsertRequest.getCustRelationShipBuilderSid();
		int productRelaitonBuilderSid = gtnWshirarchyInsertRequest.getProdRelationShipBuilderSid();
		int customerHierarchySid = gtnWshirarchyInsertRequest.getCustHierarchySid();
		int productHiearchySid = gtnWshirarchyInsertRequest.getProdHierarchySid();
		int productHierarchyVersionNo = gtnWshirarchyInsertRequest.getProjectionHierarchyProdVersionNo();
		int customerHierarchyVersionNo = gtnWshirarchyInsertRequest.getProjectionHierarchyCustVersionNo();
		int projectionMasterSid = gtnWshirarchyInsertRequest.getProjectionMasterSid();
		if (checkVersionNoAndHierarchyChanges(customerRelaitonVersionNo, customerRelaitonBuilderSid,
				projectionMasterSid, customerHierarchySid, customerHierarchyVersionNo, "checkCustRelationIsUPdateed")) {
			updateProjectionWithLatestRelation(projectionMasterSid, customerRelaitonBuilderSid,
					"RelationCustHierarchy");
		}
		if (checkVersionNoAndHierarchyChanges(productRelaitonVersionNo, productRelaitonBuilderSid, projectionMasterSid,
				productHiearchySid, productHierarchyVersionNo, "checkProdRelationIsUPdateed")) {
			updateProjectionWithLatestRelation(projectionMasterSid, productRelaitonBuilderSid, 
					"RelationProdHierarchy");
		}

	}

	private void updateProjectionWithLatestRelation(int projectionMasterSid, int customerRelaitonBuilderSid,
		 String queryName) throws GtnFrameworkGeneralException {
		List<Integer> input = new ArrayList<>();
		input.add(customerRelaitonBuilderSid);
		input.add(projectionMasterSid);
		input.add(projectionMasterSid);
		input.add(customerRelaitonBuilderSid);
		input.add(projectionMasterSid);
		input.add(projectionMasterSid);
		String query = gtnWsSqlService.getQuery(queryName);
		GtnFrameworkDataType[] inputType = new GtnFrameworkDataType[input.size()];
		Arrays.fill(inputType, GtnFrameworkDataType.INTEGER);
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(query, input.toArray(), inputType);
	}

	private boolean checkVersionNoAndHierarchyChanges(int customerRelaitonVersionNo, int customerRelaitonBuilderSid,
			int projectionMasterSid, int customerHierarchySid, int customerHierarchyVersionNo, String queryName)
			throws GtnFrameworkGeneralException {
		List<Object> input = new ArrayList<>();
		input.add(customerRelaitonBuilderSid);
		input.add(customerRelaitonVersionNo);
		input.add(projectionMasterSid);
		input.add(customerHierarchySid);
		input.add(customerHierarchyVersionNo);
		input.add(customerRelaitonBuilderSid);
		String query = gtnWsSqlService.getQuery(queryName);
		@SuppressWarnings("unchecked")
		List<Object> results = (List<Object>) gtnSqlQueryEngine.executeSelectQuery(query, input);
		return results.isEmpty() ? Boolean.FALSE : Integer.parseInt(results.get(0).toString()) == 1;
	}

}
