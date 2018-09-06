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
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.vaadin.v7.data.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author vigneshkanna
 */
public class DiscountDaoImpl implements DiscountDAO {

    private final QueryUtils queryUtils = new QueryUtils();

    @Override
    public List<String> getMarketType() {
        String query = queryUtils.getRdMarketType();
        return (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    @Override
    public List<RemoveDiscountDto> getContracts(ErrorfulFieldGroup removeDiscountDto,
            int startIndex, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumn) {
        List<RemoveDiscountDto> resultLists;
        String query = queryUtils.getDiscContract(removeDiscountDto, filters, sortByColumn);
        query = query + " OFFSET " + startIndex + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
        resultLists = (List<RemoveDiscountDto>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    @Override
    public int getContractsCount(ErrorfulFieldGroup removeDiscountDto, Set<Container.Filter> filters) {
        String query = queryUtils.getDiscContract(removeDiscountDto, filters, new ArrayList<SortByColumn>());
        List<String> list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);

        return list.size();
    }

    @Override
    public List<RsContractDetails> getContractDetails(DynamicQuery contractQuery)  {
        return RsContractDetailsLocalServiceUtil.dynamicQuery(contractQuery);
    }

    @Override
    public ItemMaster getItemDetails(int itemMasterSid) throws PortalException {
        return ItemMasterLocalServiceUtil.getItemMaster(itemMasterSid);
    }

    @Override
    public List getHelperTableListNames(final DynamicQuery dynamicQuery)  {
        return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    @Override
    public int getItemsCount(DynamicQuery query)  {
        return (int) ItemMasterLocalServiceUtil.dynamicQueryCount(query);
    }

    @Override
    public List getItems(DynamicQuery query)  {
        return ItemMasterLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public List getRebates(String query)  {
        return (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    @Override
    public List<IfpDetails> getItemsFromRs(DynamicQuery query)  {
        return IfpDetailsLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public List getValues(String query) {
        return (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    @Override
    public List<CfpModel> getCfpDetails(DynamicQuery cfpDynamicQuery)  {
        return CfpModelLocalServiceUtil.dynamicQuery(cfpDynamicQuery);
    }

    @Override
    public List<IfpModel> getIfpDetails(DynamicQuery cfpDynamicQuery)  {
        return CfpModelLocalServiceUtil.dynamicQuery(cfpDynamicQuery);
    }

    @Override
    public RsContract addRsMasterAttached(final RsContract rsMasterAttached)  {
        return RsContractLocalServiceUtil.addRsContract(rsMasterAttached);
    }

    @Override
    public RsContract updateRsMasterAttached(final RsContract rsMasterAttached)  {
        return RsContractLocalServiceUtil.updateRsContract(rsMasterAttached);
    }

    @Override
    public RsModel getRebateScheduleMaster(final int systemId) throws PortalException {
        return RsModelLocalServiceUtil.getRsModel(systemId);
    }

    @Override
    public void updateRebate(String query) {
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    @Override
    public List<RemoveDiscountDto> getRebateSearch(String field, String value, int contractSid, int rsSid) {
        List<RemoveDiscountDto> resultLists;
        String query = queryUtils.getRebates(field, value, contractSid, rsSid);
        resultLists = (List<RemoveDiscountDto>) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists;
    }

    @Override
    public List getSearchValues(ErrorfulFieldGroup discountChBinder, int start, int offset, String moduleName, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) {
        String query = queryUtils.getSearchValues(discountChBinder, moduleName, start, offset, filters, sortByColumns);
        return (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    @Override
    public int getSearchCount(ErrorfulFieldGroup discountChBinder, String moduleName, Set<Container.Filter> filters) {
        String query = queryUtils.getSearchValuesCount(discountChBinder, moduleName, filters);
        List resultLists = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return resultLists.size();
    }

    @Override
    public PsModel getPriceScheduleMaster(final int systemId) throws PortalException {
        return PsModelLocalServiceUtil.getPsModel(systemId);
    }

    @Override
    public PsContract updatePsMasterAttached(final PsContract psMasterAttached)  {
        return PsContractLocalServiceUtil.updatePsContract(psMasterAttached);
    }

    @Override
    public PsContract addPsMasterAttached(final PsContract psMasterAttached)  {
        return PsContractLocalServiceUtil.addPsContract(psMasterAttached);
    }

    @Override
    public IfpContract addIfpMasterAttached(final IfpContract ifpMasterAttached)  {
        return IfpContractLocalServiceUtil.addIfpContract(ifpMasterAttached);
    }

    @Override
    public IfpContract updateIfpMasterAttached(final IfpContract ifpMasterAttached)  {
        return IfpContractLocalServiceUtil.updateIfpContract(ifpMasterAttached);
    }

    @Override
    public IfpModel getItemFamilyPlanMaster(final int systemId) throws PortalException {
        return IfpModelLocalServiceUtil.getIfpModel(systemId);
    }

    @Override
    public CfpModel getCompanyFamilyplanMaster(final int systemId) throws PortalException {
        return CfpModelLocalServiceUtil.getCfpModel(systemId);
    }

    @Override
    public CfpContract updateCfpMasterAttached(final CfpContract cfpMasterAttached)  {
        return CfpContractLocalServiceUtil.updateCfpContract(cfpMasterAttached);
    }

    @Override
    public CfpContract addCfpMasterAttached(final CfpContract cfpMasterAttached)  {
        return CfpContractLocalServiceUtil.addCfpContract(cfpMasterAttached);
    }
}
