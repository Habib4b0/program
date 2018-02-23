/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.service.finderImpl;


import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Abishek.Ram
 */
public class BusinessRoleModuleImpl {
     private static final Logger LOGGER = LoggerFactory.getLogger(BusinessRoleModuleImpl.class);
     
    public List getBusinessFunctionPermission(String businessRoleId,String moduleName) {
		
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
				sql += " AND ubm.BUSINESSROLE_MASTER_SID in ("
						+ businessRoleId+")";
			}
			
			if (mod.length()!=0) {
				sql += " AND spm.MODULE_NAME in ('" + mod + "') ";
			}
                        
			if(str!=null && !str[1].equals(StringUtils.EMPTY) && str[1].length()!=0){
				sql += " AND spm.TAB_NAME in ('" +  str[1]  + "') ";
			}
			return HelperTableLocalServiceUtil.executeSelectQuery(sql);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
                        LOGGER.error(sql);
			return null;
		} finally {
			
		}
		
	}
	public List getBusinessFieldPermission(String businessRoleId,String moduleName) {		
		
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
                            sql = SQlUtil.getQuery(getClass(),"com.businessRoleModule.service.persistence.BusinessRoleModuleFinder.fieldPermissionForTransaction");
                        }
                        else
                        {
			sql = SQlUtil.getQuery(getClass(),"com.businessRoleModule.service.persistence.BusinessRoleModuleFinder.fieldPermission");
                        }
			
			
			if (businessRoleId.length()!=0) {
				sql += " AND ubm.BUSINESSROLE_MASTER_SID in ("
						+ businessRoleId+")";
			}
			
			
			if (mod.length()!=0) {
				sql += " AND spm.MODULE_NAME in ('" + mod + "') ";
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
			return null;
		} finally {
			
		}
	}
	public List getBusinessTabPermission(String businessRoleId,String moduleName) {
		
                String sql = StringUtils.EMPTY;
		try {
			

			sql = SQlUtil.getQuery(getClass(),"com.businessRoleModule.service.persistence.BusinessRoleModuleFinder.tabPermission");
			
			
			
			if (businessRoleId.length()!=0) {
				sql += " AND ubm.BUSINESSROLE_MASTER_SID in ("
						+ businessRoleId+")";
			}
			
			
			if (moduleName.length()!=0) {
				sql += " AND spm.MODULE_NAME in ('" + moduleName + "') ";
			}


                        return HelperTableLocalServiceUtil.executeSelectQuery(sql);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
                        LOGGER.error(sql);
			return null;
		} finally {
			
		}
	}
	
	 public Object executeSelectQuery(String query, Object udc1) {
	        
	        List<Object[]> returnList = new ArrayList<Object[]>();
	        try {
	            

	            returnList = HelperTableLocalServiceUtil.executeSelectQuery(query);

	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
                    LOGGER.error(query);
	        } finally {
	            
	        }
	        return returnList;
	    }
	 
	 @SuppressWarnings("rawtypes")
		public List getContractBusinessFunctionPermission(String businessRoleId, String moduleName) {
			LOGGER.debug("enters getBusinessFunctionPermission()");
			
                        String sql = StringUtils.EMPTY;
			try {
				

				sql = SQlUtil.getQuery(getClass(),"com.contract.businessRoleModule.service.persistence.BusinessRoleModuleFinder.functionPermission");

				if (businessRoleId.length() != 0) {
					sql += " AND ubm.BUSINESSROLE_MASTER_SID in (" + businessRoleId + ")";
				}

				if (moduleName.length() != 0) {
					sql += " AND spm.MODULE_NAME in ('" + moduleName + "') ";
				}

				LOGGER.debug("End of getBusinessFunctionPermission()");
				return HelperTableLocalServiceUtil.executeSelectQuery(sql);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
                                LOGGER.error(sql);
				return null;
			} finally {
				
			}

		}

		public List getContractBusinessFieldPermission(String businessRoleId, String moduleName) {
			
                        String sql = StringUtils.EMPTY;
			try {
				LOGGER.debug("enters getBusinessFieldPermission()");
				

				sql = SQlUtil.getQuery(getClass(),"com.contract.businessRoleModule.service.persistence.BusinessRoleModuleFinder.fieldPermission");

				if (businessRoleId.length() != 0) {
					sql += " AND ubm.BUSINESSROLE_MASTER_SID in (" + businessRoleId + ")";
				}

				if (moduleName.length() != 0) {
					sql += " AND spm.MODULE_NAME in ('" + moduleName + "') ";
				}
				LOGGER.debug("End of getBusinessFieldPermission()");
				return HelperTableLocalServiceUtil.executeSelectQuery(sql);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
                                LOGGER.error(sql);
				return null;
			} finally {
				
			}
		}

		public List getContractBusinessTabPermission(String businessRoleId, String moduleName) {
			
                        String sql = StringUtils.EMPTY;
			try {
				LOGGER.debug("enters getBusinessTabPermission()");
				

				sql = SQlUtil.getQuery(getClass(),"com.contract.businessRoleModule.service.persistence.BusinessRoleModuleFinder.tabPermission");

				if (businessRoleId.length() != 0) {
					sql += " AND ubm.BUSINESSROLE_MASTER_SID in (" + businessRoleId + ")";
				}

				if (moduleName.length() != 0) {
					sql += " AND spm.MODULE_NAME in ('" + moduleName + "') ";
				}

				LOGGER.debug("End of getBusinessTabPermission()");
				return HelperTableLocalServiceUtil.executeSelectQuery(sql);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
                                LOGGER.error(sql);
				return null;
			} finally {
				
			}
		}
	        
		@SuppressWarnings("rawtypes")
		public List findModuleAccessDetails(String businessRoleName,String moduleName,String subModuleName){
			
                        String sql = StringUtils.EMPTY;
			try {
	                    LOGGER.debug("-----Inside findModuleAccessDetails-----");
						
				

				sql = SQlUtil.getQuery(getClass(),"com.businessRoleModuleMaster.service.persistence.BusinessroleModuleMasterFinder.findModuleAccessDetails");
				

				if (businessRoleName.length()!=0) {
					sql += " AND BM.BUSINESSROLE_NAME = '"
							+ businessRoleName + "'";
				}
				if (moduleName.length()!=0) {
					sql += " AND MSM.MODULE_NAME = '" + moduleName + "' ";
				}
				if (subModuleName.length()!=0) {
					sql += " AND MSM.SUBMODULE_NAME = '" + subModuleName + "' ";
				}
				sql+=" order by SPM.CATEGORY_NAME";
				
				LOGGER.debug("Final sql statement----------->"+sql);
				return HelperTableLocalServiceUtil.executeSelectQuery(sql);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
                                LOGGER.error(sql);
				return null;
			} finally {
				
			}
		}
			
			@SuppressWarnings("rawtypes")
			public List findsubmodulePropertyDetails(String businessRoleName,String moduleName,String subModuleName){
				
                                String sql = StringUtils.EMPTY;
				try {
	                            LOGGER.debug("----Inside findsubmodulePropertyDetails----");
					

					sql = SQlUtil.getQuery(getClass(),"com.businessRoleModuleMaster.service.persistence.BusinessroleModuleMasterFinder.findsubmodulePropertyDetails");
					

					if (moduleName.length()!=0) {
						sql += " AND MSM.MODULE_NAME = '" + moduleName + "' ";
					}
					if (subModuleName.length()!=0) {
						sql += " AND MSM.SUBMODULE_NAME = '" + subModuleName + "' ";
					}
					
					sql+=" order by SPM.CATEGORY_NAME";
	                                LOGGER.debug("Final sql statement----------->"+sql);
					return HelperTableLocalServiceUtil.executeSelectQuery(sql);
				} catch (Exception e) {                            
					LOGGER.error(e.getMessage());
	                                LOGGER.error(sql);
					return null;
				} finally {
					
				}
		}
			
			@SuppressWarnings("rawtypes")
			public List findFieldAccessDetails(String businessRoleName,String moduleName,String subModuleName){
				
                                String sql = StringUtils.EMPTY;
				try {
	                            LOGGER.debug("---Inside findFieldAccessDetails-----");
					

					sql = SQlUtil.getQuery(getClass(),"com.businessRoleModuleMaster.service.persistence.BusinessroleModuleMasterFinder.findFieldAccessDetails");
					

					if (businessRoleName.length()!=0) {
						sql += " AND BM.BUSINESSROLE_NAME = '"
								+ businessRoleName + "'";
					}
					if (moduleName.length()!=0) {
						sql += " AND MSM.MODULE_NAME = '" + moduleName + "' ";
					}
					if (subModuleName.length()!=0) {
						sql += " AND MSM.SUBMODULE_NAME = '" + subModuleName + "' ";
					}
					
					
					LOGGER.debug("Final sql statement----------->"+sql);
					return HelperTableLocalServiceUtil.executeSelectQuery(sql);
				} catch (Exception e) {
					LOGGER.error(e.getMessage());
	                                LOGGER.error(sql);
					return null;
				} finally {
					
				}
			}
				
				@SuppressWarnings("rawtypes")
				public List findSubModuleFieldDetails(String businessRoleName,String moduleName,String subModuleName){
					
                                        String sql = StringUtils.EMPTY;
					try {
	                                    LOGGER.debug("---Inside findSubModuleFieldDetails----");
						

						sql = SQlUtil.getQuery(getClass(),"com.businessRoleModuleMaster.service.persistence.BusinessroleModuleMasterFinder.findsubmoduleFieldDetails");
						

						if (moduleName.length()!=0) {
							sql += " AND MSM.MODULE_NAME = '" + moduleName + "' ";
						}
						if (subModuleName.length()!=0) {
							sql += " AND MSM.SUBMODULE_NAME = '" + subModuleName + "' ";
						}
						
						
						LOGGER.debug("Final sql statement----------->"+sql);
						return HelperTableLocalServiceUtil.executeSelectQuery(sql);
					} catch (Exception e) {
						LOGGER.error(e.getMessage());
	                                        LOGGER.error(sql);
						return null;
					} finally {
						
					}
			}
                                
}
