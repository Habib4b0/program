/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.dao.impl;


import java.util.List;

import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.domain.contract.contractdashboard.CompanyFamilyplanDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.Map;

/**
 * DAO Operation for CompanyFamilyPlan Logic.
 * 
 * @author sibi
 */
public class CFPSearchLogicDAOImpl implements CompanyFamilyplanDAO {

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 * 
	 * @param dynamicQuery
	 *            - returns the CompanyMaster based on given Search Criteria.
	 * @return List of CompanyMaster
	 * @throws SystemException
	 */
	public List<CompanyMaster> getCompanyMasterList(final DynamicQuery dynamicQuery) throws SystemException {
		return CompanyMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 * 
	 * @param dynamicQuery
	 *            - returns no. of rows based on company no search criteria.
	 * @return long - numbers of records in Database
	 * @throws SystemException
	 */
	public long dynamicQueryCount(final DynamicQuery dynamicQuery) throws SystemException {
		return CompanyMasterLocalServiceUtil.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of company masters records in DataBase.
	 * 
	 * @return int - total count of records in DataBase.
	 * @throws SystemException
	 */
	public int getCompanyMasterCount() throws SystemException {
		return CompanyMasterLocalServiceUtil.getCompanyMastersCount();
	}

	/**
	 * Returns the HelperTable with company type
	 * 
	 * @param list
	 *            - company type.
	 * @return List of HelperTable.
	 * @throws SystemException
	 */
	public List<HelperTable> findByHelperTableDetails(final String companyType) throws SystemException {
		return HelperTableLocalServiceUtil.findByHelperTableDetails(companyType);
	}

        public long companyTypeQueryCount(final DynamicQuery dynamicQuery) throws SystemException {
		return HelperTableLocalServiceUtil.dynamicQueryCount(dynamicQuery);
	}
            
    @Override
    public long companyAdditionDynamicQueryCount(DynamicQuery dynamicQuery) throws SystemException {
        return CompanyMasterLocalServiceUtil.dynamicQueryCount(dynamicQuery);
    }
    
	public List<HelperTable> getHelperTable(final DynamicQuery dynamicQuery) throws SystemException {
		return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
	}    
        
    public List<HelperTable> getHelperTableDetailsByListName() throws SystemException {
        return HelperTableLocalServiceUtil.getHelperTables(0, HelperTableLocalServiceUtil.getHelperTablesCount());
    }    
    
    @Override
    public List findCfpModelV1(Map<Object, Object> cfp, String orderByColumn, Boolean sortOrder, Object index, Object next, Map<Object, Object> parameters, String operation, Object future, Object future1) {
        return CfpModelLocalServiceUtil.findCfpModelV1(cfp, orderByColumn, sortOrder, index, next, parameters, operation, future, future1);
    }
    
    public int getCompanyFamilyplanMasterTotalCount() throws PortalException, SystemException{
        return (int) CfpModelLocalServiceUtil.getCfpModelsCount(); 
    }
}
