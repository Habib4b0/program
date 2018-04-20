package com.stpl.gtn.gtn2o.ws.controller;

public class GtnWsQueryConstants {

	public static final String DATA_ASSUMPTIONS_COUNT_QUERY="select count(*) from(select	FORECAST_NAME as FORECAST_NAME,	company.COMPANY_NAME as COMPANY_NAME ,	businessunit.COMPANY_NAME as BUSINESS_UNIT,	ht.DESCRIPTION as FILE_TYPE,	VERSION,	FROM_PERIOD as ACTIVE_FROM,	FROM_PERIOD,	TO_PERIOD		from	FILE_MANAGEMENT	inner join COMPANY_MASTER company ON company.COMPANY_MASTER_SID = COMPANY inner join COMPANY_MASTER businessunit ON businessunit.COMPANY_MASTER_SID= BUSINESS_UNIT	inner join HELPER_TABLE ht on ht.HELPER_TABLE_SID=FILE_TYPE where	TO_PERIOD is null) A";
	
	public static final String DATA_ASSUMPTIONS_RESULT_QUERY="select	FORECAST_NAME as FORECAST_NAME,	company.COMPANY_NAME as COMPANY_NAME ,	businessunit.COMPANY_NAME as BUSINESS_UNIT,	ht.DESCRIPTION as FILE_TYPE,	VERSION,	FROM_PERIOD as ACTIVE_FROM,	FROM_PERIOD,	TO_PERIOD		from	FILE_MANAGEMENT	inner join COMPANY_MASTER company ON company.COMPANY_MASTER_SID = COMPANY inner join COMPANY_MASTER businessunit ON businessunit.COMPANY_MASTER_SID= BUSINESS_UNIT	inner join HELPER_TABLE ht on ht.HELPER_TABLE_SID=FILE_TYPE where	TO_PERIOD is null @filter";
	
}
