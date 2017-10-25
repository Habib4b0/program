package org.r2.etl.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.bpi.di.core.encryption.Encr;
import org.mozilla.javascript.ClassDefinitionException;
import org.r2.etl.common.util.CommonUtils;
import org.r2.etl.common.util.Constants;
import org.r2.etl.common.util.FilePathUtil;
import org.r2.etl.common.controller.BPIETLException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This class is the Controller Class for the Interface Execution.
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
	 * The variable used for interface name definition.
	 */
	public static String interfacesName="";
	/**
	 * private variable for COMPANY MASTER.
	 */
	private static final String COMPANY_MASTER="CompanyMaster";
	/**
	 * private variable for COMPANY HISTORY.
	 */
	private static final String COMPANY_HIS="CompanyMasterHistory";
	/**
	 * private variable for COMPANY PARENT.
	 */
	private static final String COMPANY_PARENT="CompanyParent";
	/**
	 * private variable for COMPANY PARENT HISTORY.
	 */
	private static final String COMPANY_PARENT_HIS="CompanyParentHistory";
	/**
	 * private variable for COMPANY TRADE CLASS HISTORY.
	 */
	private static final String COMPANY_TRADE_HIS="CompanyTradeClassHistory";
	/**
	 * private variable for COMPANY TRADE CLASS.
	 */
	private static final String COMPANY_TRADE="CompanyTradeClass";
	/**
	 * private variable for COMPANY IDENTIFIER.
	 */
	private static final String COMPANY_IDEN="CompanyIdentifier";
	/**
	 * private variable for COMPANY IDENTIFIER History.
	 */
	private static final String COMPANY_IDEN_HIS="CompanyIdentifierHistory";
	/**
	 * private variable for ITEM MASTER.
	 */
	private static final String ITEM_MASTER="ItemMaster";
	/**
	 * private variable for ITEM MASTER.
	 */
	private static final String ITEM_MASTER_HIS="ItemMasterHistory";
	/**
	 * private variable for ITEM IDENTIFIER.
	 */
	private static final String ITEM_IDEN="ItemIdentifier";
	/**
	 * private variable for ITEM IDENTIFIER.
	 */
	private static final String ITEM_IDEN_HIS="ItemIdentifierHistory";
	/**
	 * private variable for ITEM PRICING.
	 */
	private static final String ITEM_PRICING="ItemPricing";
	
	/**
	 * private variable for ITEM PRICING.
	 */
	private static final String ITEM_PRICING_HISTORY="ItemPricingHistory";
	
	/**
	 * private variable for CONTARCT MASTER.
	 */
	private static final String CONTRACT_HEADER="ContractHeader";
	/**
	 * private variable for CONTARCT HISTORY.
	 */
	private static final String CONTRACT_HIS="ContractMasterHistory";
	/**
	 * private variable for COMPANY FAMILY PLAN.
	 */
	private static final String CFP="CompanyFamilyPlan";
	/**
	 * private variable for COMPANY FAMILY PLAN.
	 */
	private static final String CFP_HISTORY="CompanyFamilyPlanHistory";
	/**
	 * private variable for ITEM FAMILY PLAN.
	 */
	private static final String IFP="ItemFamilyPlan";
	
	/**
	 * private variable for ITEM FAMILY PLAN.
	 */
	private static final String IFP_HISTORY="ItemFamilyPlanHistory";
	/**
	 * private variable for PRICE SCHEDULE.
	 */
	private static final String PRICE_SCHEDULE="PriceSchedule";
	/**
	 * private variable for PRICE SCHEDULE.
	 */
	private static final String PRICE_SCHEDULE_HISTORY="PriceScheduleHistory";
	/**
	 * private variable for REBATE SCHEDULE.
	 */
	private static final String REBATE_SCHEDULE="RebateSchedule";
	/**
	 * private variable for REBATE SCHEDULE.
	 */
	private static final String REBATE_SCHEDULE_HISTORY="RebateScheduleHistory";
	/**
	 * private variable for REBATE PLAN.
	 */
	private static final String REBATE_PLAN="RebatePlan";
	/**
	 * private variable for REBATE PLAN.
	 */
	private static final String REBATE_PLAN_HISTORY="RebatePlanHistory";
	/**
	 * private variable for ACTUAL MASTER.
	 */
	private static final String ACTUALS="ActualMaster";
	/**
	 * private variable for ACTUAL MASTER.
	 */
	private static final String ACTUALS_HISTORY="ActualMasterHistory";
	
	/**
	 * private variable for ITEM GIERARCHY.
	 */
	private static final String ITEM_HIERARCHY="ItemHierarchy";
	/**
	 * private variable for ITEM GIERARCHY.
	 */
	private static final String ITEM_HIERARCHY_HIS="ItemHierarchyHistory";
	/**
	 * private variable for SALES MASTER.
	 */
	private static final String SALES_MASTER="SalesMaster";
	/**
	 * private variable for SALES MASTER.
	 */
	private static final String SALES_MASTER_HISTORY="SalesMasterHistory";
	/**
	 * private variable for FORECAST SALES.
	 */
	private static final String FORECAST="ForecastingSales";
	/**
	 * private variable for FORECAST SALES.
	 */
	private static final String FORECAST_HIS="ForecastingSalesHistory";
	/**
	 * private variable for GL BALANCE.
	 */
	private static final String GL_BALANCE="GLBalance";
	/**
	 * private variable for GL BALANCE.
	 */
	private static final String GL_BALANCE_HISTORY="GLBalanceHistory";
	/**
	 * private variable for CPI INDEX.
	 */
	private static final String CPI_INDEX="CPIIndex";
	/**
	 * private variable for CPI INDEX.
	 */
	private static final String CPI_INDEX_HIS="CPIIndexHistory";
	/**
	 * private variable for FORMULA DETAILS.
	 */
	private static final String FORMULA_DET="FormulaDetails";
	/**
	 * private variable for FORMULA DETAILS HISTORY.
	 */
	private static final String FORMULA_DET_HIS="FormulaDetailsHistory";
	/**
	 * private variable for MASTER DATA.
	 */
	private static final String MASTER_DATA_ATT="MasterDataAttrb";
	
	/**
	 * private variable for MASTER DATA.
	 */
	private static final String MASTER_DATA_ATT_HISTORY="MasterDataAttrbHistory";
	/**
	 * private variable for ITEM HIERARCHY .
	 */
	private static final String ITEM_HIER_DEF="ItemHierarchyDefn";
	/**
	 * private variable for ITEM HIERARCHY .
	 */
	private static final String ITEM_HIER_DEF_HIS="ItemHierarchyDefnHistory";
	/**
	 * private variable for BEST PRICE.
	 */
	private static final String BEST_PRICE="BestPrice";
	/**
	 * private variable for BEST PRICE.
	 */
	private static final String BEST_PRICE_HIS="BestPriceHistory";
	/**
	 * private variable for AVERAGE SHELF LIFE.
	 */
	private static final String AVG_SHELF_LIFE="AverageShelfLife";
	/**
	 * private variable for AVERAGE SHELF LIFE.
	 */
	private static final String AVG_SHELF_LIFE_HIS="AverageShelfLifeHistory";
	/**
	 * private variable for GL COST CENTER.
	 */
	private static final String GL_COST="GLCostCenter";
	/**
	 * private variable for GL COST CENTER.
	 */
	private static final String GL_COST_HIS="GLCostCenterHistory";
	/**
	 * private variable for LOT MASTER.
	 */
	private static final String LOT_MASTER="LotMaster";
	/**
	 * private variable for ACCRUAL MASTER.
	 */
	private static final String ACCRUAL_MASTER="AccrualMaster";
	/**
	 * private variable for ACCRUAL OUTBOUND.
	 */
	private static final String ACCRUAL_OUTBOUND="AccrualOutbound";
	/**
	 * private variable for GL_POSTING.
	 */
	private static final String GL_POSTING="GL_Posting";
	/**
	 * private variable for DEMAND_ACTUAL.
	 */
	private static final String DEMAND_ACTUAL="DemandActual";
	/**
	 * private variable for DEMAND_FORECAST.
	 */
	private static final String DEMAND_FORECAST="DemandForecast";
	/**
	 * private variable for INVENTORY_ACTUAL_DETAIL.
	 */
	private static final String INVENTORY_ACTUAL_DETAIL="InventoryActualDetail";
	/**
	 * private variable for INVENTORY_ACTUAL_SUMMARY.
	 */
	private static final String INVENTORY_ACTUAL_SUMMARY="InventoryActualSummary";
	/**
	 * private variable for INVENTORY_PROJECTED_DETAIL.
	 */
	private static final String INVENTORY_PROJECTED_DETAIL="InventoryProjectedDetail";
	/**
	 * private variable for INVENTORY_PROJECTED_SUMMARY.
	 */
	private static final String INVENTORY_PROJECTED_SUMMARY="InventoryProjectedSummary";
	/**
	 * private variable for RETURNS_INTERFACE.
	 */
	private static final String RETURNS_INTERFACE="ReturnsInterface";

	/**
	 * private variable for ADJUSTED_DEMAND_ACTUAL_INTERFACE.
	 */
	private static final String ADJUSTED_DEMAND_ACTUAL_INTERFACE="AdjustedDemandActualInterface";
	/**
	 * private variable for ADJUSTED_DEMAND_FORECASTINTERFACE.
	 */
	private static final String ADJUSTED_DEMAND_FORECASTINTERFACE="AdjustedDemandForecastInterface";
	/**
	 * private variable for CUSTOMER_GTS_ACTUAL_INTERFACE.
	 */
	private static final String CUSTOMER_GTS_ACTUAL_INTERFACE="CustomerGtsActualInterface";
	/**
	 * private variable for CUSTOMER_GTS_FORECAST_INTERFACE.
	 */
	private static final String CUSTOMER_GTS_FORECAST_INTERFACE="CustomerGtsForecastInterface";

	/**
	 * private variable for RETURN_RESERVE_INTERFACE.
	 */
	private static final String RETURN_RESERVE_INTERFACE="ReturnReserveInterface";
	/**
	 * private variable for CFF_OUTBOUND_INTERFACE.
	 */
	private static final String CFF_OUTBOUND_INTERFACE="CffOutboundInterface";
	
	/**
	 * private variable for ARP_OUTBOUND_INTERFACE.
	 */
	private static final String ARP_OUTBOUND_INTERFACE="ArpOutboundInterface";
	/**
	 * private variable for ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE.
	 */
	private static final String ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE="AdjustmentGtnDetailInterface";
	/**
	 * private variable for ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE.
	 */
	private static final String ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE="AdjustmentReserveDetailInterface";
	
	
	/**
	 * private variable for LOT MASTER.
	 */
	
	private static final String LOT_MASTER_HIS="LotMasterHistory";
	/**
	 * private variable for Master ETL.
	 */
	private static final String MASTER_ETL="all";
	
	/**
	 * private variable for Master ETL.
	 */
	private static final String MASTER_ETL_HISTORICAL="allHistorical";

	
	


	
	
	
	@RequestMapping(value = "/{apikey}/{path}", method = RequestMethod.GET, produces = "application/json")
	/**
	 * The method used to call each interface based on the api key and method name.
	 * @param apikey
	 * @param interfaceName
	 * @return
	 */
	
	public @ResponseBody
	String home(@PathVariable("apikey") final String apikey,@PathVariable("path") final String interfaceName) throws BPIETLException{
	
		
		try {
		//System.out.println("Enters "+interfaceName);
           // LOGGER.info("The log file has been created."); 
			if (Constants.APP_KEY.equals(apikey)) {
				if (MASTER_ETL.equals(interfaceName)) {
					interfacesName = Constants.MASTER_ETL;
					runAllInterfaces();
				}
				else if (MASTER_ETL_HISTORICAL.equals(interfaceName)) {
					interfacesName = Constants.MASTER_ETL_HISTORY;
					runAllHistoricalInterfaces();
				}
				else if (COMPANY_MASTER.equals(interfaceName)) {
					interfacesName = Constants.COMPANY_MASTER;
				//	LOGGER.info("The Company Master started executing");
					GlobalInterfaces.runCompanyMaster();
				//	LOGGER.info("The Company Master Interface completed successfully");
				}
				else if (COMPANY_HIS.equals(interfaceName)) {
					interfacesName = Constants.COM_MASTER_INTF;
					GlobalInterfaces.runCompanyMasterHistory();
				}
				else if (COMPANY_PARENT.equals(interfaceName)) {
					interfacesName = Constants.COMPANY_PARENT;
					GlobalInterfaces.runCompanyParent();
				}
				else if (COMPANY_PARENT_HIS.equals(interfaceName)) {
					interfacesName = Constants.COMPANY_PARENT_HIS;
					GlobalInterfaces.runCompanyParentHistory();
				}
				else if (COMPANY_TRADE_HIS.equals(interfaceName)) {
					interfacesName = Constants.COMPANY_TRADE_HIS;
					GlobalInterfaces.runCompanyTradeClassHistory();
				}
				else if (COMPANY_TRADE.equals(interfaceName)) {
					interfacesName = Constants.COMPANY_TRADE;
					GlobalInterfaces.runCompanyTradeClass();
				}
				else if (COMPANY_IDEN.equals(interfaceName)) {
					interfacesName = Constants.COMPANY_IDENT;
					GlobalInterfaces.runCompanyIdentifier();
				}  
				else if (COMPANY_IDEN_HIS.equals(interfaceName)) {
					interfacesName = Constants.COMPANY_IDENT;
					GlobalInterfaces.runCompanyIdentifierHistory();
				}
			  else if (ITEM_MASTER.equals(interfaceName)) {
					interfacesName = Constants.ITEM_MASTER;
					GlobalInterfaces.runItemMaster();
				} 
			  else if (ITEM_MASTER_HIS.equals(interfaceName)) {
					interfacesName = Constants.ITEM_MASTER;
					GlobalInterfaces.runItemMasterHistory();
				}
			  else if (ITEM_IDEN.equals(interfaceName)) {
					interfacesName = Constants.ITEM_IDENTIFIER;
					GlobalInterfaces.runItemIdentifier();
				} 
			  else if (ITEM_IDEN_HIS.equals(interfaceName)) {
					interfacesName = Constants.ITEM_IDENTIFIER;
					GlobalInterfaces.runItemIdentifierHistory();
				} 
			  else if (ITEM_PRICING.equals(interfaceName)) {
					interfacesName = Constants.ITEM_PRICING;
					GlobalInterfaces.runItemPricing();
				} 
				else if (ITEM_PRICING_HISTORY.equals(interfaceName)) {
					interfacesName = Constants.ITEM_PRICING;
					GlobalInterfaces.runItemPricingHistory();
				}
				else if (CONTRACT_HEADER.equals(interfaceName)) {
					interfacesName = Constants.CONTRACT_HEADER;
					ContractInterfaces.runContractHeader();
				} 
				else if (CONTRACT_HIS.equals(interfaceName)) {
					interfacesName = Constants.CONT_MAS_INTF;
					ContractInterfaces.runContractMasterHistory();
				}
				else if (CFP.equals(interfaceName)) {
					interfacesName = Constants.CFP;
					ContractInterfaces.runCompanyFamilyPlan();
				}  
				else if (CFP_HISTORY.equals(interfaceName)) {
					interfacesName = Constants.CFP;
					ContractInterfaces.runCompanyFamilyPlanHistory();
				}
				else if (IFP.equals(interfaceName)) {
					interfacesName = Constants.ITEM_FAMILYPLAN;
					ContractInterfaces.runItemFamilyPlan();
				} 
				else if (IFP_HISTORY.equals(interfaceName)) {
					interfacesName = Constants.ITEM_FAMILYPLAN;
					ContractInterfaces.runItemFamilyPlanHistory();
				}
				else if (PRICE_SCHEDULE.equals(interfaceName)) {
					interfacesName = Constants.PRICE_SCHEDULE;
					ContractInterfaces.runPriceSchedule();
				}
				else if (PRICE_SCHEDULE_HISTORY.equals(interfaceName)) {
					interfacesName = Constants.PRICE_SCHEDULE;
					ContractInterfaces.runPriceScheduleHistory();
				}
				
				else if (REBATE_SCHEDULE.equals(interfaceName)) {
					interfacesName = Constants.REBATE_SCHEDULE;
					ContractInterfaces.runRebateSchedule();
				} 
				else if (REBATE_SCHEDULE_HISTORY.equals(interfaceName)) {
					interfacesName = Constants.REBATE_SCHEDULE;
					ContractInterfaces.runRebateScheduleHistory();
				}
				else if (REBATE_PLAN.equals(interfaceName)) {
					interfacesName = Constants.REBATE_PLAN;
					ContractInterfaces.runRebatePlan();
				} 
				else if (REBATE_PLAN_HISTORY.equals(interfaceName)) {
					interfacesName = Constants.REBATE_PLAN;
					ContractInterfaces.runRebatePlanHistory();
				}
				else if (ACTUALS.equals(interfaceName)) {
					interfacesName = Constants.ACTUAL_MASTER;
					TransactionalInterfaces.runActualMaster();
				} else if (ACTUALS_HISTORY.equals(interfaceName)) {
					interfacesName = Constants.ACTUAL_MASTER;
					TransactionalInterfaces.runActualMasterHistory();
					
				}else if (ITEM_HIERARCHY.equals(interfaceName)) {
					interfacesName = Constants.ITEM_HIERARCHY;
					TransactionalInterfaces.runItemHierarchy();
				}
				else if (ITEM_HIERARCHY_HIS.equals(interfaceName)) {
					interfacesName = Constants.ITEM_HIERARCHY;
					TransactionalInterfaces.runItemHierarchyHistory();
				}
				else if (ITEM_HIER_DEF.equals(interfaceName)) {
					interfacesName = Constants.ITEM_HIE_DEF;
					ETLInterfaces.runItemHierarchyDefn();
				}
				else if (ITEM_HIER_DEF_HIS.equals(interfaceName)) {
					interfacesName = Constants.ITEM_HIE_DEF;
					ETLInterfaces.runItemHierarchyDefnHistory();
				}
				else if (SALES_MASTER.equals(interfaceName)) {
					interfacesName = Constants.SALES_MASTER;
					TransactionalInterfaces.runSalesMaster();
				} 
				else if (SALES_MASTER_HISTORY.equals(interfaceName)) {
					interfacesName = Constants.SALES_MASTER;
					TransactionalInterfaces.runSalesMasterHistory();
				}
				else if (FORECAST.equals(interfaceName)) {
					interfacesName = Constants.FORE_MASTER;
					TransactionalInterfaces.runForecastingSales();
				} 
				else if (FORECAST_HIS.equals(interfaceName)) {
					interfacesName = Constants.FORE_MASTER;
					TransactionalInterfaces.runForecastingSalesHistory();
				}
				else if (GL_BALANCE.equals(interfaceName)) {
					interfacesName = Constants.GL_BALANCE;
					TransactionalInterfaces.runGLBalance();
				} 
				else if (GL_BALANCE_HISTORY.equals(interfaceName)) {
					interfacesName = Constants.GL_BALANCE;
					TransactionalInterfaces.runGLBalanceHistory();
				}
				else if (CPI_INDEX.equals(interfaceName)) {
					interfacesName = Constants.CPI_INDEX;
					TransactionalInterfaces.runCPIIndex();
				}
				else if (CPI_INDEX_HIS.equals(interfaceName)) {
					interfacesName = Constants.CPI_INDEX;
					TransactionalInterfaces.runCPIIndexHistory();
				}
				else if (FORMULA_DET.equals(interfaceName)) {
					interfacesName = Constants.FORMULA_DETAILS;
					TransactionalInterfaces.runFormulaDetails();
				} 
				else if (FORMULA_DET_HIS.equals(interfaceName)) {
					interfacesName = Constants.FORMULA_DET_INTF;
					TransactionalInterfaces.runFormulaDetailsHistory();
				}
				else if (MASTER_DATA_ATT.equals(interfaceName)) {
					interfacesName = Constants.MASTER_DATA;
					TransactionalInterfaces.runMasterDataAttrb();
				} 
				else if (MASTER_DATA_ATT_HISTORY.equals(interfaceName)) {
					interfacesName = Constants.MASTER_DATA;
					TransactionalInterfaces.runMasterDataAttrbHistory();
				}
				else if (BEST_PRICE.equals(interfaceName)) {
					interfacesName = Constants.BEST_PRICE;
					ETLInterfaces.runBestPrice();
				}
				else if (BEST_PRICE_HIS.equals(interfaceName)) {
					interfacesName = Constants.BEST_PRICE;
					ETLInterfaces.runBestPriceHistory();
				}
				else if (AVG_SHELF_LIFE.equals(interfaceName)) {
					interfacesName = Constants.AVERAGE_SHELF;
					ETLInterfaces.runAverageShelfLife();
				}
				else if (AVG_SHELF_LIFE_HIS.equals(interfaceName)) {
					interfacesName = Constants.AVERAGE_SHELF;
					ETLInterfaces.runAverageShelfLifeHistory();
				}
				else if (GL_COST.equals(interfaceName)) {
					interfacesName = Constants.GL_COST_CENTER;
					ETLInterfaces.runGLCostCenter();
				}
				else if (GL_COST_HIS.equals(interfaceName)) {
					interfacesName = Constants.GL_COST_CENTER;
					ETLInterfaces.runGLCostCenterHistory();
				}
				else if (LOT_MASTER.equals(interfaceName)) {
					interfacesName = Constants.LOT_MASTER;
					ETLInterfaces.runLotMaster();
				}
				else if (LOT_MASTER_HIS.equals(interfaceName)) {
					interfacesName = Constants.LOT_MASTER;
					ETLInterfaces.runLotMasterHistory();
				}
				else if (ACCRUAL_MASTER.equals(interfaceName)) {
					interfacesName = Constants.ACCRUAL_MASTER;
					ETLInterfaces.runAccrualMaster();
				}
				else if (ACCRUAL_OUTBOUND.equals(interfaceName)) {
					interfacesName = Constants.ACCRUAL_OUTBOUND;
					ETLInterfaces.runAccrualOutbound();
				}
				else if (GL_POSTING.equals(interfaceName)) {
					interfacesName = Constants.GL_POSTING;
					ETLInterfaces.runGL_Posting();
				}
				else if (DEMAND_ACTUAL.equals(interfaceName)) {
					interfacesName = Constants.DEMAND_ACTUAL;
					ETLInterfaces.runDemandActual();
				}
				else if (DEMAND_FORECAST.equals(interfaceName)) {
					interfacesName = Constants.DEMAND_FORECAST;
					ETLInterfaces.runDemandForecast();
				}
				else if (INVENTORY_ACTUAL_DETAIL.equals(interfaceName)) {
					interfacesName = Constants.INVENTORY_ACTUAL_DETAIL;
					ETLInterfaces.runInventoryActualDetail();
				}
				else if (INVENTORY_ACTUAL_SUMMARY.equals(interfaceName)) {
					interfacesName = Constants.INVENTORY_ACTUAL_SUMMARY;
					ETLInterfaces.runInventoryActualSummary();
				}
				else if (INVENTORY_PROJECTED_DETAIL.equals(interfaceName)) {
					interfacesName = Constants.INVENTORY_PROJECTED_DETAIL;
					ETLInterfaces.runInventoryProjectedDetail();
				}
				else if (INVENTORY_PROJECTED_SUMMARY.equals(interfaceName)) {
					interfacesName = Constants.INVENTORY_PROJECTED_SUMMARY;
					ETLInterfaces.runInventoryProjectedSummary();
				}
				else if (RETURNS_INTERFACE.equals(interfaceName)) {
					interfacesName = Constants.RETURNS_INTERFACE;
					ETLInterfaces.runReturns();
				}
				
				else if (ADJUSTED_DEMAND_ACTUAL_INTERFACE.equals(interfaceName)) {
					interfacesName = Constants.ADJUSTED_DEMAND_ACTUAL_INTERFACE;
					ETLInterfaces.runAdjustedDemandActualInterface();
				}
				else if (ADJUSTED_DEMAND_FORECASTINTERFACE.equals(interfaceName)) {
					interfacesName = Constants.ADJUSTED_DEMAND_FORECASTINTERFACE;
					ETLInterfaces.runAdjustedDemandForecastInterface();
				}
		     	else if (CUSTOMER_GTS_ACTUAL_INTERFACE.equals(interfaceName)) {
				    interfacesName = Constants.CUSTOMER_GTS_ACTUAL_INTERFACE;
					ETLInterfaces.runCustomerGtsActualInterface();
		     	}
		     	else if (CUSTOMER_GTS_FORECAST_INTERFACE.equals(interfaceName)) {
					interfacesName = Constants.CUSTOMER_GTS_FORECAST_INTERFACE;
					ETLInterfaces.runCustomerGtsForecastInterface();
								
				}
		     	else if (RETURN_RESERVE_INTERFACE.equals(interfaceName)) {
					interfacesName = Constants.RETURN_RESERVE_INTERFACE;
					ETLInterfaces.runReturnReserveInterface();
								
				}
		     	else if (CFF_OUTBOUND_INTERFACE.equals(interfaceName)) {
					interfacesName = Constants.CFF_OUTBOUND_INTERFACE;
					ETLInterfaces.runCffOutboundInterface();
		    	}
		     	else if (ARP_OUTBOUND_INTERFACE.equals(interfaceName)) {
					interfacesName = Constants.ARP_OUTBOUND_INTERFACE;
					ETLInterfaces.runArpOutboundInterface();
				
				}
		     	else if (ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE.equals(interfaceName)) {
					interfacesName = Constants.ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE;
					ETLInterfaces.runAdjustmentGtnDetailInterface();
								
				}
		     	else if (ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE.equals(interfaceName)) {
					interfacesName = Constants.ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE;
					ETLInterfaces.runAdjustmentReserveDetailInterface();
								
				}
				CommonUtils.createlog();
			//	LOGGER.info("The log file has been created.");
				CommonUtils.cleartemp();
			//	LOGGER.info("The temp has been cleared");
				return Constants.SUCCESS;
			} else {
				CommonUtils.createlog();
				CommonUtils.cleartemp();
				return APIKEYERROR;
			}
		} catch(BPIETLException ex) {

//			LOGGER.error(ex.getMessage());
			
			LOGGER.info("Ends "+interfacesName.toLowerCase().replaceAll("_", " "));
            System.out.println("Ends Interface "+interfacesName.toLowerCase().replaceAll("_", " "));
			
			CommonUtils.createlog();
			CommonUtils.createErrorlog();
		//	LOGGER.info("The log file has been created.");
			CommonUtils.cleartemp();
//            LOGGER.error("There were errors during job execution.");
            
			
			//LOGGER.info("The temp has been cleared");
			//CommonUtils.createlog();
			//LOGGER.info("The log file has been created.");s
			//CommonUtils.sendmail(interfacesName);
			
			//CommonUtils.cleartemp();
			return Constants.FAIL;
		}
		
	}

	
/**
 * This method is used to invoke all the interface in the mentioned sequence.
 * @return SUCCESS
 */
	public String runAllInterfaces() throws BPIETLException{
		
		GlobalInterfaces.runCompanyMaster();
		GlobalInterfaces.runCompanyParent();
		GlobalInterfaces.runCompanyTradeClass();
		GlobalInterfaces.runCompanyIdentifier();
		GlobalInterfaces.runItemMaster();
		GlobalInterfaces.runItemIdentifier();
		GlobalInterfaces.runItemPricing();
		ContractInterfaces.runContractHeader();
		ContractInterfaces.runCompanyFamilyPlan();
		ContractInterfaces.runItemFamilyPlan();
		ContractInterfaces.runPriceSchedule();
		ContractInterfaces.runRebatePlan();
		ContractInterfaces.runRebateSchedule();
		TransactionalInterfaces.runActualMaster();
		TransactionalInterfaces.runSalesMaster();
		TransactionalInterfaces.runItemHierarchy();
		ETLInterfaces.runItemHierarchyDefn();
		TransactionalInterfaces.runForecastingSales();
		TransactionalInterfaces.runGLBalance();
		TransactionalInterfaces.runCPIIndex();
		TransactionalInterfaces.runFormulaDetails();
		TransactionalInterfaces.runMasterDataAttrb();
		ETLInterfaces.runBestPrice();
		ETLInterfaces.runAverageShelfLife();
		ETLInterfaces.runGLCostCenter();
		ETLInterfaces.runLotMaster();
		return Constants.SUCCESS;
		
	}
	/**
	 * This method is used to invoke all the interface with HIstorical in the mentioned sequence.
	 * @return SUCCESS
	 */
		public String runAllHistoricalInterfaces() throws BPIETLException{
			
			GlobalInterfaces.runCompanyMasterHistory();
			GlobalInterfaces.runCompanyParentHistory();
			GlobalInterfaces.runCompanyTradeClassHistory();
			GlobalInterfaces.runCompanyIdentifierHistory();
			GlobalInterfaces.runItemMasterHistory();
			GlobalInterfaces.runItemIdentifierHistory();
			GlobalInterfaces.runItemPricingHistory();
			ContractInterfaces.runContractMasterHistory();
			ContractInterfaces.runCompanyFamilyPlanHistory();
			ContractInterfaces.runItemFamilyPlanHistory();
			ContractInterfaces.runPriceScheduleHistory();
			ContractInterfaces.runRebatePlanHistory();
			ContractInterfaces.runRebateScheduleHistory();
			TransactionalInterfaces.runActualMasterHistory();
			TransactionalInterfaces.runSalesMasterHistory();
			TransactionalInterfaces.runItemHierarchyHistory();
			ETLInterfaces.runItemHierarchyDefnHistory();
			TransactionalInterfaces.runForecastingSalesHistory();
			TransactionalInterfaces.runGLBalanceHistory();
			TransactionalInterfaces.runCPIIndexHistory();
			TransactionalInterfaces.runFormulaDetailsHistory();
			TransactionalInterfaces.runMasterDataAttrbHistory();
			ETLInterfaces.runBestPriceHistory();
			ETLInterfaces.runAverageShelfLifeHistory();
			ETLInterfaces.runGLCostCenterHistory();
			ETLInterfaces.runLotMasterHistory();
			return Constants.SUCCESS;
			
		}
	@RequestMapping(value = "/{apikey}/Encrypt/{pass}", method = RequestMethod.GET, produces = "application/json")
	/**
	 * This method is used to encrypt the password for email and DB credentials.
	 * @param apikey
	 * @param pass 
	 * @return
	 */
	public @ResponseBody
	String encrypt(@PathVariable("apikey") final String apikey,
			@PathVariable("pass") final String pass) throws BPIETLException{
		try {
			if (Constants.APP_KEY.equals(apikey)) {
			final String encrypt = Encr
						.encryptPassword(pass);
				return encrypt;
			} else{
				return APIKEYERROR;
			}
		}
		catch(ClassDefinitionException ex){
			throw new BPIETLException(ex);	
		}
		
	catch(NoClassDefFoundError ex){
		throw new BPIETLException(ex);
	}
		
		finally {
			CommonUtils.createlog();
			CommonUtils.cleartemp();
		}
	}

}
