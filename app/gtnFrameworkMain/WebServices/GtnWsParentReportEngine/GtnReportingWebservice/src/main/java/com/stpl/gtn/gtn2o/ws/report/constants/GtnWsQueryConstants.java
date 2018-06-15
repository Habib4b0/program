package com.stpl.gtn.gtn2o.ws.report.constants;

public class GtnWsQueryConstants {

	private GtnWsQueryConstants() {
		throw new IllegalStateException("Constant class");
	}

	public static final String DATA_ASSUMPTIONS_COUNT_QUERY = "select count(*) from(select	FORECAST_NAME as FORECAST_NAME,	company.COMPANY_NAME as COMPANY_NAME ,	businessunit.COMPANY_NAME as BUSINESS_UNIT,	ht.DESCRIPTION as FILE_TYPE,	VERSION,	FROM_PERIOD as ACTIVE_FROM,	FROM_PERIOD,	TO_PERIOD		from	FILE_MANAGEMENT	inner join COMPANY_MASTER company ON company.COMPANY_MASTER_SID = COMPANY inner join COMPANY_MASTER businessunit ON businessunit.COMPANY_MASTER_SID= BUSINESS_UNIT	inner join HELPER_TABLE ht on ht.HELPER_TABLE_SID=FILE_TYPE where	TO_PERIOD is null) A";

	public static final String DATA_ASSUMPTIONS_RESULT_QUERY = "select	FORECAST_NAME as FORECAST_NAME,	company.COMPANY_NAME as COMPANY_NAME ,	businessunit.COMPANY_NAME as BUSINESS_UNIT,	ht.DESCRIPTION as FILE_TYPE,	VERSION,	FROM_PERIOD as ACTIVE_FROM,	FROM_PERIOD,	TO_PERIOD		from	FILE_MANAGEMENT	inner join COMPANY_MASTER company ON company.COMPANY_MASTER_SID = COMPANY inner join COMPANY_MASTER businessunit ON businessunit.COMPANY_MASTER_SID= BUSINESS_UNIT	inner join HELPER_TABLE ht on ht.HELPER_TABLE_SID=FILE_TYPE where	TO_PERIOD is null @filter";

	public static final String HIERARCHY_SID_AND_LEVEL_DEFINITION_SID = "SELECT distinct LEVEL_NO, LEVEL_VALUE_REFERENCE, TABLE_NAME, FIELD_NAME, LEVEL_NAME, HLD.HIERARCHY_LEVEL_DEFINITION_SID, HLD.HIERARCHY_DEFINITION_SID, HT.DESCRIPTION from HIERARCHY_LEVEL_DEFINITION HLD JOIN dbo.HIERARCHY_DEFINITION HD on HD.HIERARCHY_DEFINITION_SID = hld.HIERARCHY_DEFINITION_SID JOIN dbo.HELPER_TABLE HT on HT.HELPER_TABLE_SID = HD.HIERARCHY_CATEGORY where hld.HIERARCHY_DEFINITION_SID =@HIERARCHY_DEFINITION_SID and HLD.VERSION_NO =@VERSION_NO ";

	public static final String MAIN_QUERY_REPORT_FROM_AND_TO_DATE = ";WITH cte AS "
			+ "(SELECT TOP 1 FROM_DATE AS FROM_DATE, TO_DATE AS  TO_DATE FROM   forecast_config f JOIN HELPER_TABLE h ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE' AND DESCRIPTION = 'Commercial' ORDER BY VERSION_NO DESC) SELECT    DESCRIPTION  FROM   forecast_config f JOIN HELPER_TABLE h ON f.HIST_FREQ = h.HELPER_TABLE_SID WHERE  FROM_DATE=(SELECT FROM_DATE FROM   cte c)";

	public static final String SUB_QUERY_REPORT_FROM_DATE_MONTH = ";WITH cte AS (SELECT TOP 1 FROM_DATE AS FROM_DATE, TO_DATE AS  TO_DATE FROM   forecast_config f JOIN HELPER_TABLE h ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE' AND DESCRIPTION = 'Commercial' ORDER BY VERSION_NO DESC) SELECT Min(period_sid) AS PERIOD_SID, CONCAT(SUBSTRING(DATENAME(mm,PERIOD_DATE),1,3),' ',YEAR) FROM   PERIOD p WHERE  EXISTS (SELECT 1 FROM   cte c  WHERE  p.PERIOD_DATE BETWEEN DATEADD(qq, DATEDIFF(qq, 0, FROM_DATE), 0) AND TO_DATE) GROUP  BY YEAR,PERIOD_DATE ORDER  BY YEAR,PERIOD_DATE";

	public static final String SUB_QUERY_REPORT_FROM_DATE_QUARTER = ";WITH cte AS (SELECT TOP 1 FROM_DATE AS FROM_DATE, TO_DATE   TO_DATE FROM   forecast_config f JOIN HELPER_TABLE h ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE' AND DESCRIPTION = 'Commercial' ORDER  BY VERSION_NO DESC) SELECT Min(period_sid) AS PERIOD_SID, Concat('Q', QUARTER, ' - ', YEAR) FROM   PERIOD p WHERE  EXISTS (SELECT 1 FROM   cte c WHERE  p.PERIOD_DATE BETWEEN DATEADD(qq, DATEDIFF(qq, 0, FROM_DATE), 0) AND TO_DATE) GROUP  BY Concat('Q', QUARTER, ' - ', YEAR), year, QUARTER ORDER  BY YEAR, QUARTER, Concat('Q', QUARTER, ' - ', YEAR) ";

	public static final String SUB_QUERY_REPORT_FROM_DATE_SEMI_ANNUAL = ";WITH cte AS (SELECT TOP 1 FROM_DATE AS FROM_DATE, TO_DATE AS  TO_DATE FROM   forecast_config f JOIN HELPER_TABLE h ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE' AND DESCRIPTION = 'Commercial' ORDER BY VERSION_NO DESC) SELECT Min(period_sid) AS PERIOD_SID, CONCAT('S',SEMI_ANNUAL,'-',YEAR) FROM   PERIOD p WHERE  EXISTS (SELECT 1 FROM   cte c WHERE  p.PERIOD_DATE BETWEEN DATEADD(qq, DATEDIFF(qq, 0, FROM_DATE), 0) AND TO_DATE) GROUP  BY YEAR,SEMI_ANNUAL ORDER  BY YEAR,SEMI_ANNUAL";

	public static final String SUB_QUERY_REPORT_FROM_DATE_YEAR = ";WITH cte AS (SELECT TOP 1 FROM_DATE AS FROM_DATE, TO_DATE   TO_DATE FROM   forecast_config f JOIN HELPER_TABLE h ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE' AND DESCRIPTION = 'Commercial' ORDER  BY VERSION_NO DESC) SELECT Min(period_sid) AS PERIOD_SID,YEAR FROM   PERIOD p WHERE  EXISTS (SELECT 1 FROM   cte c WHERE  p.PERIOD_DATE BETWEEN DATEADD(qq, DATEDIFF(qq, 0, FROM_DATE), 0) AND TO_DATE) GROUP  BY YEAR ORDER  BY YEAR";

	public static final String SUB_QUERY_REPORT_TO_DATE_MONTH = "     	;WITH cte     AS (SELECT TOP 1 FROM_DATE AS FROM_DATE,               TO_DATE AS  TO_DATE  		FROM   forecast_config f         		JOIN HELPER_TABLE h           			ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID  		WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE'        	   AND DESCRIPTION = 'Commercial'  		ORDER BY VERSION_NO DESC) 	SELECT TOP 1 Min(period_sid) AS PERIOD_SID,      	CONCAT(SUBSTRING(DATENAME(mm,PERIOD_DATE),1,3),' ',YEAR) 	FROM   PERIOD p 	WHERE  EXISTS (SELECT 1                FROM   cte c                WHERE  p.PERIOD_DATE BETWEEN DATEADD(qq, DATEDIFF(qq, 0, FROM_DATE), 0) AND TO_DATE) 	GROUP  BY YEAR,PERIOD_DATE 	ORDER  BY PERIOD_SID DESC";

	public static final String SUB_QUERY_REPORT_TO_DATE_QUARTER = ";WITH cte AS (SELECT TOP 1 FROM_DATE AS FROM_DATE, TO_DATE   TO_DATE FROM   forecast_config f JOIN HELPER_TABLE h ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE' AND DESCRIPTION = 'Commercial' ORDER  BY VERSION_NO DESC) SELECT TOP 1 Min(period_sid) AS PERIOD_SID, Concat('Q', QUARTER, ' - ', YEAR) FROM   PERIOD p WHERE  EXISTS (SELECT 1 FROM   cte c WHERE  p.PERIOD_DATE BETWEEN DATEADD(qq, DATEDIFF(qq, 0, FROM_DATE), 0) AND TO_DATE) GROUP  BY Concat('Q', QUARTER, ' - ', YEAR), year, QUARTER ORDER  BY PERIOD_SID DESC ";

	public static final String SUB_QUERY_REPORT_TO_DATE_SEMI_ANNUAL = "          	;WITH cte     AS (SELECT TOP 1 FROM_DATE AS FROM_DATE,               TO_DATE AS  TO_DATE  		FROM   forecast_config f         		JOIN HELPER_TABLE h           			ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID  		WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE'        	   AND DESCRIPTION = 'Commercial'  		ORDER BY VERSION_NO DESC) 	SELECT TOP 1 Min(period_sid) AS PERIOD_SID,      	CONCAT('S',SEMI_ANNUAL,'-',YEAR) 	FROM   PERIOD p 	WHERE  EXISTS (SELECT 1                FROM   cte c                WHERE  p.PERIOD_DATE BETWEEN DATEADD(qq, DATEDIFF(qq, 0, FROM_DATE), 0) AND TO_DATE) 	GROUP  BY YEAR,SEMI_ANNUAL 	ORDER  BY PERIOD_SID DESC";

	public static final String SUB_QUERY_REPORT_TO_DATE_YEAR = ";WITH cte     AS (SELECT TOP 1 FROM_DATE AS FROM_DATE,                      TO_DATE   TO_DATE         FROM   forecast_config f                JOIN HELPER_TABLE h                  ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID         WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE'                AND DESCRIPTION = 'Commercial'         ORDER  BY VERSION_NO DESC)	SELECT TOP 1 Min(period_sid) AS PERIOD_SID,YEAR	FROM   PERIOD p	WHERE  EXISTS (SELECT 1	               FROM   cte c	               WHERE  p.PERIOD_DATE BETWEEN DATEADD(qq, DATEDIFF(qq, 0, FROM_DATE), 0) AND TO_DATE)	GROUP  BY YEAR	ORDER  BY PERIOD_SID DESC";

	public static final String EXCEPTION_IN = "Exception in ";

	public static final String CONSTANT_STRING = "String";

	public static final String CONSTANT_DATE = "Date";

	public static final String UNDERSCORE = "_";

	public static final String REPORTING = "/Reporting/";

	public static final String DATA_ASSUMPTIONS_MULTIPLE_TABS_RESULTS = "select FORECAST_NAME as FORECAST_NAME, company.COMPANY_NAME as COMPANY_NAME, businessunit.COMPANY_NAME as BUSINESS_UNIT, ht.DESCRIPTION as FILE_TYPE, VERSION, FROM_PERIOD as ACTIVE_FROM, FROM_PERIOD, TO_PERIOD from FILE_MANAGEMENT FT inner join COMPANY_MASTER company ON company.COMPANY_MASTER_SID = FT.COMPANY inner join COMPANY_MASTER businessunit ON businessunit.COMPANY_MASTER_SID = FT.BUSINESS_UNIT inner join HELPER_TABLE ht on ht.HELPER_TABLE_SID = FT.FILE_TYPE inner join PROJECTION_FILE_DETAILS pfd ON pfd.FILE_MANAGEMENT_SID=FT.FILE_MANAGEMENT_SID where pfd.PROJECTION_MASTER_SID like '@projectionMasterSid' union ( select FORECAST_NAME as FORECAST_NAME, company.COMPANY_NAME as COMPANY_NAME, businessunit.COMPANY_NAME as BUSINESS_UNIT, ht.DESCRIPTION as FILE_TYPE, VERSION, FROM_PERIOD as ACTIVE_FROM, FROM_PERIOD, TO_PERIOD from FILE_MANAGEMENT inner join COMPANY_MASTER company ON company.COMPANY_MASTER_SID = COMPANY inner join COMPANY_MASTER businessunit ON businessunit.COMPANY_MASTER_SID = BUSINESS_UNIT inner join HELPER_TABLE ht on ht.HELPER_TABLE_SID = FILE_TYPE where TO_PERIOD is null) ";

	public static final String PRC_CUSTOM_CCPDV_POPULATION = " PRC_CUSTOM_CCPDV_POPULATION ? , ? , ? ";

	public static final String PRC_REPORT_DATA_POPULATION = " PRC_REPORTING_DASHBOARD ? , ? , ? , ? , ? , ? , ? , ? ";
        
        public static final String VARIABLE_BREAKDOWN_PERIOD_DATAS = "	select * from ( select row_number() over (partition by @frequency Year order by year asc) rowno, PERIOD_SID,YEAR from Period where @periodDateCondition )A where rowno=1 order by year asc";
        
        public static final String COMPARISON_BREAKDOWN_PERIOD_DATAS = "	select * from ( select row_number() over (partition by @frequency Year order by year asc) rowno, PERIOD_SID,YEAR from Period where @periodDateCondition )A where rowno=1 order by year asc";

        public static final String VARIABLE_BREAKDOWN_SAVE_SERVICE_QUERY = "INSERT INTO VARIABLE_BR_REPORT (MASTER_SID,PERIOD,YEAR,SELECTED_VARIABLE) VALUES (?,?,?,?)";
        
        public static final String COMPARISON_BREAKDOWN_SAVE_SERVICE_QUERY = "INSERT INTO COMPARISON_REPORT (MASTER_SID,PERIOD,YEAR,SELECTED_VARIABLE) VALUES (?,?,?,?)";
        
    	public static final String PRC_TEMP_TABLE_CREATION = " PRC_TEMP_TABLE_CREATION ? ? ? ";

    	public static final String PRC_TEMP_TABLE_LIST = "CCP_HIERARCHY,CUSTOM_CCP_SALES,CUSTOM_VARIABLE_HIERARCHY,CUSTOM_DISCOUNT_REPORT,CUSTOM_SALES_REPORT,APPROVED_REPORT,VARIABLE_BR_REPORT,COMPARISION_REPORT";

    	public static final String CUSTOM_VARIABLE_HIERARCHY = "ST_CUSTOM_VARIABLE_HIERARCHY";
    	

}
