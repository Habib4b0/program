/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.dao;

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
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import java.util.List;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author vigneshkanna
 */
public interface DiscountDAO {

    public List getMarketType();

    public List<RemoveDiscountDto> getContracts(CustomFieldGroup removeDiscountDto,
            int startIndex, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumn);

    public int getContractsCount(CustomFieldGroup removeDiscountDto, Set<Container.Filter> filters);

    public List<RsContractDetails> getContractDetails(DynamicQuery contractQuery) throws SystemException;

    public ItemMaster getItemDetails(int itemId) throws SystemException, PortalException;

    public List getHelperTableListNames(final DynamicQuery dynamicQuery) throws SystemException, Exception;

    public int getItemsCount(DynamicQuery query) throws SystemException, Exception;

    public List getItems(DynamicQuery query) throws SystemException, Exception;

    public List getRebates(String query) throws SystemException, Exception;

    public List<IfpDetails> getItemsFromRs(DynamicQuery query) throws SystemException, Exception;

    public List getValues(String query) throws SystemException, Exception;

    public List<CfpModel> getCfpDetails(DynamicQuery cfpDynamicQuery) throws SystemException, Exception;

    public List<IfpModel> getIfpDetails(DynamicQuery cfpDynamicQuery) throws SystemException, Exception;

    RsModel getRebateScheduleMaster(int systemId) throws SystemException, PortalException;

    RsContract updateRsMasterAttached(RsContract rsMasterAttached) throws SystemException;

    RsContract addRsMasterAttached(RsContract rsMasterAttached) throws SystemException;

    public void updateRebate(String query);

    public List<RemoveDiscountDto> getRebateSearch(String field, String value, int contractSid, int rsSid);

    public List getSearchValues(CustomFieldGroup discountChBinder, int start, int offset, String moduleName, Set<Container.Filter> filters, List<SortByColumn> sortByColumns);

    public int getSearchCount(CustomFieldGroup discountChBinder, String moduleName, Set<Container.Filter> filters);

    public PsModel getPriceScheduleMaster(int modelSysId) throws SystemException, PortalException;

    public PsContract updatePsMasterAttached(PsContract psMasterAttached) throws SystemException;

    public PsContract addPsMasterAttached(PsContract psMasterAttached) throws SystemException;

    IfpModel getItemFamilyPlanMaster(int systemId) throws SystemException,
            PortalException;

    IfpContract updateIfpMasterAttached(IfpContract ifpMasterAttached)
            throws SystemException;

    IfpContract addIfpMasterAttached(IfpContract ifpMasterAttached)
            throws SystemException;

    CfpModel getCompanyFamilyplanMaster(int systemId) throws SystemException,
            PortalException;

    CfpContract updateCfpMasterAttached(CfpContract cfpMasterAttached)
            throws SystemException;

    CfpContract addCfpMasterAttached(CfpContract cfpMasterAttached)
            throws SystemException;
}
