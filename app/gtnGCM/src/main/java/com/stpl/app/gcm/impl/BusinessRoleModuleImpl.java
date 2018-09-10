/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.impl;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.util.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class BusinessRoleModuleImpl {
     private static final Logger LOGGER = LoggerFactory.getLogger(BusinessRoleModuleImpl.class);
     
    public static List getBusinessFunctionPermission(String businessRoleId,String moduleName) {
                        String[] str = null ;
                        String mod;
                        String sql = StringUtils.EMPTY;
                if(moduleName.contains(",")){
                            str = moduleName.split(",");
                            mod = str[0];
                        }else{
                            mod = moduleName;
                        }
		try {
			sql = SQlUtil.getQuery("com.businessRoleModule.service.persistence.BusinessRoleModuleFinder.functionPermission");
			if (businessRoleId.length()!=0) {
				sql += StringConstantsUtil.BUSINESSROLE_MASTER_SID_CONDITION
						+ businessRoleId+")";
			}
			if (mod.length()!=0) {
				sql += StringConstantsUtil.MODULE_NAME_IN_CONDITION + mod + "') ";
			}
			if(str!=null && !str[1].equals(StringUtils.EMPTY) && str[1].length()!=0){
				sql += " AND spm.TAB_NAME in ('" +  str[1]  + "') ";
			}
			return HelperTableLocalServiceUtil.executeSelectQuery(sql);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
                        LOGGER.error(sql);
			return Collections.emptyList();
		} 
		
	}
	public static List getBusinessFieldPermission(String businessRoleId,String moduleName) {		
		
                String sql=StringUtils.EMPTY;
		try {
			
                        String[] str = null ;
                        String mod;
                        if(moduleName.contains(",")){
                            str = moduleName.split(",");
                            mod = str[0];
                        }else{
                            mod = moduleName;
                        }
                        
                        if(mod.equals("Item Hierarchy"))
                        {
                            sql = SQlUtil.getQuery("com.businessRoleModule.service.persistence.BusinessRoleModuleFinder.fieldPermissionForTransaction");
                        }
                        else
                        {
			sql = SQlUtil.getQuery("com.businessRoleModule.service.persistence.BusinessRoleModuleFinder.fieldPermission");
                        }
			if (businessRoleId.length()!=0) {
				sql += StringConstantsUtil.BUSINESSROLE_MASTER_SID_CONDITION
						+ businessRoleId+")";
			}
			if (mod.length()!=0) {
				sql += StringConstantsUtil.MODULE_NAME_IN_CONDITION + mod + "') ";
			}
                        if(str!=null && !str[1].equals(StringUtils.EMPTY) && str[1].length()!=0){
                            sql += " AND spm.TAB_NAME like ('" + str[1] + "') ";
                        }
                            sql=((moduleName.equalsIgnoreCase("Demand")) ||(moduleName.equalsIgnoreCase("Returns")) || (moduleName.equalsIgnoreCase("Inventory"))
                            ||(moduleName.equalsIgnoreCase("Item Hierarchy")) || (moduleName.equalsIgnoreCase("IvldReturns"))  || (moduleName.equalsIgnoreCase("IvldCompanyMaster"))|| (moduleName.equalsIgnoreCase("IvldItemMaster")) || (moduleName.equalsIgnoreCase("IvldItemPricing")) || (moduleName.equalsIgnoreCase("IvldItemIdentifier"))  || (moduleName.equalsIgnoreCase("IvldCompanyIdentifier")) || (moduleName.equalsIgnoreCase("IvldCompanyParent")) || (moduleName.equalsIgnoreCase("IvldCompanyTradeClass")) || (moduleName.equalsIgnoreCase("IvldCustomerGtsForecast")) || (moduleName.equalsIgnoreCase("IvldCustomerGtsActual"))  || ("GlobalFilesCompanyIdentifier,GlobalFilesCompanyIdentifier".equalsIgnoreCase(moduleName)))?sql.replace("distinct", ""):sql;
                    sql = ((moduleName.equalsIgnoreCase("Demand,Demand")) || (moduleName.equalsIgnoreCase("Returns,Returns")) || (moduleName.equalsIgnoreCase("IvldItemMaster,View")) || (moduleName.equalsIgnoreCase("IvldItemPricing,View")) || (moduleName.equalsIgnoreCase("IvldItemIdentifier,View")) || (moduleName.equalsIgnoreCase("Inventory,Inventory"))
                            || (moduleName.equalsIgnoreCase("Item Hierarchy,Item Hierarchy")) || ("Sales Master".equalsIgnoreCase(moduleName)) || ("Customer Sales".equalsIgnoreCase(moduleName)) || (moduleName.equalsIgnoreCase("GlobalFilesCompanyMaster,View")) || (moduleName.equalsIgnoreCase("IvldCompanyIdentifier,View")) || (moduleName.equalsIgnoreCase("IvldCompanyParent,View")) || (moduleName.equalsIgnoreCase("IvldCompanyTradeClass,View")) || ("GlobalFilesItemIdentifier".equalsIgnoreCase(moduleName)) || ("GlobalFilesItemPricing".equalsIgnoreCase(moduleName)) || ("GlobalFilesItemMaster".equalsIgnoreCase(moduleName)) || ("GlobalFilesCompanyMaster".equalsIgnoreCase(moduleName))
                            || ("GlobalFilesCompanyIdentifier,GlobalFilesCompanyIdentifier".equalsIgnoreCase(moduleName)) || ("GlobalFilesCompanyParent".equalsIgnoreCase(moduleName)) || ("GlobalFilesCompanyTradeClass".equalsIgnoreCase(moduleName))
                            || ("Actual GTS Customer Product".equalsIgnoreCase(moduleName))|| ("Cpi Index".equalsIgnoreCase(moduleName))  || ("Audit Inbound".equalsIgnoreCase(moduleName)) ||("Actual Master".equalsIgnoreCase(moduleName)) || ("Forecast Sales".equalsIgnoreCase(moduleName)) || ("IvldActualMaster".equalsIgnoreCase(moduleName))) ? sql.replace("distinct", "") : sql;
			return HelperTableLocalServiceUtil.executeSelectQuery(sql);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
                        LOGGER.error(sql);
			return Collections.emptyList();
		} 
	}
	public static List getBusinessTabPermission(String businessRoleId,String moduleName) {
		
                String sql = StringUtils.EMPTY;
		try {
			sql = SQlUtil.getQuery("com.businessRoleModule.service.persistence.BusinessRoleModuleFinder.tabPermission");
			if (businessRoleId.length()!=0) {
				sql += StringConstantsUtil.BUSINESSROLE_MASTER_SID_CONDITION
						+ businessRoleId+")";
			}
			if (moduleName.length()!=0) {
				sql += StringConstantsUtil.MODULE_NAME_IN_CONDITION + moduleName + "') ";
			}
                        return HelperTableLocalServiceUtil.executeSelectQuery(sql);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
                        LOGGER.error(sql);
			return Collections.emptyList();
		} 
	}
	
	 public Object executeSelectQuery(String query) {
	        
	        List<Object[]> returnList = new ArrayList<>();
	        try {
	            returnList = HelperTableLocalServiceUtil.executeSelectQuery(query);
	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
                    LOGGER.error(query);
	        } 
	        return returnList;
	    }
	 
	 @SuppressWarnings("rawtypes")
		public List getContractBusinessFunctionPermission(String businessRoleId, String moduleName) {
			LOGGER.debug("enters getBusinessFunctionPermission()");
			
                        String sql = StringUtils.EMPTY;
			try {
				sql = SQlUtil.getQuery("com.contract.businessRoleModule.service.persistence.BusinessRoleModuleFinder.functionPermission");
				if (businessRoleId.length() != 0) {
					sql += StringConstantsUtil.BUSINESSROLE_MASTER_SID_CONDITION + businessRoleId + ")";
				}
				if (moduleName.length() != 0) {
					sql += StringConstantsUtil.MODULE_NAME_IN_CONDITION + moduleName + "') ";
				}
				LOGGER.debug("End of getBusinessFunctionPermission()");
				return HelperTableLocalServiceUtil.executeSelectQuery(sql);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
                                LOGGER.error(sql);
				return Collections.emptyList();
			}

		}

		public List getContractBusinessFieldPermission(String businessRoleId, String moduleName) {
			
                        String sql = StringUtils.EMPTY;
			try {
				LOGGER.debug("enters getBusinessFieldPermission()");
				sql = SQlUtil.getQuery("com.contract.businessRoleModule.service.persistence.BusinessRoleModuleFinder.fieldPermission");

				if (businessRoleId.length() != 0) {
					sql += StringConstantsUtil.BUSINESSROLE_MASTER_SID_CONDITION + businessRoleId + ")";
				}
				if (moduleName.length() != 0) {
					sql += StringConstantsUtil.MODULE_NAME_IN_CONDITION + moduleName + "') ";
				}
				LOGGER.debug("End of getBusinessFieldPermission()");
				return HelperTableLocalServiceUtil.executeSelectQuery(sql);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
                                LOGGER.error(sql);
				return Collections.emptyList();
			} 
		}

		public List getContractBusinessTabPermission(String businessRoleId, String moduleName) {
			
                        String sql = StringUtils.EMPTY;
			try {
				LOGGER.debug("enters getBusinessTabPermission()");
				sql = SQlUtil.getQuery("com.contract.businessRoleModule.service.persistence.BusinessRoleModuleFinder.tabPermission");

				if (businessRoleId.length() != 0) {
					sql += StringConstantsUtil.BUSINESSROLE_MASTER_SID_CONDITION + businessRoleId + ")";
				}
				if (moduleName.length() != 0) {
					sql += StringConstantsUtil.MODULE_NAME_IN_CONDITION + moduleName + "') ";
				}
				LOGGER.debug("End of getBusinessTabPermission()");
				return HelperTableLocalServiceUtil.executeSelectQuery(sql);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
                                LOGGER.error(sql);
				return Collections.emptyList();
			} 
		}
	        
		@SuppressWarnings("rawtypes")
		public List findModuleAccessDetails(String businessRoleName,String moduleName,String subModuleName){
			
                        String sql = StringUtils.EMPTY;
			try {
	                    LOGGER.debug("-----Inside findModuleAccessDetails-----");
				sql = SQlUtil.getQuery("com.businessRoleModuleMaster.service.persistence.BusinessroleModuleMasterFinder.findModuleAccessDetails");
				if (businessRoleName.length()!=0) {
					sql += " AND BM.BUSINESSROLE_NAME = '"
							+ businessRoleName + "'";
				}
				if (moduleName.length()!=0) {
					sql += StringConstantsUtil.MODULE_NAME_CONDITION + moduleName + "' ";
				}
				if (subModuleName.length()!=0) {
					sql += StringConstantsUtil.SUBMODULE_NAME_IN_CONDITION + subModuleName + "' ";
				}
				sql+=" order by SPM.CATEGORY_NAME";
				return HelperTableLocalServiceUtil.executeSelectQuery(sql);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
                                LOGGER.error(sql);
				return Collections.emptyList();
			} 
		}
			
			@SuppressWarnings("rawtypes")
			public List findsubmodulePropertyDetails(String moduleName,String subModuleName){
				
                                String sql = StringUtils.EMPTY;
				try {
	                            LOGGER.debug("----Inside findsubmodulePropertyDetails----");
					sql = SQlUtil.getQuery("com.businessRoleModuleMaster.service.persistence.BusinessroleModuleMasterFinder.findsubmodulePropertyDetails");
					if (moduleName.length()!=0) {
						sql += StringConstantsUtil.MODULE_NAME_CONDITION + moduleName + "' ";
					}
					if (subModuleName.length()!=0) {
						sql += StringConstantsUtil.SUBMODULE_NAME_IN_CONDITION + subModuleName + "' ";
					}
					sql+=" order by SPM.CATEGORY_NAME";
					return HelperTableLocalServiceUtil.executeSelectQuery(sql);
				} catch (Exception e) {                            
					LOGGER.error(e.getMessage());
	                                LOGGER.error(sql);
					return Collections.emptyList();
				} 
		}
			
			@SuppressWarnings("rawtypes")
			public List findFieldAccessDetails(String businessRoleName,String moduleName,String subModuleName){
				
                                String sql = StringUtils.EMPTY;
				try {
	                            LOGGER.debug("---Inside findFieldAccessDetails-----");
					sql = SQlUtil.getQuery("com.businessRoleModuleMaster.service.persistence.BusinessroleModuleMasterFinder.findFieldAccessDetails");
					if (businessRoleName.length()!=0) {
						sql += " AND BM.BUSINESSROLE_NAME = '"
								+ businessRoleName + "'";
					}
					if (moduleName.length()!=0) {
						sql += StringConstantsUtil.MODULE_NAME_CONDITION + moduleName + "' ";
					}
					if (subModuleName.length()!=0) {
						sql += StringConstantsUtil.SUBMODULE_NAME_IN_CONDITION + subModuleName + "' ";
					}
					return HelperTableLocalServiceUtil.executeSelectQuery(sql);
				} catch (Exception e) {
					LOGGER.error(e.getMessage());
	                                LOGGER.error(sql);
					return Collections.emptyList();
				} 
			}
				
				@SuppressWarnings("rawtypes")
				public List findSubModuleFieldDetails(String moduleName,String subModuleName){
					
                                        String sql = StringUtils.EMPTY;
					try {
	                                    LOGGER.debug("---Inside findSubModuleFieldDetails----");
						sql = SQlUtil.getQuery("com.businessRoleModuleMaster.service.persistence.BusinessroleModuleMasterFinder.findsubmoduleFieldDetails");
						if (moduleName.length()!=0) {
							sql += StringConstantsUtil.MODULE_NAME_CONDITION + moduleName + "' ";
						}
						if (subModuleName.length()!=0) {
							sql += StringConstantsUtil.SUBMODULE_NAME_IN_CONDITION + subModuleName + "' ";
						}
						return HelperTableLocalServiceUtil.executeSelectQuery(sql);
					} catch (Exception e) {
						LOGGER.error(e.getMessage());
	                                        LOGGER.error(sql);
						return Collections.emptyList();
					} 
			}
                                
}
