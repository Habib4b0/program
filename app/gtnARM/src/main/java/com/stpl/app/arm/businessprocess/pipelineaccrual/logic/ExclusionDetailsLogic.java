/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.arm.adjustmentrateconfiguration.dto.ExclusionLookupDTO;
import com.stpl.app.arm.adjustmentrateconfiguration.dto.ViewLookupDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.CustomerGroupDTO;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dao.CommonDao;
import com.stpl.app.arm.dao.impl.CommonImpl;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.VariableConstants;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.Between;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gopinath.Mathiyalaga
 */
public class ExclusionDetailsLogic {

    private static final CommonDao DAO = CommonImpl.getInstance();
    protected Map<String, String> userMap = new HashMap<>();
    public static final Logger LOGGER = LoggerFactory.getLogger(ExclusionDetailsLogic.class);

    public List<ExclusionLookupDTO> getCompanySid(String viewSid) {
        List<ExclusionLookupDTO> finalList = Collections.emptyList();
        try {
            String query = SQlUtil.getQuery("getExclusionViewDetails");
            query = query.replace(CommonConstant.ARM_VIEW_MASTER_SID, viewSid);
            List<Object[]> rawList = QueryUtils.executeSelect(query);
            if (!rawList.isEmpty()) {
                finalList = new ArrayList();
                for (int i = 0; i < rawList.size(); i++) {
                    Object[] obj = rawList.get(i);
                    ExclusionLookupDTO dto = new ExclusionLookupDTO();
                    dto.setExcludedField(String.valueOf(obj[0]));
                    dto.setValues(String.valueOf(obj[1]));
                    finalList.add(dto);
                }
            }
            return finalList;
        } catch (Exception e) {
            LOGGER.error("Error in getCompanySid :", e);
            return finalList;
        }
    }

    public void saveORUpdateExclusionDetailsLookUp(int projectionSid, List<ExclusionLookupDTO> list, String accountId, String accountName, String contractId, SessionDTO sessionDTO) {
        StringBuilder sbQuery = new StringBuilder();
        boolean isView = sessionDTO.getAction().equals(ARMUtils.VIEW_SMALL);
        String saveQuery = isView ? SQlUtil.getQuery("saveORUpdateQuery") : SQlUtil.getQuery("saveORUpdateQueryEdit");
        saveQuery = saveQuery.replace(CommonConstant.PROJECTION_MASTER_SID, String.valueOf(projectionSid));
        sbQuery.append(saveQuery);
        for (ExclusionLookupDTO dtoList : list) {
            sbQuery.append(ARMUtils.OPEN_PARANTHESIS).append(projectionSid).append(",'").append(dtoList.getExcludedField()).append(ARMUtils.SINGLE_QUOTES).append(",'").append(dtoList.getValues()).append("'),");
        }
        sbQuery.replace(sbQuery.length() - 1, sbQuery.length(), "");
        String query = isView ? SQlUtil.getQuery("saveORUpdateQueryPipeline") : SQlUtil.getQuery("saveORUpdateQueryPipelineEdit");
        query = query.replace("@COMPANY_ID", accountId);
        query = query.replace("@COMPANY_NAME", accountName);
        query = query.replace("@CONTRACT_ID", contractId);
        query = query.replace(CommonConstant.PROJECTION_MASTER_SID, String.valueOf(projectionSid));
        query = query.replace("[TABLE]", "" + sessionDTO.getCurrentTableNames().get("ST_ARM_PIPELINE_EXCLUSION_DETAILS"));
        sbQuery.append(" ; ").append(query);
        DAO.executeUpdate(sbQuery.toString());
    }

    public List<ExclusionLookupDTO> getFieldListValue(String fieldValue, String prevSelectedValues) {
        try {
            String query = QueryUtils.buildFieldNameselectQuery(fieldValue, prevSelectedValues);
            List<ExclusionLookupDTO> finalList = new ArrayList<>();
            List<Object[]> rawList = QueryUtils.executeSelect(query);
            if (rawList == null || rawList.isEmpty()) {
                return Collections.emptyList();
            }
            if (!rawList.isEmpty()) {
                for (int i = 0; i < rawList.size(); i++) {
                    ExclusionLookupDTO dtoValue = new ExclusionLookupDTO();
                    dtoValue.setValues(String.valueOf(rawList.get(i)));
                    dtoValue.setExcludedField(fieldValue);
                    finalList.add(dtoValue);
                }
            }
            return finalList;
        } catch (Exception e) {
            LOGGER.error("Error in getFieldListValue", e);
            return Collections.emptyList();
        }
    }

    public List<ExclusionLookupDTO> getIntialLoadValue(int rateDetailsSid) {
        List<ExclusionLookupDTO> finalList = new ArrayList<>();
        try {
            String query = SQlUtil.getQuery("getIntialLoadQuery");
            query = query.replace(CommonConstant.PROJECTION_MASTER_SID, String.valueOf(rateDetailsSid));
            List<Object[]> rawList = QueryUtils.executeSelect(query);
            if (rawList == null || rawList.isEmpty()) {
                return Collections.emptyList();
            }
            if (!rawList.isEmpty()) {
                for (int i = 0; i < rawList.size(); i++) {
                    Object[] obj = rawList.get(i);
                    ExclusionLookupDTO dtoValue = new ExclusionLookupDTO();
                    dtoValue.setValues(String.valueOf(obj[0]));
                    dtoValue.setExcludedField(String.valueOf(obj[1]));
                    finalList.add(dtoValue);
                }
            }
            return finalList;
        } catch (Exception e) {
            LOGGER.error("Error in getintialLoadValue", e);
            return Collections.emptyList();
        }
    }

    public boolean isDuplicateName(String viewName) {
        try {
            String query = SQlUtil.getQuery("getIntialViewLoad");
            query = query.replace("@View_Name", viewName);
            List<Object[]> rawList = QueryUtils.executeSelect(query);
            return !rawList.isEmpty();
        } catch (Exception e) {
            LOGGER.error("Error in isDuplicateName", e);
            return true;
        }
    }

    public boolean isAddORUpdateView(ExclusionLookupDTO saveViewDTO) {
        StringBuilder sbQuery = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            String viewSid;
            if (saveViewDTO.isViewStatus()) {
                String updateQuery = SQlUtil.getQuery("updateMasterViewQuery");
                updateQuery = updateQuery.replace(CommonConstant.ARM_VIEW_MASTER_SID, saveViewDTO.getViewMasterSid());
                updateQuery = updateQuery.replace("@MODIFIED_BY", "" + saveViewDTO.getUserID());
                updateQuery = updateQuery.replace("@MODIFIED_DATE", dateFormat.format(new Date()));

                sbQuery.append(updateQuery);
                viewSid = saveViewDTO.getViewMasterSid();
            } else {
                viewSid = isSaveView(saveViewDTO);
            }
            sbQuery.append(SQlUtil.getQuery("insertViewDetailsQuery"));
            if (saveViewDTO.isScreenFlag()) {
                if (!StringUtils.EMPTY.equals(viewSid)) {
                    for (CustomerGroupDTO dtoValue : saveViewDTO.getCustGrpList()) {
                        sbQuery.append(ARMUtils.OPEN_PARANTHESIS).append(viewSid).append(ARMUtils.COMMA).append(StringUtils.EMPTY.equalsIgnoreCase(dtoValue.getCompanyMasterSid()) ? null + "," : dtoValue.getCompanyMasterSid() + ",").append(StringUtils.EMPTY.equalsIgnoreCase(dtoValue.getCustomerGroupSid()) ? null + ARMUtils.COMMA : dtoValue.getCustomerGroupSid() + ARMUtils.COMMA).append(dtoValue.isInclude() ? 1 : 0).append(',');
                        if (dtoValue.getIndicator() != null) {
                            if (dtoValue.getIndicator()) {
                                sbQuery.append(1);
                            } else {
                                sbQuery.append(0);
                            }
                        } else {
                            sbQuery.append("null");
                        }
                        sbQuery.append(ARMUtils.COMMA).append("null").append(ARMUtils.COMMA_CHAR).append("null),");

                    }
                }
            } else {
                if (!StringUtils.EMPTY.equals(viewSid)) {
                    for (ExclusionLookupDTO idValue : saveViewDTO.getFieldList()) {
                        sbQuery.append(ARMUtils.OPEN_PARANTHESIS).append(viewSid).append(ARMUtils.COMMA_CHAR).append("null").append(ARMUtils.COMMA_CHAR).append("null").append(ARMUtils.COMMA_CHAR).append("null").append(ARMUtils.COMMA_CHAR).append("null,'").append(idValue.getExcludedField()).append("','").append(idValue.getValues()).append("'),");
                    }
                }

            }
            sbQuery.replace(sbQuery.length() - 1, sbQuery.length(), "");
            DAO.executeUpdate(sbQuery.toString());
            return true;
        } catch (Exception e) {
            LOGGER.error("Error in isAddORUpdateView", e);
            return false;
        }
    }

    public String isSaveView(ExclusionLookupDTO saveViewDTO) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String query = SQlUtil.getQuery("insertSaveViewQuery");
            query = query.replace("@View_Name", saveViewDTO.getViewName());
            query = query.replace("@View_Type", saveViewDTO.getViewType());
            query = query.replace("@Field_Name", saveViewDTO.getFieldName());
            query = query.replace("@Created_By", "" + saveViewDTO.getUserID());
            query = query.replace("@Created_Date", dateFormat.format(new Date()));
            List<Object[]> rawList = QueryUtils.executeSelect(query);
            if (!rawList.isEmpty()) {
                return String.valueOf(rawList.get(0));
            }
            return StringUtils.EMPTY;
        } catch (Exception e) {
            LOGGER.error("Error in isSaveView", e);
            return StringUtils.EMPTY;
        }

    }

    public void deleteViewLogic(String viewMasterSid) {
        try {
            String deleteQuery = SQlUtil.getQuery("deleteViewMasterQuery");
            deleteQuery = deleteQuery.replace(CommonConstant.ARM_VIEW_MASTER_SID, viewMasterSid);

            DAO.executeUpdate(deleteQuery);
        } catch (Exception ex) {
            LOGGER.error("Error in deleteViewLogic", ex);
        }
    }

    public void deleteViewLogicForInventory(String viewMasterSid) {
        try {
            String deleteQuery = SQlUtil.getQuery("deleteViewMasterQueryForInventory");
            deleteQuery = deleteQuery.replace(CommonConstant.ARM_VIEW_MASTER_SID, viewMasterSid);

            DAO.executeUpdate(deleteQuery);
        } catch (Exception ex) {
            LOGGER.error("Error in deleteViewLogicForInventory", ex);
        }
    }

    public List getSavedViewList(ViewLookupDTO exRateDTO, boolean isCount, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet) {
        try {
            getAllUsers();
            String exclusionViewValue = exRateDTO.getViewName();
            if (StringUtils.isNotBlank(exRateDTO.getViewName())) {
                exclusionViewValue = exclusionViewValue.replace(ARMUtils.CHAR_ASTERISK, "%");
            }
            StringBuilder exclQuery;
            if (isCount) {
                exclQuery = new StringBuilder();
                exclQuery.append("select count(Distinct AVM.ARM_VIEW_MASTER_SID) from ARM_VIEW_MASTER AVM   ");
                exclQuery.append("Join ARM_VIEW_DETAILS AVD ON AVD.ARM_VIEW_MASTER_SID=AVM.ARM_VIEW_MASTER_SID \n");
                exclQuery.append("where AVM.VIEW_TYPE  = '").append(VariableConstants.PRIVATE_VIEW.equalsIgnoreCase(exRateDTO.getViewType()) ? VariableConstants.A_PRIVATE : "Public").append(" '  AND AVM.VIEW_NAME like'@VIEW_NAME'");

            } else {
                exclQuery = new StringBuilder();
                exclQuery.append("select Distinct AVM.ARM_VIEW_MASTER_SID,AVM.VIEW_NAME,AVM.VIEW_TYPE,AVM.CREATED_BY,AVM.CREATED_DATE,AVM.MODIFIED_DATE,AVM.MODIFIED_BY from ARM_VIEW_MASTER AVM \n");
                exclQuery.append("Join ARM_VIEW_DETAILS AVD ON AVD.ARM_VIEW_MASTER_SID=AVM.ARM_VIEW_MASTER_SID \n");
                exclQuery.append("where AVM.VIEW_TYPE  = '").append(VariableConstants.PRIVATE_VIEW.equalsIgnoreCase(exRateDTO.getViewType()) ? VariableConstants.A_PRIVATE : "Public").append(" '  AND AVM.VIEW_NAME like'@VIEW_NAME'  ");

            }

            String queryVal = exclQuery.toString().replace("@VIEW_NAME", exclusionViewValue);
            exclQuery = new StringBuilder(queryVal);
            if (StringUtils.EMPTY.equalsIgnoreCase(exRateDTO.getDetailsValue())) {
                exclQuery.append(" AND AVD.FIELD_VALUES is NOT Null");
            } else {
                exclQuery.append("C".equalsIgnoreCase(exRateDTO.getDetailsValue()) ? " AND AVD.COMPANY_MASTER_SID is NOT Null AND AVD.CHECK_RECORD is NOT NULL  " : "    AND AVD.COMPANY_GROUP_SID is NOT Null AND AVD.CHECK_RECORD is NOT NULL  ");
            }
            if (exRateDTO.getViewTypeFlag()) {
                exclQuery.append("  AND AVM.CREATED_BY=").append(exRateDTO.getCreatedBy());
            }
            HashMap<String, String> detailsColumn = new HashMap<>();
            detailsColumn.put("viewName", "AVM.VIEW_NAME");
            detailsColumn.put("viewType", "AVM.VIEW_TYPE");
            detailsColumn.put("createdDate", "AVM.CREATED_DATE");
            detailsColumn.put("createdBy", "AVM.CREATED_BY");
            detailsColumn.put("modifiedBy", "AVM.MODIFIED_BY");
            detailsColumn.put("modifiedDate", "AVM.MODIFIED_DATE");
            boolean makeCount = false;
            StringBuilder filterQuery = new StringBuilder();
            makeCount = getExculstionFilters(filterSet, exRateDTO, makeCount, filterQuery, detailsColumn, exclQuery);
            StringBuilder finalQuery;
            String order = getOrderBy(isCount, sortByColumns, detailsColumn, startIndex, endIndex);
            if (makeCount) {
                if (isCount) {
                    List<Object> list = new ArrayList<>();
                    list.add(0);
                    return list;
                } else {
                    return new ArrayList<ViewLookupDTO>();
                }
            } else {
                if (isCount) {
                    finalQuery = new StringBuilder();
                    finalQuery.append(exclQuery.toString()).append(filterQuery.toString());
                } else {
                    finalQuery = new StringBuilder();
                    finalQuery.append(exclQuery.toString()).append(filterQuery.toString()).append(order);
                }
                if (isCount) {
                    return HelperTableLocalServiceUtil.executeSelectQuery(finalQuery.toString());
                }

                List<Object[]> rawList = QueryUtils.executeSelect(finalQuery.toString());
                return getCustomizedList(rawList, exRateDTO);
            }
        } catch (Exception e) {
            LOGGER.error("Error in getSavedViewList :", e);
            return Collections.emptyList();
        }

    }

    private List getCustomizedList(List<Object[]> rawList, ViewLookupDTO exRateDTO) {
        List<ViewLookupDTO> dtoList = new ArrayList<>();
        if (!rawList.isEmpty()) {
            for (int i = 0; i < rawList.size(); i++) {
                Object[] obj = rawList.get(i);
                ViewLookupDTO dto = new ViewLookupDTO();
                dto.setViewSid(String.valueOf(obj[0]));
                dto.setViewName(String.valueOf(obj[1]));
                dto.setViewType(VariableConstants.A_PRIVATE.equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO])) ? VariableConstants.PRIVATE_VIEW : "publicView");
                dto.setViewCategory(exRateDTO.getViewCategory());
                dto.setCreatedUser(String.valueOf(obj[NumericConstants.THREE]));
                dto.setCreatedBy(userMap.get(String.valueOf(obj[NumericConstants.THREE])));
                dto.setCreatedDate((Date) obj[NumericConstants.FOUR]);
                dto.setModifiedDate((Date) obj[NumericConstants.FIVE]);
                dto.setModifiedBy(userMap.get(String.valueOf(obj[NumericConstants.SIX])) != null ? userMap.get(String.valueOf(obj[NumericConstants.SIX])) : StringUtils.EMPTY);
                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    private String getOrderBy(boolean isCount, final List<SortByColumn> exclSortByColumns, HashMap<String, String> exclDetailsColumn, final int startIndex, final int endIndex) {
        String exclOrder = StringUtils.EMPTY;
        if (!isCount) {
            boolean sortOrder = false;
            String columnName = null;
            String orderByColumn = null;
            if (exclSortByColumns != null) {
                for (final Iterator<SortByColumn> iterator = exclSortByColumns.iterator(); iterator.hasNext();) {
                    final SortByColumn sortByColumn = iterator.next();
                    columnName = sortByColumn.getName();
                    orderByColumn = exclDetailsColumn.get(columnName);

                    sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
                }
            }
            if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
                exclOrder = exclOrder + " ORDER BY AVM.VIEW_NAME ";
            } else {
                exclOrder = exclOrder + " ORDER BY " + orderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
            }
            exclOrder = exclOrder + ARMUtils.SPACE + "OFFSET ";
            exclOrder = exclOrder + startIndex;
            exclOrder = exclOrder + " ROWS FETCH NEXT " + endIndex;
            exclOrder = exclOrder + " ROWS ONLY;";
        }
        return exclOrder;
    }

    private boolean getExculstionFilters(final Set<Container.Filter> filterSet, ViewLookupDTO exRateDTO, boolean makeCount, StringBuilder filterQuery, HashMap<String, String> detailsColumn, StringBuilder exclQuery) {
        boolean retVal = makeCount;
        if (filterSet != null) {

            for (Container.Filter exclFilter : filterSet) {
                if (exclFilter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) exclFilter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    if ("viewCategory".equals(stringFilter.getPropertyId())) {
                        retVal = getMakeCount(exRateDTO, stringFilter, retVal);
                    } else {
                        filterQuery.append(" AND ").append(detailsColumn.get(String.valueOf(stringFilter.getPropertyId())));
                        filterQuery.append(" like '").append(filterString).append(ARMUtils.SINGLE_QUOTES);
                    }

                } else if (exclFilter instanceof Between) {
                    getBetweenFilter((Between) exclFilter, detailsColumn, exclQuery);
                }
            }
        }
        return retVal;
    }

    private boolean getMakeCount(ViewLookupDTO exRateDTO, SimpleStringFilter stringFilter, boolean retVal) {
        boolean retValue = retVal;
        if (!exRateDTO.getViewCategory().toLowerCase(Locale.ENGLISH).contains(stringFilter.getFilterString().toLowerCase(Locale.ENGLISH))) {
            retValue = true;
        }
        return retValue;
    }

    private void getBetweenFilter(Between exclFilter, HashMap<String, String> detailsColumn, StringBuilder exclQuery) {
        Between betweenexclFilter = exclFilter;
        StringBuilder dateStar = new StringBuilder("AND ( * >='?')");
        StringBuilder dateEnd = new StringBuilder("AND ( * <='?')");
        if (!detailsColumn.get(betweenexclFilter.getPropertyId().toString()).isEmpty()) {
            Date startValue = (Date) betweenexclFilter.getStartValue();
            Date endValue = (Date) betweenexclFilter.getEndValue();
            StringBuilder initialStart = new StringBuilder("where ( ( * >= '?' )");
            StringBuilder initialEnd = new StringBuilder("where ( ( * <= '?' )");
            if (!betweenexclFilter.getStartValue().toString().isEmpty()) {
                StringBuilder tempStart = getTempStringBuilder(exclQuery, initialStart, dateStar);
                tempStart.replace(tempStart.indexOf(ARMUtils.CHAR_ASTERISK), tempStart.indexOf(ARMUtils.CHAR_ASTERISK) + 1, detailsColumn.get(betweenexclFilter.getPropertyId().toString()));
                tempStart.replace(tempStart.indexOf(ARMUtils.CHAR_QUS), tempStart.indexOf(ARMUtils.CHAR_QUS) + 1, ARMUtils.getInstance().getDbDate().format(startValue));
                exclQuery.append(tempStart);
            }
            if (!betweenexclFilter.getEndValue().toString().isEmpty()) {
                StringBuilder tempEnd = getTempStringBuilder(exclQuery, initialEnd, dateEnd);
                tempEnd.replace(tempEnd.indexOf(ARMUtils.CHAR_ASTERISK), tempEnd.indexOf(ARMUtils.CHAR_ASTERISK) + 1, detailsColumn.get(betweenexclFilter.getPropertyId().toString()));
                tempEnd.replace(tempEnd.indexOf(ARMUtils.CHAR_QUS), tempEnd.indexOf(ARMUtils.CHAR_QUS) + 1, ARMUtils.getInstance().getDbDate().format(endValue));
                exclQuery.append(tempEnd);
            }
        }
    }

    private StringBuilder getTempStringBuilder(StringBuilder exclQuery, StringBuilder initialStart, StringBuilder dateStar) {
        StringBuilder tempStart;
        if (exclQuery.length() == 0) {
            tempStart = new StringBuilder(initialStart);
        } else {
            tempStart = new StringBuilder(dateStar);
        }
        return tempStart;
    }

    public void getAllUsers() {
        List<Object> userList = new ArrayList<>();
        try {
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(User.class);
            final ProjectionList productexclusionProjectionList = ProjectionFactoryUtil.projectionList();
            productexclusionProjectionList.add(ProjectionFactoryUtil.property("userId"));
            productexclusionProjectionList.add(ProjectionFactoryUtil.property("firstName"));
            productexclusionProjectionList.add(ProjectionFactoryUtil.property("lastName"));
            query.setProjection(productexclusionProjectionList);

            userList = UserLocalServiceUtil.dynamicQuery(query);
            for (int loop = 0, limit = userList.size(); loop < limit; loop++) {
                Object[] array = (Object[]) userList.get(loop);
                userMap.put(String.valueOf(array[0]), String.valueOf(array[NumericConstants.TWO]) + ", " + array[1]);
            }
        } catch (Exception ex) {
            LOGGER.error("Error in getAllUsers :", ex);
        }
    }

    public List<ExclusionLookupDTO> getListInitialInsertFromARC(List input) {
        List<ExclusionLookupDTO> finalList = new ArrayList<>();
        try {
            List<Object[]> exclusionRawList = QueryUtils.getItemData(input, "getIntialInsertQueryFromARC", null);
            if (exclusionRawList == null || exclusionRawList.isEmpty()) {
                return Collections.emptyList();
            }
            if (!exclusionRawList.isEmpty()) {
                for (int i = 0; i < exclusionRawList.size(); i++) {
                    Object[] obj = exclusionRawList.get(i);
                    ExclusionLookupDTO dtoValue = new ExclusionLookupDTO();
                    dtoValue.setValues(String.valueOf(obj[1]));
                    dtoValue.setExcludedField(String.valueOf(obj[0]));
                    finalList.add(dtoValue);
                }
            }
            return finalList;
        } catch (Exception e) {
            LOGGER.error("Error in getListInitialInsertFromARC :", e);
            return Collections.emptyList();
        }
    }

}
