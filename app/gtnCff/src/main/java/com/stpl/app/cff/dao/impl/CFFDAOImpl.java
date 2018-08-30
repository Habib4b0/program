/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dao.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.dao.CFFDAO;
import com.stpl.app.cff.dao.CommonServiceImpl;
import com.stpl.app.model.HelperTable;
import com.stpl.app.parttwo.model.CffApprovalDetails;
import com.stpl.app.parttwo.model.CffDetails;
import com.stpl.app.parttwo.model.CffMaster;
import com.stpl.app.parttwo.service.CffApprovalDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.CffDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.CffMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.List;

/**
 *
 * @author Manasa
 */
public class CFFDAOImpl implements CFFDAO {

    private static final CFFDAOImpl dao = new CFFDAOImpl();

    private CFFDAOImpl() {
    }

    public static CFFDAOImpl getInstance() {
        return dao;
    }

    /**
     * Executes Update query
     *
     * @param query
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    @Override
    public Object executeUpdateQuery(String query) throws  PortalException {
        return HelperTableLocalServiceUtil.executeUpdateQueryCount(query);
    }

    /**
     * Executes select query
     *
     * @param query
     * @return
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    @Override
    public Object executeSelectQuery(String query) throws PortalException {
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param query - dynamic query of HelperTable
     * @return list of HelperTable
     * @throws com.liferay.portal.kernel.exception.PortalException
     * @throws SystemException
     */
    @Override
    public List<HelperTable> getHelperTableList(final DynamicQuery query) throws SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * (non-Javadoc)
     *
     * @throws com.liferay.portal.kernel.exception.SystemException
     * @throws com.liferay.portal.kernel.exception.PortalException
     * @see
     * com.stpl.app.cff.dao.CffLogicDAO#addCffMaster(com.stpl.app.cff.model.CffMaster)
     */
    @Override
    public CffMaster addCffMaster(final CffMaster cffMaster) throws PortalException {
        return CffMasterLocalServiceUtil.addCffMaster(cffMaster);
    }

    /**
     * (non-Javadoc)
     *
     * @throws com.liferay.portal.kernel.exception.SystemException
     * @throws com.liferay.portal.kernel.exception.PortalException
     * @see com.stpl.app.cff.dao.CffLogicDAO#getCffMaster(int)
     */
    @Override
    public CffMaster getCffMaster(final int cffMasterSid) throws PortalException {
        return CffMasterLocalServiceUtil.getCffMaster(cffMasterSid);
    }

    /**
     * (non-Javadoc)
     *
     * @throws com.liferay.portal.kernel.exception.SystemException
     * @throws com.liferay.portal.kernel.exception.PortalException
     * @see
     * com.stpl.app.cff.dao.CffLogicDAO#updateCffMaster(com.stpl.app.cff.model.CffMaster)
     */
    @Override
    public CffMaster updateCffMaster(final CffMaster cffMaster) throws PortalException {
        return CffMasterLocalServiceUtil.updateCffMaster(cffMaster);
    }

    /**
     * (non-Javadoc)
     *
     * @throws com.liferay.portal.kernel.exception.SystemException
     * @see
     * com.stpl.app.cff.dao.CffLogicDAO#addCffDetails(com.stpl.app.cff.model.CffDetails)
     */
    @Override
    public CffDetails addCffDetails(final CffDetails details) throws SystemException {
        return CffDetailsLocalServiceUtil.addCffDetails(details);
    }

    /**
     * (non-Javadoc)
     *
     * @throws com.liferay.portal.kernel.exception.SystemException
     * @see
     * com.stpl.app.cff.dao.CffLogicDAO#addCffApprovalDetails(com.stpl.app.cff.model.CffApprovalDetails)
     */
    @Override
    public CffApprovalDetails addCffApprovalDetails(final CffApprovalDetails approvalDetails) throws SystemException {
        return CffApprovalDetailsLocalServiceUtil.addCffApprovalDetails(approvalDetails);
    }

    /**
     *
     * @param cffSid
     * @return
     * @throws SystemException
     */
    @Override
    public List<CffApprovalDetails> getApprovalDetails(final int cffSid) throws SystemException {
        List<CffApprovalDetails> resultList;

        DynamicQuery query = CffApprovalDetailsLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("cffMasterSid", cffSid));
        resultList = CffApprovalDetailsLocalServiceUtil.dynamicQuery(query);

        return resultList;
    }

    /**
     *
     * @param cffSid
     * @return
     * @throws SystemException
     */
    @Override
    public List<CffDetails> getCffDetails(final int cffSid) throws SystemException {
        List<CffDetails> resultList;

        DynamicQuery query = CffDetailsLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq("cffMasterSid", cffSid));
        resultList = CffDetailsLocalServiceUtil.dynamicQuery(query);

        return resultList;
    }

    /**
     *
     * @param approvalDetails
     * @return
     * @throws SystemException
     */
    @Override
    public CffApprovalDetails updateCffApprovalDetails(final CffApprovalDetails approvalDetails) throws SystemException {
        return CffApprovalDetailsLocalServiceUtil.updateCffApprovalDetails(approvalDetails);
    }

    /**
     *
     * @param cffDetails
     * @return
     * @throws SystemException
     */
    @Override
    public CffDetails updateCffDetails(final CffDetails cffDetails) throws SystemException {
        return CffDetailsLocalServiceUtil.updateCffDetails(cffDetails);
    }

    @Override
    public List<HelperTable> getHelperTableDetailsByListName(String listName) throws SystemException {
        return CommonServiceImpl.getInstance().getHelperTableSId(listName);
    }

}
