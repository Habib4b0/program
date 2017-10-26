package com.stpl.app.service.impl;

import java.util.List;

import com.stpl.app.service.base.BusinessroleModuleLocalServiceBaseImpl;
import com.stpl.app.service.persistence.BusinessroleModuleFinderUtil;

/**
 * The implementation of the businessrole module local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.stpl.app.service.BusinessroleModuleLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author
 * @see com.stpl.app.service.base.BusinessroleModuleLocalServiceBaseImpl
 * @see com.stpl.app.service.BusinessroleModuleLocalServiceUtil
 */
public class BusinessroleModuleLocalServiceImpl
    extends BusinessroleModuleLocalServiceBaseImpl {
	/*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.stpl.app.global.service.BusinessroleModuleLocalServiceUtil} to access the businessrole module local service.
     */
    public List getBusinessFunctionPermission(String businessRoleId,String moduleName) {
		//HashMap <String,AppPermission> hashMap=new HashMap<String,AppPermission>();
		return BusinessroleModuleFinderUtil.getBusinessFunctionPermission(businessRoleId, moduleName);
	}
	public List getBusinessFieldPermission(String businessRoleId,String moduleName) {
		//HashMap <String,AppPermission> hashMap=new HashMap<String,AppPermission>();
		return BusinessroleModuleFinderUtil.getBusinessFieldPermission(businessRoleId, moduleName);
	}
	public List getBusinessTabPermission(String businessRoleId,String moduleName) {
		//HashMap <String,AppPermission> hashMap=new HashMap<String,AppPermission>();
		return BusinessroleModuleFinderUtil.getBusinessTabPermission(businessRoleId, moduleName);
	}
	
	public java.lang.Object executeSelectQuery(java.lang.String query,
            java.lang.Object udc1) {
        return BusinessroleModuleFinderUtil.executeSelectQuery(query, udc1);
    }
	
	public List getContractBusinessFunctionPermission(String businessRoleId,String moduleName) {
		//HashMap <String,AppPermission> hashMap=new HashMap<String,AppPermission>();
		return BusinessroleModuleFinderUtil.getContractBusinessFunctionPermission(businessRoleId, moduleName);
	}
	public List getContractBusinessFieldPermission(String businessRoleId,String moduleName) {
		//HashMap <String,AppPermission> hashMap=new HashMap<String,AppPermission>();
		return BusinessroleModuleFinderUtil.getContractBusinessFieldPermission(businessRoleId, moduleName);
	}
	public List getContractBusinessTabPermission(String businessRoleId,String moduleName) {
		//HashMap <String,AppPermission> hashMap=new HashMap<String,AppPermission>();
		return BusinessroleModuleFinderUtil.getContractBusinessTabPermission(businessRoleId, moduleName);
	}  
	public List findFieldAccessDetails(String businessRoleName,String moduleName,String subModuleName){
        return BusinessroleModuleFinderUtil.findFieldAccessDetails(businessRoleName, moduleName, subModuleName);
    }
    
    public List findSubModuleFieldDetails(String businessRoleName,String moduleName,String subModuleName){
        return BusinessroleModuleFinderUtil.findSubModuleFieldDetails(businessRoleName, moduleName, subModuleName);
    }
    
    public List findModuleAccessDetails(String businessRoleName,String moduleName,String subModuleName){
        return BusinessroleModuleFinderUtil.findModuleAccessDetails(businessRoleName, moduleName, subModuleName);
    }
    
    public List findsubmodulePropertyDetails(String businessRoleName,String moduleName,String subModuleName){
        return BusinessroleModuleFinderUtil.findsubmodulePropertyDetails(businessRoleName, moduleName, subModuleName);
    }
}
