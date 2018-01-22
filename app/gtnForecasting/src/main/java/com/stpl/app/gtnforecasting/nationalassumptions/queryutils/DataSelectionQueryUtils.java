/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.queryutils;

import com.stpl.app.gtnforecasting.dao.NACommonResultsDAO;
import com.stpl.app.gtnforecasting.dao.impl.NACommonResultsDAOImpl;
import static com.stpl.app.gtnforecasting.logic.CommonLogic.LOGGER;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import com.stpl.app.gtnforecasting.nationalassumptions.util.Constants;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.utils.Constants.CommonConstants.DATE_FORMAT;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author Manasa
 */
public class DataSelectionQueryUtils {

     private static final NACommonResultsDAO DAO = new NACommonResultsDAOImpl();
     private String mode = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);
     public static final String AFALSE_STRING = "false";
     public static final String FILTERBUSINESS_UNIT_NAME = "filter~businessUnitName";
     public static final String FILTERCREATED_BY = "filter~createdBy";
     
    public List loadResultsTable(String projectionName, String getSelectedProducts, Object companyValueId, Object thearupeticValueId, int productGroupId, int startIndex, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns,Object businessUnit) throws PortalException, SystemException, ParseException {
        Map<String, Object> parameters = new HashMap<>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {

                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT;
                    if (!"createdBy".equals(stringFilter.getPropertyId())) {
                        parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                    } else {
                        try {

                            filterString = stringFilter.getFilterString();
                            parameters.put(Constant.FILTER + stringFilter.getPropertyId(), CommonUtils.filterUser(filterString));
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    parameters.put(Constant.FILTER + betweenFilter.getPropertyId() + Constant.TILT_FROM, String.valueOf(startValue));
                    parameters.put(Constant.FILTER + betweenFilter.getPropertyId() + "~to", String.valueOf(endValue));
                } else if (filter instanceof Compare) {
                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    Date value = (Date) compare.getValue();
                    if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        parameters.put(Constant.FILTER + compare.getPropertyId() + Constant.TILT_FROM, String.valueOf(value));
                    } else {
                        parameters.put(Constant.FILTER + compare.getPropertyId() + "~to", String.valueOf(value));
                    }
                }
            }
        }

        List resultsList;
        StringBuilder sql = null;
        sql = new StringBuilder(CustomSQLUtil.get("getTableResults"));

        String projName = projectionName;
        Integer companyId = (Integer) companyValueId;
        Integer therapeuticId = (Integer) thearupeticValueId;
        Integer itemGroupId = (Integer) productGroupId;
        projName = projName.replace("*", Constant.PERCENT);
        String ids = getSelectedProducts;
        boolean flag = false;
        if (!ids.equals(StringUtils.EMPTY)) {
            sql.append("where NAD.ITEM_MASTER_SID in(").append(ids).append(")");
            flag = true;
        }
        if (!projName.equals(StringUtils.EMPTY)) {

            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }

            sql.append(" NAM.NA_PROJ_NAME like '").append(projName).append("' ");
        }
        if (companyId != 0) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append(" CM.COMPANY_MASTER_SID = ").append(companyId);

        }
        if (itemGroupId != 0) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append(" IG.ITEM_GROUP_SID = ").append(itemGroupId);

        }
        if (therapeuticId != 0) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append(" NAM.THERAPEUTIC_CLASS = ").append(therapeuticId);
        }
        if (!"0".equals(String.valueOf(businessUnit)) && !"null".equals(String.valueOf(businessUnit))) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append(" NAM.BUSINESS_UNIT =  ").append(businessUnit);
        }
        if (parameters.containsKey(FILTERBUSINESS_UNIT_NAME)) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }

            sql.append(" CM1.COMPANY_NAME like '").append(String.valueOf(parameters.get(FILTERBUSINESS_UNIT_NAME))).append(Constant.SLASH_N);

        }

        if (parameters.containsKey(Constant.FILTERPROJECTION_NAME)) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }

            sql.append(" NAM.NA_PROJ_NAME like '").append(String.valueOf(parameters.get(Constant.FILTERPROJECTION_NAME))).append(Constant.SLASH_N);

        }

        if (parameters.containsKey(Constant.FILTER_COMPANY)) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }

            sql.append(" CM.COMPANY_NAME like  '").append(String.valueOf(parameters.get(Constant.FILTER_COMPANY))).append(Constant.SLASH_N);

        }

        if (parameters.containsKey(Constant.FILTER_PRODUCT_GROUP)) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }

            sql.append(" IG.ITEM_GROUP_NAME like '").append(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_GROUP))).append(Constant.SLASH_N);

        }

        if (parameters.containsKey(Constant.FILTER_THERAPEUTIC_CLASS)) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append("  HT.DESCRIPTION like '").append(String.valueOf(parameters.get(Constant.FILTER_THERAPEUTIC_CLASS))).append(Constant.SLASH_N);

        }

        if ((parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM))))
                && (parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO))))) {

            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append(" NAM.CREATED_DATE BETWEEN '");
            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM))));
            sql.append(from);
            sql.append(Constant.AND_SPACE);
            String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO))));
            sql.append(to);
            sql.append("' ");
        } else if ((parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM)))
                || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM))))
                && (parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO))))) {

            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append("  NAM.CREATED_DATE < '");
            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO))));
            sql.append(to);
            sql.append("' ");
        } else if ((parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM))))
                && (parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO) == null || Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO)))
                || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO))))) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }

            sql.append("  NAM.CREATED_DATE > '");
            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM))));
            sql.append(from);
            sql.append("' ");
        }
        if ((parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM))))
                && (parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO))))) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append("  NAM.MODIFIED_DATE BETWEEN '");
            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM))));
            sql.append(from);
            sql.append(Constant.AND_SPACE);
            String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO))));
            sql.append(to);
            sql.append("' ");
        } else if ((parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM)))
                || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM))))
                && (parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO))))) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append("  NAM.MODIFIED_DATE < '");
            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO))));
            sql.append(to);
            sql.append("' ");
        } else if ((parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM))))
                && (parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO) == null || Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO)))
                || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO))))) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append("  NAM.MODIFIED_DATE > '");
            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM))));
            sql.append(from);
            sql.append("' ");
        }
        if (parameters.get(FILTERCREATED_BY) != null && !Constants.NULL.equals(String.valueOf(parameters.get(FILTERCREATED_BY)))) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }

            sql.append("  NAM.CREATED_BY in(" + parameters.get(FILTERCREATED_BY) + ")");

        }

        if (flag) {
            sql.append(Constant.SPACE_AND_SMALL);
        } else {
            sql.append(Constant.SPACE_WHERE_SMALL);
        }

        sql.append("  NAM.SAVE_FLAG = 1");

        parameters.put(Constant.ISORDERED, AFALSE_STRING);

        for (Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
            SortByColumn orderByColumn = (SortByColumn) iterator.next();
            String columnId = orderByColumn.getName();
            if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                parameters.put(Constant.ORDERBY + columnId, "asc");
                parameters.put(Constant.ISORDERED, Constant.TRUE);
            } else {
                parameters.put(Constant.ORDERBY + columnId, "desc");
                parameters.put(Constant.ISORDERED, Constant.TRUE);
            }
        }

        if (parameters.get(Constant.ISORDERED) == null || AFALSE_STRING.equalsIgnoreCase(String.valueOf(parameters.get(Constant.ISORDERED)))) {
            sql.append(" ORDER BY NAM.CREATED_DATE DESC ");
        } else if (parameters.get(Constant.ISORDERED) != null && Constant.TRUE.equalsIgnoreCase(String.valueOf(parameters.get(Constant.ISORDERED)))) {
            if (parameters.get(Constant.ORDER_BYPROJECTION_NAME) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BYPROJECTION_NAME))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BYPROJECTION_NAME)))) {
                sql.append(" ORDER BY NAM.NA_PROJ_NAME ");
                sql.append(String.valueOf(parameters.get(Constant.ORDER_BYPROJECTION_NAME)));
            }

            if (parameters.get(Constant.ORDER_BYCOMPANY) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BYCOMPANY))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BYCOMPANY)))) {
                sql.append(" ORDER BY CM.COMPANY_NAME ");
                sql.append(String.valueOf(parameters.get(Constant.ORDER_BYCOMPANY)));
            }
            if (parameters.get(Constant.ORDER_BYPRODUCT_GROUP) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BYPRODUCT_GROUP))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BYPRODUCT_GROUP)))) {
                sql.append(" ORDER BY IG.ITEM_GROUP_NAME ");
                sql.append(String.valueOf(parameters.get(Constant.ORDER_BYPRODUCT_GROUP)));
            }
            if (parameters.get(Constant.ORDER_BYTHERAPEUTIC_CLASS) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BYTHERAPEUTIC_CLASS))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BYTHERAPEUTIC_CLASS)))) {
                sql.append(" ORDER BY HT.DESCRIPTION ");
                sql.append(String.valueOf(parameters.get(Constant.ORDER_BYTHERAPEUTIC_CLASS)));
            }
            if (parameters.get(Constant.ORDER_BYCREATED_DATE_SEARCH) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BYCREATED_DATE_SEARCH))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BYCREATED_DATE_SEARCH)))) {
                sql.append(" ORDER BY NAM.CREATED_DATE ");
                sql.append(String.valueOf(parameters.get(Constant.ORDER_BYCREATED_DATE_SEARCH)));
            }
            if (parameters.get(Constant.ORDER_BYMODIFIED_DATE_SEARCH) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BYMODIFIED_DATE_SEARCH))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BYMODIFIED_DATE_SEARCH)))) {
                sql.append(" ORDER BY NAM.MODIFIED_DATE ");
                sql.append(String.valueOf(parameters.get(Constant.ORDER_BYMODIFIED_DATE_SEARCH)));
            }
            if (parameters.get(Constant.ORDER_BYCREATED_BY) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BYCREATED_BY))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BYCREATED_BY)))) {
                sql.append(" ORDER BY NAM.CREATED_BY ");
                sql.append(String.valueOf(parameters.get(Constant.ORDER_BYCREATED_BY)));
            }
            if (parameters.get(Constant.ORDER_BYBUSINESS_UNIT_NAME) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BYBUSINESS_UNIT_NAME))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BYBUSINESS_UNIT_NAME)))) {
                sql.append(" ORDER BY NAM.BUSINESS_UNIT ");
                sql.append(String.valueOf(parameters.get(Constant.ORDER_BYBUSINESS_UNIT_NAME)));
        }
        }
        sql.append(" OFFSET ").append(startIndex).append(Constant.ROWS_FETCH_NEXT_SPACE).append(offset).append(Constant.ROWS_ONLY_SPACE);

        resultsList = (List) DAO.executeSelectQuery(String.valueOf(sql));
        return resultsList;
    }

    public int loadResultsTableCount(String projectionName, String getSelectedProducts, Object companyValueId, Object thearupeticValueId, int productGroupId, Set<Container.Filter> filters,Object businessUnit) throws PortalException, SystemException, ParseException {

        Map<String, Object> parameters = new HashMap<>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {

                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT;
                    if (!"createdBy".equals(stringFilter.getPropertyId())) {
                        parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);

                    } else {
                        try {
                            filterString = stringFilter.getFilterString();
                            parameters.put(Constant.FILTER + stringFilter.getPropertyId(), CommonUtils.filterUser(filterString));
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }

                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    parameters.put(Constant.FILTER + betweenFilter.getPropertyId() + Constant.TILT_FROM, String.valueOf(startValue));
                    parameters.put(Constant.FILTER + betweenFilter.getPropertyId() + "~to", String.valueOf(endValue));
                } else if (filter instanceof Compare) {
                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    Date value = (Date) compare.getValue();
                    if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        parameters.put(Constant.FILTER + compare.getPropertyId() + Constant.TILT_FROM, String.valueOf(value));
                    } else {
                        parameters.put(Constant.FILTER + compare.getPropertyId() + "~to", String.valueOf(value));
                    }
                }
            }
        }

        List countList;
        StringBuilder sql = new StringBuilder(CustomSQLUtil.get("getTableResultsCount"));
        int count = 0;
        String projName = projectionName;
        Integer companyId = (Integer) companyValueId;
        Integer therapeuticId = (Integer) thearupeticValueId;
        Integer itemGroupId = (Integer) productGroupId;
        projName = projName.replace("*", Constant.PERCENT);
        String ids = getSelectedProducts;
        boolean flag = false;
        if (!ids.equals(StringUtils.EMPTY)) {
            sql.append("where NAD.ITEM_MASTER_SID in(").append(ids).append(")");
            flag = true;
        }
        if (!projName.equals(StringUtils.EMPTY)) {

            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }

            sql.append("  NAM.NA_PROJ_NAME like '").append(projName).append("' ");
        }
        if (companyId != 0) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append(" CM.COMPANY_MASTER_SID = ").append(companyId);

        }
        if (itemGroupId != 0) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append(" IG.ITEM_GROUP_SID = ").append(itemGroupId);

        }
        if (therapeuticId != 0) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append(" NAM.THERAPEUTIC_CLASS =  ").append(therapeuticId);
        }
        if (!"0".equals(String.valueOf(businessUnit)) && !"null".equals(String.valueOf(businessUnit))) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append(" NAM.BUSINESS_UNIT =  ").append(businessUnit);
        }
        if (parameters.containsKey(FILTERBUSINESS_UNIT_NAME)) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }

            sql.append(" CM1.COMPANY_NAME like '").append(String.valueOf(parameters.get(FILTERBUSINESS_UNIT_NAME))).append(Constant.SLASH_N);

        }
        if (parameters.containsKey(Constant.FILTERPROJECTION_NAME)) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }

            sql.append(" NAM.NA_PROJ_NAME like  '").append(String.valueOf(parameters.get(Constant.FILTERPROJECTION_NAME))).append(Constant.SLASH_N);

        }

        if (parameters.containsKey(Constant.FILTER_COMPANY)) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }

            sql.append("  CM.COMPANY_NAME like '").append(String.valueOf(parameters.get(Constant.FILTER_COMPANY))).append(Constant.SLASH_N);

        }

        if (parameters.containsKey(Constant.FILTER_PRODUCT_GROUP)) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }

            sql.append(" IG.ITEM_GROUP_NAME like '").append(String.valueOf(parameters.get(Constant.FILTER_PRODUCT_GROUP))).append(Constant.SLASH_N);

        }

        if (parameters.containsKey(Constant.FILTER_THERAPEUTIC_CLASS)) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append("  HT.DESCRIPTION like '").append(String.valueOf(parameters.get(Constant.FILTER_THERAPEUTIC_CLASS))).append(Constant.SLASH_N);

        }
        if ((parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM))))
                && (parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO))))) {

            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append(" NAM.CREATED_DATE BETWEEN '");
            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM))));
            sql.append(from);
            sql.append(Constant.AND_SPACE);
            String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO))));
            sql.append(to);
            sql.append("' ");
        } else if ((parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM)))
                || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM))))
                && (parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO))))) {

            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append("  NAM.CREATED_DATE < '");
            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO))));
            sql.append(to);
            sql.append("' ");
        } else if ((parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM))))
                && (parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO) == null || Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO)))
                || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHTO))))) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }

            sql.append("  NAM.CREATED_DATE > '");
            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTERCREATED_DATE_SEARCHFROM))));
            sql.append(from);
            sql.append("' ");
        }
        if ((parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM))))
                && (parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO))))) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append("  NAM.MODIFIED_DATE BETWEEN '");
            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM))));
            sql.append(from);
            sql.append(Constant.AND_SPACE);
            String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO))));
            sql.append(to);
            sql.append("' ");
        } else if ((parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM) == null || Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM)))
                || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM))))
                && (parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO))))) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append("  NAM.MODIFIED_DATE < '");
            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String to = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO))));
            sql.append(to);
            sql.append("' ");
        } else if ((parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM)))
                && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM))))
                && (parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO) == null || Constants.NULL.equals(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO)))
                || StringUtils.isBlank(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHTO))))) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }
            sql.append("  NAM.MODIFIED_DATE > '");
            SimpleDateFormat parse = new SimpleDateFormat(Constant.EEE_MMM_DD_H_HMMSS_Z_YYYY);
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String from = format.format(parse.parse(String.valueOf(parameters.get(Constant.FILTERMODIFIED_DATE_SEARCHFROM))));
            sql.append(from);
            sql.append("' ");
        }
        if (parameters.get(FILTERCREATED_BY) != null && !Constants.NULL.equals(String.valueOf(parameters.get(FILTERCREATED_BY)))) {
            if (flag) {
                sql.append(Constant.SPACE_AND_SMALL);
            } else {
                sql.append(Constant.SPACE_WHERE_SMALL);
                flag = true;
            }

            sql.append("  NAM.CREATED_BY in(" + parameters.get(FILTERCREATED_BY) + ")");

        }

        if (flag) {
            sql.append(Constant.SPACE_AND_SMALL);
        } else {
            sql.append(Constant.SPACE_WHERE_SMALL);
        }

        sql.append(" NAM.SAVE_FLAG = 1");
        
        countList = (List) DAO.executeSelectQuery(String.valueOf(sql));

        if (countList != null && !countList.isEmpty()) {
            count = Integer.parseInt(String.valueOf(countList.get(0)));
        }
        return count;

    }

    public List getPriceTypesList(SessionDTO session) throws PortalException, SystemException {
        String sql = CustomSQLUtil.get(Constant.VIEW.equalsIgnoreCase(mode)?"getNaPriceTypesForView":"getNaPriceTypes");
     if (session.getProjectionId() != 0) {
         if (Constant.VIEW.equalsIgnoreCase(mode)) {
             sql += "   NA_PROJ_MASTER_SID =  "
                     + session.getProjectionId();
         } else {
             sql += " WHERE  NA_PROJ_MASTER_SID =  "
                     + session.getProjectionId();
         }
        }

        return (List) DAO.executeSelectQuery(QueryUtil.replaceTableNames(sql,session.getCurrentTableNames()));
    }

    public String deleteResultsTable(int projMasterId) throws PortalException, SystemException {

        Map<String, Object> input = new HashMap<>();
        input.put("?PID", projMasterId);
        String customSql = CustomSQLUtil.get("na.deleteMain");

        for (String key : input.keySet()) {
            customSql = customSql.replace(key, String.valueOf(input.get(key)));
        }

        DAO.executeBulkUpdateQuery(customSql);
        return "Success";
    }

    public List getNdcList(SessionDTO session) throws PortalException, SystemException {
        String sql = CustomSQLUtil.get("getNdcList");


        return (List) DAO.executeSelectQuery(QueryUtil.replaceTableNames(sql,session.getCurrentTableNames()));
    }

    public List getFederalList(SessionDTO session) throws PortalException, SystemException {
        String sql = CustomSQLUtil.get("getFederalList");
        return (List) DAO.executeSelectQuery(QueryUtil.replaceTableNames(sql,session.getCurrentTableNames()));
    }

    public static int loadProductGroupsCount(String productGroupNo, String productGroupName, Set<Container.Filter> filters) {
        Map<String, Object> parameters = new HashMap<>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT;
                    parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                }
            }
        }

        List countList = null;
        String sql = CustomSQLUtil.get("getProductGroupsCount");
        boolean andAppend = false;
        boolean whereAppend = true;
        if (StringUtils.isNotBlank(productGroupNo)) {
            if (whereAppend) {
                sql = sql + Constant.WHERE_SPACE;
                whereAppend = false;
            }
            if (andAppend) {
                sql = sql + Constant.SPACE_AND_SPACE;
            }
            sql += " ITEM_GROUP_NO LIKE '" + productGroupNo + "' ";
            andAppend = true;
        }

        if (StringUtils.isNotBlank(productGroupName)) {
            if (whereAppend) {
                sql = sql + Constant.WHERE_SPACE;
                whereAppend = false;
            }
            if (andAppend) {
                sql = sql + Constant.SPACE_AND_SPACE;
            }
            sql += " ITEM_GROUP_NAME LIKE '" + productGroupName + "' ";
            andAppend = true;
        }
        if (parameters.containsKey(FILTER_PRODUCT_GROUP_NAME)) {
            if (whereAppend) {
                sql = sql + Constant.WHERE_SPACE;
                whereAppend = false;
            }
            if (andAppend) {
                sql = sql + Constant.SPACE_AND_SPACE;
            }

            sql += " ITEM_GROUP_NAME like '" + String.valueOf(parameters.get(FILTER_PRODUCT_GROUP_NAME)) + Constant.SLASH_N;

        }

        if (parameters.containsKey(Constant.FILTER_PRODUCT_GROUP)) {
            if (whereAppend) {
                sql = sql + Constant.WHERE_SPACE;
                whereAppend = false;
            }
            if (andAppend) {
                sql = sql + Constant.SPACE_AND_SPACE;
            }

            sql += (" IG.ITEM_GROUP_NO like '") + (String.valueOf(parameters.get(Constant.FILTER_PRODUCT_GROUP))) + (Constant.SLASH_N);

        }

        if (parameters.containsKey(Constant.FILTER_COMPANY)) {
            if (whereAppend) {
                sql = sql + Constant.WHERE_SPACE;
            }
            if (andAppend) {
                sql = sql + Constant.SPACE_AND_SPACE;
            }

            sql += (" CM.COMPANY_NAME  like '") + (String.valueOf(parameters.get(Constant.FILTER_COMPANY))) + (Constant.SLASH_N);

        }

        try {
            countList = (List) DAO.executeSelectQuery(sql);
        } catch (PortalException | SystemException ex) {
            Logger.getLogger(DataSelectionQueryUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;
        if (countList != null && !countList.isEmpty()) {
            count = Integer.parseInt(String.valueOf(countList.get(0)));
        }
        return count;

    }
    public static final String FILTER_PRODUCT_GROUP_NAME = "filter~productGroupName";

    /**
     * This method is used to get Product Group based on Search Criteria
     *
     * @param productGroupNo
     * @param productGroupName
     * @param company
     * @param segment
     * @param startIndex
     * @param offset
     * @param filters
     * @param sortByColumns
     * @return list
     */
    public List getProductGroups(String productGroupNo, String productGroupName, int startIndex, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) {

        try {
            Map<String, Object> parameters = new HashMap<>();
            if (filters != null) {
                for (Container.Filter filter : filters) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT;
                        parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                    }
                }
            }
            String sql = CustomSQLUtil.get("getProductGroups");
            boolean andAppend = false;
            boolean whereAppend = true;
            if (StringUtils.isNotBlank(productGroupNo)) {
                if (whereAppend) {
                    sql = sql + Constant.WHERE_SPACE;
                    whereAppend = false;
                }
                if (andAppend) {
                    sql = sql + Constant.SPACE_AND_SPACE;
                }
                sql += " ITEM_GROUP_NO LIKE '" + productGroupNo + "' ";
                andAppend = true;
            }

            if (StringUtils.isNotBlank(productGroupName)) {
                if (whereAppend) {
                    sql = sql + Constant.WHERE_SPACE;
                    whereAppend = false;
                }
                if (andAppend) {
                    sql = sql + Constant.SPACE_AND_SPACE;
                }
                sql += " ITEM_GROUP_NAME LIKE '" + productGroupName + "' ";
                andAppend = true;
            }

            if (parameters.containsKey(FILTER_PRODUCT_GROUP_NAME)) {
                if (whereAppend) {
                    sql = sql + Constant.WHERE_SPACE;
                    whereAppend = false;
                }
                if (andAppend) {
                    sql = sql + Constant.SPACE_AND_SPACE;
                }

                sql += " ITEM_GROUP_NAME like '" + String.valueOf(parameters.get(FILTER_PRODUCT_GROUP_NAME)) + Constant.SLASH_N;

            }

            if (parameters.containsKey(Constant.FILTER_PRODUCT_GROUP)) {
                if (whereAppend) {
                    sql = sql + Constant.WHERE_SPACE;
                    whereAppend = false;
                }
                if (andAppend) {
                    sql = sql + Constant.SPACE_AND_SPACE;
                }

                sql += (" IG.ITEM_GROUP_NO like '") + (String.valueOf(parameters.get(Constant.FILTER_PRODUCT_GROUP))) + (Constant.SLASH_N);

            }

            if (parameters.containsKey("filter~productGroupDescription")) {
                if (whereAppend) {
                    sql = sql + Constant.WHERE_SPACE;
                    whereAppend = false;
                }
                if (andAppend) {
                    sql = sql + Constant.SPACE_AND_SPACE;
                }

                sql += (" IG.ITEM_GROUP_DESCRIPTION like '") + (String.valueOf(parameters.get("filter~productGroupDescription"))) + (Constant.SLASH_N);

            }

            if (parameters.containsKey(Constant.FILTER_COMPANY)) {
                if (whereAppend) {
                    sql = sql + Constant.WHERE_SPACE;
                }
                if (andAppend) {
                    sql = sql + Constant.SPACE_AND_SPACE;
                }

                sql += (" CM.COMPANY_NAME like '") + (String.valueOf(parameters.get(Constant.FILTER_COMPANY))) + (Constant.SLASH_N);

            }

            parameters.put(Constant.ISORDERED, AFALSE_STRING);

            for (Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                SortByColumn orderByColumn = (SortByColumn) iterator.next();
                String columnId = orderByColumn.getName();
                if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                    parameters.put(Constant.ORDERBY + columnId, "asc");
                    parameters.put(Constant.ISORDERED, Constant.TRUE);
                } else {
                    parameters.put(Constant.ORDERBY + columnId, "desc");
                    parameters.put(Constant.ISORDERED, Constant.TRUE);
                }
            }

            if (parameters.get(Constant.ISORDERED) == null || AFALSE_STRING.equalsIgnoreCase(String.valueOf(parameters.get(Constant.ISORDERED)))) {
                sql = sql + (" ORDER BY ITEM_GROUP_NAME DESC ");
            } else if (parameters.get(Constant.ISORDERED) != null && Constant.TRUE.equalsIgnoreCase(String.valueOf(parameters.get(Constant.ISORDERED)))) {
                if (parameters.get(Constant.ORDER_BYPRODUCT_GROUP_NAME) != null && !com.stpl.app.serviceUtils.Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BYPRODUCT_GROUP_NAME))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BYPRODUCT_GROUP_NAME)))) {
                    sql = sql + (" ORDER BY ITEM_GROUP_NAME ");
                    sql = sql + (String.valueOf(parameters.get(Constant.ORDER_BYPRODUCT_GROUP_NAME)));
                }

                if (parameters.get(Constant.ORDER_BYPRODUCT_GROUP) != null && !com.stpl.app.serviceUtils.Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BYPRODUCT_GROUP))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BYPRODUCT_GROUP)))) {
                    sql = sql + (" ORDER BY ITEM_GROUP_NO ");
                    sql = sql + (String.valueOf(parameters.get(Constant.ORDER_BYPRODUCT_GROUP)));
                }
                if (parameters.get(Constant.ORDER_BYCOMPANY) != null && !com.stpl.app.serviceUtils.Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BYCOMPANY))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BYCOMPANY)))) {
                    sql = sql + (" ORDER BY CM.COMPANY_NAME ");
                    sql = sql + (String.valueOf(parameters.get(Constant.ORDER_BYCOMPANY)));
                }
                if (parameters.get(Constant.ORDER_BYPRODUCT_GROUP_DESCRIPTION) != null && !Constants.NULL.equals(String.valueOf(parameters.get(Constant.ORDER_BYPRODUCT_GROUP_DESCRIPTION))) && !StringUtils.isBlank(String.valueOf(parameters.get(Constant.ORDER_BYPRODUCT_GROUP_DESCRIPTION)))) {
                    sql = sql + (" ORDER BY IG.ITEM_GROUP_DESCRIPTION ");
                    sql = sql + (String.valueOf(parameters.get(Constant.ORDER_BYPRODUCT_GROUP_DESCRIPTION)));
                }
            }
            sql += (" OFFSET ") + (startIndex) + (Constant.ROWS_FETCH_NEXT_SPACE) + (offset) + (Constant.ROWS_ONLY_SPACE);
            return (List) DAO.executeSelectQuery(sql);
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
            return Collections.emptyList();
        } finally {
        }
    }
    
    public List getProductGroupresults(Object companyValue, Object therapeuticClassValue, Object productGroupValue,Object businessUnit) {
        try {
            Map<String, Object> input = new HashMap<>();
            String customSql = CustomSQLUtil.get("getProductNamesFromGroup");
            input.put("?PGQUERY", "AND IG.ITEM_GROUP_NAME LIKE '" + productGroupValue.toString().trim() + "'");
            if (!StringUtils.EMPTY.equals(companyValue.toString())) {
                input.put("?CMQUERY", "AND CM.COMPANY_NAME= '" + companyValue.toString() + "'");
            } else {
                input.put("?CMQUERY", StringUtils.EMPTY);
            }
            boolean flag=false;
            if (!StringUtils.EMPTY.equals(therapeuticClassValue) && !therapeuticClassValue.toString().equals("0")) {
                input.put("?TCQUERY", "WHERE IM.THERAPEUTIC_CLASS= '" + therapeuticClassValue.toString() + "'");
                flag=true;
            } else {
                input.put("?TCQUERY", StringUtils.EMPTY);
            }

            for (String key : input.keySet()) {
                customSql = customSql.replace(key, String.valueOf(input.get(key)));
            }
            if (!String.valueOf(businessUnit).equals("0") && !String.valueOf(businessUnit).equals("null") && !String.valueOf(businessUnit).isEmpty()) {
                if (flag) {
                    customSql = customSql + " AND  IM.ORGANIZATION_KEY like '" + String.valueOf(businessUnit) + "' ";
                } else {
                    customSql = customSql + " WHERE  IM.ORGANIZATION_KEY like '" + String.valueOf(businessUnit) + "' ";
                }
            }
            return (List) DAO.executeSelectQuery(customSql);
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
            return new ArrayList();
        }
    }
    
}
