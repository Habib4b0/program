/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.service.persistence;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import com.stpl.app.model.BusinessroleModule;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Asha
 */
public class BusinessroleModuleFinderImpl extends BasePersistenceImpl<BusinessroleModule> implements BusinessroleModuleFinder{
    private static final Logger LOGGER = Logger.getLogger(BusinessroleModuleFinderImpl.class);
	
	@SuppressWarnings("rawtypes")	
    public List getBusinessFunctionPermission(String businessRoleId,String moduleName) {
		//HashMap <String,AppPermission> hashMap=new HashMap<String,AppPermission>();
		Session session = null;
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
			session = openSession();

			sql = CustomSQLUtil
					.get("com.businessRoleModule.service.persistence.BusinessRoleModuleFinder.functionPermission");
			
			
			
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
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			return sqlQuery.list();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
                        LOGGER.error(sql);
			return null;
		} finally {
			closeSession(session);
		}
		
	}
	public List getBusinessFieldPermission(String businessRoleId,String moduleName) {		
		Session session = null;
                String sql=StringUtils.EMPTY;
		try {
			session = openSession();
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
                            sql = CustomSQLUtil
					.get("com.businessRoleModule.service.persistence.BusinessRoleModuleFinder.fieldPermissionForTransaction");
                        }
                        else
                        {
			sql = CustomSQLUtil
					.get("com.businessRoleModule.service.persistence.BusinessRoleModuleFinder.fieldPermission");
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
//                        if(tabName.length()!=0){
//			sql += "AND spm.TAB_NAME = '"+tabName+"'";
                        sql=((moduleName.equalsIgnoreCase("Demand")) ||(moduleName.equalsIgnoreCase("Returns")) || (moduleName.equalsIgnoreCase("Inventory"))
                            ||(moduleName.equalsIgnoreCase("Item Hierarchy")) || (moduleName.equalsIgnoreCase("IvldReturns"))  || (moduleName.equalsIgnoreCase("IvldCustomerGtsForecast")) || (moduleName.equalsIgnoreCase("IvldCustomerGtsActual"))  )?sql.replace("distinct", ""):sql;
                        sql=((moduleName.equalsIgnoreCase("Demand,Demand"))|| (moduleName.equalsIgnoreCase("Returns,Returns"))|| (moduleName.equalsIgnoreCase("Inventory,Inventory"))
                         || (moduleName.equalsIgnoreCase("Item Hierarchy,Item Hierarchy"))||("Sales Master".equalsIgnoreCase(moduleName)) || ("Customer Sales".equalsIgnoreCase(moduleName)) )?sql.replace("distinct", ""):sql;
                        SQLQuery sqlQuery = session.createSQLQuery(sql);
			
			return sqlQuery.list();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
                        LOGGER.error(sql);
			return null;
		} finally {
			closeSession(session);
		}
	}
	public List getBusinessTabPermission(String businessRoleId,String moduleName) {
		Session session = null;
                String sql = StringUtils.EMPTY;
		try {
			session = openSession();

			sql = CustomSQLUtil
					.get("com.businessRoleModule.service.persistence.BusinessRoleModuleFinder.tabPermission");
			
			
			
			if (businessRoleId.length()!=0) {
				sql += " AND ubm.BUSINESSROLE_MASTER_SID in ("
						+ businessRoleId+")";
			}
			
			
			if (moduleName.length()!=0) {
				sql += " AND spm.MODULE_NAME in ('" + moduleName + "') ";
			}
			SQLQuery sqlQuery = session.createSQLQuery(sql);

                        return sqlQuery.list();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
                        LOGGER.error(sql);
			return null;
		} finally {
			closeSession(session);
		}
	}
	
	 public Object executeSelectQuery(String query, Object udc1) {
	        Session session = null;
	        List<Object[]> returnList = new ArrayList<Object[]>();
	        try {
	            session = openSession();

	            //LOGGER.info("executeSelectQuery "+query);
	            Query sqlQuery = session.createSQLQuery(query);
//				LOGGER.info("SIZE--------->" + sqlQuery.list().size());
	            returnList = sqlQuery.list();

	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
                    LOGGER.error(query);
	        } finally {
	            closeSession(session);
	        }
	        return returnList;
	    }
	 
	 @SuppressWarnings("rawtypes")
		public List getContractBusinessFunctionPermission(String businessRoleId, String moduleName) {
			LOGGER.info("enters getBusinessFunctionPermission()");
			Session session = null;
                        String sql = StringUtils.EMPTY;
			try {
				session = openSession();

				sql = CustomSQLUtil.get("com.contract.businessRoleModule.service.persistence.BusinessRoleModuleFinder.functionPermission");

				if (businessRoleId.length() != 0) {
					sql += " AND ubm.BUSINESSROLE_MASTER_SID in (" + businessRoleId + ")";
				}

				if (moduleName.length() != 0) {
					sql += " AND spm.MODULE_NAME in ('" + moduleName + "') ";
				}

				SQLQuery sqlQuery = session.createSQLQuery(sql);
				LOGGER.info("End of getBusinessFunctionPermission()");
				return sqlQuery.list();
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
                                LOGGER.error(sql);
				return null;
			} finally {
				closeSession(session);
			}

		}

		public List getContractBusinessFieldPermission(String businessRoleId, String moduleName) {
			Session session = null;
                        String sql = StringUtils.EMPTY;
			try {
				LOGGER.info("enters getBusinessFieldPermission()");
				session = openSession();

				sql = CustomSQLUtil.get("com.contract.businessRoleModule.service.persistence.BusinessRoleModuleFinder.fieldPermission");

				if (businessRoleId.length() != 0) {
					sql += " AND ubm.BUSINESSROLE_MASTER_SID in (" + businessRoleId + ")";
				}

				if (moduleName.length() != 0) {
					sql += " AND spm.MODULE_NAME in ('" + moduleName + "') ";
				}

			

				SQLQuery sqlQuery = session.createSQLQuery(sql);
				LOGGER.info("End of getBusinessFieldPermission()");
				return sqlQuery.list();
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
                                LOGGER.error(sql);
				return null;
			} finally {
				closeSession(session);
			}
		}

		public List getContractBusinessTabPermission(String businessRoleId, String moduleName) {
			Session session = null;
                        String sql = StringUtils.EMPTY;
			try {
				LOGGER.info("enters getBusinessTabPermission()");
				session = openSession();

				sql = CustomSQLUtil.get("com.contract.businessRoleModule.service.persistence.BusinessRoleModuleFinder.tabPermission");

				if (businessRoleId.length() != 0) {
					sql += " AND ubm.BUSINESSROLE_MASTER_SID in (" + businessRoleId + ")";
				}

				if (moduleName.length() != 0) {
					sql += " AND spm.MODULE_NAME in ('" + moduleName + "') ";
				}

				SQLQuery sqlQuery = session.createSQLQuery(sql);
				LOGGER.info("End of getBusinessTabPermission()");
				return sqlQuery.list();
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
                                LOGGER.error(sql);
				return null;
			} finally {
				closeSession(session);
			}
		}
	        
		@SuppressWarnings("rawtypes")
		public List findModuleAccessDetails(String businessRoleName,String moduleName,String subModuleName){
			Session session = null;
                        String sql = StringUtils.EMPTY;
			try {
	                    LOGGER.info("-----Inside findModuleAccessDetails-----");
				session = openSession();		
				

				sql = CustomSQLUtil
						.get("com.businessRoleModuleMaster.service.persistence.BusinessroleModuleMasterFinder.findModuleAccessDetails");
				
				//LOGGER.info("sql query------------>"+sql);

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
				
				LOGGER.info("Final sql statement----------->"+sql);
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				return sqlQuery.list();
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
                                LOGGER.error(sql);
				return null;
			} finally {
				closeSession(session);
			}
		}
			
			@SuppressWarnings("rawtypes")
			public List findsubmodulePropertyDetails(String businessRoleName,String moduleName,String subModuleName){
				Session session = null;
                                String sql = StringUtils.EMPTY;
				try {
	                            LOGGER.info("----Inside findsubmodulePropertyDetails----");
					session = openSession();

					sql = CustomSQLUtil
							.get("com.businessRoleModuleMaster.service.persistence.BusinessroleModuleMasterFinder.findsubmodulePropertyDetails");
					
					////LOGGER.info("sql query------------>"+sql);

					if (moduleName.length()!=0) {
						sql += " AND MSM.MODULE_NAME = '" + moduleName + "' ";
					}
					if (subModuleName.length()!=0) {
						sql += " AND MSM.SUBMODULE_NAME = '" + subModuleName + "' ";
					}
					
					sql+=" order by SPM.CATEGORY_NAME";
	                                LOGGER.info("Final sql statement----------->"+sql);
					SQLQuery sqlQuery = session.createSQLQuery(sql);
					return sqlQuery.list();
				} catch (Exception e) {                            
					LOGGER.error(e.getMessage());
	                                LOGGER.error(sql);
					return null;
				} finally {
					closeSession(session);
				}
		}
			
			@SuppressWarnings("rawtypes")
			public List findFieldAccessDetails(String businessRoleName,String moduleName,String subModuleName){
				Session session = null;
                                String sql = StringUtils.EMPTY;
				try {
	                            LOGGER.info("---Inside findFieldAccessDetails-----");
					session = openSession();

					sql = CustomSQLUtil
							.get("com.businessRoleModuleMaster.service.persistence.BusinessroleModuleMasterFinder.findFieldAccessDetails");
					
					////LOGGER.info("sql query------------>"+sql);

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
					
					
					LOGGER.info("Final sql statement----------->"+sql);
					SQLQuery sqlQuery = session.createSQLQuery(sql);
					return sqlQuery.list();
				} catch (Exception e) {
					LOGGER.error(e.getMessage());
	                                LOGGER.error(sql);
					return null;
				} finally {
					closeSession(session);
				}
			}
				
				@SuppressWarnings("rawtypes")
				public List findSubModuleFieldDetails(String businessRoleName,String moduleName,String subModuleName){
					Session session = null;
                                        String sql = StringUtils.EMPTY;
					try {
	                                    LOGGER.info("---Inside findSubModuleFieldDetails----");
						session = openSession();

						sql = CustomSQLUtil
								.get("com.businessRoleModuleMaster.service.persistence.BusinessroleModuleMasterFinder.findsubmoduleFieldDetails");
						
						////LOGGER.info("sql query------------>"+sql);

						if (moduleName.length()!=0) {
							sql += " AND MSM.MODULE_NAME = '" + moduleName + "' ";
						}
						if (subModuleName.length()!=0) {
							sql += " AND MSM.SUBMODULE_NAME = '" + subModuleName + "' ";
						}
						
						
						LOGGER.info("Final sql statement----------->"+sql);
						SQLQuery sqlQuery = session.createSQLQuery(sql);
						return sqlQuery.list();
					} catch (Exception e) {
						LOGGER.error(e.getMessage());
	                                        LOGGER.error(sql);
						return null;
					} finally {
						closeSession(session);
					}
			}
}
