/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.dao.impl;

import com.stpl.app.model.HelperTable;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.RebatePlanMaster;
import com.stpl.app.model.RebateTierFormula;
import com.stpl.app.model.RsDetails;
import com.stpl.app.model.RsModel;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.RebatePlanMasterLocalServiceUtil;
import com.stpl.app.service.RebateTierFormulaLocalServiceUtil;
import com.stpl.app.service.RsDetailsLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.domain.global.rebateschedule.RebateScheduleDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;
import org.jboss.logging.Logger;

/**
 * DAO operations for RebateScheduleLogic
 *
 * @author shrihariharan
 */
public class RebateScheduleLogicDAOImpl implements RebateScheduleDAO {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(RebateScheduleLogicDAOImpl.class);

    /**
     * This method will retrieve the list of rebateSchedule based on the query
     * passed
     *
     * @param query - dynamic query of RebateScheduleMaster
     * @return list of type RebateScheduleMaster
     * @throws SystemException
     */
    public List<RsModel> getRebateScheduleMasterList(
            final DynamicQuery query) throws SystemException {
        LOGGER.info("In Query-getRebateScheduleMasterList P1:query");
        return RsModelLocalServiceUtil.dynamicQuery(query);
    }
    
    public List<Integer> getRebateScheduleSIdList(
            final DynamicQuery query) throws SystemException {
        LOGGER.info("In Query-getRebateScheduleSIdList P1:query");
        return RsModelLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * This method will retrieve the rebateSchedule based on the systemId
     *
     * @param systemId
     * @return object of type RebateScheduleMaster
     * @throws SystemException
     * @throws PortalException
     */
    public RsModel getRebateScheduleMasterBySystemId(final int systemId)
            throws SystemException, PortalException {
        LOGGER.info("In Query-getRebateScheduleMasterBySystemId P1:systemId=" + systemId);
        return RsModelLocalServiceUtil
                .getRsModel(systemId);
    }

    /**
     * This method will save the rebateSchedule into the database
     *
     * @param rebateScheduleMaster
     * @return object of type RebateScheduleMaster
     * @throws SystemException
     */
    public RsModel saveRebateScheduleMaster(final RsModel rebateScheduleMaster) throws SystemException {
        LOGGER.info("In Query-saveRebateScheduleMaster P1:rebateScheduleMaster");
        return RsModelLocalServiceUtil
                .addRsModel(rebateScheduleMaster);
    }

    /**
     * This method will update the edited rebateSchedule into the database
     *
     * @param rebateScheduleMaster
     * @return object of type RebateScheduleMaster
     * @throws SystemException
     */
    public RsModel updateRebateScheduleMaster(final RsModel rebateScheduleMaster) throws SystemException {
        LOGGER.info("In Query-updateRebateScheduleMaster P1:rebateScheduleMaster");
        return RsModelLocalServiceUtil
                .updateRsModel(rebateScheduleMaster);
    }

    /**
     * This method will save the rebateScheduleDetails that has been attached to
     * the rebateSchedule into the database
     *
     * @param rebateScheduledetail
     * @return object of type RebateScheduleDetails
     * @throws SystemException
     */
    public RsDetails saveRebateScheduleDetails(final RsDetails rebateScheduledetail) throws SystemException {
        LOGGER.info("In Query-saveRebateScheduleDetails P1:rebateScheduledetail");
        return RsDetailsLocalServiceUtil
                .addRsDetails(rebateScheduledetail);
    }

    /**
     * This method will fetch the rebateSchedule based on the systemId
     *
     * @param systemId
     * @return object of type RebateScheduleMaster
     * @throws SystemException
     * @throws PortalException
     */
    public RsModel fetchRebateScheduleMaster(final int systemId)
            throws SystemException, PortalException {
        LOGGER.info("In Query-fetchRebateScheduleMaster P1:systemId=" + systemId);
        return RsModelLocalServiceUtil
                .fetchRsModel(systemId);
    }

    /**
     * This method will delete the rebateScheduleDetails based on the systemId
     *
     * @param rebateScheduleDetails
     * @return object of type RebateScheduleDetails
     * @throws SystemException
     * @throws PortalException
     */
    public RsDetails deleteRebateScheduleDetails(
            final RsDetails rebateScheduledateil) throws SystemException,
            PortalException {
        LOGGER.info("In Query-deleteRebateScheduleDetails P1:rebateScheduledateil");
        return RsDetailsLocalServiceUtil
                .deleteRsDetails(rebateScheduledateil);
    }

    /**
     * This method will delete the rebateSchedule based on the systemId
     *
     * @param rebateScheduleMasterSystemId
     * @return object of type RebateScheduleMaster
     * @throws SystemException
     * @throws PortalException
     */
    public RsModel deleteRebateScheduleMaster(final int rebateScheduleMasterSystemId) throws SystemException,
            PortalException {
        LOGGER.info("In Query-deleteRebateScheduleMaster P1:rebateScheduleMasterSystemId=" + rebateScheduleMasterSystemId);
        return RsModelLocalServiceUtil
                .deleteRsModel(rebateScheduleMasterSystemId);
    }

    /**
     * This method will retrieve the list of rebateScheduleDetails based on the
     * query passed
     *
     * @param query - dynamic query of RebateScheduleDetails
     * @return list of type RebateScheduleDetails
     * @throws SystemException
     */
    public List<RsDetails> getRebateScheduleDetailsList(final DynamicQuery query) throws SystemException {
        LOGGER.info("In Query-getRebateScheduleDetailsList P1:query");
        return RsDetailsLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * This method will retrieve the Helper Table value based on the dynamic
     * query created
     *
     * @param query - dynamic query of HelperTable
     * @return list of type HelperTable
     * @throws SystemException
     */
    public List<HelperTable> getHelperTableList(final DynamicQuery query)
            throws SystemException {
        LOGGER.info("In Query-getHelperTableList P1:query");
        return HelperTableLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * This method will retrieve the values from Helper Table based on the
     * listName
     *
     * @param listName
     * @return list of type HelperTable
     * @throws SystemException
     */
    public List<HelperTable> getHelperTableDetailsByListName(final String listName)
            throws SystemException {
        LOGGER.info("In Query-getHelperTableDetailsByListName P1:ListName=" + listName);
        return HelperTableLocalServiceUtil.findByHelperTableDetails(listName);
    }

    /**
     * This method will retrieve the list of IFP based on the query passed
     *
     * @param query - dynamic query of IfpModel
     * @return list of type IfpModel
     * @throws SystemException
     */
    public List<IfpModel> getItemFamilyplanMasterList(final DynamicQuery query) throws SystemException {
        LOGGER.info("In Query-getItemFamilyplanMasterList P1:query");
        return IfpModelLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * This method will get the item master details that has been attached to
     * the IFP.
     *
     * @param ifp
     * @return list that contains item details
     */
    /*public List getItemDetailsOfIfp(final String ifp) {
        LOGGER.info("In Query-getItemDetailsOfIfp P1:ifp=" + ifp);
        return RsModelLocalServiceUtil.getItemDetailsOfIfp(ifp);
    }*/

    /**
     * This method will fetch the IFP based on the systemId
     *
     * @param systemId
     * @return object of type IfpModel
     * @throws SystemException
     */
    public IfpModel fetchItemFamilyplanMaster(final int systemId)
            throws SystemException {
        LOGGER.info("In Query-fetchItemFamilyplanMaster P1:systemId=" + systemId);
        return IfpModelLocalServiceUtil
                .fetchIfpModel(systemId);
    }

    /**
     * This method will retrieve rebateSchedule based on the systemID passed
     *
     * @param systemId
     * @return list that contains rebateSchedule values
     * @throws SystemException
     */
    public List getRebateScheduleDetails(final int systemId,Object future1,Object future2 ) throws SystemException {
        LOGGER.info("In Query-getRebateScheduleDetails P1:systemId=" + systemId);
        return RsModelLocalServiceUtil
                .getRebateScheduleDetails(systemId,future1,future2);
    }

    /**
     * This method will return the total count of the rebatePlan records
     *
     * @return total count of the records
     * @throws SystemException
     */
    public int getRebatePlanMasterTotalCount() throws SystemException {
        LOGGER.info("In Query-getRebatePlanMasterTotalCount");
        return RebatePlanMasterLocalServiceUtil.getRebatePlanMastersCount();
    }

    /**
     * This method will return the total count of the rebateSchedule records
     *
     * @return total count of the records
     * @throws SystemException
     */
    public int getRebateScheduleMasterTotalCount() throws SystemException {
        LOGGER.info("In Query-getRebateScheduleMasterTotalCount");
        return RsModelLocalServiceUtil.getRsModelsCount();
    }

    /**
     * This method will return the list of rebatePlan records within the
     * particular limit
     *
     * @param startIndex
     * @param endindex
     * @return list of type rebatePlan
     * @throws SystemException
     */
    public List<RebatePlanMaster> getRebatePlanMasterByLimit(final int startIndex,
            final int endindex) throws SystemException {
        LOGGER.info("In Query-getRebatePlanMasterByLimit startIndex=" + startIndex + " endindex=" + endindex);
        return RebatePlanMasterLocalServiceUtil.getRebatePlanMasters(
                startIndex, endindex);
    }

    /**
     * This method will return the count of the rebateSchedule records based on
     * the query passed
     *
     * @param query - dynamic query of RebatePlanMaster
     * @return count of the records
     * @throws SystemException
     */
    public int getRebatePlanMasterQueryCount(final DynamicQuery query)
            throws SystemException {
        LOGGER.info("In Query-getRebatePlanMasterQueryCount P1:query");
        return (int) RebatePlanMasterLocalServiceUtil.dynamicQueryCount(query);
    }

    /**
     * This method will return the list of rebateSchedule records within the
     * particular limit
     *
     * @param startIndex
     * @param endindex
     * @return list of type rebatePlan
     * @throws SystemException
     */
    public List<RsModel> getRebateScheduleMasterByLimit(
            final int startIndex, final int endindex) throws SystemException {
        LOGGER.info("In Query-getRebateScheduleMasterByLimit startIndex=" + startIndex + " endindex=" + endindex);
        return RsModelLocalServiceUtil.getRsModels(
                startIndex, endindex);
    }

    /**
     * This method will retrieve the list of rebateTierFormula based on the
     * query passed
     *
     * @param systemId
     * @return list of type tierFormula
     * @throws SystemException
     * @throws PortalException
     */
    public List<RebateTierFormula> getTierFormulaList(final DynamicQuery query)
            throws SystemException {
        LOGGER.info("In Query-getTierFormulaList P1:query");
        return RebateTierFormulaLocalServiceUtil.dynamicQuery(query);
    }

    /**
     * This method will retrieve the list of formula id based on the query
     * passed
     *
     * @param rsDynamicQuery
     * @return list of type formula id
     * @throws SystemException
     */
    public List getFormulaIdList(final DynamicQuery rsDynamicQuery) throws SystemException {
        LOGGER.info("In Query-getFormulaIdList P1:query");
        return RsDetailsLocalServiceUtil.dynamicQuery(rsDynamicQuery);
    }

    /**
     *
     * @param rebateScheduledateil
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public RsDetails updateRebateScheduleDetails(
            final RsDetails rebateScheduledateil) throws SystemException,
            PortalException {
        LOGGER.info("In Query-deleteRebateScheduleDetails P1:rebateScheduledateil");
        return RsDetailsLocalServiceUtil
                .updateRsDetails(rebateScheduledateil);
    }

    /**
     *
     * @param dynamicQuery
     * @return
     */
    public List<Object[]> getRebatePlanList(DynamicQuery dynamicQuery) throws PortalException, SystemException {
        return RebatePlanMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public List getRebatePlanNameList(final DynamicQuery query) throws PortalException, SystemException {
        return RebatePlanMasterLocalServiceUtil.dynamicQuery(query);
    }

    public int getRsModelQueryCount(DynamicQuery query) throws SystemException {
        LOGGER.info("In Query-getRsModelQueryCount P1:query");
        return (int) RsModelLocalServiceUtil.dynamicQueryCount(query);
    }
    
    public int getRsDetailsQueryCount(DynamicQuery query) throws SystemException {
        LOGGER.info("In Query-getRsModelQueryCount P1:query");
        return (int) RsDetailsLocalServiceUtil.dynamicQueryCount(query);
    }
}
