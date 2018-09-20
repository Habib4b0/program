/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction7.logic;

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
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
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
public class Trx7ExclusionDetailsLogic {

    public static final Logger LOGGER = LoggerFactory.getLogger(Trx7ExclusionDetailsLogic.class);
    private static final CommonDao DAO = CommonImpl.getInstance();
    protected static Map<String, String> userMap = new HashMap<>();

    public List<ExclusionLookupDTO> getCompanySid(String viewSid) {
        LOGGER.debug("--Inside getCompanySid--{}", viewSid);
        List<ExclusionLookupDTO> finalList = Collections.emptyList();
        try {
            String tr7ExclQuery = SQlUtil.getQuery("getExclusionViewDetails");
            tr7ExclQuery = tr7ExclQuery.replace(CommonConstant.ARM_VIEW_MASTER_SID, viewSid);
            List<Object[]> tr7ExclRawList = QueryUtils.executeSelect(tr7ExclQuery);
            if (!tr7ExclRawList.isEmpty()) {
                finalList = new ArrayList();
                for (int i = 0; i < tr7ExclRawList.size(); i++) {
                    Object[] obj = tr7ExclRawList.get(i);
                    ExclusionLookupDTO tr7Excldto = new ExclusionLookupDTO();
                    tr7Excldto.setExcludedField(String.valueOf(obj[0]));
                    tr7Excldto.setValues(String.valueOf(obj[1]));
                    finalList.add(tr7Excldto);
                }
            }
            LOGGER.debug("--Inside getCompanySid--{}", viewSid);
            return finalList;
        } catch (Exception e) {
            LOGGER.error("Error in getCompanySid :", e);
            return finalList;
        }
    }

    public void saveORUpdateExclusionDetailsLookUp(int projectionSid, List<ExclusionLookupDTO> list, String accountId, String accountName, String contractId, SessionDTO sessionDTO) {
        LOGGER.debug("--Inside saveORUpdate_Exclusion_Details_LookUp--{}", projectionSid);
        StringBuilder sbQuery = new StringBuilder();
        boolean isView = sessionDTO.getAction().equals(ARMUtils.VIEW_SMALL);
        String saveQuery = isView ? SQlUtil.getQuery("saveORUpdateQuery") : SQlUtil.getQuery("saveORUpdateQueryEdit");
        saveQuery = saveQuery.replace(CommonConstant.PROJECTION_MASTER_SID, String.valueOf(projectionSid));
        saveQuery = saveQuery.replace("@USER_ID", "" + sessionDTO.getUserId());
        saveQuery = saveQuery.replace("@SESSION_ID", "" + sessionDTO.getSessionId());
        sbQuery.append(saveQuery);
        for (ExclusionLookupDTO dtoList : list) {
            sbQuery.append(ARMUtils.OPEN_PARANTHESIS + projectionSid + ",'" + dtoList.getExcludedField() + ARMUtils.SINGLE_QUOTES + ",'" + dtoList.getValues() + "'),");
        }
        sbQuery.replace(sbQuery.length() - 1, sbQuery.length(), "");
        String query = isView ? SQlUtil.getQuery("saveORUpdateQueryPipeline") : SQlUtil.getQuery("saveORUpdateQueryPipelineEdit");
        query = query.replace("@COMPANY_ID", accountId);
        query = query.replace("@COMPANY_NAME", accountName);
        query = query.replace("@CONTRACT_ID", contractId);
        query = query.replace(CommonConstant.PROJECTION_MASTER_SID, String.valueOf(projectionSid));
        query = query.replace("@USER_ID", "" + sessionDTO.getUserId());
        query = query.replace("@SESSION_ID", "" + sessionDTO.getSessionId());
        sbQuery.append(" ; " + query);
        LOGGER.debug("--Exit saveORUpdate_Exclusion_Details_LookUp--{}", sbQuery);
        DAO.executeUpdate(sbQuery.toString());
    }

    public List<ExclusionLookupDTO> getFieldListValue(String fieldValue, String prevSelectedValues) {
        LOGGER.debug("--Inside getFieldListValue--{}", fieldValue);
        try {
            String query = QueryUtils.buildFieldNameselectQuery(fieldValue, prevSelectedValues);
            List<ExclusionLookupDTO> finalList = new ArrayList<>();
            LOGGER.debug("query --> {}", query);
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
            LOGGER.debug("--Inside getFieldListValue--{}", finalList.size());
            return finalList;
        } catch (Exception e) {
            LOGGER.error("Error in getFieldListValue :", e);
            return Collections.emptyList();
        }
    }

    public List<ExclusionLookupDTO> getIntialLoadValue(int rateDetailsSid) {
        LOGGER.debug("--Inside getIntialLoadValue--{}", rateDetailsSid);
        List<ExclusionLookupDTO> finalList = new ArrayList<>();
        try {
            String tr7ExclQuery = SQlUtil.getQuery("getIntialLoadQuery");
            tr7ExclQuery = tr7ExclQuery.replace(CommonConstant.PROJECTION_MASTER_SID, String.valueOf(rateDetailsSid));
            List<Object[]> tr7ExclRawList = QueryUtils.executeSelect(tr7ExclQuery);
            if (tr7ExclRawList == null || tr7ExclRawList.isEmpty()) {
                return Collections.emptyList();
            }
            if (!tr7ExclRawList.isEmpty()) {
                for (int i = 0; i < tr7ExclRawList.size(); i++) {
                    Object[] obj = tr7ExclRawList.get(i);
                    ExclusionLookupDTO tr7ExclDtoValue = new ExclusionLookupDTO();
                    tr7ExclDtoValue.setValues(String.valueOf(obj[0]));
                    tr7ExclDtoValue.setExcludedField(String.valueOf(obj[1]));
                    finalList.add(tr7ExclDtoValue);
                }
            }
            LOGGER.debug("--Exit getIntialLoadValue--{}", tr7ExclQuery);
            return finalList;
        } catch (Exception e) {
            LOGGER.error("Error in getIntialLoadValue :", e);
            return Collections.emptyList();
        }
    }

    public int loadViewRateConfig(DataSelectionDTO dataSelectionDTO) {
        LOGGER.debug("--Inside LoadView_RateConfig--");
        try {
            String query = SQlUtil.getQuery("LoadViewQuery");
            query = query.replace("@ADJUSTMENT_TYPE", String.valueOf(dataSelectionDTO.getAdjustmentId()));
            query = query.replace("@GL_COMPANY_MASTER_SID", String.valueOf(dataSelectionDTO.getCompanyMasterSid()));
            query = query.replace("@BU_COMPANY_MASTER_SID", String.valueOf(dataSelectionDTO.getBucompanyMasterSid()));
            List rawList = QueryUtils.executeSelect(query);
            LOGGER.debug("--Exit LoadView_RateConfig--{}", query);
            return rawList == null || rawList.isEmpty() || rawList.get(0) == null ? 0 : Integer.parseInt(String.valueOf(rawList.get(0)));
        } catch (Exception e) {
            LOGGER.error("Error in loadViewRateConfig :", e);
            return 0;
        }
    }

    public ViewLookupDTO loadViewViewName(int viewMasterSid) {
        LOGGER.debug("--Inside LoadView_ViewName--{}", viewMasterSid);
        ViewLookupDTO dtoValue = new ViewLookupDTO();
        try {

            String query = SQlUtil.getQuery("LoadViewNameQuery");
            query = query.replace("@VIEWID", String.valueOf(viewMasterSid));
            List<Object[]> rawList = QueryUtils.executeSelect(query);
            if (rawList == null || rawList.isEmpty()) {
                return dtoValue;
            }
            if (!rawList.isEmpty()) {
                for (int i = 0; i < rawList.size(); i++) {
                    Object[] obj = rawList.get(i);

                    dtoValue.setViewName(String.valueOf(obj[1]));
                    dtoValue.setViewType(String.valueOf(obj[NumericConstants.TWO]));
                    dtoValue.setViewSid(String.valueOf(viewMasterSid));
                }
            }
            LOGGER.debug("--Exit LoadView_ViewName--{}", query);
            return dtoValue;
        } catch (Exception e) {
            LOGGER.error("Error in loadViewViewName :", e);
            return dtoValue;
        }
    }

    public boolean isDuplicateName(String viewName) {
        LOGGER.debug("--Inside isDuplicateName--{}", viewName);
        try {
            String query = SQlUtil.getQuery("getIntialViewLoad");
            query = query.replace("@View_Name", viewName);
            List<Object[]> rawList = QueryUtils.executeSelect(query);
            LOGGER.debug("--Inside isDuplicateName--{}", query);
            return !rawList.isEmpty();
        } catch (Exception e) {
            LOGGER.error("Error in isDuplicateName :", e);
            return true;
        }
    }

    public boolean isAddORUpdateView(ExclusionLookupDTO tr7ExclSaveViewDTO) {
        LOGGER.debug("--Inside isAdd_OR_UpdateView--{}", tr7ExclSaveViewDTO.getViewMasterSid());
        StringBuilder sbQuery = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
        try {
            String viewSid;
            if (tr7ExclSaveViewDTO.isViewStatus()) {
                String updateQuery = SQlUtil.getQuery("updateMasterViewQuery");
                updateQuery = updateQuery.replace(CommonConstant.ARM_VIEW_MASTER_SID, tr7ExclSaveViewDTO.getViewMasterSid());
                updateQuery = updateQuery.replace("@MODIFIED_BY", "" + tr7ExclSaveViewDTO.getUserID());
                updateQuery = updateQuery.replace("@MODIFIED_DATE", dateFormat.format(new Date()));

                sbQuery.append(updateQuery);
                viewSid = tr7ExclSaveViewDTO.getViewMasterSid();
            } else {
                viewSid = isSaveView(tr7ExclSaveViewDTO);
            }
            sbQuery.append(SQlUtil.getQuery("insertViewDetailsQuery"));
            if (tr7ExclSaveViewDTO.isScreenFlag()) {
                if (!StringUtils.EMPTY.equals(viewSid)) {
                    for (CustomerGroupDTO dtoValue : tr7ExclSaveViewDTO.getCustGrpList()) {
                        sbQuery.append(ARMUtils.OPEN_PARANTHESIS + viewSid + ARMUtils.COMMA_CHAR).append(StringUtils.EMPTY.equalsIgnoreCase(dtoValue.getCompanyMasterSid()) ? null : dtoValue.getCompanyMasterSid() + ARMUtils.COMMA_CHAR).append(StringUtils.EMPTY.equalsIgnoreCase(dtoValue.getCustomerGroupSid()) ? null : dtoValue.getCustomerGroupSid() + ARMUtils.COMMA_CHAR).append(dtoValue.isInclude() == true ? 1 : 0).append(ARMUtils.COMMA_CHAR);
                        if (dtoValue.getIndicator() != null) {
                            if (dtoValue.getIndicator() == true) {
                                sbQuery.append(1);
                            } else {
                                sbQuery.append(0);
                            }
                        } else {
                            sbQuery.append("null");
                        }
                        sbQuery.append("," + null + ARMUtils.COMMA_CHAR + null + "),");
                    }
                }
            } else if (!StringUtils.EMPTY.equals(viewSid)) {
                for (ExclusionLookupDTO idValue : tr7ExclSaveViewDTO.getFieldList()) {
                    sbQuery.append(ARMUtils.OPEN_PARANTHESIS + viewSid + ARMUtils.COMMA_CHAR + null + ARMUtils.COMMA_CHAR + null + ARMUtils.COMMA_CHAR + null + ARMUtils.COMMA_CHAR + null + ",'" + idValue.getExcludedField() + "','" + idValue.getValues() + "'),");
                }
            }
            sbQuery.replace(sbQuery.length() - 1, sbQuery.length(), "");
            LOGGER.debug("--Exit isAdd_OR_UpdateView--{}", sbQuery);
            DAO.executeUpdate(sbQuery.toString());
            return true;
        } catch (Exception e) {
            LOGGER.debug("sbQuery:===========>{}", sbQuery.toString());
            LOGGER.error("Error in isAddORUpdateView :", e);
            return false;
        }
    }

    public String isSaveView(ExclusionLookupDTO saveViewDTO) {
        LOGGER.debug("--Inside isSaveView--{}", saveViewDTO.getViewName());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String query = SQlUtil.getQuery("insertSaveViewQuery");
            query = query.replace("@View_Name", saveViewDTO.getViewName());
            query = query.replace("@View_Type", saveViewDTO.getViewType());
            query = query.replace("@Field_Name", saveViewDTO.getFieldName());
            query = query.replace("@Created_By", "" + saveViewDTO.getUserID());
            query = query.replace("@Created_Date", dateFormat.format(new Date()));
            List<Object[]> rawList = QueryUtils.executeSelect(query);
            LOGGER.debug("--Exit isSaveView--{}", query);
            if (!rawList.isEmpty()) {
                return String.valueOf(rawList.get(0));
            }
            return StringUtils.EMPTY;
        } catch (Exception e) {
            LOGGER.error("Error in isSaveView :", e);
            return StringUtils.EMPTY;
        }

    }

    public void deleteViewLogic(String viewMasterSid) {
        LOGGER.debug("--Inside deleteViewLogic--{}", viewMasterSid);
        try {
            String deleteQuery = SQlUtil.getQuery("deleteViewMasterQuery");
            deleteQuery = deleteQuery.replace(CommonConstant.ARM_VIEW_MASTER_SID, viewMasterSid);

            DAO.executeUpdate(deleteQuery);
            LOGGER.debug("--Exit deleteViewLogic--{}", deleteQuery);
        } catch (Exception ex) {
            LOGGER.error("Error in deleteViewLogic :", ex);
        }
    }

    public void deleteViewLogicForInventory(String viewMasterSid) {
        LOGGER.debug("--Inside deleteViewLogicForInventory--{}", viewMasterSid);
        try {
            String deleteQuery = SQlUtil.getQuery("deleteViewMasterQueryForInventory");
            deleteQuery = deleteQuery.replace(CommonConstant.ARM_VIEW_MASTER_SID, viewMasterSid);

            DAO.executeUpdate(deleteQuery);
            LOGGER.debug("--Exit deleteViewLogicForInventory--{}", deleteQuery);
        } catch (Exception ex) {
            LOGGER.error("Error in deleteViewLogicForInventory :", ex);
        }
    }

    public List getSavedViewList(ViewLookupDTO exRateDTO, boolean isCount, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet) {
        LOGGER.debug("--Inside getSavedViewList--");
        List<ViewLookupDTO> dtoList = Collections.emptyList();
        try {
            getAllUsers();
            String viewValue = exRateDTO.getViewName();
            if (StringUtils.isNotBlank(exRateDTO.getViewName())) {
                viewValue = viewValue.replace(ARMUtils.CHAR_ASTERISK, "%");
            }
            StringBuilder query;
            if (isCount) {
                query = new StringBuilder();
                query.append("select count(Distinct AVM.ARM_VIEW_MASTER_SID) from ARM_VIEW_MASTER AVM   ");
                query.append("Join ARM_VIEW_DETAILS AVD ON AVD.ARM_VIEW_MASTER_SID=AVM.ARM_VIEW_MASTER_SID \n");
                query.append("where AVM.VIEW_TYPE IN(Select DESCRIPTION from HELPER_TABLE where HELPER_TABLE_SID='@View_Type')  AND AVM.VIEW_NAME like'@VIEW_NAME'");

            } else {
                query = new StringBuilder();
                query.append("select Distinct AVM.ARM_VIEW_MASTER_SID,AVM.VIEW_NAME,AVM.VIEW_TYPE,AVM.CREATED_BY,AVM.CREATED_DATE,AVM.MODIFIED_DATE,AVM.MODIFIED_BY from ARM_VIEW_MASTER AVM \n");
                query.append("Join ARM_VIEW_DETAILS AVD ON AVD.ARM_VIEW_MASTER_SID=AVM.ARM_VIEW_MASTER_SID \n");
                query.append("where AVM.VIEW_TYPE IN(Select DESCRIPTION from HELPER_TABLE where HELPER_TABLE_SID='@View_Type')  AND AVM.VIEW_NAME like'@VIEW_NAME'  ");

            }

            String queryVal = query.toString().replace("@VIEW_NAME", viewValue);
            String helperSidQuery = "SELECT HELPER_TABLE_SID from dbo.HELPER_TABLE where DESCRIPTION like '" + ("privateView".equalsIgnoreCase(exRateDTO.getViewType()) ? "Private" : "Public") + ARMUtils.SINGLE_QUOTES;
            List<Object> viewSid = HelperTableLocalServiceUtil.executeSelectQuery(helperSidQuery);
            queryVal = queryVal.replace("@View_Type", String.valueOf(viewSid.get(0)));
            query = new StringBuilder(queryVal);
            if (StringUtils.EMPTY.equalsIgnoreCase(exRateDTO.getDetailsValue())) {
                query.append(" AND AVD.FIELD_VALUES is NOT Null");
            } else {
                query.append("C".equalsIgnoreCase(exRateDTO.getDetailsValue()) ? " AND AVD.COMPANY_MASTER_SID is NOT Null AND AVD.CHECK_RECORD is NOT NULL  " : "    AND AVD.COMPANY_GROUP_SID is NOT Null AND AVD.CHECK_RECORD is NOT NULL  ");
            }
            if (exRateDTO.getViewTypeFlag()) {
                query.append("  AND AVM.CREATED_BY=").append(exRateDTO.getCreatedBy());

            }
            LOGGER.debug(query.toString());
            StringBuilder filterQuery = new StringBuilder();
            HashMap<String, String> detailsColumn = new HashMap<>();
            detailsColumn.put("viewName", "AVM.VIEW_NAME");
            detailsColumn.put("viewType", "AVM.VIEW_TYPE");
            detailsColumn.put("createdDate", "AVM.CREATED_DATE");
            detailsColumn.put("createdBy", "AVM.CREATED_BY");
            detailsColumn.put("modifiedBy", "AVM.MODIFIED_BY");
            detailsColumn.put("modifiedDate", "AVM.MODIFIED_DATE");

            if (filterSet != null) {
                for (Container.Filter filter : filterSet) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = "%" + stringFilter.getFilterString() + "%";
                        filterQuery.append(" AND ").append(detailsColumn.get(String.valueOf(stringFilter.getPropertyId()))).append(" like '");
                        filterQuery.append(filterString).append(ARMUtils.SINGLE_QUOTES);

                    } else if (filter instanceof Between) {
                        Between tr7ExclbetweenFilter = (Between) filter;
                        StringBuilder dateStartstr = new StringBuilder("AND ( * >='?')");
                        StringBuilder dateEndstr = new StringBuilder("AND ( * <='?')");
                        if (!detailsColumn.get(tr7ExclbetweenFilter.getPropertyId().toString()).isEmpty()) {
                            Date startValue = (Date) tr7ExclbetweenFilter.getStartValue();
                            Date endValue = (Date) tr7ExclbetweenFilter.getEndValue();
                            StringBuilder initialStart = new StringBuilder("where ( ( * >= '?' )");
                            StringBuilder initialEnd = new StringBuilder("where ( ( * <= '?' )");
                            if (!tr7ExclbetweenFilter.getStartValue().toString().isEmpty()) {
                                StringBuilder tempStart;
                                if (query.length() == 0) {
                                    tempStart = new StringBuilder(initialStart);
                                } else {
                                    tempStart = new StringBuilder(dateStartstr);
                                }
                                tempStart.replace(tempStart.indexOf(ARMUtils.CHAR_ASTERISK), tempStart.indexOf(ARMUtils.CHAR_ASTERISK) + 1, detailsColumn.get(tr7ExclbetweenFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf(ARMUtils.CHAR_QUS), tempStart.indexOf(ARMUtils.CHAR_QUS) + 1, ARMUtils.getInstance().getDbDate().format(startValue));
                                query.append(tempStart);
                            }
                            if (!tr7ExclbetweenFilter.getEndValue().toString().isEmpty()) {
                                StringBuilder tempEnd;
                                if (query.length() == 0) {
                                    tempEnd = new StringBuilder(initialEnd);
                                } else {
                                    tempEnd = new StringBuilder(dateEndstr);
                                }

                                tempEnd.replace(tempEnd.indexOf(ARMUtils.CHAR_ASTERISK), tempEnd.indexOf(ARMUtils.CHAR_ASTERISK) + 1, detailsColumn.get(tr7ExclbetweenFilter.getPropertyId().toString()));
                                tempEnd.replace(tempEnd.indexOf(ARMUtils.CHAR_QUS), tempEnd.indexOf(ARMUtils.CHAR_QUS) + 1, ARMUtils.getInstance().getDbDate().format(endValue));
                                query.append(tempEnd);
                            }
                        }
                    }
                }
            }
            StringBuilder finalQuery;
            String tr7ExclOrder = StringUtils.EMPTY;
            if (!isCount) {
                boolean sortOrder = false;
                String tr7ExclColumnName = null;
                String tr7ExclOrderByColumn = null;
                if (sortByColumns != null) {
                    for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                        final SortByColumn sortByColumn = iterator.next();

                        tr7ExclColumnName = sortByColumn.getName();
                        tr7ExclOrderByColumn = detailsColumn.get(tr7ExclColumnName);

                        if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                            sortOrder = false;
                        } else {
                            sortOrder = true;
                        }
                    }
                }
                if (tr7ExclOrderByColumn == null || StringUtils.EMPTY.equals(tr7ExclOrderByColumn)) {
                    tr7ExclOrder = tr7ExclOrder + " ORDER BY AVM.VIEW_NAME ";
                } else {
                    tr7ExclOrder = tr7ExclOrder + " ORDER BY " + tr7ExclOrderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
                }
                tr7ExclOrder = tr7ExclOrder + ARMUtils.SPACE + "OFFSET ";
                tr7ExclOrder = tr7ExclOrder + startIndex;
                tr7ExclOrder = tr7ExclOrder + " ROWS FETCH NEXT " + endIndex;
                tr7ExclOrder = tr7ExclOrder + " ROWS ONLY;";
            }
            if (isCount) {
                finalQuery = new StringBuilder();
                finalQuery.append(query.toString()).append(filterQuery.toString());
            } else {
                finalQuery = new StringBuilder();
                finalQuery.append(query.toString()).append(filterQuery.toString()).append(tr7ExclOrder);
            }
            LOGGER.debug("-- finalQuery--{}", finalQuery);
            if (isCount) {
                return HelperTableLocalServiceUtil.executeSelectQuery(finalQuery.toString());
            }

            List<Object[]> rawList = QueryUtils.executeSelect(finalQuery.toString());
            if (!rawList.isEmpty()) {

                dtoList = new ArrayList();
                for (int i = 0; i < rawList.size(); i++) {
                    Object[] obj = rawList.get(i);
                    ViewLookupDTO dto = new ViewLookupDTO();
                    dto.setViewSid(String.valueOf(obj[0]));
                    dto.setViewName(String.valueOf(obj[1]));
                    dto.setViewType("Private".equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO])) ? "privateView" : "publicView");
                    dto.setViewCategory(exRateDTO.getViewCategory());
                    dto.setCreatedBy(userMap.get(String.valueOf(obj[NumericConstants.THREE])));
                    dto.setCreatedDate((Date) obj[NumericConstants.FOUR]);
                    dto.setModifiedDate((Date) obj[NumericConstants.FIVE]);
                    dto.setModifiedBy(userMap.get(String.valueOf(obj[NumericConstants.SIX])) != null ? userMap.get(String.valueOf(obj[NumericConstants.SIX])) : StringUtils.EMPTY);
                    dtoList.add(dto);
                }
            }
            LOGGER.debug("--Exit getSavedViewList--{}", dtoList.size());
            return dtoList;
        } catch (Exception e) {
            LOGGER.error("Error in getSavedViewList :", e);
            return dtoList;
        }

    }

    public static void getAllUsers() {
        LOGGER.debug("--Inside getAllUsers--");
        List<Object> userList = new ArrayList<>();
        try {
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(User.class);
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property("userId"));
            productProjectionList.add(ProjectionFactoryUtil.property("firstName"));
            productProjectionList.add(ProjectionFactoryUtil.property("lastName"));
            query.setProjection(productProjectionList);

            userList = UserLocalServiceUtil.dynamicQuery(query);
            for (int loop = 0, limit = userList.size(); loop < limit; loop++) {
                Object[] array = (Object[]) userList.get(loop);
                userMap.put(String.valueOf(array[0]), String.valueOf(array[NumericConstants.TWO]) + ", " + array[1]);
            }
            LOGGER.debug("--Exit getCompanySid--{}", userMap.size());
        } catch (Exception ex) {
            LOGGER.error("Error in getAllUsers :", ex);
        }
    }
}
