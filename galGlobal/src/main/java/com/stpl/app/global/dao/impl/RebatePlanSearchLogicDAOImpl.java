/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.dao.impl;


import com.stpl.app.model.HelperTable;
import com.stpl.app.model.RebatePlanMaster;
import com.stpl.app.model.RebatePlanTier;
import com.stpl.app.model.RebateTierFormula;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.RebatePlanMasterLocalServiceUtil;
import com.stpl.app.service.RebatePlanTierLocalServiceUtil;
import com.stpl.app.service.RebateTierFormulaLocalServiceUtil;
import com.stpl.domain.global.rebateplan.RebatePlanDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.List;
import org.jboss.logging.Logger;

/**
 * DAO operations for RebatePlanSearchLogic
 * @author shrihariharan
 */
public class RebatePlanSearchLogicDAOImpl implements RebatePlanDAO {
	
	/**
     * The Constant LOGGER.
     */   
private static final Logger LOGGER = Logger.getLogger(RebatePlanSearchLogicDAOImpl.class);
    /**
	 * This method will retrieve the rebatePlan value based on the dynamic query
	 * created
	 * 
	 * @param query - dynamic query of RebatePlanMaster
	 * @return list of type RebatePlanMaster
	 * @throws SystemException
	 */
	  public List<RebatePlanMaster> getRebatePlanMasterList(final DynamicQuery query) throws SystemException {
          LOGGER.info("Query hit to retrive the List for Reabate plan master ,List size = "+RebatePlanMasterLocalServiceUtil.dynamicQuery(query).size() );
        return RebatePlanMasterLocalServiceUtil.dynamicQuery(query);
    }

	/**
	 * This method will retrieve the rebatePlan master based on the systemID
	 * 
	 * @param systemId
	 * @return object of rebatePlan
	 * @throws SystemException
	 * @throws PortalException
	 */
	 public RebatePlanMaster getRebatePlanMasterBySystemId(final int systemId) throws SystemException, PortalException {
         LOGGER.info("Query hit for Reabate plan master ,systemId ="+systemId);
        return RebatePlanMasterLocalServiceUtil.getRebatePlanMaster(systemId);
    }
	/**
	 * This method will retrieve the Helper Table value based on the dynamic
	 * query created
	 * 
	 * @param query - dynamic query of HelperTable
	 * @return list of type HelperTable
	 * @throws SystemException
	 */
	 public List<HelperTable> getHelperTableList(final DynamicQuery query) throws SystemException {
         LOGGER.info("Query hit for getHelperTableList ");
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
	  public List<HelperTable> getHelperTableDetailsByListName(final String listName) throws SystemException {
        LOGGER.info("Query Hit for getting form DDLB values from Helper DTO, listName = "+listName);
        return HelperTableLocalServiceUtil.findByHelperTableDetails(listName);
    }


	/**
	 * This method will fetch the rebatePlan master based on the systemID
	 * 
	 * @param systemId
	 * @return object of rebatePlan
	 * @throws SystemException
	 * @throws PortalException
	 */
	 public RebatePlanMaster fetchRebatePlanMaster(final int rebatePlanSystemId) throws SystemException, PortalException {
        LOGGER.info("Query Hit to Fetch with rebatePlanSystemId ="+rebatePlanSystemId);
       return RebatePlanMasterLocalServiceUtil.fetchRebatePlanMaster(rebatePlanSystemId);
    }

	/**
	 * This method will update the edited rebatePlan based on the systemId
	 * 
	 * @param rebatePlanMaster
	 * @return object of rebatePlan
	 * @throws SystemException
	 */
	 public RebatePlanMaster updateRebatePlanMaster(final RebatePlanMaster rebatePlanMaster) throws SystemException {
         LOGGER.info("Query Hit for : updateRebatePlanMaster in RebatePlanMaster table");
        return RebatePlanMasterLocalServiceUtil.updateRebatePlanMaster(rebatePlanMaster);
    }

	/**
	 * This method will save the newly created rebatePlan
	 * 
	 * @param rebatePlanMaster
	 * @return object of rebatePlan
	 * @throws SystemException
	 */
	 public RebatePlanMaster saveRebatePlanMaster(final RebatePlanMaster rebatePlanMaster) throws SystemException {
        LOGGER.info("Query Hit to saveRebatePlanMaster");
       return RebatePlanMasterLocalServiceUtil.addRebatePlanMaster(rebatePlanMaster);
    }

	/**
	 * This method will save the newly created rebatePlanTier
	 * 
	 * @param rebatePlanTier
	 * @return object of type RebatePlanTier
	 * @throws SystemException
	 */
	   public RebatePlanTier saveRebatePlanTier(final RebatePlanTier rebatePlanTier) throws SystemException {
         LOGGER.info("Query Hit to saveRebatePlanTier");
       return RebatePlanTierLocalServiceUtil.addRebatePlanTier(rebatePlanTier);
    }
	/**
	 * This method will retrieve the rebatePlanTier value based on the dynamic
	 * query passed as an argument
	 * 
	 * @param query - dynamic query of RebatePlanTier
	 * @return list of type RebatePlanTier
	 * @throws SystemException
	 */
	  public List<RebatePlanTier> getRebatePlanTierList(final DynamicQuery query) throws SystemException {
         LOGGER.info("Query Hit for : getRebatePlanTierList method, DynamicQuery="+query);
        return RebatePlanTierLocalServiceUtil.dynamicQuery(query);
    }
	/**
	 * This method is to delete the selected rebatePlanTier based on the
	 * rebatePlanTierSystemId
	 * 
	 * @param rebatePlanTierSystemId
	 * @return object of type RebatePlanTier
	 * @throws SystemException
	 * @throws PortalException
	 */
	  public RebatePlanTier deleteRebatePlanTier(final int rebatePlanTierSystemId) throws SystemException,PortalException {
        LOGGER.info("Query Hit for : deleteRebatePlanTier with, rebatePlanTierSystemId of "+rebatePlanTierSystemId);
        return RebatePlanTierLocalServiceUtil.deleteRebatePlanTier(rebatePlanTierSystemId);
    }

	/**
	 * This method is to delete the selected rebatePlan based on the
	 * rebatePlanMasterSystemId
	 * 
	 * @param rebatePlanMasterSystemId
	 * @return RebatePlanMaster modal object
	 * @throws SystemException
	 * @throws PortalException
	 */
	 public RebatePlanMaster deleteRebatePlanMaster(final int rebatePlanMasterSystemId) throws SystemException, PortalException {
        LOGGER.info("Query Hit for : deleteRebatePlanMaster with, rebatePlanMasterSystemId of "+rebatePlanMasterSystemId);
        return RebatePlanMasterLocalServiceUtil.deleteRebatePlanMaster(rebatePlanMasterSystemId);
    }

	/**
	 * This method will return the count of the rebateplan records based on the
	 * query passed
	 * 
	 * @param query - dynamic query of RebatePlanMaster
	 * @return count of the records
	 * @throws SystemException
	 */
	   public int getRebatePlanMasterQueryCount(final DynamicQuery query) throws SystemException {
           LOGGER.info("Query Hit for getting getRebatePlanMasterQueryCount from RebatePlanMaster Table ");
        return (int) RebatePlanMasterLocalServiceUtil.dynamicQueryCount(query);
    }

	/**
	 * This method will return the total count of the rebatePlan records
	 * 
	 * @return total count of the records
	 * @throws SystemException
	 */
	  public int getRebatePlanMasterTotalCount()throws SystemException{
        LOGGER.info("Query Hit for getting getRebatePlanMasterTotalCount from RebatePlanMaster Table ");
        return RebatePlanMasterLocalServiceUtil.getRebatePlanMastersCount();
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
	  public List<RebatePlanMaster> getRebatePlanMasterByLimit(final int startIndex, final int endindex) throws SystemException {
         LOGGER.info("Query Hit for getting getRebatePlanMasterByLimit  from RebatePlanMaster Table ");
      return RebatePlanMasterLocalServiceUtil.getRebatePlanMasters(startIndex, endindex);
    }

	/**
	 * This method will retrieve the rebateTierFormula based on the query passed
	 * 
	 * @param systemId
	 * @return list of tierFormulas of type String
	 * @throws SystemException
	 * @throws PortalException
	 */
	  public List<String> getTierFormula(final DynamicQuery query) throws SystemException, PortalException {
            LOGGER.info("Query Hit for getting getTierFormula  from RebateTierFormula Table ");
        return RebateTierFormulaLocalServiceUtil.dynamicQuery(query);
    }

	/**
	 * This method will retrieve the rebateTierFormula based on the systemId
	 * 
	 * @param systemId
	 * @return object of RebateTierFormula
	 * @throws SystemException
	 * @throws PortalException
	 */
	  public RebateTierFormula getRebateTierFormula(final int systemId) throws SystemException,PortalException {
         LOGGER.info("Query Hit for getting getRebateTierFormula  from RebateTierFormula Table, systemId = "+systemId);
        return RebateTierFormulaLocalServiceUtil.getRebateTierFormula(systemId);
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
	 public List<RebateTierFormula> getTierFormulaList(final DynamicQuery query) throws SystemException {
         LOGGER.info("Query Hit for getting formula Id from RebateTierFormula Table, DynamicQuery = ");
        return RebateTierFormulaLocalServiceUtil.dynamicQuery(query);
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
	 public List getTierFormulaIdList(final DynamicQuery query) throws SystemException {
         LOGGER.info("Query Hit for getting formula Id from RebateTierFormula Table, DynamicQuery = ");
        return RebateTierFormulaLocalServiceUtil.dynamicQuery(query);
    }
}
