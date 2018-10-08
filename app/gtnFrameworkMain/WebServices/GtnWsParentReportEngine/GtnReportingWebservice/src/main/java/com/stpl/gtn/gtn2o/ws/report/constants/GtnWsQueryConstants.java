package com.stpl.gtn.gtn2o.ws.report.constants;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class GtnWsQueryConstants {

	private GtnWsQueryConstants() {
		throw new IllegalStateException("Constant class");
	}

	public static final String DATA_ASSUMPTIONS_COUNT_QUERY = "SELECT COUNT(*) FROM (select	FORECAST_NAME as FORECAST_NAME,	company.COMPANY_NAME as COMPANY_NAME ,	businessunit.COMPANY_NAME as BUSINESS_UNIT,	ht.DESCRIPTION as FILE_TYPE,	VERSION,	FROM_PERIOD = FORMAT(FROM_PERIOD , 'MM/dd/yyyy HH:mm:ss'), TO_PERIOD = FORMAT(TO_PERIOD , 'MM/dd/yyyy HH:mm:ss') ,	case when File_management.TO_PERIOD is null then 'Yes' else 'No' end ACTIVE_FILE 	"
			+ "from	FILE_MANAGEMENT	" + "inner join COMPANY_MASTER company ON company.COMPANY_MASTER_SID = COMPANY "
			+ "inner join COMPANY_MASTER businessunit ON businessunit.COMPANY_MASTER_SID= BUSINESS_UNIT	"
			+ "inner join HELPER_TABLE ht on ht.HELPER_TABLE_SID=FILE_TYPE where company.COMPANY_MASTER_SID=@company and businessunit.COMPANY_MASTER_SID=@businessunit ) A "
			+ "where TO_PERIOD is null @filter";

	public static final String DATA_ASSUMPTIONS_RESULT_QUERY = "SELECT * FROM (select	FORECAST_NAME as FORECAST_NAME,	company.COMPANY_NAME as COMPANY_NAME ,	businessunit.COMPANY_NAME as BUSINESS_UNIT,	ht.DESCRIPTION as FILE_TYPE,	VERSION,	FROM_PERIOD = FORMAT(FROM_PERIOD , 'MM/dd/yyyy HH:mm:ss') , TO_PERIOD = FORMAT(TO_PERIOD , 'MM/dd/yyyy HH:mm:ss'),	case when File_management.TO_PERIOD is null then 'Yes' else 'No' end ACTIVE_FILE 	"
			+ "from	FILE_MANAGEMENT	" + "inner join COMPANY_MASTER company ON company.COMPANY_MASTER_SID = COMPANY "
			+ "inner join COMPANY_MASTER businessunit ON businessunit.COMPANY_MASTER_SID= BUSINESS_UNIT	"
			+ "inner join HELPER_TABLE ht on ht.HELPER_TABLE_SID=FILE_TYPE where company.COMPANY_MASTER_SID=@company and businessunit.COMPANY_MASTER_SID=@businessunit) A "
			+ "where TO_PERIOD is null @filter";

	public static final String HIERARCHY_SID_AND_LEVEL_DEFINITION_SID = "SELECT distinct LEVEL_NO, LEVEL_VALUE_REFERENCE, TABLE_NAME, FIELD_NAME, LEVEL_NAME, HLD.HIERARCHY_LEVEL_DEFINITION_SID, HLD.HIERARCHY_DEFINITION_SID, HT.DESCRIPTION from HIERARCHY_LEVEL_DEFINITION HLD JOIN dbo.HIERARCHY_DEFINITION HD on HD.HIERARCHY_DEFINITION_SID = hld.HIERARCHY_DEFINITION_SID JOIN dbo.HELPER_TABLE HT on HT.HELPER_TABLE_SID = HD.HIERARCHY_CATEGORY where hld.HIERARCHY_DEFINITION_SID =@HIERARCHY_DEFINITION_SID and HLD.VERSION_NO =@VERSION_NO ";

	public static final String MAIN_QUERY_REPORT_FROM_AND_TO_DATE = ";WITH cte AS "
			+ "(SELECT TOP 1 FROM_DATE AS FROM_DATE, TO_DATE AS  TO_DATE FROM   forecast_config f JOIN HELPER_TABLE h ON f.BUSINESS_PROCESS_TYPE = h.HELPER_TABLE_SID WHERE  LIST_NAME LIKE 'BUSINESS_PROCESS_TYPE' AND DESCRIPTION = 'Commercial' ORDER BY VERSION_NO DESC) SELECT    DESCRIPTION  FROM   forecast_config f JOIN HELPER_TABLE h ON f.HIST_FREQ = h.HELPER_TABLE_SID WHERE  FROM_DATE=(SELECT FROM_DATE FROM   cte c)  and ACTIVE_END_DATE is null ";

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

	public static final char UNDERSCORE = '_';

	public static final String REPORTING = "/Reporting/";

	public static final String YEAR_FREQUENCY = "[0-9]+";

	public static final String DATA_ASSUMPTIONS_MULTIPLE_TABS_RESULTS = "select distinct FORECAST_NAME as FORECAST_NAME, company.COMPANY_NAME as COMPANY_NAME, businessunit.COMPANY_NAME as BUSINESS_UNIT, ht.DESCRIPTION as FILE_TYPE, VERSION, FROM_PERIOD = FORMAT(FROM_PERIOD , 'MM/dd/yyyy HH:mm:ss'), TO_PERIOD = FORMAT(TO_PERIOD , 'MM/dd/yyyy HH:mm:ss'),case\n"
			+ "				when FT.TO_PERIOD is null then 'Yes'\n" + "				else 'No'\n"
			+ "			end ACTIVE_FILE from FILE_MANAGEMENT FT inner join COMPANY_MASTER company ON company.COMPANY_MASTER_SID = FT.COMPANY inner join COMPANY_MASTER businessunit ON businessunit.COMPANY_MASTER_SID = FT.BUSINESS_UNIT inner join HELPER_TABLE ht on ht.HELPER_TABLE_SID = FT.FILE_TYPE inner join PROJECTION_FILE_DETAILS pfd ON pfd.FILE_MANAGEMENT_SID=FT.FILE_MANAGEMENT_SID where pfd.PROJECTION_MASTER_SID like '@projectionMasterSid'";
	public static final String DATA_ASSUMPTIONS_NO_SOURCE_MULTIPLE_TABS_RESULTS = "select FORECAST_NAME as FORECAST_NAME, company.COMPANY_NAME as COMPANY_NAME, businessunit.COMPANY_NAME as BUSINESS_UNIT, ht.DESCRIPTION as FILE_TYPE, VERSION, FROM_PERIOD = FORMAT(FROM_PERIOD , 'MM/dd/yyyy HH:mm:ss') , TO_PERIOD = FORMAT(TO_PERIOD , 'MM/dd/yyyy HH:mm:ss') ,case\n"
			+ "				when FT.TO_PERIOD is null then 'Yes'\n" + "				else 'No'\n"
			+ "			end ACTIVE_FILE from FILE_MANAGEMENT FT inner join COMPANY_MASTER company ON company.COMPANY_MASTER_SID = FT.COMPANY inner join COMPANY_MASTER businessunit ON businessunit.COMPANY_MASTER_SID = FT.BUSINESS_UNIT inner join HELPER_TABLE ht on ht.HELPER_TABLE_SID = FT.FILE_TYPE inner join PROJECTION_FILE_DETAILS pfd ON pfd.FILE_MANAGEMENT_SID=FT.FILE_MANAGEMENT_SID where pfd.PROJECTION_MASTER_SID like '@projectionMasterSid' ";
	public static final String PRC_CUSTOM_CCPDV_POPULATION = " PRC_CUSTOM_CCPDV_POPULATION ? , ? , ? ";

	public static final String PRC_REPORT_DATA_POPULATION = " PRC_REPORTING_DASHBOARD ? , ? , ? , ? , ? , ? , ? , ? ";

	public static final String VARIABLE_AND_COMPARISON_BREAKDOWN_PERIOD_DATAS = "	select * from ( select row_number() over (partition by @frequency Year order by year asc) rowno, PERIOD_SID,YEAR from Period where @periodDateCondition )A where rowno=1 order by year asc";

	public static final String VARIABLE_BREAKDOWN_SAVE_SERVICE_QUERY = "INSERT INTO ST_VARIABLE_BR_REPORT (MASTER_SID,PERIOD,YEAR,SELECTED_VARIABLE) VALUES (?,?,?,?)";

	public static final String COMPARISON_BREAKDOWN_SAVE_SERVICE_QUERY = "INSERT INTO ST_COMPARISION_REPORT (PROJECTION_MASTER_SID,PERIOD,YEAR,SELECTED_VARIABLE) VALUES (?,?,?,?)";

	public static final String PRC_TEMP_TABLE_CREATION = " PRC_TEMP_TABLE_CREATION ? ? ? ";

	public static final String COMPARISON_BREAKDOWN_TRUNCATE_QUERY = "TRUNCATE TABLE ST_COMPARISION_REPORT";

	public static final String VARIABLE_BREAKDOWN_TRUNCATE_QUERY = "TRUNCATE TABLE ST_VARIABLE_BR_REPORT";

	public static final String PRC_TEMP_TABLE_LIST = "CCP_HIERARCHY,CUSTOM_CCP_SALES,CUSTOM_VARIABLE_HIERARCHY,CUSTOM_DISCOUNT_REPORT,CUSTOM_SALES_REPORT,APPROVED_REPORT,VARIABLE_BR_REPORT,COMPARISION_REPORT,ITEM_UOM_DETAILS";

	public static final String CUSTOM_VARIABLE_HIERARCHY = "ST_CUSTOM_VARIABLE_HIERARCHY";

	public static final String CUSTOM_VIEW_TYPE = "select CUST_VIEW_TYPE from CUST_VIEW_MASTER where CUST_VIEW_MASTER_SID = ?";

	public static final String ST_CCP_HIERARCHY = "ST_CCP_HIERARCHY";

	public static final String CHECK_PROCESS_MODE_FOR_REPORT_FROM_AND_TO_DATE = "select PROCESS_MODE from forecast_config where ACTIVE_END_DATE IS null and BUSINESS_PROCESS_TYPE = 331";

	public static final String PERCENTAGE_OPERATOR = "%";
	public static final String CONTRACT_UNITS = "Contract Units";
	public static final String WEIGHTED_GTN_CONTRIBUTION = "Weighted GTN Contribution";
	public static final String VOLUME = "VOLUME";
	public static final String CHANGE = "CHANGE";
	public static final String TOTAL_CAPS = "TOTAL";

	public static final String CUSTOM_CCP_FILE_NAME = "CustomViewCCP";

	public static final String HIERARCHY_INDICATOR_QUERY = "SELECT DISTINCT HT.DESCRIPTION, CUSTOM_VIEW_VARIABLES.VARIABLE_INDICATOR\r\n"
			+ "FROM            CUSTOM_VIEW_VARIABLES INNER JOIN\r\n"
			+ "                         HELPER_TABLE AS HT ON HT.HELPER_TABLE_SID = CUSTOM_VIEW_VARIABLES.VARIABLE_SID\r\n"
			+ "ORDER BY CUSTOM_VIEW_VARIABLES.VARIABLE_INDICATOR";
	public static final String UOM_DEFAULT = "EACH";

	public static final String FILTER_CONSTANT = "@filter";
	public static final String FILTER_COMPANY = "@company";
	public static final String FILTER_BUSINESSUNIT = "@businessunit";
	public static final String WHERE = "WHERE";
	public static final String DEDUCTION_DYNAMIC_FILTER = "deduction-dynamic-filter";
	public static final String HT_HELPER_TABLE_SID_IN = "HT.HELPER_TABLE_SID in";
	public static final String LEVELVALUES = "@LEVELVALUES";
	public static final String PROJECTION_NAME = "projectionName";

	public static final String MARKET_TYPE = "marketType";

	public static final String BRAND = "brand";

	public static final String WORKFLOW_STATUS = "workflowStatus";

	public static final String TO_PERIOD = "toPeriod";

	public static final String NDC_NAME = "ndcName";

	public static final String HIERARCHY_NAME = "hierarchyName";

	public static final String FROM_PERIOD = "fromPeriod";

	public static final String CONTRACT_HOLDER = "contractHolder";

	public static final String CUSTOM_VIEW_NAME = "customViewName";

	public static final String CREATED_BY = "createdBy";

	public static final String CONTRACT2 = "contract";

	public static final String COMPARISON_NDC = "comparisonNDC";

	public static final String CONVERT_DATE = " CONVERT(date, ";

	public static final String REPORT_PROFILE_LOOKUP_VIEW_TYPE = "reportProfileLookup_viewType";

	public static final String REPORT_PROFILE_LOOKUP_VIEW_NAME = "reportProfileLookup_viewName";

	public static final String PUBLIC_VIEW_NAME = "publicViewName";

	public static final String PROJECTION_TYPE = "projectionType";

	public static final String PRIVATE_VIEW_NAME = "privateViewName";

	public static final String LOAD_RESULT = "loadResult";

	public static final String DESCRIPTION = "description";

	public static final String CREATED_DATE = "createdDate";

	public static final String GENERATED_HIERARCHY_NO = "generatedHierarchyNo";

	public static final String LEVEL_VALUE = "levelValue";

	public static final String LEVEL_NAME = "levelName";

	public static final String HIERARCHY_NO = "hierarchyNo";

	public static final String LEVEL_NUMBER = "levelNumber";

	public static final char STRING_COMMA = ',';

	public static final String SINGLE_QUOTES = "' ";

	public static final String TOTAL = "Total";

	public static final String WEIGHTED = "Weighted";

	private static final List<String> VARIABLE_CHARATER_LIST = Arrays.asList(".A.", ".B.", ".C.", ".D.", ".E.", ".F.",
			".G.", ".H.", ".I.", ".J.", ".K.", ".L.", ".M.", ".N.");

	public static String getVariableFromHierarchy(String key) {
		for (String element : VARIABLE_CHARATER_LIST) {
			if (key.contains(element)) {
				return String.valueOf(element.charAt(1));
			}
		}
		return StringUtils.EMPTY;
	}
}
