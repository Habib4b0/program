/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.logic;

import com.stpl.app.model.AccrualDetails;
import com.stpl.app.transactional.common.dto.AccrualdetailsDTO;
import com.stpl.app.transactional.common.dto.DetailsDTO;
import com.stpl.app.util.AbstractNotificationUtils;
import com.stpl.app.util.ConstantUtil;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author Maheshj
 */
public class TransactionalSearchLogic extends PageTableLogic {

    Map<Object, Object> searchValues;
    private String className = StringUtils.EMPTY;
    List<DetailsDTO> moduleDetailsValue = null;
    private String tableName;
    SearchLogic searchLogic = new SearchLogic();
    Map<Integer, Boolean> SelectedAccrualsIdMap = new LinkedHashMap<Integer, Boolean>();
    AccrualdetailsDTO accDto = null;
    boolean firstGenerated = false;
    boolean isReset =true;
    public static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(TransactionalSearchLogic.class);
    DetailsDTO primaryDTO;
    
    public void configureSearchLogic(final DetailsDTO primaryDTO){
        this.primaryDTO = primaryDTO;
    }
    /**
     * Loads the table in sales projection based on start and end index.
     *
     * @param start
     * @param offset
     * @return
     */
    /**
     * Returns the available number of records based on the generate criteria.
     *
     * @return
     */
    @Override
    public int getCount() {
        int count = 0;
        LOGGER.info("Entering count with search SearchDAO---> tableName====== " + tableName);
        if(!isReset){
        try {

            if (tableName != null) {

                if (searchValues.isEmpty() && this.getFilters() == null) {
                    count = (int) searchLogic.getActualsTotalCount(tableName);
                } else {
                    if ("ActualsMaster".equals(tableName) || "IvldActualMaster".equals(tableName)) {
                        count = searchLogic.getActualSearchResults(searchValues, this.getFilters(), tableName);
                    } else {
                        if (searchValues != null && searchValues.get("accrualType") != null 
                                && !searchValues.get("accrualType").equals("Other") 
                                && !tableName.equals(ConstantUtil.INVALID_ACCURAL_INBOUND)) {
                            tableName = "AccrualDetails";
                        }
                        count = searchLogic.getDynamicQuerySearch(searchValues, this.getFilters(), tableName);
                    }
                }
            }
            LOGGER.info("Ends count with count value :::: " + count);
            return count;
        } catch (SystemException e) {
            LOGGER.error(e.getMessage());
//            e.printStackTrace();
        } catch (PortalException e) {
            LOGGER.error(e.getMessage());
//            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
//            e.printStackTrace();
        }
        }
        return 0;

    }

    /**
     *
     * @param object
     * @param datasource
     * @return
     */
    @Override
    public Object configureContainer(Object object, Container datasource) {
        Class cl = null;
        try {
            if ((tableName.equals(ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW)) || (tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)) || (tableName.equals(ConstantUtil.INVALID_GTS_CUSTOMER)) || (tableName.equals(ConstantUtil.ADJUSTED_DEMAND_VIEW))
                    || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL) || tableName.equals(ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL) || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(tableName)) {
                cl = Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + tableName);
            } else if ("AccrualDetails".equals(tableName)) {
                cl = Class.forName("com.stpl.app.model.AccrualDetails");
            } else {
                cl = Class.forName(ConstantUtil.TABLE_MODEL_PATH + tableName);
            }
            if ("AccrualDetails".equals(tableName)) {
                ((BeanItemContainer<Object>) datasource).addBean(getAccuralsDTO((AccrualDetails) (object)));

            } else {
                ((BeanItemContainer<Object>) datasource).addBean(cl.cast(object));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cl.cast(object);
    }

    public void groupChange() {
        clearAll();
        setCurrentPage(1);
    }

    public int getPageForItem(int pos) {
        int curPage = ((pos - 2) / getPageLength()) + 1;
        return curPage;
    }

    public int getItemIndex(int pos) {
        int index = (pos - 2) % getPageLength();
        return index;
    }

    public int getStartIndex(int count, int index) {
        int start = count - index;
        return start;
    }

    @Override
    protected void createCurrentPageStart() {

        setRefresh(Boolean.FALSE);
    }

    @Override
    protected void createCurrentPageEnd() {

        setRefresh(Boolean.TRUE);
    }

    @Override
    public List loadData(int start, int offset) {
        List<Object> salesList1 = new ArrayList<Object>();

        LOGGER.info("map size" + SelectedAccrualsIdMap.size());
        LOGGER.info("Entering find with startIndex value :::: " + start + " , offset value :::: " + offset + " and columns size ::::" + this.getSortByColumns().size() + " and criteria -->");
        try {
            if (tableName != null) {
                if ("ActualsMaster".equals(tableName) || "IvldActualMaster".equals(tableName)) {
                    salesList1 = searchLogic.searchFindForActualMaster(searchValues, start, start + offset, this.getSortByColumns(), this.getFilters(), tableName, moduleDetailsValue, false, null,primaryDTO);
                } else {
                    if (searchValues != null && searchValues.get("accrualType") != null 
                            && !searchValues.get("accrualType").equals("Other") 
                            && !tableName.equals(ConstantUtil.INVALID_ACCURAL_INBOUND)) {
                        tableName = "AccrualDetails";
                    }
                    salesList1 = searchLogic.searchFind(searchValues, start, start + offset, this.getSortByColumns(), this.getFilters(), tableName, moduleDetailsValue, false, null, SelectedAccrualsIdMap,primaryDTO);
                }

                LOGGER.info("Ends find with salesList1 size ::::" + salesList1.size());
            }
        } catch (SystemException e) {
//            e.printStackTrace();
            final String errorMsg = ErrorCodeUtil.getErrorMessage(e);
            LOGGER.error(errorMsg);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        } catch (PortalException e) {
//            e.printStackTrace();
            LOGGER.error(e.getMessage());
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_3002));
        } catch (Exception e) {
//            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return salesList1;
    }

    public void configureSearchData(final Map<Object, Object> searchValues, String tableName, final List<DetailsDTO> moduleDetailsValue, Map AccrualIds,boolean isReset) {
        this.clearAll();
        this.getFilters().clear();
        String className = StringUtils.EMPTY;
        this.tableName = tableName;
        this.isReset=isReset;
        if ((tableName.equals("VwCustomerGtsForecast")) || (tableName.equals("IvldCustomerGtsForecast")) || tableName.equals("VwAdjustDemandForecastAct")
                || ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW.equals(tableName) || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL)
                || tableName.equals(ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL)) {
            className = ConstantUtil.TABLE_SERVICE_PATH_TWO + tableName + ConstantUtil.TABLE_UTIL_APPEND;
        } else {
            className = ConstantUtil.TABLE_SERVICE_PATH + tableName + ConstantUtil.TABLE_UTIL_APPEND;
        }

        this.className = className;
        this.searchValues = searchValues;
        this.moduleDetailsValue = moduleDetailsValue;
        this.SelectedAccrualsIdMap = AccrualIds;
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }

    @Override
    public void setContainerDataSource(Container newDataSource) {
        if (!(newDataSource instanceof Container.Indexed)
                || !(newDataSource instanceof Container.Filterable)) {
            throw new IllegalArgumentException(
                    "ExtPagedFilterTable can only use containers that implement Container.Indexed, Container.Filterable AND Container.Hierarchical");
        }
        container = newDataSource;
        for (ExtPagedTable tbl : getTables()) {
            tbl.setContainerDataSource(container);

        }
    }

    private AccrualdetailsDTO getAccuralsDTO(AccrualDetails acDetails) {
        accDto = new AccrualdetailsDTO();
        accDto.setGlCompanyName(acDetails.getCompanyId());
        accDto.setAccrualId(acDetails.getAccountId());
        accDto.setAccountName(acDetails.getAccountName());
        accDto.setAccountNo(acDetails.getAccountNo());
        accDto.setAccrualDetailsSid(acDetails.getAccrualDetailsSid());
        accDto.setAccrualId(acDetails.getAccrualId());
        accDto.setAccrualPeriodStartDate(acDetails.getAccrualPeriodStartDate());
        accDto.setAccrualPeriodEndDate(acDetails.getAccrualPeriodEndDate());
        acDetails.getAcctIdentifierCodeQualifier();
        acDetails.getAmount();
        accDto.setBatchId(acDetails.getBatchId());
        accDto.setBrandName(acDetails.getBrandName());
        accDto.setCompanyCostCenter(acDetails.getCompanyCostCenter());
        accDto.setContractNo(acDetails.getContractNo());
        accDto.setCreatedBy(String.valueOf(acDetails.getCreatedBy()));
        accDto.setCreatedDate(acDetails.getCreatedDate());
        accDto.setItemName(acDetails.getItemName());
        accDto.setItemNo(acDetails.getItemNo());
        accDto.setPostingDate(acDetails.getPostingDate());
        accDto.setPostingIndicator(acDetails.getPostingIndicator());
        accDto.setProgramNo(acDetails.getProgramNo());
        accDto.setProgramType(String.valueOf(acDetails.getProgramType()));
        accDto.setRecordLockStatus(acDetails.getRecordLockStatus());
        accDto.setSalesMasterId(acDetails.getSalesMasterId());
        accDto.setSource(acDetails.getSource());
        accDto.setSubLedger(acDetails.getSubLedger());
        accDto.setSubLedgerType(acDetails.getSubLedgerType());

        return accDto;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}
