/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.dao.impl;

import com.stpl.app.gcm.common.QueryUtils;
import com.stpl.app.gcm.discount.dao.DiscountDAO;
import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.stpl.app.model.CfpContract;
import com.stpl.app.model.CfpModel;
import com.stpl.app.model.IfpContract;
import com.stpl.app.model.IfpDetails;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.PsContract;
import com.stpl.app.model.PsModel;
import com.stpl.app.model.RsContract;
import com.stpl.app.model.RsContractDetails;
import com.stpl.app.model.RsModel;
import com.stpl.app.service.CfpContractLocalServiceUtil;
import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.IfpContractLocalServiceUtil;
import com.stpl.app.service.IfpDetailsLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.PsContractLocalServiceUtil;
import com.stpl.app.service.PsModelLocalServiceUtil;
import com.stpl.app.service.RsContractDetailsLocalServiceUtil;
import com.stpl.app.service.RsContractLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author vigneshkanna
 */
public class DiscountDaoImpl implements DiscountDAO {

    QueryUtils queryUtils = new QueryUtils();

    public List<String> getMarketType() {
        String query = queryUtils.rdMarketType;
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);

        return list;
    }

    public List<RemoveDiscountDto> getContracts(CustomFieldGroup removeDiscountDto,
            int startIndex, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumn) {
        List<RemoveDiscountDto> resultLists;
        String query = queryUtils.getDiscContract(removeDiscountDto, filters, sortByColumn);
        query = query + " OFFSET " + startIndex + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
        resultLists = (List<RemoveDiscountDto>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    public int getContractsCount(CustomFieldGroup removeDiscountDto, Set<Container.Filter> filters) {
        String query = queryUtils.getDiscContract(removeDiscountDto, filters, new ArrayList<SortByColumn>());
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);

        return list.size();
    }

    public List<RsContractDetails> getContractDetails(DynamicQuery contractQuery) throws SystemException {
        return RsContractDetailsLocalServiceUtil.dynamicQuery(contractQuery);
    }

    public ItemMaster getItemDetails(int itemMasterSid) throws SystemException, PortalException {
        return ItemMasterLocalServiceUtil.getItemMaster(itemMasterSid);
    }

    public List getHelperTableListNames(final DynamicQuery dynamicQuery) throws SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public int getItemsCount(DynamicQuery query) throws SystemException {
        return (int) ItemMasterLocalServiceUtil.dynamicQueryCount(query);
    }

    public List getItems(DynamicQuery query) throws SystemException {
        return ItemMasterLocalServiceUtil.dynamicQuery(query);
    }

    public List getRebates(String query) throws SystemException {
        return (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    public List<IfpDetails> getItemsFromRs(DynamicQuery query) throws SystemException {
        return IfpDetailsLocalServiceUtil.dynamicQuery(query);
    }

    public List getValues(String query) {
        return (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    public List<CfpModel> getCfpDetails(DynamicQuery cfpDynamicQuery) throws SystemException {
        return CfpModelLocalServiceUtil.dynamicQuery(cfpDynamicQuery);
    }

    public List<IfpModel> getIfpDetails(DynamicQuery cfpDynamicQuery) throws SystemException {
        return CfpModelLocalServiceUtil.dynamicQuery(cfpDynamicQuery);
    }

    public RsContract addRsMasterAttached(final RsContract rsMasterAttached) throws SystemException {
        return RsContractLocalServiceUtil.addRsContract(rsMasterAttached);
    }

    public RsContract updateRsMasterAttached(final RsContract rsMasterAttached) throws SystemException {
        return RsContractLocalServiceUtil.updateRsContract(rsMasterAttached);
    }

    public RsModel getRebateScheduleMaster(final int systemId) throws SystemException, PortalException {
        return RsModelLocalServiceUtil.getRsModel(systemId);
    }

    public void updateRebate(String query) {
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public List<RemoveDiscountDto> getRebateSearch(String field, String value, int contractSid, int rsSid) {
        List<RemoveDiscountDto> resultLists;
        String query = queryUtils.getRebates(field, value, contractSid, rsSid);
        resultLists = (List<RemoveDiscountDto>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    public List getSearchValues(CustomFieldGroup discountChBinder, int start, int offset, String moduleName, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) {
        String query = queryUtils.getSearchValues(discountChBinder, moduleName, start, offset, filters, sortByColumns);
        List resultLists = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    public int getSearchCount(CustomFieldGroup discountChBinder, String moduleName, Set<Container.Filter> filters) {
        String query = queryUtils.getSearchValuesCount(discountChBinder, moduleName, filters);
        List resultLists = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists.size();
    }

    public PsModel getPriceScheduleMaster(final int systemId) throws SystemException, PortalException {
        return PsModelLocalServiceUtil.getPsModel(systemId);
    }

    public PsContract updatePsMasterAttached(final PsContract psMasterAttached) throws SystemException {
        return PsContractLocalServiceUtil.updatePsContract(psMasterAttached);
    }

    public PsContract addPsMasterAttached(final PsContract psMasterAttached) throws SystemException {
        return PsContractLocalServiceUtil.addPsContract(psMasterAttached);
    }

    public IfpContract addIfpMasterAttached(final IfpContract ifpMasterAttached) throws SystemException {
        return IfpContractLocalServiceUtil.addIfpContract(ifpMasterAttached);
    }

    public IfpContract updateIfpMasterAttached(final IfpContract ifpMasterAttached) throws SystemException {
        return IfpContractLocalServiceUtil.updateIfpContract(ifpMasterAttached);
    }

    public IfpModel getItemFamilyPlanMaster(final int systemId) throws SystemException, PortalException {
        return IfpModelLocalServiceUtil.getIfpModel(systemId);
    }

    public CfpModel getCompanyFamilyplanMaster(final int systemId) throws SystemException, PortalException {
        return CfpModelLocalServiceUtil.getCfpModel(systemId);
    }

    public CfpContract updateCfpMasterAttached(final CfpContract cfpMasterAttached) throws SystemException {
        return CfpContractLocalServiceUtil.updateCfpContract(cfpMasterAttached);
    }

    public CfpContract addCfpMasterAttached(final CfpContract cfpMasterAttached) throws SystemException {
        return CfpContractLocalServiceUtil.addCfpContract(cfpMasterAttached);
    }
}
