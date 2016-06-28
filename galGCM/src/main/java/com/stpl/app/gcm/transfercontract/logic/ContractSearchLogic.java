/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.logic;

import com.stpl.app.gcm.common.QueryUtils;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.gcm.transfercontract.dto.ContractSearchDTO;
import com.stpl.app.gcm.transfercontract.util.CommonUtil;
import com.stpl.app.gcm.transfercontract.util.Constant;
import com.stpl.app.gcm.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author harlin
 */
public class ContractSearchLogic {

    public static final Logger LOGGER = Logger.getLogger(ContractSearchLogic.class);
    QueryUtils queryUtils = new QueryUtils();

    public List<ContractSearchDTO> getPlaceHolderContractData(ContractSearchDTO binderDTO, int start, int offset,List<SortByColumn> sortByColumns) {
        List input = getInputForContractSearch(binderDTO, start, offset, false,sortByColumns);
        return configurePHContract(ItemQueries.getItemData(input, "Copy Contract-contract Search", null));
    }

    private List<ContractSearchDTO> configurePHContract(List resultList) {
        List<ContractSearchDTO> retList = new ArrayList<ContractSearchDTO>();
        for (int i = 0; i < resultList.size(); i++) {
            Object[] obj = (Object[]) resultList.get(i);
            ContractSearchDTO tempDTO = new ContractSearchDTO();
            tempDTO.setContractHolder(CommonUtil.getPureValue(String.valueOf(obj[1])));
            tempDTO.setContractNo(String.valueOf(obj[3]));
            tempDTO.setContractName(String.valueOf(obj[2]));
            tempDTO.setMarketTypeValue(CommonUtil.getPureValue(String.valueOf(obj[4])));
            tempDTO.setStartDate((Date) obj[5]);
            if (obj[6] != null) {
                tempDTO.setEndDate((Date) obj[6]);
            } else {
                tempDTO.setEndDate(null);
            }
            tempDTO.setCompanyFamilyPlan(CommonUtil.getPureValue(String.valueOf(obj[7])));
            tempDTO.setItemFamilyPlan(CommonUtil.getPureValue(String.valueOf(obj[8])));
            tempDTO.setPriceSchedule(CommonUtil.getPureValue(String.valueOf(obj[9])));
            tempDTO.setRebateSchedule(CommonUtil.getPureValue(String.valueOf(obj[10])));
            tempDTO.setContractSid(CommonUtil.getPureValue(String.valueOf(obj[11])));
            tempDTO.setCfpContractSid(CommonUtil.getPureValue(String.valueOf(obj[12])));
            tempDTO.setIfpContractSid(CommonUtil.getPureValue(String.valueOf(obj[13])));
            tempDTO.setPsContractSid(CommonUtil.getPureValue(String.valueOf(obj[14])));
            tempDTO.setRsContractSid(CommonUtil.getPureValue(String.valueOf(obj[15])));
            tempDTO.setCheck(obj[16] == null ? false : Boolean.TRUE);
            retList.add(tempDTO);
        }
        return retList;
    }

    public List<HelperDTO> getDropDownList(final String listType) throws SystemException {
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        LOGGER.info("Helper Table listType=" + listType);
        final DynamicQuery helperTableQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        helperTableQuery.add(RestrictionsFactoryUtil.like(Constants.LIST_NAME, listType));
        helperTableQuery.addOrder(OrderFactoryUtil.asc(Constants.DESCRIPTION));
        final List<HelperTable> list = HelperTableLocalServiceUtil.dynamicQuery(helperTableQuery);
        helperList.add(Constant.HELPER_DTO);
        if (list != null) {
            for (HelperTable temp : list) {
                final HelperTable helperTable = (HelperTable) temp;
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(),
                        helperTable.getDescription()));
            }
        }
        LOGGER.info("Helper Table list size =" + helperList.size());
        return helperList;
    }

    public Boolean isSearch(final ErrorfulFieldGroup binder) throws Exception {
        ContractSearchDTO binderDTO = ((BeanItem<ContractSearchDTO>) (binder.getItemDataSource())).getBean();
        return StringUtils.isNotBlank(binderDTO.getContractHolder()) || StringUtils.isNotBlank(binderDTO.getContractNo()) || StringUtils.isNotBlank(binderDTO.getContractName())
                || StringUtils.isNotBlank(binderDTO.getAliasNumber()) || (binderDTO.getAliastypecc() != null) || binderDTO.getAliasStartDate() != null
                || binderDTO.getAliasEndDate() != null || StringUtils.isNotBlank(binderDTO.getCompanyFamilyPlan()) || StringUtils.isNotBlank(binderDTO.getItemFamilyPlan())
                || StringUtils.isNotBlank(binderDTO.getPriceSchedule()) || StringUtils.isNotBlank(binderDTO.getRebateSchedule()) || StringUtils.isNotBlank(binderDTO.getCustomerNo())
                || StringUtils.isNotBlank(binderDTO.getCustomerName()) || StringUtils.isNotBlank(binderDTO.getItemNo()) || StringUtils.isNotBlank(binderDTO.getItemName())
                || binderDTO.getStartDate() != null || binderDTO.getEndDate() != null || (binderDTO.getMarketType() != null);
    }

    public List getInputForContractSearch(ContractSearchDTO binderDTO, int start, int offset, boolean isCount,List<SortByColumn> sortByColumns) {
        List input = new ArrayList();
        String columnName = StringUtils.EMPTY;
        if (binderDTO.getAliasNumber() != null && !binderDTO.getAliasNumber().isEmpty()) {
            input.add(binderDTO.getAliasNumber().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDTO.getAliastypecc() != null && !binderDTO.getAliastypecc().isEmpty()) {
            input.add(binderDTO.getAliastypecc().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDTO.getCompanyFamilyPlan() != null && !binderDTO.getCompanyFamilyPlan().isEmpty()) {
            input.add(binderDTO.getCompanyFamilyPlan().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDTO.getItemFamilyPlan() != null && !binderDTO.getItemFamilyPlan().isEmpty()) {
            input.add(binderDTO.getItemFamilyPlan().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (binderDTO.getPriceSchedule() != null && !binderDTO.getPriceSchedule().isEmpty()) {
            input.add(binderDTO.getPriceSchedule().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (!isCount) {
            input.add(binderDTO.getSessionId());
            input.add(binderDTO.getUserId());
        }
        if (binderDTO.getHiddenId() != null && !binderDTO.getHiddenId().isEmpty()) {
            input.add(binderDTO.getHiddenId());
        } else {
            input.add("%");
        }
        if (binderDTO.getContractNo() != null && !binderDTO.getContractNo().isEmpty()) {
            input.add(binderDTO.getContractNo().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDTO.getContractName() != null && !binderDTO.getContractName().isEmpty()) {
            input.add(binderDTO.getContractName().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDTO.getMarketType() != null && !binderDTO.getMarketType().isEmpty()) {
            input.add(binderDTO.getMarketType().replace("*", "%"));
        } else {
            input.add("%");
        }
        
        
        if (binderDTO.getStartDate() != null) {
            input.add(" AND CM.START_DATE > '" + CommonUtil.getDBDate(binderDTO.getStartDate()) + "'");
        } else {
            input.add(StringUtils.EMPTY);
        }
        if (binderDTO.getEndDate() != null) {
            input.add(" AND CM.end_date = < '" + CommonUtil.getDBDate(binderDTO.getEndDate()) + "'");
        } else {
            input.add(StringUtils.EMPTY);
        }
        if (binderDTO.getAliasStartDate() != null) {
            input.add(" AND CAM.START_DATE > '" + CommonUtil.getDBDate(binderDTO.getAliasStartDate()) + "'");
        } else {
            input.add(StringUtils.EMPTY);
        }
        if (binderDTO.getAliasEndDate() != null) {
            input.add(" AND cam.end_date = < '" + CommonUtil.getDBDate(binderDTO.getAliasEndDate()) + "'");
        } else {
            input.add(StringUtils.EMPTY);
        }
        boolean asc = false;
        if (!isCount) {
            if (sortByColumns != null && !sortByColumns.isEmpty()) {
                for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                    final SortByColumn sortByColumn = (SortByColumn) iterator.next();

                    columnName = sortByColumn.getName();
                    asc = sortByColumn.getType() == SortByColumn.Type.ASC;
                }
                input.add("order by " + columnName +" "+(asc ? "ASC " : "DESC ")+ " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY " );
            } else {
                input.add("order by CONTRACT_MASTER_SID OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY ;");
            }
        
        }
        return input;
    }
}
