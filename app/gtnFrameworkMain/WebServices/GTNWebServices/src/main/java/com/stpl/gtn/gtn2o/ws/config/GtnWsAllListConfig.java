package com.stpl.gtn.gtn2o.ws.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.entity.companyfamilyplan.CfpModel;
import com.stpl.gtn.gtn2o.ws.entity.itemfamilyplan.IfpModel;
import com.stpl.gtn.gtn2o.ws.entity.priceshedule.PsModel;
import com.stpl.gtn.gtn2o.ws.entity.rebateplan.RebatePlanMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.AccrualDetails;
import com.stpl.gtn.gtn2o.ws.entity.transaction.AccrualMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.ActualsMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.AuditMasterInbound;
import com.stpl.gtn.gtn2o.ws.entity.transaction.AverageShelfLifeMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.CpiIndexMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.CustomerGtsActual;
import com.stpl.gtn.gtn2o.ws.entity.transaction.ForecastingMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.GlBalanceMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.GlCostCenterMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.ItemUom;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldAccrualInbound;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldActualMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldAdjustedDemandActual;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldAdjustedDemandForecast;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldAverageShelfLife;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldCfp;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldCompanyIdentifier;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldCompanyMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldCompanyParent;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldCompanyTradeClass;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldContractHeader;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldCpi;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldCustomerGtsActual;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldCustomerGtsForecast;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldDemandActual;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldDemandForecast;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldForecastSales;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldGlBalance;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldGlCostCenter;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldIfp;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldInventoryWdActualDt;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldInventoryWdActualMas;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldInventoryWdProjDt;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldInventoryWdProjMas;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldItemIdentifier;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldItemMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldItemPricing;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldItemUom;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldLotMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldPriceSchedule;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldRebatePlan;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldRebateSchedule;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldReturnRateForecast;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldReturns;
import com.stpl.gtn.gtn2o.ws.entity.transaction.IvldSalesMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.LotMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.ReturnRateForecast;
import com.stpl.gtn.gtn2o.ws.entity.transaction.ReturnsMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.SalesMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.StCffOutboundMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwAdjustDemandForecastAct;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwCffOutboundMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwCompanyIdentifier;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwCompanyMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwCompanyParentDetails;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwCompanyTradeClass;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwContractHeader;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwCustomerGtsForecast;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwDemandForecastActual;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwInventoryWdActualProjMas;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwItemIdentifier;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwItemMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwItemPricing;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwIvldActualsMaster;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwIvldAdjDemandForeActual;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwIvldDemandForecastActual;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwIvldInventoryWdActualProjMas;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwRebateSchedule;
import com.stpl.gtn.gtn2o.ws.entity.transaction.VwReturnReserve;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.config.GtnWebServiceCompanyFamilyPlanAdditionSearchConfig;
import com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.config.GtnWebServiceCompanyFamilyPlanSearchConfig;
import com.stpl.gtn.gtn2o.ws.module.companygroups.config.GtnWebServiceCompanyGroupConfig;
import com.stpl.gtn.gtn2o.ws.module.companymaster.config.GtnWsCMasterConfig;
import com.stpl.gtn.gtn2o.ws.module.companymaster.config.GtnWsCMasterFinancialCloseConfig;
import com.stpl.gtn.gtn2o.ws.module.compliancedeductionrule.config.GtnWebServiceCDRConfig;
import com.stpl.gtn.gtn2o.ws.module.compliancedeductionrule.config.GtnWebServiceFormulaConfig;
import com.stpl.gtn.gtn2o.ws.module.compliancedeductionrule.config.GtnWebServicePopUpConfig;
import com.stpl.gtn.gtn2o.ws.module.contractheader.config.GtnWsContractHeaderConfig;
import com.stpl.gtn.gtn2o.ws.module.deductioncalendar.config.GtnWebServiceDeductionCalendarConfig;
import com.stpl.gtn.gtn2o.ws.module.itemaster.config.GtnWsItemMasterConfig;
import com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.config.GtnWebServiceItemFamilyPlanConfig;
import com.stpl.gtn.gtn2o.ws.module.itemgroups.config.GtnWebServiceItemGroupConfig;
import com.stpl.gtn.gtn2o.ws.module.netsales.config.GtnWebServiceNetSalesFormulaconfig;
import com.stpl.gtn.gtn2o.ws.module.priceschedule.config.GtnWebServicePriceScheduleConfig;
import com.stpl.gtn.gtn2o.ws.module.rebateplan.config.GtnWebServiceRebatePlanConfig;
import com.stpl.gtn.gtn2o.ws.module.rebateschedule.config.GtnWebServiceRebateScheduleConfig;
import com.stpl.gtn.gtn2o.ws.module.udc.config.GtnWsUdcConfig;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.config.GtnWebServiceArmWorkflowSearchConfig;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.config.GtnWebServiceArpWorkflowSearchConfig;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.config.GtnWebServiceAttachmentWorkflowSearchConfig;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.config.GtnWebServiceForecastingWorkflowSearchConfig;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.config.GtnWebServiceHistoryWorkflowSearchConfig;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.config.GtnWebServiceReturnWorkflowSearchConfig;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.config.GtnWebServiceWFCreatedandApprovedBySearchConfig;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.config.GtnWebServiceWFPrivateViewSearchConfig;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.config.GtnWebServiceWorkflowSearchConfig;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;

public class GtnWsAllListConfig {
	public GtnWsAllListConfig() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsAllListConfig.class);

	private SessionFactory sessionFactory;
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	private Map<Integer, String> idDescMap = new HashMap<>();
	private Map<Integer, String> idDescMapForNonCombobox = new HashMap<>();
	private Map<String, GtnUIFrameworkWebserviceComboBoxResponse> comboBoxResponseMap = new HashMap<>();
	private Map<String, GtnUIFrameworkWebserviceComboBoxResponse> nonHelperComboBoxResponseMap = new HashMap<>();
	private Map<String, String> comboBoxQueryMap = new HashMap<>();
	private Map<Integer, String> userIdNameMap = new HashMap<>();
	private SessionFactory sysSessionFactory;
	private String sysSchemaName;
	private final Map<String, Object> dynamicClassObjectMap = new HashMap<>();
	private final Map<String, Class<?>> transactionDynamicClassObjectMap = new HashMap<>();
	private GtnUIFrameworkWebserviceComboBoxResponse userIdNameResponse;

	public Map<String, GtnUIFrameworkWebserviceComboBoxResponse> getNonHelperComboBoxResponseMap() {
		return nonHelperComboBoxResponseMap;
	}

	public void setNonHelperComboBoxResponseMap(
			Map<String, GtnUIFrameworkWebserviceComboBoxResponse> nonHelperComboBoxResponseMap) {
		this.nonHelperComboBoxResponseMap = nonHelperComboBoxResponseMap;
	}

	public Map<String, String> getComboBoxQueryMap() {
		return comboBoxQueryMap;
	}

	public void setComboBoxQueryMap(Map<String, String> comboBoxQueryMap) {
		this.comboBoxQueryMap = comboBoxQueryMap;
	}

	public Map<String, GtnUIFrameworkWebserviceComboBoxResponse> getComboBoxResponseMap() {
		return comboBoxResponseMap;
	}

	public void setComboBoxResponseMap(Map<String, GtnUIFrameworkWebserviceComboBoxResponse> comboBoxResponseMap) {
		this.comboBoxResponseMap = comboBoxResponseMap;
	}

	public Map<Integer, String> getIdDescMap() {
		return idDescMap;
	}

	public void setIdDescMap(Map<Integer, String> idDescMap) {
		this.idDescMap = idDescMap;
	}

	public Map<String, Object> getDynamicClassObjectMap() {
		return dynamicClassObjectMap;
	}

	public void addTransctionDynamicClassObject(String key, Class<?> value) {
		transactionDynamicClassObjectMap.put(key, value);
	}

	public Class<?> getTransctionDynamicClass(String key) {
		return transactionDynamicClassObjectMap.get(key);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	public void setSysSessionFactory(SessionFactory sysSessionFactory) {
		this.sysSessionFactory = sysSessionFactory;

	}

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
		this.loadAllMaps();
	}

	public void loadAllMaps() {

		this.loadAllComboBoxListFromHelperTable();
		this.loadcomboBoxTypeMap();
		loadGLCompany();
		loadDynamicClassObjects();
		loadTransctionDynamicClassObjects();
		loadUserIdNameMap();
	}

	@SuppressWarnings("unchecked")
	public void loadAllComboBoxListFromHelperTable() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(
					"SELECT LIST_NAME,HELPER_TABLE_SID,DESCRIPTION  FROM HELPER_TABLE ORDER BY LIST_NAME ASC,DESCRIPTION ASC;");
			List<Object[]> resultList = null;

			resultList = session.createSQLQuery(sqlQuery.toString()).list();

			for (int i = 0; i < resultList.size(); i++) {

				Object[] currentRow = resultList.get(i);

				if (comboBoxResponseMap.get(currentRow[0].toString()) == null) {
					GtnUIFrameworkWebserviceComboBoxResponse response = new GtnUIFrameworkWebserviceComboBoxResponse();
					response.setKey(currentRow[0].toString());
					comboBoxResponseMap.put(response.getKey(), response);
				}

				String itemCode = currentRow[1].toString();
				String itemValue = currentRow[2].toString();
				comboBoxResponseMap.get(currentRow[0].toString()).addItemCodeList(itemCode);
				comboBoxResponseMap.get(currentRow[0].toString()).addItemValueList(itemValue);

				idDescMap.put(Integer.valueOf(itemCode), itemValue);

			}
		} catch (Exception e) {
			logger.error("Exception in loadAllComboBoxList()----" + e.getMessage());

		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	private void loadcomboBoxTypeMap() {
            
		comboBoxQueryMap.put(GtnFrameworkForecastConstantCommon.ACCRUAL_MASTER_SALES_MASTER_ID,
				"select distinct sales_master_id as code ,sales_master_id as description from ACCRUAL_MASTER order by sales_master_id Asc");
		comboBoxQueryMap.put(GtnFrameworkForecastConstantCommon.ACCRUAL_MASTER_CATEGORY_ID,
				"select distinct category_id as code1, category_id as description1 from ACCRUAL_MASTER order by category_id Asc");
		comboBoxQueryMap.put(GtnFrameworkForecastConstantCommon.ACCRUAL_MASTER_DOC_TYPE,
				"select distinct document_type as code2, document_type as description2 from ACCRUAL_MASTER order by document_type Asc");

		comboBoxQueryMap.put(GtnFrameworkForecastConstantCommon.COMPANY_IDENTIFIER,
				"select company_Qualifier_Sid,company_Qualifier_Name from dbo.Company_Qualifier WHERE INBOUND_STATUS <> 'D' order by company_Qualifier_Name Asc");
		comboBoxQueryMap.put(GtnFrameworkForecastConstantCommon.COMPANY_MASTER_GLCOMP,
				"select cm.company_master_sid,cm.company_no +' - '+ cm.company_name as company_name from company_master cm join helper_table ht on ht.helper_table_sid = cm.company_type where  ht.list_name like 'COMPANY_TYPE' and ht.description like 'GLCOMP' and cm.inbound_status <> 'D' order by company_no");
		comboBoxQueryMap.put(GtnFrameworkForecastConstantCommon.BUSINESS_UNIT_GLCOMP,
				"select cm.company_master_sid,cm.company_no+' - '+cm.company_name as company_name from company_master cm join helper_table ht on ht.helper_table_sid = cm.company_type where  ht.list_name like 'COMPANY_TYPE' and ht.description like 'BUSINESS UNIT' and cm.inbound_status <> 'D'  order by company_no");
		comboBoxQueryMap.put(GtnFrameworkForecastConstantCommon.PRODUCT_RELATIONSHIP,
				"select rb.relationship_builder_sid,rb.relationship_name from dbo.relationship_builder  rb where hierarchy_definition_sid = ?");
		comboBoxQueryMap.put(GtnFrameworkForecastConstantCommon.PRODUCT_FORCAST_LEVEL,
				"select distinct rld.level_no,'Level '+rld.level_no+' - '+rld.level_name from relationship_level_definition rld, relationship_builder rb, hierarchy_level_definition hld where rld.relationship_builder_sid in (select rbt.relationship_builder_sid from relationship_builder rbt where rbt.hierarchy_definition_sid = ?) and rld.hierarchy_level_definition_sid = hld.hierarchy_level_definition_sid order by rld.level_no asc");
		comboBoxQueryMap.put(GtnFrameworkForecastConstantCommon.PRODUCT_INNER_LEVEL,
				"select distinct rld.level_no,'Level '+rld.level_no+' - '+rld.level_name from relationship_level_definition rld, relationship_builder rb, hierarchy_level_definition hld where rld.relationship_builder_sid in (select rbt.relationship_builder_sid from relationship_builder rbt where rbt.hierarchy_definition_sid = ?) and rld.level_no <=? and rld.hierarchy_level_definition_sid = hld.hierarchy_level_definition_sid order by rld.level_no asc");
		comboBoxQueryMap.put(GtnFrameworkForecastConstantCommon.TIME_PERIOD_FROM_DATE,
				GtnFrameworkForecastConstantCommon.LOAD_QUERY_FROM_XML);
		comboBoxQueryMap.put(GtnFrameworkForecastConstantCommon.TIME_PERIOD_TO_DATE,
				GtnFrameworkForecastConstantCommon.LOAD_QUERY_FROM_XML);
		comboBoxQueryMap.put("ItemPricingQualifier",
				"SELECT ITEM_PRICING_QUALIFIER_SID,ITEM_PRICING_QUALIFIER_NAME FROM ITEM_PRICING_QUALIFIER WHERE ITEM_PRICING_QUALIFIER_NAME IS NOT NULL AND ITEM_PRICING_QUALIFIER_NAME <> ''");
		comboBoxQueryMap.put("Brands",
				"SELECT DISTINCT BM.BRAND_MASTER_SID,BM.BRAND_NAME FROM  BRAND_MASTER BM WHERE BM.INBOUND_STATUS <> 'D'");
		comboBoxQueryMap.put("NDC8",
				"SELECT TOP 100 CONVERT( INT ,ROW_NUMBER() OVER (ORDER BY NDC8)) AS ROWNUMBER,NDC8 FROM (SELECT DISTINCT NDC8 FROM ITEM_MASTER ) A  ");
		comboBoxQueryMap.put("NDC9",
				"SELECT TOP 100 CONVERT( INT ,ROW_NUMBER() OVER (ORDER BY NDC9)) AS ROWNUMBER,NDC9 FROM (SELECT DISTINCT NDC9 FROM ITEM_MASTER ) A ");
		comboBoxQueryMap.put("ItemQualifier",
				"SELECT TOP 100 ITEM_QUALIFIER_SID,ITEM_QUALIFIER_NAME FROM DBO.ITEM_QUALIFIER WHERE ITEM_QUALIFIER_NAME IS NOT NULL AND ITEM_QUALIFIER_NAME <>'' ORDER BY CREATED_DATE DESC ,ITEM_QUALIFIER_NAME DESC  ");
		comboBoxQueryMap.put("CompanyManufacture",
				"SELECT COMPANY_MASTER_SID,COMPANY_ID FROM Company_master cm JOIN DBO.HELPER_TABLE HT ON HT.HELPER_TABLE_SID=cm.COMPANY_TYPE WHERE ht.LIST_NAME='COMPANY_TYPE' AND ht.DESCRIPTION= 'Manufacturer'");
		comboBoxQueryMap.put("CompanyOrganizationKey",
				"SELECT CM.COMPANY_MASTER_SID, CM.COMPANY_NO+' - '+CM.COMPANY_ID FROM COMPANY_MASTER CM JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = CM.COMPANY_TYPE WHERE HT.LIST_NAME LIKE 'COMPANY_TYPE' AND HT.DESCRIPTION LIKE 'BUSINESS UNIT'");
		comboBoxQueryMap.put("hierarchyName",
				"select  HIERARCHY_DEFINITION_SID,HIERARCHY_NAME from dbo.HIERARCHY_DEFINITION WHERE HIERARCHY_NAME NOT Like 'Deduction%'");
		comboBoxQueryMap.put("forecastConfigFrequency",
				"select	HELPER_TABLE_SID,DESCRIPTION from HELPER_TABLE where LIST_NAME like 'FORECAST_FREQUENCY' AND DESCRIPTION NOT like '%LY%' order by HELPER_TABLE_SID");
		comboBoxQueryMap.put("pricingCodeQualifierName",
				"select DISTINCT ITEM_PRICING_QUALIFIER_SID, IQ.ITEM_PRICING_QUALIFIER_NAME from ITEM_PRICING_QUALIFIER IQ WHERE IQ.ITEM_PRICING_QUALIFIER_NAME IS NOT NULL");
		comboBoxQueryMap.put("identifierCodeQualifierName",
				"select DISTINCT ITEM_QUALIFIER_SID,ITEM_QUALIFIER_VALUE from ITEM_QUALIFIER WHERE ITEM_QUALIFIER_VALUE IS NOT NULL");
		comboBoxQueryMap.put("companyCodeQualifierName",
				"select DISTINCT COMPANY_QUALIFIER_SID,COMPANY_QUALIFIER_NAME from COMPANY_QUALIFIER WHERE COMPANY_QUALIFIER_VALUE IS NOT NULL");
		comboBoxQueryMap.put("companyTradeClass",
				"     select DISTINCT PT.COMPANY_TRADE_CLASS ,HT.DESCRIPTION     from COMPANY_TRADE_CLASS PT      Join HELPER_TABLE HT on HT.HELPER_TABLE_SID=PT.COMPANY_TRADE_CLASS     where PT.COMPANY_TRADE_CLASS IS NOT NULL AND PT.COMPANY_TRADE_CLASS <> 0");
		comboBoxQueryMap.put("priorTradeClass",
				"     select DISTINCT PT.PRIOR_TRADE_CLASS ,HT.DESCRIPTION     from COMPANY_TRADE_CLASS PT      Join HELPER_TABLE HT on HT.HELPER_TABLE_SID=PT.PRIOR_TRADE_CLASS     where PT.PRIOR_TRADE_CLASS IS NOT NULL AND PT.PRIOR_TRADE_CLASS <> 0");
		comboBoxQueryMap.put("InventoryOrganizationKey",
				"    select cm.COMPANY_MASTER_SID, cm.company_name from COMPANY_master cm ");
		comboBoxQueryMap.put("BusinessUnit",
				"    SELECT  Distinct CM.COMPANY_MASTER_SID,CM.COMPANY_NAME\n"
						+ "                                            FROM   COMPANY_MASTER CM \n"
						+ "                                                JOIN HELPER_TABLE HT ON CM.COMPANY_TYPE = HT.HELPER_TABLE_SID\n"
						+ "                        where  HT.DESCRIPTION = 'GLCOMP'  AND CM.INBOUND_STATUS <> 'D' \n"
						+ "                                         ORDER BY CM.COMPANY_NAME ");
		comboBoxQueryMap.put("processName",
				"    SELECT WP.PROCESS_SID, WP.PROCESS_DISPLAY_NAME FROM WORKFLOW_PROFILE WP ");
		comboBoxQueryMap.put("Module",
				"select HELPER_TABLE_SID,DESCRIPTION from HELPER_TABLE where LIST_NAME like 'PERIODCONFIG_MODULES'  order by HELPER_TABLE_SID");

		comboBoxQueryMap.put("BUSINESS_PROCESS",
				"SELECT ARM_ADJUSTMENT_CONFIG_SID,TRANSACTION_NAME FROM ARM_ADJUSTMENT_CONFIG");

		comboBoxQueryMap.put("company",
				"select COMPANY_MASTER_SID,COMPANY_NO+' - '+COMPANY_NAME as company "
						+ "from COMPANY_MASTER CM JOIN  HELPER_TABLE HT ON HT.HELPER_TABLE_SID=CM.COMPANY_TYPE "
						+ "where HT.LIST_NAME='COMPANY_TYPE' AND HT.DESCRIPTION='GLCOMP'");

		comboBoxQueryMap.put("Business_Unit",
				"select COMPANY_MASTER_SID,COMPANY_NO+' - '+COMPANY_NAME as company "
						+ "from COMPANY_MASTER CM JOIN  HELPER_TABLE HT ON HT.HELPER_TABLE_SID=CM.COMPANY_TYPE "
						+ "where HT.LIST_NAME='COMPANY_TYPE' AND HT.DESCRIPTION='GLCOMP'");

		comboBoxQueryMap.put("Mode",
				"select HELPER_TABLE_SID,DESCRIPTION from HELPER_TABLE where LIST_NAME like 'PERIODCONFIG_MODE'  order by HELPER_TABLE_SID");

		comboBoxQueryMap.put("Frequency",
				"select HELPER_TABLE_SID,DESCRIPTION from HELPER_TABLE where LIST_NAME like 'PERIODCONFIG_FREQUENCY'  order by HELPER_TABLE_SID");
		comboBoxQueryMap.put("adjustmentTypeDdlb",
				"SELECT ADJ.ARM_ADJUSTMENT_CONFIG_SID,HT.DESCRIPTION,ADJ.TRANSACTION_NAME FROM ARM_ADJUSTMENT_CONFIG ADJ JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = ADJ.METHODOLGY");

		comboBoxQueryMap.put("deductionLevelLoad",
				"select HELPER_TABLE_SID,DESCRIPTION from HELPER_TABLE where LIST_NAME like 'BUSINESS_PROCESS_NAR'  order by HELPER_TABLE_SID");

		comboBoxQueryMap.put("companyARMddlb",
				"SELECT DISTINCT COMPANY_MASTER_SID,COMPANY_ID,COMPANY_NAME FROM COMPANY_MASTER JOIN HELPER_TABLE ON COMPANY_TYPE = HELPER_TABLE_SID "
						+ "WHERE LIST_NAME = 'COMPANY_TYPE' AND DESCRIPTION = 'GLCOMP'");

		comboBoxQueryMap.put("businessUnitARMDdlb",
				"SELECT DISTINCT COMPANY_MASTER_SID,COMPANY_ID,COMPANY_NAME FROM COMPANY_MASTER JOIN HELPER_TABLE ON COMPANY_TYPE = HELPER_TABLE_SID WHERE LIST_NAME = 'COMPANY_TYPE' AND DESCRIPTION = 'BUSINESS UNIT'");
		comboBoxQueryMap.put("PM_CALENDER",
				"select SLA_CALENDAR_MASTER_SID,CALENDAR_NAME from dbo.SLA_CALENDAR_MASTER");
		comboBoxQueryMap.put("deductionvalueARMddlb",
				"SELECT DISTINCT RS_CONTRACT_SID,RS_ID FROM   RS_CONTRACT A JOIN   HELPER_TABLE H1 ON H1.HELPER_TABLE_SID = A.RS_CATEGORY JOIN   HELPER_TABLE H2 ON H2.HELPER_TABLE_SID = A.RS_TYPE"
						+ " JOIN   HELPER_TABLE H3 ON H3.HELPER_TABLE_SID = A.REBATE_PROGRAM_TYPE JOIN   UDCS U ON U.MASTER_SID=A.RS_CONTRACT_SID AND U.MASTER_TYPE='RS_CONTRACT' "
						+ " JOIN   HELPER_TABLE H5 ON H5.HELPER_TABLE_SID = U.UDC2  JOIN   HELPER_TABLE H6 ON H6.HELPER_TABLE_SID = U.UDC3 JOIN   HELPER_TABLE H7 ON H7.HELPER_TABLE_SID = U.UDC4 "
						+ " JOIN   HELPER_TABLE H8 ON H8.HELPER_TABLE_SID = U.UDC5 JOIN   HELPER_TABLE H9 ON H9.HELPER_TABLE_SID = U.UDC6");

		comboBoxQueryMap.put("Business_Month_Load",
				"SELECT HELPER_TABLE_SID,DESCRIPTION FROM HELPER_TABLE WHERE LIST_NAME LIKE 'BUSINESS_MONTH'  ORDER BY HELPER_TABLE_SID ASC");

		comboBoxQueryMap.put("OrganizationKey",
				"SELECT DISTINCT CM.COMPANY_MASTER_SID,CM.COMPANY_ID FROM ITEM_MASTER IM JOIN COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID=IM.ORGANIZATION_KEY");
		comboBoxQueryMap.put("BusinessRole",
				"SELECT BUSINESSROLE_MASTER_SID, BUSINESSROLE_NAME FROM dbo.BUSINESSROLE_MASTER");
		comboBoxQueryMap.put("moduleName",
				"SELECT distinct MODULE_NAME as code, MODULE_NAME as description FROM dbo.MODULE_SUBMODULE_MASTER");
		comboBoxQueryMap.put("subModuleName",
				"SELECT MODULE_SUBMODULE_SID, SUBMODULE_NAME FROM dbo.MODULE_SUBMODULE_MASTER where MODULE_NAME=?");
		comboBoxQueryMap.put("Ndc9ItemId",
				"SELECT DISTINCT IM.ITEM_MASTER_SID,IM.ITEM_ID FROM ITEM_MASTER IM join HELPER_TABLE HT ON IM.ITEM_TYPE=HT.HELPER_TABLE_SID WHERE LIST_NAME ='ITEM_TYPE' AND DESCRIPTION='NDC-9'");
	}

	public void loadGLCompany() {
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("SELECT ht.DESCRIPTION,cm.COMPANY_MASTER_SID,\n" + "	COMPANY_NAME\n" + "FROM\n"
				+ "	COMPANY_MASTER cm\n" + "JOIN HELPER_TABLE ht ON\n" + "	ht.HELPER_TABLE_SID = cm.COMPANY_TYPE\n"
				+ "WHERE\n" + "	ht.DESCRIPTION = 'GLcomp' OR ht.DESCRIPTION='Business Unit';");
		List<Object[]> resultList;
		try {
			resultList = executeAndGetData(sqlQuery);

			for (int i = 0; resultList != null && i < resultList.size(); i++) {

				Object[] currentRow = resultList.get(i);

				if (nonHelperComboBoxResponseMap.get(currentRow[0].toString()) == null) {
					GtnUIFrameworkWebserviceComboBoxResponse response = new GtnUIFrameworkWebserviceComboBoxResponse();
					response.setKey(currentRow[0].toString());
					nonHelperComboBoxResponseMap.put(response.getKey(), response);
				}

				String itemCode = currentRow[1].toString();
				String itemValue = currentRow[2].toString();
				nonHelperComboBoxResponseMap.get(currentRow[0].toString()).addItemCodeList(itemCode);
				nonHelperComboBoxResponseMap.get(currentRow[0].toString()).addItemValueList(itemValue);

				idDescMapForNonCombobox.put(Integer.valueOf(itemCode), itemValue);

			}
		} catch (GtnFrameworkGeneralException e) {
			logger.error("Exception in loadGLCompany()----" + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private List<Object[]> executeAndGetData(StringBuilder sqlQuery) throws GtnFrameworkGeneralException {
		return (List<Object[]>) gtnSqlQueryEngine.executeSelectQuery(sqlQuery.toString());

	}

	private void loadUserIdNameMap() {

		StringBuilder sqlQuery = new StringBuilder();
		Session session = null;
		Connection connection = null;
		try {
			session = sysSessionFactory.openSession();

			connection = sysSessionFactory.getSessionFactoryOptions().getServiceRegistry()
					.getService(ConnectionProvider.class).getConnection();
			sqlQuery.append("select userId,ISNULL(firstName, '') +' '+ISNULL(middleName, '')+' '+ISNULL(lastName, '') from "
					+ connection.getCatalog() + ".dbo.User_");
			List<Object[]> resultList = null;

			resultList = gtnSqlQueryEngine.executeSelectQuery(sqlQuery.toString(), session);
			GtnUIFrameworkWebserviceComboBoxResponse ddlbresponse = new GtnUIFrameworkWebserviceComboBoxResponse();
			ddlbresponse.setComboBoxList(resultList);
			comboBoxResponseMap.put("USERS", ddlbresponse);

			userIdNameResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
			userIdNameResponse.setKey(GtnFrameworkWebserviceConstant.USER_ID_NAME);

			for (int i = 0; i < resultList.size(); i++) {

				Object[] currentRow = resultList.get(i);

				String itemCode = String.valueOf(currentRow[0]);
				String itemValue = String.valueOf(currentRow[1]);
				userIdNameResponse.addItemCodeList(itemCode);
				userIdNameResponse.addItemValueList(!itemCode.equals("0") ? itemValue : "");

				userIdNameMap.put(Integer.valueOf(itemCode), itemValue);

			}
		} catch (Exception e) {
			logger.error("Exception in loadGLCompany()----" + e.getMessage());

		} finally {
			if (session != null) {
				session.close();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}
	}

	public String getSysSchemaName() throws GtnFrameworkGeneralException {
		if (sysSchemaName == null || sysSchemaName.isEmpty())
			try (Connection connection = getSysSessionFactory().getSessionFactoryOptions().getServiceRegistry()
					.getService(ConnectionProvider.class).getConnection()) {
				sysSchemaName = connection.getCatalog();
			} catch (Exception ex) {
				logger.error("Exception in getSysSchemaCatalog", ex);
				throw new GtnFrameworkGeneralException("Exception in getSysSchemaCatalog", ex);
			}
		return sysSchemaName;
	}

	public GtnUIFrameworkWebserviceComboBoxResponse getUserIdNameResponse() {
		return userIdNameResponse;
	}

	public void setUserIdNameResponseMap(GtnUIFrameworkWebserviceComboBoxResponse userIdNameResponse) {
		this.userIdNameResponse = userIdNameResponse;
	}

	public Map<Integer, String> getUserIdNameMap() {
		return userIdNameMap;
	}

	public void setUserIdNameMap(Map<Integer, String> userIdNameMap) {
		this.userIdNameMap = userIdNameMap;
	}

	public SessionFactory getSysSessionFactory() {
		return sysSessionFactory;
	}

	private void loadDynamicClassObjects() {
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.ARM_WORKFLOW_SEARCH.getClassName(),
				new GtnWebServiceArmWorkflowSearchConfig());

		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.COMPANY_MASTER.getClassName(),
				new GtnWsCMasterConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.COMPANY_FAMILY_PLAN_SEARCH.getClassName(),
				new GtnWebServiceCompanyFamilyPlanSearchConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.COMPANY_FAMILY_PLAN_ADDITION_SEARCH.getClassName(),
				new GtnWebServiceCompanyFamilyPlanAdditionSearchConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.ITEM_FAMILY_PLAN.getClassName(),
				new GtnWebServiceItemFamilyPlanConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.COMPANY_GROUPS.getClassName(),
				new GtnWebServiceCompanyGroupConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.ITEM_GROUPS.getClassName(),
				new GtnWebServiceItemGroupConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.CDR.getClassName(), new GtnWebServiceCDRConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.PRICE_SCHEDULE.getClassName(),
				new GtnWebServicePriceScheduleConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.FORECAST_FORMULA.getClassName(),
				new GtnWebServiceFormulaConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.REBATE_SCHEDULE.getClassName(),
				new GtnWebServiceRebateScheduleConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.POPUP_CONFIG.getClassName(),
				new GtnWebServicePopUpConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.DEDUCTION_CALENDAR.getClassName(),
				new GtnWebServiceDeductionCalendarConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.NET_SALES.getClassName(),
				new GtnWebServiceNetSalesFormulaconfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.REBATE_PLAN.getClassName(),
				new GtnWebServiceRebatePlanConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.ITEM_MASTER.getClassName(),
				new GtnWsItemMasterConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.CONTRACT_HEADER.getClassName(),
				new GtnWsContractHeaderConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.COMPANY_FINANCIAL_HEADER.getClassName(),
				new GtnWsCMasterFinancialCloseConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.CONTRACT_WORKFLOW_SEARCH.getClassName(),
				new GtnWebServiceWorkflowSearchConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.FORECASTING_WORKFLOW_SEARCH.getClassName(),
				new GtnWebServiceForecastingWorkflowSearchConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.ARP_WORKFLOW_SEARCH.getClassName(),
				new GtnWebServiceArpWorkflowSearchConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.RETURNS_WORKFLOW_SEARCH.getClassName(),
				new GtnWebServiceReturnWorkflowSearchConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.WF_PRIVATE_VIEW_SEARCH.getClassName(),
				new GtnWebServiceWFPrivateViewSearchConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.WF_CREATED_BY_SEARCH.getClassName(),
				new GtnWebServiceWFCreatedandApprovedBySearchConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.WF_HISTORYBUTTON_SEARCH.getClassName(),
				new GtnWebServiceHistoryWorkflowSearchConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.WF_ATTACHMENT_SEARCH.getClassName(),
				new GtnWebServiceAttachmentWorkflowSearchConfig());
		dynamicClassObjectMap.put(GtnWsSearchQueryConfigLoaderType.UDC_CONFIGURATION.getClassName(),
				new GtnWsUdcConfig());

	}

	private void loadTransctionDynamicClassObjects() {
		transactionDynamicClassObjectMap.put(AccrualDetails.class.getName(), AccrualDetails.class);
		transactionDynamicClassObjectMap.put(AccrualMaster.class.getName(), AccrualMaster.class);
		transactionDynamicClassObjectMap.put(ActualsMaster.class.getName(), ActualsMaster.class);
		transactionDynamicClassObjectMap.put(AuditMasterInbound.class.getName(), AuditMasterInbound.class);
		transactionDynamicClassObjectMap.put(AverageShelfLifeMaster.class.getName(), AverageShelfLifeMaster.class);
		transactionDynamicClassObjectMap.put(CfpModel.class.getName(), CfpModel.class);
		transactionDynamicClassObjectMap.put(CpiIndexMaster.class.getName(), CpiIndexMaster.class);
		transactionDynamicClassObjectMap.put(CustomerGtsActual.class.getName(), CustomerGtsActual.class);
		transactionDynamicClassObjectMap.put(ForecastingMaster.class.getName(), ForecastingMaster.class);
		transactionDynamicClassObjectMap.put(GlBalanceMaster.class.getName(), GlBalanceMaster.class);
		transactionDynamicClassObjectMap.put(GlCostCenterMaster.class.getName(), GlCostCenterMaster.class);
		transactionDynamicClassObjectMap.put(IfpModel.class.getName(), IfpModel.class);
		transactionDynamicClassObjectMap.put(IvldAccrualInbound.class.getName(), IvldAccrualInbound.class);
		transactionDynamicClassObjectMap.put(IvldActualMaster.class.getName(), IvldActualMaster.class);
		transactionDynamicClassObjectMap.put(IvldAdjustedDemandActual.class.getName(), IvldAdjustedDemandActual.class);
		transactionDynamicClassObjectMap.put(IvldAdjustedDemandForecast.class.getName(),
				IvldAdjustedDemandForecast.class);
		transactionDynamicClassObjectMap.put(IvldAverageShelfLife.class.getName(), IvldAverageShelfLife.class);
		transactionDynamicClassObjectMap.put(IvldCfp.class.getName(), IvldCfp.class);
		transactionDynamicClassObjectMap.put(IvldCompanyIdentifier.class.getName(), IvldCompanyIdentifier.class);
		transactionDynamicClassObjectMap.put(IvldCompanyMaster.class.getName(), IvldCompanyMaster.class);
		transactionDynamicClassObjectMap.put(IvldCompanyParent.class.getName(), IvldCompanyParent.class);
		transactionDynamicClassObjectMap.put(IvldCompanyTradeClass.class.getName(), IvldCompanyTradeClass.class);
		transactionDynamicClassObjectMap.put(IvldContractHeader.class.getName(), IvldContractHeader.class);
		transactionDynamicClassObjectMap.put(IvldCpi.class.getName(), IvldCpi.class);
		transactionDynamicClassObjectMap.put(IvldCustomerGtsActual.class.getName(), IvldCustomerGtsActual.class);
		transactionDynamicClassObjectMap.put(IvldCustomerGtsForecast.class.getName(), IvldCustomerGtsForecast.class);
		transactionDynamicClassObjectMap.put(IvldDemandActual.class.getName(), IvldDemandActual.class);
		transactionDynamicClassObjectMap.put(IvldDemandForecast.class.getName(), IvldDemandForecast.class);
		transactionDynamicClassObjectMap.put(IvldForecastSales.class.getName(), IvldForecastSales.class);
		transactionDynamicClassObjectMap.put(IvldGlBalance.class.getName(), IvldGlBalance.class);
		transactionDynamicClassObjectMap.put(IvldGlCostCenter.class.getName(), IvldGlCostCenter.class);
		transactionDynamicClassObjectMap.put(IvldIfp.class.getName(), IvldIfp.class);
		transactionDynamicClassObjectMap.put(IvldInventoryWdActualDt.class.getName(), IvldInventoryWdActualDt.class);
		transactionDynamicClassObjectMap.put(IvldInventoryWdActualMas.class.getName(), IvldInventoryWdActualMas.class);
		transactionDynamicClassObjectMap.put(IvldInventoryWdProjDt.class.getName(), IvldInventoryWdProjDt.class);
		transactionDynamicClassObjectMap.put(IvldInventoryWdProjMas.class.getName(), IvldInventoryWdProjMas.class);
		transactionDynamicClassObjectMap.put(IvldItemIdentifier.class.getName(), IvldItemIdentifier.class);
		transactionDynamicClassObjectMap.put(IvldItemMaster.class.getName(), IvldItemMaster.class);
		transactionDynamicClassObjectMap.put(IvldItemPricing.class.getName(), IvldItemPricing.class);
		transactionDynamicClassObjectMap.put(IvldLotMaster.class.getName(), IvldLotMaster.class);
		transactionDynamicClassObjectMap.put(IvldPriceSchedule.class.getName(), IvldPriceSchedule.class);
		transactionDynamicClassObjectMap.put(IvldRebatePlan.class.getName(), IvldRebatePlan.class);
		transactionDynamicClassObjectMap.put(IvldRebateSchedule.class.getName(), IvldRebateSchedule.class);
		transactionDynamicClassObjectMap.put(IvldReturns.class.getName(), IvldReturns.class);
		transactionDynamicClassObjectMap.put(IvldSalesMaster.class.getName(), IvldSalesMaster.class);
		transactionDynamicClassObjectMap.put(LotMaster.class.getName(), LotMaster.class);
		transactionDynamicClassObjectMap.put(PsModel.class.getName(), PsModel.class);
		transactionDynamicClassObjectMap.put(RebatePlanMaster.class.getName(), RebatePlanMaster.class);
		transactionDynamicClassObjectMap.put(ReturnsMaster.class.getName(), ReturnsMaster.class);
		transactionDynamicClassObjectMap.put(SalesMaster.class.getName(), SalesMaster.class);
		transactionDynamicClassObjectMap.put(VwAdjustDemandForecastAct.class.getName(),
				VwAdjustDemandForecastAct.class);
		transactionDynamicClassObjectMap.put(VwCompanyIdentifier.class.getName(), VwCompanyIdentifier.class);
		transactionDynamicClassObjectMap.put(VwCompanyMaster.class.getName(), VwCompanyMaster.class);
		transactionDynamicClassObjectMap.put(VwCompanyParentDetails.class.getName(), VwCompanyParentDetails.class);
		transactionDynamicClassObjectMap.put(VwCompanyTradeClass.class.getName(), VwCompanyTradeClass.class);
		transactionDynamicClassObjectMap.put(VwContractHeader.class.getName(), VwContractHeader.class);
		transactionDynamicClassObjectMap.put(VwCustomerGtsForecast.class.getName(), VwCustomerGtsForecast.class);
		transactionDynamicClassObjectMap.put(VwDemandForecastActual.class.getName(), VwDemandForecastActual.class);
		transactionDynamicClassObjectMap.put(VwInventoryWdActualProjMas.class.getName(),
				VwInventoryWdActualProjMas.class);
		transactionDynamicClassObjectMap.put(VwItemIdentifier.class.getName(), VwItemIdentifier.class);
		transactionDynamicClassObjectMap.put(VwItemMaster.class.getName(), VwItemMaster.class);
		transactionDynamicClassObjectMap.put(VwItemPricing.class.getName(), VwItemPricing.class);
		transactionDynamicClassObjectMap.put(VwIvldActualsMaster.class.getName(), VwIvldActualsMaster.class);
		transactionDynamicClassObjectMap.put(VwIvldAdjDemandForeActual.class.getName(),
				VwIvldAdjDemandForeActual.class);
		transactionDynamicClassObjectMap.put(VwIvldDemandForecastActual.class.getName(),
				VwIvldDemandForecastActual.class);
		transactionDynamicClassObjectMap.put(VwIvldInventoryWdActualProjMas.class.getName(),
				VwIvldInventoryWdActualProjMas.class);
		transactionDynamicClassObjectMap.put(VwRebateSchedule.class.getName(), VwRebateSchedule.class);
		transactionDynamicClassObjectMap.put(VwReturnReserve.class.getName(), VwReturnReserve.class);
		transactionDynamicClassObjectMap.put(ReturnRateForecast.class.getName(), ReturnRateForecast.class);
		transactionDynamicClassObjectMap.put(IvldReturnRateForecast.class.getName(), IvldReturnRateForecast.class);
		transactionDynamicClassObjectMap.put(ItemUom.class.getName(), ItemUom.class);
		transactionDynamicClassObjectMap.put(IvldItemUom.class.getName(), IvldItemUom.class);
		transactionDynamicClassObjectMap.put(VwCffOutboundMaster.class.getName(), VwCffOutboundMaster.class);
		transactionDynamicClassObjectMap.put(StCffOutboundMaster.class.getName(), StCffOutboundMaster.class);

	}

}
