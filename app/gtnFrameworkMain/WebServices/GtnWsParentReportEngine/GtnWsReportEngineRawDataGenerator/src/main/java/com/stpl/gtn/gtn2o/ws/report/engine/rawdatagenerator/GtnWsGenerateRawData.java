package com.stpl.gtn.gtn2o.ws.report.engine.rawdatagenerator;

import java.util.List;

import com.stpl.gtn.gtn20.ws.report.engine.hibernate.GtnWsCustomSqlClass;

public class GtnWsGenerateRawData {

	private static GtnWsGenerateRawData RAW_DATA_INSTANCE = null;

	private GtnWsGenerateRawData() {
		super();
	}

	public static GtnWsGenerateRawData getInstance() {
		if (RAW_DATA_INSTANCE == null) {
			RAW_DATA_INSTANCE = new GtnWsGenerateRawData();
		}
		return RAW_DATA_INSTANCE;
	}

	public static final GtnWsCustomSqlClass SQL_INSTANCE = GtnWsCustomSqlClass.getInstance();

	public List<Object[]> generateRawData(int start, int offset) {
		String query = "IF Object_id('TEMPDB..#tempSales') IS NOT NULL\n" + "DROP TABLE #tempSales\n" + "\n"
				+ "SELECT \n"
				+ "CCP_DETAILS_SID,PROJECTION_MASTER_SID,PERIOD_SID,PROJECTION_SALES,PROJECTION_UNITS, ACTUAL_SALES,ACTUAL_UNITS \n"
				+ "INTO #tempSales\n" + "FROM \n" + "(\n" + "SELECT OUT_P.CCP_DETAILS_SID_ORGI, \n"
				+ "                         WM.PROJECTION_MASTER_SID, \n"
				+ "                         OUT_P.PROJECTION_DETAILS_SID, \n"
				+ "                         OUT_P.CCP_DETAILS_SID, \n"
				+ "                         OUT_P.FORECASTING_TYPE, \n"
				+ "                                          PROJECTION_SALES,\n"
				+ "                                         PROJECTION_UNITS,\n"
				+ "                                         PERIOD_SID,\n"
				+ "                                         ACTUAL_SALES,\n"
				+ "                                         ACTUAL_UNITS,\n"
				+ "                         ROW_NUMBER() \n" + "                           OVER ( \n"
				+ "                             PARTITION BY CCP_DETAILS_SID_ORGI, OUT_P.CCP_DETAILS_SID ,PERIOD_SID\n"
				+ "                             ORDER BY WM.MODIFIED_DATE DESC ) RNO \n"
				+ "                  FROM   WORKFLOW_MASTER WM \n"
				+ "                         INNER JOIN (SELECT PD.CCP_DETAILS_SID AS CCP_DETAILS_SID_ORGI, \n"
				+ "                                            PM.PROJECTION_MASTER_SID, \n"
				+ "                                            PD.PROJECTION_DETAILS_SID, \n"
				+ "                                            PD.CCP_DETAILS_SID,\n"
				+ "                                            PM.FORECASTING_TYPE ,\n"
				+ "                                            NP.PROJECTION_SALES,\n"
				+ "                                            NP.PROJECTION_UNITS,\n"
				+ "                                            NA.ACTUAL_SALES,\n"
				+ "                                            NA.ACTUAL_UNITS,\n"
				+ "                                            P.PERIOD_SID\n"
				+ "                                     FROM  PROJECTION_DETAILS PD \n"
				+ "                                            INNER JOIN PROJECTION_MASTER PM \n"
				+ "                                                    ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID\n"
				+ "                                                                           INNER JOIN NM_SALES_PROJECTION NP\n"
				+ "                                                                           ON NP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
				+ "                                                                           JOIN PERIOD P ON P.PERIOD_SID=NP.PERIOD_SID\n"
				+ "                                                                           FULL OUTER JOIN NM_ACTUAL_SALES NA ON NA.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
				+ "                                                                           AND P.PERIOD_SID=NA.PERIOD_SID\n"
				+ "                                     WHERE  PM.SAVE_FLAG = 1) OUT_P \n"
				+ "                                 ON WM.PROJECTION_MASTER_SID = OUT_P.PROJECTION_MASTER_SID \n"
				+ "                                                        WHERE EXISTS (SELECT HELPER_TABLE_SID\n"
				+ "                                                                              FROM   HELPER_TABLE H1\n"
				+ "                                                                              WHERE  LIST_NAME = 'WORKFLOWSTATUS'\n"
				+ "                                                                                     AND DESCRIPTION = 'APPROVED'\n"
				+ "                                                                                     AND H1.HELPER_TABLE_SID=WM.WORKFLOW_STATUS_ID))V\n"
				+ "                                                        WHERE  RNO = 1 \n"
				+ "IF Object_id('TEMPDB..#tempDiscount') IS NOT NULL\n"
				+ "DROP TABLE #tempDiscount                                                                                                                                             \n"
				+ "SELECT \n"
				+ "CCP_DETAILS_SID,PROJECTION_MASTER_SID,PERIOD_SID,PROJECTION_SALES, ACTUAL_SALES,RS_CONTRACT_SID\n"
				+ "into #tempDiscount\n" + "FROM \n" + "(\n" + "SELECT OUT_P.CCP_DETAILS_SID_ORGI, \n"
				+ "                         WM.PROJECTION_MASTER_SID, \n"
				+ "                         OUT_P.PROJECTION_DETAILS_SID, \n"
				+ "                         OUT_P.CCP_DETAILS_SID, \n"
				+ "                         OUT_P.FORECASTING_TYPE, \n" + "                         PROJECTION_SALES,\n"
				+ "                         PERIOD_SID,\n" + "                         ACTUAL_SALES,\n"
				+ "                         RS_CONTRACT_SID,\n" + "                         ROW_NUMBER() \n"
				+ "                           OVER ( \n"
				+ "                             PARTITION BY CCP_DETAILS_SID_ORGI, OUT_P.CCP_DETAILS_SID ,RS_CONTRACT_SID,PERIOD_SID\n"
				+ "                             ORDER BY WM.MODIFIED_DATE DESC ) RNO \n"
				+ "                  FROM   WORKFLOW_MASTER WM \n"
				+ "                         INNER JOIN (SELECT PD.CCP_DETAILS_SID AS CCP_DETAILS_SID_ORGI, \n"
				+ "                                            PM.PROJECTION_MASTER_SID, \n"
				+ "                                            PD.PROJECTION_DETAILS_SID, \n"
				+ "                                            PD.CCP_DETAILS_SID,\n"
				+ "                                            PM.FORECASTING_TYPE ,\n"
				+ "                                            NP.PROJECTION_SALES,\n"
				+ "                                            NA.ACTUAL_SALES,\n"
				+ "                                            ISNULL( NA.RS_CONTRACT_SID,NP.RS_CONTRACT_SID)  RS_CONTRACT_SID,\n"
				+ "                                            P.PERIOD_SID\n"
				+ "                                     FROM  PROJECTION_DETAILS PD \n"
				+ "                                                    \n"
				+ "                                            INNER JOIN PROJECTION_MASTER PM \n"
				+ "                                                    ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID\n"
				+ "                                                                           INNER JOIN NM_DISCOUNT_PROJECTION NP\n"
				+ "                                                                           ON NP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
				+ "                                                                           JOIN PERIOD P ON P.PERIOD_SID=NP.PERIOD_SID\n"
				+ "                                                                           FULL OUTER JOIN NM_ACTUAL_DISCOUNT NA ON NA.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID\n"
				+ "                                                                           AND P.PERIOD_SID=NA.PERIOD_SID AND NA.RS_CONTRACT_SID=NP.RS_CONTRACT_SID\n"
				+ "                                     WHERE  PM.SAVE_FLAG = 1) OUT_P \n"
				+ "                                 ON WM.PROJECTION_MASTER_SID = OUT_P.PROJECTION_MASTER_SID \n"
				+ "                                                        WHERE EXISTS (SELECT HELPER_TABLE_SID\n"
				+ "                                                                              FROM   HELPER_TABLE H1\n"
				+ "                                                                              WHERE  LIST_NAME = 'WORKFLOWSTATUS'\n"
				+ "                                                                                     AND DESCRIPTION = 'APPROVED'\n"
				+ "                                                                                     AND H1.HELPER_TABLE_SID=WM.WORKFLOW_STATUS_ID))V\n"
				+ "														WHERE  RNO = 1 \n" + "\n" + "\n" + "Select \n"
				+ "sales.CCP_DETAILS_SID AS CCP_DETAILS, \n" + "sales.PERIOD_SID AS PERIOD, \n"
				+ "RS_CONTRACT_SID AS RS_CONTRACT,\n" + "sales.ACTUAL_SALES AS SALES_ACTUAL, \n"
				+ "sales.ACTUAL_UNITS AS UNITS_ACTUAL, \n" + "sales.PROJECTION_SALES AS PROJECTED_SALES, \n"
				+ "sales.PROJECTION_UNITS AS PROJECTED_UNITS,\n" + "discount.ACTUAL_SALES AS DISCOUNT_ACTUAL,\n"
				+ "discount.PROJECTION_SALES AS DISCOUNT_PROJECTED\n" + ",P.QUARTER AS QUARTER\n"
				+ ",P.SEMI_ANNUAL AS SEMI_ANNUAL\n" + ",P.YEAR AS YEAR \n" + ",1000 AS EXFACTORY_ACTUAL\n"
				+ ",1000 AS EXFACTORY_PROJECTION\n" + "from #tempSales sales\n"
				+ "LEFT JOIN #tempDiscount discount on sales.CCP_DETAILS_SID= discount.CCP_DETAILS_SID\n"
				+ "and sales.PERIOD_SID = discount.PERIOD_SID\n"
				+ "Join PERIOD P ON P.PERIOD_SID = sales.PERIOD_SID ORDER BY sales.CCP_DETAILS_SID,P.PERIOD_SID,P.YEAR, P.QUARTER";
		List result = SQL_INSTANCE.executeQuery(query);
		System.out.println(" List zise = = = " + result.size());
		return result;
	}
}
