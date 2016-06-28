package com.stpl.app.security.businessRoleModuleMaster.util;

public class ModuleNameCheckingUtil {
	
	public static final String[] SubModuleName = {"Item Hierarchy","Audit Inbound",
		"Average Shelf Life Master","Formula Details","Master Data Attribute","Item Hierarchy Definition",
		"Actual Master","Sales Master","GL Balance","Forecast Sales","Best Price","Lot Master","GL Cost Center","Cpi Index"};
	/*public static final String AuditInbound = ;
	public static final String AverageShelfLifeMaster = ;
	public static final String FormulaDetails = ;
	public static final String MasterDataAttribute = ;
	public static final String ItemHierarchyDefinition = ;
	public static final String ActualMaster = ;
	public static final String SalesMaster = ;
	public static final String GLBalance = ;
	public static final String ForecastSales = ;
	public static final String BestPrice = ;
	public static final String LotMaster = ;
	public static final String GLCostCenter=;
	public static final String CpiIndex=;*/
	public static boolean moduleNameCheckingFun(String subModuleName)
	{
		String[] str=ModuleNameCheckingUtil.SubModuleName;
		for(int i=0;i<str.length;i++)
		{
			if(str[i].equals(subModuleName))
			{				
				return true;
			}
		}
		return false;
		
	}
	
}
