package org.r2.etl.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.encryption.Encr;
import org.pentaho.di.core.encryption.TwoWayPasswordEncoderPluginType;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.plugins.PluginRegistry;
import org.pentaho.di.core.util.EnvUtil;
import org.r2.etl.common.util.CommonUtils;
import org.r2.etl.common.util.Constants;
import org.r2.etl.common.util.FilePathUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

/**
 * This class is the Controller Class for the Interface Execution.
 *
 * @author Stpl
 */
@Controller
public class HomeController {

	/**
	 * The variable used for logger.
	 */
	public static final Logger LOGGER = Logger.getLogger(HomeController.class);

	/**
	 * The variable used for API key definition.
	 */
	public static final String APIKEYERROR = "Authentication Failed";

	/**
	 * private variable for COMPANY MASTER.
	 */
	private static final String COMPANY_MASTER = "CompanyMaster";
	/**
	 * private variable for COMPANY HISTORY.
	 */
	private static final String COMPANY_HIS = "CompanyMasterHistory";
	/**
	 * private variable for COMPANY PARENT.
	 */
	private static final String COMPANY_PARENT = "CompanyParent";
	/**
	 * private variable for COMPANY PARENT HISTORY.
	 */
	private static final String COMPANY_PARENT_HIS = "CompanyParentHistory";
	/**
	 * private variable for COMPANY TRADE CLASS HISTORY.
	 */
	private static final String COMPANY_TRADE_HIS = "CompanyTradeClassHistory";
	/**
	 * private variable for COMPANY TRADE CLASS.
	 */
	private static final String COMPANY_TRADE = "CompanyTradeClass";
	/**
	 * private variable for COMPANY IDENTIFIER.
	 */
	private static final String COMPANY_IDEN = "CompanyIdentifier";
	/**
	 * private variable for COMPANY IDENTIFIER History.
	 */
	private static final String COMPANY_IDEN_HIS = "CompanyIdentifierHistory";
	/**
	 * private variable for ITEM MASTER.
	 */
	private static final String ITEM_MASTER = "ItemMaster";
	/**
	 * private variable for ITEM MASTER.
	 */
	private static final String ITEM_MASTER_HIS = "ItemMasterHistory";
	/**
	 * private variable for ITEM IDENTIFIER.
	 */
	private static final String ITEM_IDEN = "ItemIdentifier";
	/**
	 * private variable for ITEM IDENTIFIER.
	 */
	private static final String ITEM_IDEN_HIS = "ItemIdentifierHistory";
	/**
	 * private variable for ITEM PRICING.
	 */
	private static final String ITEM_PRICING = "ItemPricing";

	/**
	 * private variable for ITEM PRICING.
	 */
	private static final String ITEM_PRICING_HISTORY = "ItemPricingHistory";

	/**
	 * private variable for CONTARCT MASTER.
	 */
	private static final String CONTRACT_HEADER = "ContractHeader";
	/**
	 * private variable for CONTARCT HISTORY.
	 */
	private static final String CONTRACT_HIS = "ContractMasterHistory";
	/**
	 * private variable for COMPANY FAMILY PLAN.
	 */
	private static final String CFP = "CompanyFamilyPlan";
	/**
	 * private variable for COMPANY FAMILY PLAN.
	 */
	private static final String CFP_HISTORY = "CompanyFamilyPlanHistory";
	/**
	 * private variable for ITEM FAMILY PLAN.
	 */
	private static final String IFP = "ItemFamilyPlan";

	/**
	 * private variable for ITEM FAMILY PLAN.
	 */
	private static final String IFP_HISTORY = "ItemFamilyPlanHistory";
	/**
	 * private variable for PRICE SCHEDULE.
	 */
	private static final String PRICE_SCHEDULE = "PriceSchedule";
	/**
	 * private variable for PRICE SCHEDULE.
	 */
	private static final String PRICE_SCHEDULE_HISTORY = "PriceScheduleHistory";
	/**
	 * private variable for REBATE SCHEDULE.
	 */
	private static final String REBATE_SCHEDULE = "RebateSchedule";
	/**
	 * private variable for REBATE SCHEDULE.
	 */
	private static final String REBATE_SCHEDULE_HISTORY = "RebateScheduleHistory";
	/**
	 * private variable for REBATE PLAN.
	 */
	private static final String REBATE_PLAN = "RebatePlan";
	/**
	 * private variable for REBATE PLAN.
	 */
	private static final String REBATE_PLAN_HISTORY = "RebatePlanHistory";
	/**
	 * private variable for ACTUAL MASTER.
	 */
	private static final String ACTUALS = "ActualMaster";
	/**
	 * private variable for ACTUAL MASTER.
	 */
	private static final String ACTUALS_HISTORY = "ActualMasterHistory";

	/**
	 * private variable for ITEM GIERARCHY.
	 */
	private static final String ITEM_HIERARCHY = "ItemHierarchy";
	/**
	 * private variable for ITEM GIERARCHY.
	 */
	private static final String ITEM_HIERARCHY_HIS = "ItemHierarchyHistory";
	/**
	 * private variable for SALES MASTER.
	 */
	private static final String SALES_MASTER = "SalesMaster";
	/**
	 * private variable for SALES MASTER.
	 */
	private static final String SALES_MASTER_HISTORY = "SalesMasterHistory";
	/**
	 * private variable for FORECAST SALES.
	 */
	private static final String FORECAST = "ForecastingSales";
	/**
	 * private variable for FORECAST SALES.
	 */
	private static final String FORECAST_HIS = "ForecastingSalesHistory";
	/**
	 * private variable for GL BALANCE.
	 */
	private static final String GL_BALANCE = "GLBalance";
	/**
	 * private variable for GL BALANCE.
	 */
	private static final String GL_BALANCE_HISTORY = "GLBalanceHistory";
	/**
	 * private variable for CPI INDEX.
	 */
	private static final String CPI_INDEX = "CPIIndex";
	/**
	 * private variable for CPI INDEX.
	 */
	private static final String CPI_INDEX_HIS = "CPIIndexHistory";
	/**
	 * private variable for FORMULA DETAILS.
	 */
	private static final String FORMULA_DET = "FormulaDetails";
	/**
	 * private variable for FORMULA DETAILS HISTORY.
	 */
	private static final String FORMULA_DET_HIS = "FormulaDetailsHistory";
	/**
	 * private variable for MASTER DATA.
	 */
	private static final String MASTER_DATA_ATT = "MasterDataAttrb";

	/**
	 * private variable for MASTER DATA.
	 */
	private static final String MASTER_DATA_ATT_HISTORY = "MasterDataAttrbHistory";
	/**
	 * private variable for ITEM HIERARCHY .
	 */
	private static final String ITEM_HIER_DEF = "ItemHierarchyDefn";
	/**
	 * private variable for ITEM HIERARCHY .
	 */
	private static final String ITEM_HIER_DEF_HIS = "ItemHierarchyDefnHistory";
	/**
	 * private variable for BEST PRICE.
	 */
	private static final String BEST_PRICE = "BestPrice";
	/**
	 * private variable for BEST PRICE.
	 */
	private static final String BEST_PRICE_HIS = "BestPriceHistory";
	/**
	 * private variable for AVERAGE SHELF LIFE.
	 */
	private static final String AVG_SHELF_LIFE = "AverageShelfLife";
	/**
	 * private variable for AVERAGE SHELF LIFE.
	 */
	private static final String AVG_SHELF_LIFE_HIS = "AverageShelfLifeHistory";
	/**
	 * private variable for GL COST CENTER.
	 */
	private static final String GL_COST = "GLCostCenter";
	/**
	 * private variable for GL COST CENTER.
	 */
	private static final String GL_COST_HIS = "GLCostCenterHistory";
	/**
	 * private variable for LOT MASTER.
	 */
	private static final String LOT_MASTER = "LotMaster";
	/**
	 * private variable for ACCRUAL MASTER.
	 */
	private static final String ACCRUAL_MASTER = "AccrualMaster";
	/**
	 * private variable for ACCRUAL OUTBOUND.
	 */
	private static final String ACCRUAL_OUTBOUND = "AccrualOutbound";
	/**
	 * private variable for GL_POSTING.
	 */
	private static final String GL_POSTING = "GL_Posting";
	/**
	 * private variable for DEMAND_ACTUAL.
	 */
	private static final String DEMAND_ACTUAL = "DemandActual";
	/**
	 * private variable for DEMAND_FORECAST.
	 */
	private static final String DEMAND_FORECAST = "DemandForecast";
	/**
	 * private variable for INVENTORY_ACTUAL_DETAIL.
	 */
	private static final String INVENTORY_ACTUAL_DETAIL = "InventoryActualDetail";
	/**
	 * private variable for INVENTORY_ACTUAL_SUMMARY.
	 */
	private static final String INVENTORY_ACTUAL_SUMMARY = "InventoryActualSummary";
	/**
	 * private variable for INVENTORY_PROJECTED_DETAIL.
	 */
	private static final String INVENTORY_PROJECTED_DETAIL = "InventoryProjectedDetail";
	/**
	 * private variable for INVENTORY_PROJECTED_SUMMARY.
	 */
	private static final String INVENTORY_PROJECTED_SUMMARY = "InventoryProjectedSummary";
	/**
	 * private variable for RETURNS_INTERFACE.
	 */
	private static final String RETURNS_INTERFACE = "ReturnsInterface";

	/**
	 * private variable for ADJUSTED_DEMAND_ACTUAL_INTERFACE.
	 */
	private static final String ADJUSTED_DEMAND_ACTUAL_INTERFACE = "AdjustedDemandActualInterface";
	/**
	 * private variable for ADJUSTED_DEMAND_FORECASTINTERFACE.
	 */
	private static final String ADJUSTED_DEMAND_FORECASTINTERFACE = "AdjustedDemandForecastInterface";
	/**
	 * private variable for CUSTOMER_GTS_ACTUAL_INTERFACE.
	 */
	private static final String CUSTOMER_GTS_ACTUAL_INTERFACE = "CustomerGtsActualInterface";
	/**
	 * private variable for CUSTOMER_GTS_FORECAST_INTERFACE.
	 */
	private static final String CUSTOMER_GTS_FORECAST_INTERFACE = "CustomerGtsForecastInterface";

	/**
	 * private variable for RETURN_RESERVE_INTERFACE.
	 */
	private static final String RETURN_RESERVE_INTERFACE = "ReturnReserveInterface";
	/**
	 * private variable for CFF_OUTBOUND_INTERFACE.
	 */
	private static final String CFF_OUTBOUND_INTERFACE = "CffOutboundInterface";

	/**
	 * private variable for ARP_OUTBOUND_INTERFACE.
	 */
	private static final String ARP_OUTBOUND_INTERFACE = "ArpOutboundInterface";
	/**
	 * private variable for ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE.
	 */
	private static final String ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE = "AdjustmentGtnDetailInterface";
	/**
	 * private variable for ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE.
	 */
	private static final String ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE = "AdjustmentReserveDetailInterface";
	/**
	 * private variable for ITEM_UOM_INTERFACE.
	 */
	private static final String ITEM_UOM_INTERFACE = "ItemUomInterface";

	/**
	 * private variable for LOT MASTER.
	 */
	private static final String LOT_MASTER_HIS = "LotMasterHistory";
	/**
	 * private variable for Master ETL.
	 */

	/**
	 * private variable for Master ETL.
	 */

	/**
	 * private variable for RETURN RATE FORECAST INTERFACE.
	 */
	private static final String RETURN_RATE_FORECAST_INTERFACE = "ReturnRateForecastInterface";

	/**
	 * private variable for TESTING-ENCRYPTION INTERFACE.
	 */
	private static final String TESTING_INTERFACE = "TestingInterface";
	/**
	 * private variable for ADROIT_ACTUAL_MASTER_CHARGEBACK_INTERFACE
	 */
	private static final String ACTUALS_CHARGEBACK_INTERFACE = "ActualsChargebackInterface";

	/**
	 * private variable for ADROIT_ACTUAL_MASTER_MEDICAID_INTERFACE
	 */
	private static final String ACTUALS_MEDICAID_INTERFACE = "ActualsMedicaidInterface";
	/**
	 * private variable for ADROIT_ACTUAL_MASTER_REBATE_INTERFACE
	 */
	private static final String ACTUALS_REBATE_INTERFACE = "ActualsRebateInterface";
	/**
	 * private variable for ADROIT_ACTUAL_MASTER_COUPON_INTERFACE
	 */
	private static final String ACTUALS_COUPON_INTERFACE = "ActualsCouponInterface";
	/**
	 * private variable for ADROIT_ITEM_HIERARCHY_INTERFACE
	 */
	private static final String PRODUCT_PROD_HIERARCHY_INTERFACE = "ProductHierarchyInterface";
	/**
	 * private variable for ADROIT_ITEM_BUSINESS_WAREHOUSE_INTERFACE .
	 */
	private static final String PRODUCT_PROD_BW_INTERFACE = "ProductProdBwInterface";

	/**
	 * private variable for ADROIT_COMPANY_IDENTIFIER_INTERFACE
	 */
	private static final String COMPANY_BUID_INTERFACE = "CompanyBuidInterface";

	/**
	 * private variable for ADROIT_COMPANY_MASTER_INTERFACE
	 */
	private static final String COMPANY_BUNIT_INTERFACE = "CompanyBunitInterface";

	/**
	 * private variable for ADROIT_COMPANY_TRADE_CLASS_INTERFACE
	 */
	private static final String COMPANY_BUTYPE_INTERFACE = "CompanyBuTypeInterface";
	/**
	 * private variable for ADROIT_ITEM_IDENTIFIER_INTERFACE.
	 */
	private static final String PRODUCT_PRODID_INTERFACE = "ProductProdIdInterface";
	/**
	 * private variable for ADROIT_ITEM_MASTER_INTERFACE.
	 */
	private static final String PRODUCT_PROD_INTERFACE = "ProductProdInterface";
	/**
	 * private variable for ADROIT_ITEM_PRICING_INTERFACE.
	 */
	private static final String PRICING_INTERFACE = "PricingInterface";

	/**
	 * private variable for ADROIT_CONTRACT_HEADER_INTERFACE
	 */
	private static final String CONTRACT_CONT_INTERFACE = "ContractContInterface";
	/**
	 * private variable for ADROIT_COMPANY_FAMILY_PLAN_INTERFACE
	 */
	private static final String CONTRACT_CPGRP_INTERFACE = "ContractCpgrpInterface";
	/**
	 * private variable for ADROIT_ITEM_FAMILY_PLAN_INTERFACE.
	 */
	private static final String CONTRACT_CPPPO_INTERFACE = "ContractCpppoInterface";

	/**
	 * private variable for ADROIT_REBATE_PLAN_INTERFACE.
	 */
	private static final String CONTRACT_CPPT_INTERFACE = "ContractCpptInterface";

	/**
	 * private variable for ADROIT_PRICE_SCHEDULE_INTERFACE.
	 */
	private static final String CONTRACT_CPGRP_PGMMKT_INTERFACE = "ContractCpgrpPgmmktInterface";
	/**
	 * private variable for ADROIT_REBATE_SCHEDULE_INTERFACE.
	 */
	private static final String CONTRACT_PRGMMKT_INTERFACE = "ContractPrgmmktInterface";

	/**
	 * private variable for ADROIT_SALES_MASTER_INTERFACE.
	 */
	private static final String PSTG_SALES_MASTER_INTERFACE = "PstgSalesMaster";
	/**
	 * private variable for ADROIT_CUSTOMER_GTS_ACTUAL_INTERFACE.
	 */
	private static final String PSTG_CUSTOMER_GTS_ACTUAL_INTERFACE = "PstgCustomerGtsActualInterface";
	/**
	 * private variable for ADROIT_CUSTOMER_GTS_FORECAST_INTERFACE.
	 */
	private static final String PSTG_CUSTOMER_GTS_FORECAST_INTERFACE = "PstgCustomerGtsForecastInterface";
	/**
	 * private variable for ADROIT_RETURNS_INTERFACE.
	 */
	private static final String PSTG_RETURNS_INTERFACE = "PstgReturnsInterface";

	/**
	 * private variable for LOT ADROIT_CPI_INDEX_INTERFACE.
	 */
	private static final String CONSUMER_PRICE_INDEX_INTERFACE = "ConsumerPriceIndexInterface";

	/**
	 * private variable for ADROIT_GL_COST_CENTER_INTERFACE.
	 */
	private static final String PSTG_GL_COST_CENTER_INTERFACE = "PstgGLCostCenter";
	/**
	 * private variable for ADROIT_ITEM_UOM_INTERFACE.
	 */
	private static final String UNIT_OF_MEASURE_INTERFACE = "UnitOfMeasureInterface";
	/**
	 * private variable for ADROIT_ACCRUAL_INBOUND_INTERFACE.
	 */
	private static final String ACCRUALS_INTERFACE = "AccrualsInterface";

	/**
	 * private variable for LOT ADROIT_FORECASTING_MASTER_INTERFACE.
	 */
	private static final String PSTG_SALES_FORECAST_INTERFACE = "PstgSalesForecastInterface";

	private static final String CONTRACT_CFP_MBR_INTERFACE = "ContractCfpMbrInterface";
	private static final String PSTG_FORECAST_SALES_WAC_INTERFACE = "PstgForecastSalesWacInterface";
	private static final String PSTG_GCC_PROD_HIERARCHY_INTERFACE = "PstgGccProdHierarchyInterface";
        
        private static final String DTX_UOM_INTERFACE = "DtxUOMInterface";
        private static final String DTX_CPI_INTERFACE = "DtxCPIInterface";
        private static final String DTX_RETURN_RATE_INTERFACE = "DtxReturnRateInterface";
        private static final String DTX_ITEM_PRICING_INTERFACE = "DtxItemPricingInterface";
        private static final String DTX_ACCRUAL_ACTUAL_INTERFACE = "DtxAccrualActualInterface";
        private static final String DTX_ITEM_MASTER_INTERFACE = "DtxItemMasterInterface";
        private static final String DTX_ITEM_IDENTIFIER_INTERFACE = "DtxItemIdentifierInterface";
        private static final String DTX_CUSTOMER_GTS_ACTUAL_INTERFACE = "DtxCustomerGtsActualInterface";
        private static final String DTX_CUSTOMER_GTS_FORECAST_INTERFACE = "DtxCustomerGtsForecastInterface";
        private static final String DTX_COMPANY_MASTER_INTERFACE = "DtxCompanyMasterInterface";
        private static final String DTX_COMPANY_TRADE_CLASS_INTERFACE = "DtxCompanyTradeClassInterface";
        private static final String DTX_COMPANY_IDENTIFIER_INTERFACE = "DtxCompanyIdentifierInterface";
        private static final String DTX_CONTRACT_CFP_INTERFACE = "DtxContractCFPInterface";
        private static final String DTX_CONTRACT_HEADER_INTERFACE = "DtxContractHeaderInterface";
        private static final String DTX_FORECAST_SALES_INTERFACE = "DtxForecastSalesInterface";
        private static final String DTX_FORECAST_SALES_WAC_INTERFACE = "DtxForecastSalesWacInterface";
        private static final String DTX_CONTRACT_PRICE_PLAN_INTERFACE = "DtxContractPPInterface";
        private static final String DTX_CONTRACT_CPGRP_INTERFACE = "DtxContractCPGRPInterface";
        private static final String DTX_CONTRACT_IFP_INTERFACE = "DtxContractIFPInterface";
        private static final String DTX_CONTRACT_CPPPO_INTERFACE = "DtxContractCPPPOInterface";
        private static final String DTX_CONTRACT_CPGRP_PGM_MKT_INTERFACE = "DtxContractCpgrpPgmMktInterface";

	protected static Map<String, String[]> loaddata = new HashMap<>();

	String[] loaddat(String keyValue) {
		if (loaddata.isEmpty()) {

			loaddata.put(COMPANY_MASTER,
					new String[] { Constants.COMPANY_MASTER, FilePathUtil.COMPANY_MASTER_INTERFACE });
			loaddata.put(COMPANY_HIS,
					new String[] { Constants.COM_MASTER_INTF, FilePathUtil.COMPANY_MASTER_INTERFACE_HISTORY });

			loaddata.put(COMPANY_PARENT,
					new String[] { Constants.COMPANY_PARENT, FilePathUtil.COMPANY_PARENT_INTERFACE });
			loaddata.put(COMPANY_PARENT_HIS,
					new String[] { Constants.COMPANY_PARENT_HIS, FilePathUtil.COMPANY_PARENT_INTERFACE_HISTORY });

			loaddata.put(COMPANY_TRADE_HIS,
					new String[] { Constants.COMPANY_TRADE_HIS, FilePathUtil.COMPANY_TRADE_CLASS_INTERFACE_HISTORY });
			loaddata.put(COMPANY_TRADE,
					new String[] { Constants.COMPANY_TRADE, FilePathUtil.COMPANY_TRADE_CLASS_INTERFACE });

			loaddata.put(COMPANY_IDEN,
					new String[] { Constants.COMPANY_IDENT, FilePathUtil.COMPANY_IDENTIFIER_INTERFACE });
			loaddata.put(COMPANY_IDEN_HIS,
					new String[] { Constants.COMPANY_IDENT, FilePathUtil.COMPANY_IDENTIFIER_INTERFACE_HISTORY });

			loaddata.put(ITEM_MASTER, new String[] { Constants.ITEM_MASTER, FilePathUtil.ITEM_MASTER_INTERFACE });
			loaddata.put(ITEM_MASTER_HIS,
					new String[] { Constants.ITEM_MASTER, FilePathUtil.ITEM_MASTER_INTERFACE_HISTORY });

			loaddata.put(ITEM_IDEN, new String[] { Constants.ITEM_IDENTIFIER, FilePathUtil.ITEM_IDENTIFIER_INTERFACE });
			loaddata.put(ITEM_IDEN_HIS,
					new String[] { Constants.ITEM_IDENTIFIER, FilePathUtil.ITEM_IDENTIFIER_INTERFACE_HISTORY });

			loaddata.put(ITEM_PRICING, new String[] { Constants.ITEM_PRICING, FilePathUtil.ITEM_PRICING_INTERFACE });
			loaddata.put(ITEM_PRICING_HISTORY,
					new String[] { Constants.ITEM_PRICING, FilePathUtil.ITEM_PRICING_INTERFACE_HISTORY });

			loaddata.put(CONTRACT_HEADER,
					new String[] { Constants.CONTRACT_HEADER, FilePathUtil.CONTRACT_HEADER_INTERFACE });
			loaddata.put(CONTRACT_HIS,
					new String[] { Constants.CONT_MAS_INTF, FilePathUtil.CONTRACT_HEADER_INTERFACE_HISTORY });

			loaddata.put(CFP, new String[] { Constants.CFP, FilePathUtil.COMPANY_FAMILYPLAN_INTERFACE });
			loaddata.put(CFP_HISTORY,
					new String[] { Constants.CFP, FilePathUtil.COMPANY_FAMILYPLAN_INTERFACE_HISTORY });

			loaddata.put(IFP, new String[] { Constants.ITEM_FAMILYPLAN, FilePathUtil.ITEM_FAMILY_PLAN_INTERFACE });
			loaddata.put(IFP_HISTORY,
					new String[] { Constants.ITEM_FAMILYPLAN, FilePathUtil.ITEM_FAMILY_PLAN_INTERFACE_HISTORY });

			loaddata.put(PRICE_SCHEDULE,
					new String[] { Constants.PRICE_SCHEDULE, FilePathUtil.PRICE_SCHEDULE_INTERFACE });
			loaddata.put(PRICE_SCHEDULE_HISTORY,
					new String[] { Constants.PRICE_SCHEDULE, FilePathUtil.PRICE_SCHEDULE_INTERFACE_HISTORY });

			loaddata.put(REBATE_SCHEDULE,
					new String[] { Constants.REBATE_SCHEDULE, FilePathUtil.REBATE_SCHEDULE_INTERFACE });
			loaddata.put(REBATE_SCHEDULE_HISTORY,
					new String[] { Constants.REBATE_SCHEDULE, FilePathUtil.REBATE_SCHEDULE_INTERFACE_HISTORY });

			loaddata.put(REBATE_PLAN, new String[] { Constants.REBATE_PLAN, FilePathUtil.REBATE_PLAN_INTERFACE });
			loaddata.put(REBATE_PLAN_HISTORY,
					new String[] { Constants.REBATE_PLAN, FilePathUtil.REBATE_PLAN_INTERFACE_HISTORY });

			loaddata.put(ACTUALS, new String[] { Constants.ACTUAL_MASTER, FilePathUtil.ACTUAL_MASTER_INTERFACE });
			loaddata.put(ACTUALS_HISTORY,
					new String[] { Constants.ACTUAL_MASTER, FilePathUtil.ACTUAL_MASTER_INTERFACE_HISTORY });

			loaddata.put(ITEM_HIERARCHY,
					new String[] { Constants.ITEM_HIERARCHY, FilePathUtil.ITEM_HIERARCHY_INTERFACE });
			loaddata.put(ITEM_HIERARCHY_HIS,
					new String[] { Constants.ITEM_HIERARCHY, FilePathUtil.ITEM_HIERARCHY_INTERFACE_HISTORY });

			loaddata.put(ITEM_HIER_DEF,
					new String[] { Constants.ITEM_HIE_DEF, FilePathUtil.ITEM_HIERARCHY_DEF_INTERFACE });
			loaddata.put(ITEM_HIER_DEF_HIS,
					new String[] { Constants.ITEM_HIE_DEF, FilePathUtil.ITEM_HIERARCHY_DEF_INTERFACE_HISTORY });

			loaddata.put(SALES_MASTER, new String[] { Constants.SALES_MASTER, FilePathUtil.SALES_MASTER_INTERFACE });
			loaddata.put(SALES_MASTER_HISTORY,
					new String[] { Constants.SALES_MASTER, FilePathUtil.SALES_MASTER_INTERFACE_HISTORY });

			loaddata.put(FORECAST, new String[] { Constants.FORE_MASTER, FilePathUtil.FORECASTING_MASTER_INTERFACE });
			loaddata.put(FORECAST_HIS,
					new String[] { Constants.FORE_MASTER, FilePathUtil.FORECASTING_MASTER_INTERFACE_HISTORY });

			loaddata.put(GL_BALANCE, new String[] { Constants.GL_BALANCE, FilePathUtil.GL_BALANCE_INTERFACE });
			loaddata.put(GL_BALANCE_HISTORY,
					new String[] { Constants.GL_BALANCE, FilePathUtil.GL_BALANCE_INTERFACE_HISTORY });

			loaddata.put(CPI_INDEX, new String[] { Constants.CPI_INDEX, FilePathUtil.CPI_INDEX_INTERFACE });
			loaddata.put(CPI_INDEX_HIS, new String[] { Constants.CPI_INDEX, FilePathUtil.CPI_INDEX_INTERFACE_HISTORY });

			loaddata.put(FORMULA_DET,
					new String[] { Constants.FORMULA_DETAILS, FilePathUtil.FORMULA_DETAILS_INTERFACE });
			loaddata.put(FORMULA_DET_HIS,
					new String[] { Constants.FORMULA_DET_INTF, FilePathUtil.FORMULA_DETAILS_INTERFACE_HISTORY });

			loaddata.put(MASTER_DATA_ATT, new String[] { Constants.MASTER_DATA, FilePathUtil.MASTER_DATA_INTERFACE });
			loaddata.put(MASTER_DATA_ATT_HISTORY,
					new String[] { Constants.MASTER_DATA, FilePathUtil.MASTER_DATA_INTERFACE_HISTORY });

			loaddata.put(BEST_PRICE, new String[] { Constants.BEST_PRICE, FilePathUtil.BEST_PRICE_INTERFACE });
			loaddata.put(BEST_PRICE_HIS,
					new String[] { Constants.BEST_PRICE, FilePathUtil.BEST_PRICE_INTERFACE_HISTORY });

			loaddata.put(AVG_SHELF_LIFE,
					new String[] { Constants.AVERAGE_SHELF, FilePathUtil.AVERAGE_SHELF_LIFE_INTERFACE });
			loaddata.put(AVG_SHELF_LIFE_HIS,
					new String[] { Constants.AVERAGE_SHELF, FilePathUtil.AVERAGE_SHELF_LIFE_INTERFACE_HISTORY });

			loaddata.put(GL_COST, new String[] { Constants.GL_COST_CENTER, FilePathUtil.GL_COST_CENTER_INTERFACE });
			loaddata.put(GL_COST_HIS,
					new String[] { Constants.GL_COST_CENTER, FilePathUtil.GL_COST_CENTER_INTERFACE_HISTORY });

			loaddata.put(LOT_MASTER, new String[] { Constants.LOT_MASTER, FilePathUtil.LOT_MASTER_INTERFACE });
			loaddata.put(LOT_MASTER_HIS,
					new String[] { Constants.LOT_MASTER, FilePathUtil.LOT_MASTER_INTERFACE_HISTORY });

			loaddata.put(ACCRUAL_MASTER,
					new String[] { Constants.ACCRUAL_MASTER, FilePathUtil.ACCRUAL_MASTER_INBOUND });
			loaddata.put(ACCRUAL_OUTBOUND,
					new String[] { Constants.ACCRUAL_OUTBOUND, FilePathUtil.ACCRUAL_DETAIL_OUTBOUND });

			loaddata.put(GL_POSTING, new String[] { Constants.GL_POSTING, FilePathUtil.GL_POSTING_INTERFACE });
			loaddata.put(DEMAND_ACTUAL, new String[] { Constants.DEMAND_ACTUAL, FilePathUtil.DEMAND_ACTUAL_INTERFACE });
			loaddata.put(DEMAND_FORECAST,
					new String[] { Constants.DEMAND_FORECAST, FilePathUtil.DEMAND_FORECAST_INTERFACE });

			loaddata.put(INVENTORY_ACTUAL_DETAIL, new String[] { Constants.INVENTORY_ACTUAL_DETAIL,
					FilePathUtil.INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERFACE });
			loaddata.put(INVENTORY_ACTUAL_SUMMARY, new String[] { Constants.INVENTORY_ACTUAL_SUMMARY,
					FilePathUtil.INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTERFACE });
			loaddata.put(INVENTORY_PROJECTED_DETAIL, new String[] { Constants.INVENTORY_PROJECTED_DETAIL,
					FilePathUtil.INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INTERFACE });
			loaddata.put(INVENTORY_PROJECTED_SUMMARY, new String[] { Constants.INVENTORY_PROJECTED_SUMMARY,
					FilePathUtil.INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_INTERFACE });

			loaddata.put(RETURNS_INTERFACE,
					new String[] { Constants.RETURNS_INTERFACE, FilePathUtil.RETURNS_INTERFACE1 });
			loaddata.put(ADJUSTED_DEMAND_ACTUAL_INTERFACE, new String[] { Constants.ADJUSTED_DEMAND_ACTUAL_INTERFACE,
					FilePathUtil.ADJUSTED_DEMAND_ACTUAL_INTERFACE1 });
			loaddata.put(ADJUSTED_DEMAND_FORECASTINTERFACE, new String[] { Constants.ADJUSTED_DEMAND_FORECASTINTERFACE,
					FilePathUtil.ADJUSTED_DEMAND_FORECASTINTERFACE1 });
			loaddata.put(CUSTOMER_GTS_ACTUAL_INTERFACE, new String[] { Constants.CUSTOMER_GTS_ACTUAL_INTERFACE,
					FilePathUtil.CUSTOMER_GTS_ACTUAL_INTERFACE1 });
			loaddata.put(CUSTOMER_GTS_FORECAST_INTERFACE, new String[] { Constants.CUSTOMER_GTS_FORECAST_INTERFACE,
					FilePathUtil.CUSTOMER_GTS_FORECAST_INTERFACE1 });

			loaddata.put(RETURN_RESERVE_INTERFACE,
					new String[] { Constants.RETURN_RESERVE_INTERFACE, FilePathUtil.RETURN_RESERVE_INTERFACE1 });
			loaddata.put(CFF_OUTBOUND_INTERFACE,
					new String[] { Constants.CFF_OUTBOUND_INTERFACE, FilePathUtil.CFF_OUTBOUND_INTERFACE1 });
			loaddata.put(ARP_OUTBOUND_INTERFACE,
					new String[] { Constants.ARP_OUTBOUND_INTERFACE, FilePathUtil.ARP_OUTBOUND_INTERFACE1 });
			loaddata.put(ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE,
					new String[] { Constants.ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE,
							FilePathUtil.ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE1 });
			loaddata.put(ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE,
					new String[] { Constants.ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE,
							FilePathUtil.ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE1 });
			loaddata.put(ITEM_UOM_INTERFACE,
					new String[] { Constants.ITEM_UOM_INTERFACE, FilePathUtil.ITEM_UOM_INTERFACE });
			loaddata.put(RETURN_RATE_FORECAST_INTERFACE, new String[] { Constants.RETURN_RATE_FORECAST_INTERFACE,
					FilePathUtil.RETURN_RATE_FORECAST_INTERFACE });
			loaddata.put(TESTING_INTERFACE,
					new String[] { Constants.TESTING_INTERFACE, FilePathUtil.TESTING_INTERFACE });

			loaddata.put(ACTUALS_CHARGEBACK_INTERFACE,
					new String[] { Constants.ACTUALS_CHARGEBACK_INTERFACE, FilePathUtil.ACTUALS_CHARGEBACK_INTERFACE });
			loaddata.put(ACTUALS_MEDICAID_INTERFACE,
					new String[] { Constants.ACTUALS_MEDICAID_INTERFACE, FilePathUtil.ACTUALS_MEDICAID_INTERFACE });
			loaddata.put(ACTUALS_REBATE_INTERFACE,
					new String[] { Constants.ACTUALS_REBATE_INTERFACE, FilePathUtil.ACTUALS_REBATE_INTERFACE });
			loaddata.put(ACTUALS_COUPON_INTERFACE,
					new String[] { Constants.ACTUALS_COUPON_INTERFACE, FilePathUtil.ACTUALS_COUPON_INTERFACE });
			loaddata.put(PRODUCT_PROD_HIERARCHY_INTERFACE, new String[] { Constants.PRODUCT_PROD_HIERARCHY_INTERFACE,
					FilePathUtil.PRODUCT_PROD_HIERARCHY_INTERFACE });
			loaddata.put(PRODUCT_PROD_BW_INTERFACE,
					new String[] { Constants.PRODUCT_PROD_BW_INTERFACE, FilePathUtil.PRODUCT_PROD_BW_INTERFACE });

			loaddata.put(COMPANY_BUID_INTERFACE,
					new String[] { Constants.COMPANY_BUID_INTERFACE, FilePathUtil.COMPANY_BUID_INTERFACE });

			loaddata.put(COMPANY_BUNIT_INTERFACE,
					new String[] { Constants.COMPANY_BUNIT_INTERFACE, FilePathUtil.COMPANY_BUNIT_INTERFACE });
			loaddata.put(COMPANY_BUTYPE_INTERFACE,
					new String[] { Constants.COMPANY_BUTYPE_INTERFACE, FilePathUtil.COMPANY_BUTYPE_INTERFACE });
			loaddata.put(PRODUCT_PRODID_INTERFACE,
					new String[] { Constants.PRODUCT_PRODID_INTERFACE, FilePathUtil.PRODUCT_PRODID_INTERFACE });
			loaddata.put(PRODUCT_PROD_INTERFACE,
					new String[] { Constants.PRODUCT_PROD_INTERFACE, FilePathUtil.PRODUCT_PROD_INTERFACE });

			loaddata.put(PRICING_INTERFACE,
					new String[] { Constants.PRICING_INTERFACE, FilePathUtil.PRICING_INTERFACE });
			loaddata.put(CONTRACT_CONT_INTERFACE,
					new String[] { Constants.CONTRACT_CONT_INTERFACE, FilePathUtil.CONTRACT_CONT_INTERFACE });
			loaddata.put(CONTRACT_CPGRP_INTERFACE,
					new String[] { Constants.CONTRACT_CPGRP_INTERFACE, FilePathUtil.CONTRACT_CPGRP_INTERFACE });
			loaddata.put(CONTRACT_CPPPO_INTERFACE,
					new String[] { Constants.CONTRACT_CPPPO_INTERFACE, FilePathUtil.CONTRACT_CPPPO_INTERFACE });
			loaddata.put(CONTRACT_CPPT_INTERFACE,
					new String[] { Constants.CONTRACT_CPPT_INTERFACE, FilePathUtil.CONTRACT_CPPT_INTERFACE });

			loaddata.put(CONTRACT_CPGRP_PGMMKT_INTERFACE, new String[] { Constants.CONTRACT_CPGRP_PGMMKT_INTERFACE,
					FilePathUtil.CONTRACT_CPGRP_PGMMKT_INTERFACE });
			loaddata.put(CONTRACT_PRGMMKT_INTERFACE,
					new String[] { Constants.CONTRACT_PRGMMKT_INTERFACE, FilePathUtil.CONTRACT_PRGMMKT_INTERFACE });
			loaddata.put(PSTG_SALES_MASTER_INTERFACE,
					new String[] { Constants.PSTG_SALES_MASTER_INTERFACE, FilePathUtil.PSTG_SALES_MASTER_INTERFACE });
			loaddata.put(PSTG_CUSTOMER_GTS_ACTUAL_INTERFACE, new String[] {
					Constants.PSTG_CUSTOMER_GTS_ACTUAL_INTERFACE, FilePathUtil.PSTG_CUSTOMER_GTS_ACTUAL_INTERFACE });
			loaddata.put(PSTG_CUSTOMER_GTS_FORECAST_INTERFACE,
					new String[] { Constants.PSTG_CUSTOMER_GTS_FORECAST_INTERFACE,
							FilePathUtil.PSTG_CUSTOMER_GTS_FORECAST_INTERFACE });
			loaddata.put(PSTG_RETURNS_INTERFACE,
					new String[] { Constants.PSTG_RETURNS_INTERFACE, FilePathUtil.PSTG_RETURNS_INTERFACE });
			loaddata.put(CONSUMER_PRICE_INDEX_INTERFACE, new String[] { Constants.CONSUMER_PRICE_INDEX_INTERFACE,
					FilePathUtil.CONSUMER_PRICE_INDEX_INTERFACE });
			loaddata.put(PSTG_GL_COST_CENTER_INTERFACE, new String[] { Constants.PSTG_GL_COST_CENTER_INTERFACE,
					FilePathUtil.PSTG_GL_COST_CENTER_INTERFACE });
			loaddata.put(UNIT_OF_MEASURE_INTERFACE,
					new String[] { Constants.UNIT_OF_MEASURE_INTERFACE, FilePathUtil.UNIT_OF_MEASURE_INTERFACE });
			loaddata.put(ACCRUALS_INTERFACE,
					new String[] { Constants.ACCRUALS_INTERFACE, FilePathUtil.ACCRUALS_INTERFACE });
			loaddata.put(PSTG_SALES_FORECAST_INTERFACE, new String[] { Constants.PSTG_SALES_FORECAST_INTERFACE,
					FilePathUtil.PSTG_SALES_FORECAST_INTERFACE });

			loaddata.put(CONTRACT_CFP_MBR_INTERFACE,
					new String[] { Constants.CONTRACT_CFP_MBR_INTERFACE, FilePathUtil.CONTRACT_CFP_MBR_INTERFACE });
			loaddata.put(PSTG_FORECAST_SALES_WAC_INTERFACE, new String[] { Constants.PSTG_FORECAST_SALES_WAC_INTERFACE,
					FilePathUtil.PSTG_FORECAST_SALES_WAC_INTERFACE });
			loaddata.put(PSTG_GCC_PROD_HIERARCHY_INTERFACE, new String[] { Constants.PSTG_GCC_PROD_HIERARCHY_INTERFACE,
					FilePathUtil.PSTG_GCC_PROD_HIERARCHY_INTERFACE });
                        loaddata.put(DTX_UOM_INTERFACE,
					new String[] { Constants.DTX_UOM_INTERFACE, FilePathUtil.DTX_UOM_INTERFACE });
                        loaddata.put(DTX_CPI_INTERFACE,
					new String[] { Constants.DTX_CPI_INTERFACE, FilePathUtil.DTX_CPI_INTERFACE });
                        loaddata.put(DTX_RETURN_RATE_INTERFACE,
					new String[] { Constants.DTX_RETURN_RATE_INTERFACE, FilePathUtil.DTX_RETURN_RATE_INTERFACE });
                        loaddata.put(DTX_ITEM_PRICING_INTERFACE,
					new String[] { Constants.DTX_ITEM_PRICING_INTERFACE, FilePathUtil.DTX_ITEM_PRICING_INTERFACE });
                        loaddata.put(DTX_ACCRUAL_ACTUAL_INTERFACE,
					new String[] { Constants.DTX_ACCRUAL_ACTUAL_INTERFACE, FilePathUtil.DTX_ACCRUAL_ACTUAL_INTERFACE });
                        loaddata.put(DTX_ITEM_MASTER_INTERFACE,
            		new String[] { Constants.DTX_ITEM_MASTER_INTERFACE, FilePathUtil.DTX_ITEM_MASTER_INTERFACE });
                        loaddata.put(DTX_ITEM_IDENTIFIER_INTERFACE,
                    new String[] { Constants.DTX_ITEM_IDENTIFIER_INTERFACE, FilePathUtil.DTX_ITEM_IDENTIFIER_INTERFACE });
                        loaddata.put(DTX_CUSTOMER_GTS_ACTUAL_INTERFACE,
                    new String[] { Constants.DTX_CUSTOMER_GTS_ACTUAL_INTERFACE, FilePathUtil.DTX_CUSTOMER_GTS_ACTUAL_INTERFACE });
                        loaddata.put(DTX_CUSTOMER_GTS_FORECAST_INTERFACE,
                    new String[] { Constants.DTX_CUSTOMER_GTS_FORECAST_INTERFACE, FilePathUtil.DTX_CUSTOMER_GTS_FORECAST_INTERFACE });
                        loaddata.put(DTX_COMPANY_MASTER_INTERFACE,
                    new String[] { Constants.DTX_COMPANY_MASTER_INTERFACE, FilePathUtil.DTX_COMPANY_MASTER_INTERFACE });
                        loaddata.put(DTX_COMPANY_TRADE_CLASS_INTERFACE,
                    new String[] { Constants.DTX_COMPANY_TRADE_CLASS_INTERFACE, FilePathUtil.DTX_COMPANY_TRADE_CLASS_INTERFACE });
                        loaddata.put(DTX_COMPANY_IDENTIFIER_INTERFACE,
                    new String[] { Constants.DTX_COMPANY_IDENTIFIER_INTERFACE, FilePathUtil.DTX_COMPANY_IDENTIFIER_INTERFACE });
                        loaddata.put(DTX_CONTRACT_CFP_INTERFACE,
                    new String[] { Constants.DTX_CONTRACT_CFP_INTERFACE, FilePathUtil.DTX_CONTRACT_CFP_INTERFACE });
                        loaddata.put(DTX_CONTRACT_HEADER_INTERFACE,
                    new String[] { Constants.DTX_CONTRACT_HEADER_INTERFACE, FilePathUtil.DTX_CONTRACT_HEADER_INTERFACE });
                        loaddata.put(DTX_FORECAST_SALES_INTERFACE,
                    new String[] { Constants.DTX_FORECAST_SALES_INTERFACE, FilePathUtil.DTX_FORECAST_SALES_INTERFACE });
                        loaddata.put(DTX_FORECAST_SALES_WAC_INTERFACE,
                    new String[] { Constants.DTX_FORECAST_SALES_WAC_INTERFACE, FilePathUtil.DTX_FORECAST_SALES_WAC_INTERFACE });
                        loaddata.put(DTX_CONTRACT_PRICE_PLAN_INTERFACE,
                    new String[] { Constants.DTX_CONTRACT_PRICE_PLAN_INTERFACE, FilePathUtil.DTX_CONTRACT_PRICE_PLAN_INTERFACE });
                        loaddata.put(DTX_CONTRACT_IFP_INTERFACE,
                    new String[] { Constants.DTX_CONTRACT_IFP_INTERFACE, FilePathUtil.DTX_CONTRACT_IFP_INTERFACE });
                        loaddata.put(DTX_CONTRACT_CPGRP_INTERFACE,
                    new String[] { Constants.DTX_CONTRACT_CPGRP_INTERFACE, FilePathUtil.DTX_CONTRACT_CPGRP_INTERFACE });
                        loaddata.put(DTX_CONTRACT_CPPPO_INTERFACE,
                   new String[] { Constants.DTX_CONTRACT_CPPPO_INTERFACE, FilePathUtil.DTX_CONTRACT_CPPPO_INTERFACE });
                        loaddata.put(DTX_CONTRACT_CPGRP_PGM_MKT_INTERFACE,
                   new String[] { Constants.DTX_CONTRACT_CPGRP_PGM_MKT_INTERFACE, FilePathUtil.DTX_CONTRACT_CPGRP_PGM_MKT_INTERFACE });
                        		
		}
		return loaddata.get(keyValue);
	}

	@RequestMapping(value = "/{apikey}/{path}", method = RequestMethod.GET, produces = "application/json")
	/**
	 * The method used to call each interface based on the api key and method name.
	 *
	 * @param apikey
	 * @param interfaceName
	 * @return
	 */

	public @ResponseBody String home(@PathVariable("apikey") final String apikey,
			@PathVariable("path") final String interfaceName) throws BPIETLException, IOException {
		try {

			List<String> global = Arrays.asList(COMPANY_MASTER, COMPANY_HIS, COMPANY_PARENT, COMPANY_PARENT_HIS,
					COMPANY_TRADE_HIS, COMPANY_TRADE, COMPANY_IDEN, COMPANY_IDEN_HIS, ITEM_MASTER, ITEM_MASTER_HIS,
					ITEM_IDEN, ITEM_IDEN_HIS, ITEM_PRICING, ITEM_PRICING_HISTORY, COMPANY_BUID_INTERFACE,
					COMPANY_BUNIT_INTERFACE, COMPANY_BUTYPE_INTERFACE, PRODUCT_PRODID_INTERFACE, PRODUCT_PROD_INTERFACE,
					PRICING_INTERFACE, PRODUCT_PROD_BW_INTERFACE, PRODUCT_PROD_HIERARCHY_INTERFACE,DTX_ITEM_PRICING_INTERFACE,
					DTX_ITEM_MASTER_INTERFACE,DTX_ITEM_IDENTIFIER_INTERFACE,DTX_COMPANY_MASTER_INTERFACE,DTX_COMPANY_TRADE_CLASS_INTERFACE,
					DTX_COMPANY_IDENTIFIER_INTERFACE);

			List<String> contract = Arrays.asList(CONTRACT_HEADER, CONTRACT_HIS, CFP, CFP_HISTORY, IFP, IFP_HISTORY,
					PRICE_SCHEDULE, PRICE_SCHEDULE_HISTORY, REBATE_SCHEDULE, REBATE_SCHEDULE_HISTORY, REBATE_PLAN,
					REBATE_PLAN_HISTORY, CONTRACT_CONT_INTERFACE, CONTRACT_CPGRP_INTERFACE, CONTRACT_CPPPO_INTERFACE,
					CONTRACT_CPPT_INTERFACE, CONTRACT_CPGRP_PGMMKT_INTERFACE, CONTRACT_PRGMMKT_INTERFACE,
					CONTRACT_CFP_MBR_INTERFACE,DTX_CONTRACT_CFP_INTERFACE,DTX_CONTRACT_HEADER_INTERFACE,
					DTX_CONTRACT_PRICE_PLAN_INTERFACE,DTX_CONTRACT_IFP_INTERFACE,DTX_CONTRACT_CPGRP_INTERFACE,
					DTX_CONTRACT_CPGRP_PGM_MKT_INTERFACE,DTX_CONTRACT_CPPPO_INTERFACE);

			List<String> transaction = Arrays.asList(ACTUALS, ACTUALS_HISTORY, ITEM_HIERARCHY, ITEM_HIERARCHY_HIS,
					ITEM_HIER_DEF, ITEM_HIER_DEF_HIS, SALES_MASTER, SALES_MASTER_HISTORY, FORECAST, FORECAST_HIS,
					GL_BALANCE, GL_BALANCE_HISTORY, CPI_INDEX, CPI_INDEX_HIS, FORMULA_DET, FORMULA_DET_HIS,
					MASTER_DATA_ATT, MASTER_DATA_ATT_HISTORY, BEST_PRICE, BEST_PRICE_HIS, AVG_SHELF_LIFE,
					AVG_SHELF_LIFE_HIS, GL_COST, GL_COST_HIS, LOT_MASTER, LOT_MASTER_HIS, ACCRUAL_MASTER,
					ACCRUAL_OUTBOUND, GL_POSTING, DEMAND_ACTUAL, DEMAND_FORECAST, INVENTORY_ACTUAL_DETAIL,
					INVENTORY_ACTUAL_SUMMARY, INVENTORY_PROJECTED_DETAIL, INVENTORY_PROJECTED_SUMMARY,
					RETURNS_INTERFACE, ADJUSTED_DEMAND_ACTUAL_INTERFACE, ADJUSTED_DEMAND_FORECASTINTERFACE,
					CUSTOMER_GTS_ACTUAL_INTERFACE, CUSTOMER_GTS_FORECAST_INTERFACE, RETURN_RESERVE_INTERFACE,
					CFF_OUTBOUND_INTERFACE, ARP_OUTBOUND_INTERFACE, ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE,
					ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE, ITEM_UOM_INTERFACE, RETURN_RATE_FORECAST_INTERFACE,
					TESTING_INTERFACE, ACTUALS_COUPON_INTERFACE, ACTUALS_CHARGEBACK_INTERFACE,
					ACTUALS_MEDICAID_INTERFACE, ACTUALS_REBATE_INTERFACE, PSTG_SALES_MASTER_INTERFACE,
					PSTG_CUSTOMER_GTS_ACTUAL_INTERFACE, PSTG_CUSTOMER_GTS_FORECAST_INTERFACE, PSTG_RETURNS_INTERFACE,
					CONSUMER_PRICE_INDEX_INTERFACE, PSTG_GL_COST_CENTER_INTERFACE, UNIT_OF_MEASURE_INTERFACE,
					ACCRUALS_INTERFACE, PSTG_SALES_FORECAST_INTERFACE, PSTG_FORECAST_SALES_WAC_INTERFACE,
					PSTG_GCC_PROD_HIERARCHY_INTERFACE,DTX_UOM_INTERFACE,DTX_CPI_INTERFACE,DTX_RETURN_RATE_INTERFACE,DTX_ACCRUAL_ACTUAL_INTERFACE,
					DTX_CUSTOMER_GTS_ACTUAL_INTERFACE,DTX_CUSTOMER_GTS_FORECAST_INTERFACE,DTX_FORECAST_SALES_INTERFACE
					,DTX_FORECAST_SALES_WAC_INTERFACE
			);

			if (Constants.APP_KEY.equals(apikey)) {

				String[] dataList = loaddat(interfaceName);
				String interfacesName1 = dataList[0];
				String fileName = dataList[1];

				if (global.contains(interfaceName)) {
					GlobalInterfaces.commonMetodtoLoadAllFile(interfacesName1, fileName);
					updateAllAutomaticRelationShip();
				} else if (contract.contains(interfaceName)) {
					ContractInterfaces.commonMetodtoLoadAllFile(interfacesName1, fileName);
					updateAllAutomaticRelationShip();
				} else if (transaction.contains(interfaceName)) {
					TransactionalInterfaces.commonMetodtoLoadAllFile(interfacesName1, fileName);

				}
				CommonUtils.createlog();

				CommonUtils.cleartemp();

				return Constants.SUCCESS;
			} else {
				CommonUtils.createlog();
				CommonUtils.cleartemp();
				return APIKEYERROR;
			}
		} catch (Exception ex) {
			LOGGER.error("Exception occured in  " + interfaceName, ex);
			CommonUtils.createlog();

			CommonUtils.createErrorlog();

			CommonUtils.cleartemp();

			return Constants.FAIL;
		}

	}

	/**
	 * This method is used to invoke all the interface in the mentioned sequence.
	 *
	 * @return SUCCESS
	 */
	/**
	 * This method is used to invoke all the interface with HIstorical in the
	 * mentioned sequence.
	 *
	 * @return SUCCESS
	 */
	@RequestMapping(value = "/{apikey}/Encrypt/{pass}", method = RequestMethod.GET, produces = "application/json")
	/**
	 * This method is used to encrypt the password for email and DB credentials.
	 *
	 * @param apikey
	 * @param pass
	 * @return
	 */
	public @ResponseBody String encrypt(@PathVariable("apikey") final String apikey,
			@PathVariable("pass") final String pass) throws BPIETLException, IOException {
		try {
			if (Constants.APP_KEY.equals(apikey)) {
				PluginRegistry.addPluginType(TwoWayPasswordEncoderPluginType.getInstance());
				PluginRegistry.init();
				String passwordEncoderPluginID = Const
						.NVL(EnvUtil.getSystemProperty(Const.KETTLE_PASSWORD_ENCODER_PLUGIN), "Kettle");
				Encr.init(passwordEncoderPluginID);
				return Encr.encryptPassword(pass);

			} else {
				return APIKEYERROR;
			}
		} catch (NoClassDefFoundError | KettleException ex) {
			throw new BPIETLException(ex);
		} finally {
			CommonUtils.createlog();
			CommonUtils.cleartemp();
		}
	}

	public void updateAllAutomaticRelationShip() throws IOException {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		final File file = new File("");
		String logpath = "";
		logpath = file.getCanonicalPath();
		final Properties dbResouce = new Properties();
		final String path = logpath + FilePathUtil.DATABASE_CONFIGURATION_FILE_NAME;
		dbResouce.load(new FileInputStream(path));
		System.setProperty("com.stpl.gtnframework.base.path", dbResouce.getProperty("com_stpl_gtnframework_base_path"));
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		wsclient.callGtnWebServiceUrl(GtnWebServiceUrlConstants.GTN_AUTOMATIC_RELATION_SERIVCE
				+ GtnWebServiceUrlConstants.AUTOMATIC_ALL_RELATION_UPDATE, request, getGsnWsSecurityToken());
	}

	private static GtnWsSecurityToken getGsnWsSecurityToken() {
		GtnWsSecurityToken token = new GtnWsSecurityToken();
		Integer sessionId = Calendar.getInstance().get(Calendar.MILLISECOND);
		String userId = "110";
		token.setUserId(userId);
		token.setSessionId(sessionId.toString());
		return token;
	}

}
