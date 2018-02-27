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
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.v7.data.Container;
import java.util.List;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author vigneshkanna
 */
public interface DiscountDAO {

    public List getMarketType();

    public List<RemoveDiscountDto> getContracts(ErrorfulFieldGroup removeDiscountDto,
            int startIndex, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumn);

    public int getContractsCount(ErrorfulFieldGroup removeDiscountDto, Set<Container.Filter> filters);

    public List<RsContractDetails> getContractDetails(DynamicQuery contractQuery) ;

    public ItemMaster getItemDetails(int itemId) throws PortalException;

    public List getHelperTableListNames(final DynamicQuery dynamicQuery) ;

    public int getItemsCount(DynamicQuery query) ;

    public List getItems(DynamicQuery query) ;

    public List getRebates(String query) ;

    public List<IfpDetails> getItemsFromRs(DynamicQuery query) ;

    public List getValues(String query) ;

    public List<CfpModel> getCfpDetails(DynamicQuery cfpDynamicQuery) ;

    public List<IfpModel> getIfpDetails(DynamicQuery cfpDynamicQuery) ;

    RsModel getRebateScheduleMaster(int systemId) throws PortalException;

    RsContract updateRsMasterAttached(RsContract rsMasterAttached) ;

    RsContract addRsMasterAttached(RsContract rsMasterAttached) ;

    public void updateRebate(String query);

    public List<RemoveDiscountDto> getRebateSearch(String field, String value, int contractSid, int rsSid);

    public List getSearchValues(ErrorfulFieldGroup discountChBinder, int start, int offset, String moduleName, Set<Container.Filter> filters, List<SortByColumn> sortByColumns);

    public int getSearchCount(ErrorfulFieldGroup discountChBinder, String moduleName, Set<Container.Filter> filters);

    public PsModel getPriceScheduleMaster(int modelSysId) throws PortalException;

    public PsContract updatePsMasterAttached(PsContract psMasterAttached) ;

    public PsContract addPsMasterAttached(PsContract psMasterAttached) ;

    IfpModel getItemFamilyPlanMaster(int systemId) throws  PortalException;

    IfpContract updateIfpMasterAttached(IfpContract ifpMasterAttached);

    IfpContract addIfpMasterAttached(IfpContract ifpMasterAttached);

    CfpModel getCompanyFamilyplanMaster(int systemId) throws PortalException;

    CfpContract updateCfpMasterAttached(CfpContract cfpMasterAttached);

    CfpContract addCfpMasterAttached(CfpContract cfpMasterAttached);
}
