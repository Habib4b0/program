/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.logic;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.ExclusionLookupDTO;
import com.stpl.app.arm.adjustmentrateconfiguration.dto.ViewLookupDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.CustomerGroupDTO;
import static com.stpl.app.arm.common.CommonFilterLogic.DBDate;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dao.CommonDao;
import com.stpl.app.arm.dao.impl.CommonImpl;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.SimpleStringFilter;
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
import org.jboss.logging.Logger;

/**
 *
 * @author Gopinath.Mathiyalaga
 */
public class ExclusionDetailsLogic {

    private static final CommonDao DAO = CommonImpl.getInstance();
    public static Map<String, String> userMap = new HashMap<String, String>();
    public static final Logger LOGGER = Logger.getLogger(ExclusionDetailsLogic.class);
    
    public List<ExclusionLookupDTO> getCompanySid(String viewSid) {
        List<ExclusionLookupDTO> finalList = Collections.EMPTY_LIST;
        try {
            String query = SQlUtil.getQuery("getExclusionViewDetails");
            query = query.replace("@ARM_VIEW_MASTER_SID", viewSid);
            List<Object[]> rawList = QueryUtils.executeSelect(query);
            if (rawList.size() > 0 && !rawList.isEmpty()) {
                finalList = new ArrayList();
                for (int i = 0; i < rawList.size(); i++) {
                    Object[] obj = (Object[]) rawList.get(i);
                    ExclusionLookupDTO dto = new ExclusionLookupDTO();
                    dto.setExcludedField(String.valueOf(obj[0]));
                    dto.setValues(String.valueOf(obj[1]));
                    finalList.add(dto);
                }
            }
            return finalList;
        } catch (Exception e) {
            LOGGER.error(e);
            return finalList;
        }
    }

    public void saveORUpdate_Exclusion_Details_LookUp(int projectionSid, List<ExclusionLookupDTO> list, String accountId, String accountName, String contractId, SessionDTO sessionDTO) {
        StringBuilder sbQuery = new StringBuilder(StringUtils.EMPTY);
        boolean isView = sessionDTO.getAction().equals(ARMUtils.VIEW_SMALL);
        String saveQuery = isView ? SQlUtil.getQuery("saveORUpdateQuery") : SQlUtil.getQuery("saveORUpdateQueryEdit");
        saveQuery = saveQuery.replace("@PROJECTION_MASTER_SID", "" + projectionSid);
        sbQuery.append(saveQuery);
        for (ExclusionLookupDTO dtoList : list) {
            sbQuery.append("(").append(projectionSid).append(",'").append(dtoList.getExcludedField()).append("'" + ",'").append(dtoList.getValues()).append("'),");
        }
        sbQuery.replace(sbQuery.length() - 1, sbQuery.length(), "");
        String query = isView ? SQlUtil.getQuery("saveORUpdateQueryPipeline") : SQlUtil.getQuery("saveORUpdateQueryPipelineEdit");
        query = query.replace("@COMPANY_ID", accountId);
        query = query.replace("@COMPANY_NAME", accountName);
        query = query.replace("@CONTRACT_ID", contractId);
        query = query.replace("@PROJECTION_MASTER_SID", "" + projectionSid);
        query = query.replace("[TABLE]", "" + sessionDTO.getCurrentTableNames().get("ST_ARM_PIPELINE_EXCLUSION_DETAILS"));
        sbQuery.append(" ; ").append(query);
        DAO.executeUpdate(sbQuery.toString());
    }

    public List<ExclusionLookupDTO> getFieldListValue(String fieldValue, String prevSelectedValues) {
        try {
            String query = QueryUtils.build_FieldName_selectQuery(fieldValue, prevSelectedValues);
            List<ExclusionLookupDTO> finalList = new ArrayList<ExclusionLookupDTO>();
            List<Object[]> rawList = QueryUtils.executeSelect(query);
            if (rawList == null || rawList.isEmpty()) {
                return Collections.EMPTY_LIST;
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
            LOGGER.error(e);
            return Collections.EMPTY_LIST;
        }
    }

    public List<ExclusionLookupDTO> getIntialLoadValue(int rate_Details_Sid) {
        List<ExclusionLookupDTO> finalList = new ArrayList<ExclusionLookupDTO>();
        try {
            String query = SQlUtil.getQuery("getIntialLoadQuery");
            query = query.replace("@PROJECTION_MASTER_SID", "" + rate_Details_Sid);
            List<Object[]> rawList = QueryUtils.executeSelect(query);
            if (rawList == null || rawList.isEmpty()) {
                return Collections.EMPTY_LIST;
            }
            if (!rawList.isEmpty()) {
                for (int i = 0; i < rawList.size(); i++) {
                    Object[] obj = (Object[]) rawList.get(i);
                    ExclusionLookupDTO dtoValue = new ExclusionLookupDTO();
                    dtoValue.setValues(String.valueOf(obj[0]));
                    dtoValue.setExcludedField(String.valueOf(obj[1]));
                    finalList.add(dtoValue);
                }
            }
            return finalList;
        } catch (Exception e) {
             LOGGER.error(e);
            return Collections.EMPTY_LIST;
        }
    }

    public boolean isDuplicateName(String viewName) {
        try {
            String query = SQlUtil.getQuery("getIntialViewLoad");
            query = query.replace("@View_Name", viewName);
            List<Object[]> rawList = QueryUtils.executeSelect(query);
            if (!rawList.isEmpty() && rawList.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error(e);
            return true;
        }
    }

    public boolean isAdd_OR_UpdateView(ExclusionLookupDTO saveViewDTO) {
        StringBuilder sbQuery = new StringBuilder(StringUtils.EMPTY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
        try {
            String viewSid = StringUtils.EMPTY;
            if (saveViewDTO.isViewStatus()) {
                String updateQuery = SQlUtil.getQuery("updateMasterViewQuery");
                updateQuery = updateQuery.replace("@ARM_VIEW_MASTER_SID", saveViewDTO.getViewMasterSid());
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
                        sbQuery.append("(" + viewSid + "," + (StringUtils.EMPTY.equalsIgnoreCase(dtoValue.getCompanyMasterSid())? null : dtoValue.getCompanyMasterSid()) + "," + (StringUtils.EMPTY.equalsIgnoreCase(dtoValue.getCustomerGroupSid()) ? null : dtoValue.getCustomerGroupSid()) + "," + (dtoValue.isInclude() == true ? 1 : 0) + "," + (dtoValue.getIndicator() != null ? dtoValue.getIndicator() == true ? 1 : 0 : "null") + "," + null + "," + null + "),");
                    }
                }
            } else {
                if (!StringUtils.EMPTY.equals(viewSid)) {
                    for (ExclusionLookupDTO idValue : saveViewDTO.getFieldList()) {
                        sbQuery.append("(" + viewSid + "," + null + "," + null + "," + null + "," + null + ",'" + idValue.getExcludedField() + "','" + idValue.getValues() + "'),");
                    }
                }

            }
            sbQuery.replace(sbQuery.length() - 1, sbQuery.length(), "");
            DAO.executeUpdate(sbQuery.toString());
            return true;
        } catch (Exception e) {
            LOGGER.error(e);
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
            if (rawList.size() > 0 && !rawList.isEmpty()) {
                return String.valueOf(rawList.get(0));
            }
            return StringUtils.EMPTY;
        } catch (Exception e) {
             LOGGER.error(e);
            return StringUtils.EMPTY;
        }

    }

    public void deleteViewLogic(String viewMasterSid) {
        try {
            String deleteQuery = SQlUtil.getQuery("deleteViewMasterQuery");
            deleteQuery = deleteQuery.replace("@ARM_VIEW_MASTER_SID", viewMasterSid);

            DAO.executeUpdate(deleteQuery);
        } catch (Exception ex) {
             LOGGER.error(ex);
        }
    }
    public void deleteViewLogicForInventory(String viewMasterSid) {
        try {
            String deleteQuery = SQlUtil.getQuery("deleteViewMasterQueryForInventory");
            deleteQuery = deleteQuery.replace("@ARM_VIEW_MASTER_SID", viewMasterSid);

            DAO.executeUpdate(deleteQuery);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public List getSavedViewList(ViewLookupDTO exRateDTO, boolean isCount, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet) {
        List<ViewLookupDTO> dtoList = Collections.EMPTY_LIST;
        try {
            getAllUsers();
            String viewValue = exRateDTO.getViewName();
            if (StringUtils.isNotBlank(exRateDTO.getViewName())) {
                viewValue = viewValue.replace("*", "%");
            }
            String query = StringUtils.EMPTY;
            if (isCount) {
                query = "select count(Distinct AVM.ARM_VIEW_MASTER_SID) from ARM_VIEW_MASTER AVM   "
                        + "Join ARM_VIEW_DETAILS AVD ON AVD.ARM_VIEW_MASTER_SID=AVM.ARM_VIEW_MASTER_SID \n"
                        + "where AVM.VIEW_TYPE IN(Select DESCRIPTION from HELPER_TABLE where HELPER_TABLE_SID='@View_Type')  AND AVM.VIEW_NAME like'@VIEW_NAME'";
            } else {
                query = "select Distinct AVM.ARM_VIEW_MASTER_SID,AVM.VIEW_NAME,AVM.VIEW_TYPE,AVM.CREATED_BY,AVM.CREATED_DATE,AVM.MODIFIED_DATE,AVM.MODIFIED_BY from ARM_VIEW_MASTER AVM \n"
                        + "Join ARM_VIEW_DETAILS AVD ON AVD.ARM_VIEW_MASTER_SID=AVM.ARM_VIEW_MASTER_SID \n"
                        + "where AVM.VIEW_TYPE IN(Select DESCRIPTION from HELPER_TABLE where HELPER_TABLE_SID='@View_Type')  AND AVM.VIEW_NAME like'@VIEW_NAME'  ";
            }

            query = query.replace("@VIEW_NAME", viewValue);
            String helperSidQuery = "SELECT HELPER_TABLE_SID from dbo.HELPER_TABLE where DESCRIPTION like '"+(exRateDTO.getViewType().equalsIgnoreCase("privateView")?"Private":"Public")+"'";
            List<Object> viewSid = HelperTableLocalServiceUtil.executeSelectQuery(helperSidQuery);
            LOGGER.debug("viewSid----"+ String.valueOf(viewSid.get(0)));
            query = query.replace("@View_Type", String.valueOf(viewSid.get(0)));
            if (StringUtils.EMPTY.equalsIgnoreCase(exRateDTO.getDetailsValue())) {
                query += " AND AVD.FIELD_VALUES is NOT Null";
            } else {
                query += "C".equalsIgnoreCase(exRateDTO.getDetailsValue()) ? " AND AVD.COMPANY_MASTER_SID is NOT Null AND AVD.CHECK_RECORD is NOT NULL  " : "    AND AVD.COMPANY_GROUP_SID is NOT Null AND AVD.CHECK_RECORD is NOT NULL  ";
            }
            if (exRateDTO.getViewTypeFlag()) {
                query += "  AND AVM.CREATED_BY=" + exRateDTO.getCreatedBy();
            }
            String filterQuery = StringUtils.EMPTY;
            HashMap<String, String> detailsColumn = new HashMap<String, String>();
            detailsColumn.put("viewName", "AVM.VIEW_NAME");
            detailsColumn.put("viewType", "AVM.VIEW_TYPE");
            detailsColumn.put("createdDate", "AVM.CREATED_DATE");
            detailsColumn.put("createdBy", "AVM.CREATED_BY");
            detailsColumn.put("modifiedBy", "AVM.MODIFIED_BY");
            detailsColumn.put("modifiedDate", "AVM.MODIFIED_DATE");
            boolean makeCount = false;
            if (filterSet != null) {
               
                for (Container.Filter filter : filterSet) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = "%" + stringFilter.getFilterString() + "%";
                        if ("viewCategory".equals(stringFilter.getPropertyId())) {
                            if (!exRateDTO.getViewCategory().toLowerCase().contains(stringFilter.getFilterString().toLowerCase())) {
                                makeCount = true;
                            }
                        } else {
                            filterQuery = filterQuery + " AND " + detailsColumn.get(String.valueOf(stringFilter.getPropertyId())) + " like '" + filterString + "'";
                        }

                    } else if (filter instanceof Between) {
                        Between betweenFilter = (Between) filter;
                        StringBuilder dateStartstr = new StringBuilder("AND ( * >='?')");
                        StringBuilder dateEndstr = new StringBuilder("AND ( * <='?')");
                        if (!detailsColumn.get(betweenFilter.getPropertyId().toString()).isEmpty()) {
                            Date startValue = (Date) betweenFilter.getStartValue();
                            Date endValue = (Date) betweenFilter.getEndValue();
                            StringBuilder initialStart = new StringBuilder("where ( ( * >= '?' )");
                            StringBuilder initialEnd = new StringBuilder("where ( ( * <= '?' )");
                            if (!betweenFilter.getStartValue().toString().isEmpty()) {
                                StringBuilder tempStart;
                                if (query.length() == 0) {
                                    tempStart = new StringBuilder(initialStart);
                                } else {
                                    tempStart = new StringBuilder(dateStartstr);
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, detailsColumn.get(betweenFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, DBDate.format(startValue));
                                query+=tempStart;
                            }
                            if (!betweenFilter.getEndValue().toString().isEmpty()) {
                                StringBuilder tempEnd;
                                if (query.length() == 0) {
                                    tempEnd = new StringBuilder(initialEnd);
                                } else {
                                    tempEnd = new StringBuilder(dateEndstr);
                                }

                                tempEnd.replace(tempEnd.indexOf("*"), tempEnd.indexOf("*") + 1, detailsColumn.get(betweenFilter.getPropertyId().toString()));
                                tempEnd.replace(tempEnd.indexOf("?"), tempEnd.indexOf("?") + 1, DBDate.format(endValue));
                                query+=tempEnd;
                            }
                        }
                    } 
                }
            }
            String finalQuery = StringUtils.EMPTY;
            String order = StringUtils.EMPTY;
            if (!isCount) {
                boolean sortOrder = false;
                String columnName = null;
                String orderByColumn = null;
                if (sortByColumns != null) {
                    for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                        final SortByColumn sortByColumn = (SortByColumn) iterator.next();

                        columnName = sortByColumn.getName();
                        orderByColumn = detailsColumn.get(columnName);

                        if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                            sortOrder = false;
                        } else {
                            sortOrder = true;
                        }
                    }
                }
                if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
                    order = order + " ORDER BY AVM.VIEW_NAME ";
                } else {
                    order = order + " ORDER BY " + orderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
                }
                order = order + " " + "OFFSET ";
                order = order + startIndex;
                order = order + " ROWS FETCH NEXT " + endIndex;
                order = order + " ROWS ONLY;";
            }
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
                    finalQuery = query + filterQuery;
                } else {
                    finalQuery = query + filterQuery + order;
                }
                if (isCount) {
                    List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery);
                    return list;
                }

                List<Object[]> rawList = QueryUtils.executeSelect(finalQuery);
                if (rawList.size() > 0 && !rawList.isEmpty()) {

                    dtoList = new ArrayList();
                    for (int i = 0; i < rawList.size(); i++) {
                        Object[] obj = (Object[]) rawList.get(i);
                        ViewLookupDTO dto = new ViewLookupDTO();
                        dto.setViewSid(String.valueOf(obj[0]));
                        dto.setViewName(String.valueOf(obj[1]));
                    dto.setViewType(String.valueOf(obj[NumericConstants.TWO]).equalsIgnoreCase("Private")?"privateView":"publicView");
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
        } catch (Exception e) {
             LOGGER.error(e);
            return dtoList;
        }

    }

    public static void getAllUsers() {
        List<Object> userList = new ArrayList<Object>();
        try {
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(User.class);
            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
            productProjectionList.add(ProjectionFactoryUtil.property("userId"));
            productProjectionList.add(ProjectionFactoryUtil.property("firstName"));
            productProjectionList.add(ProjectionFactoryUtil.property("lastName"));
            query.setProjection(productProjectionList);

            userList = UserLocalServiceUtil.dynamicQuery(query);
            for (int loop = 0, limit = userList.size(); loop < limit; loop++) {
                Object array[] = (Object[]) userList.get(loop);
                userMap.put(String.valueOf(array[0]), String.valueOf(array[NumericConstants.TWO]) + ", " + String.valueOf(array[1]));
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public List<ExclusionLookupDTO> getListInitialInsertFromARC(List input) {
        List<ExclusionLookupDTO> finalList = new ArrayList<ExclusionLookupDTO>();
        try {
            List<Object[]> rawList = QueryUtils.getItemData(input, "getIntialInsertQueryFromARC", null);
            if (rawList == null || rawList.isEmpty()) {
                return Collections.EMPTY_LIST;
            }
            if (!rawList.isEmpty()) {
                for (int i = 0; i < rawList.size(); i++) {
                    Object[] obj = (Object[]) rawList.get(i);
                    ExclusionLookupDTO dtoValue = new ExclusionLookupDTO();
                    dtoValue.setValues(String.valueOf(obj[1]));
                    dtoValue.setExcludedField(String.valueOf(obj[0]));
                    finalList.add(dtoValue);
                }
            }
            return finalList;
        } catch (Exception e) {
             LOGGER.error(e);
            return Collections.EMPTY_LIST;
        }
    }

}
