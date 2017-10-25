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
     public String mode = (String) VaadinSession.getCurrent().getAttribute(Constant.MODE);

    public List loadResultsTable(String projectionName, String getSelectedProducts, Object companyValueId, Object thearupeticValueId, int productGroupId, int startIndex, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns,Object businessUnit) throws PortalException, SystemException, ParseException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {

                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT;
                    if (!"createdBy".equals(stringFilter.getPropertyId())) {
                        parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                    } else {
                        String lastName = StringUtils.EMPTY;
                        if (filterString.contains(",")) {
                            String array[] = filterString.split(",");
                            lastName = array[0].trim();
                        } else {
                            lastName = filterString;
                            lastName = lastName.trim();
                        }

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
                    parameters.put(Constant.FILTER + betweenFilter.getPropertyId() + "~from", String.valueOf(startValue));
                    parameters.put(Constant.FILTER + betweenFilter.getPropertyId() + "~to", String.valueOf(endValue));
                } else if (filter instanceof Compare) {
                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    Date value = (Date) compare.getValue();
                    if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        parameters.put(Constant.FILTER + compare.getPropertyId() + "~from", String.valueOf(value));
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
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }

            sql.append(" NAM.NA_PROJ_NAME like '").append(projName).append("' ");
        }
        if (companyId != 0) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append(" CM.COMPANY_MASTER_SID = ").append(companyId);

        }
        if (itemGroupId != 0) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append(" IG.ITEM_GROUP_SID = ").append(itemGroupId);

        }
        if (therapeuticId != 0) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append(" IM.THERAPEUTIC_CLASS = ").append(therapeuticId);
        }
        if (!"0".equals(String.valueOf(businessUnit)) && !"null".equals(String.valueOf(businessUnit))) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append(" NAM.BUSINESS_UNIT =  ").append(businessUnit);
        }
        if (parameters.containsKey("filter~businessUnitName")) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }

            sql.append(" CM1.COMPANY_NAME like '").append(String.valueOf(parameters.get("filter~businessUnitName"))).append("'  \n ");

        }

        if (parameters.containsKey("filter~projectionName")) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }

            sql.append(" NAM.NA_PROJ_NAME like '").append(String.valueOf(parameters.get("filter~projectionName"))).append("'  \n ");

        }

        if (parameters.containsKey("filter~company")) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }

            sql.append(" CM.COMPANY_NAME like '").append(String.valueOf(parameters.get("filter~company"))).append("'  \n ");

        }

        if (parameters.containsKey("filter~productGroup")) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }

            sql.append(" IG.ITEM_GROUP_NAME like '").append(String.valueOf(parameters.get("filter~productGroup"))).append("'  \n ");

        }

        if (parameters.containsKey("filter~therapeuticClass")) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append("  HT.DESCRIPTION like '").append(String.valueOf(parameters.get("filter~therapeuticClass"))).append("'  \n ");

        }

        if ((parameters.get("filter~createdDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                && (parameters.get("filter~createdDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {

            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append(" NAM.CREATED_DATE BETWEEN '");
            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~from"))));
            sql.append(from);
            sql.append("' AND '");
            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~to"))));
            sql.append(to);
            sql.append("' ");
        } else if ((parameters.get("filter~createdDateSearch~from") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                || StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                && (parameters.get("filter~createdDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {

            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append("  NAM.CREATED_DATE < '");
            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~to"))));
            sql.append(to);
            sql.append("' ");
        } else if ((parameters.get("filter~createdDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                && (parameters.get("filter~createdDateSearch~to") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                || StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }

            sql.append("  NAM.CREATED_DATE > '");
            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~from"))));
            sql.append(from);
            sql.append("' ");
        }
        if ((parameters.get("filter~modifiedDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))))
                && (parameters.get("filter~modifiedDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))
                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))))) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append("  NAM.MODIFIED_DATE BETWEEN '");
            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))));
            sql.append(from);
            sql.append("' AND '");
            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))));
            sql.append(to);
            sql.append("' ");
        } else if ((parameters.get("filter~modifiedDateSearch~from") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                || StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))))
                && (parameters.get("filter~modifiedDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))
                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))))) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append("  NAM.MODIFIED_DATE < '");
            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))));
            sql.append(to);
            sql.append("' ");
        } else if ((parameters.get("filter~modifiedDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))))
                && (parameters.get("filter~modifiedDateSearch~to") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))
                || StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))))) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append("  NAM.MODIFIED_DATE > '");
            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))));
            sql.append(from);
            sql.append("' ");
        }
        if (parameters.get("filter~createdBy") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdBy")))) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }

            sql.append("  NAM.CREATED_BY in(" + parameters.get("filter~createdBy") + ")");

        }

        if (flag) {
            sql.append(" and");
        } else {
            sql.append(" where ");
            flag = true;
        }

        sql.append("  NAM.SAVE_FLAG = 1");

        parameters.put(Constant.ISORDERED, "false");

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

        if (parameters.get(Constant.ISORDERED) == null || "false".equalsIgnoreCase(String.valueOf(parameters.get(Constant.ISORDERED)))) {
            sql.append(" ORDER BY NAM.CREATED_DATE DESC ");
        } else if (parameters.get(Constant.ISORDERED) != null && Constant.TRUE.equalsIgnoreCase(String.valueOf(parameters.get(Constant.ISORDERED)))) {
            if (parameters.get("orderBy~projectionName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~projectionName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~projectionName")))) {
                sql.append(" ORDER BY NAM.NA_PROJ_NAME ");
                sql.append(String.valueOf(parameters.get("orderBy~projectionName")));
            }

            if (parameters.get("orderBy~company") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~company"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~company")))) {
                sql.append(" ORDER BY CM.COMPANY_NAME ");
                sql.append(String.valueOf(parameters.get("orderBy~company")));
            }
            if (parameters.get("orderBy~productGroup") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productGroup"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productGroup")))) {
                sql.append(" ORDER BY IG.ITEM_GROUP_NAME ");
                sql.append(String.valueOf(parameters.get("orderBy~productGroup")));
            }
            if (parameters.get("orderBy~therapeuticClass") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~therapeuticClass"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~therapeuticClass")))) {
                sql.append(" ORDER BY HT.DESCRIPTION ");
                sql.append(String.valueOf(parameters.get("orderBy~therapeuticClass")));
            }
            if (parameters.get("orderBy~createdDateSearch") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~createdDateSearch"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~createdDateSearch")))) {
                sql.append(" ORDER BY NAM.CREATED_DATE ");
                sql.append(String.valueOf(parameters.get("orderBy~createdDateSearch")));
            }
            if (parameters.get("orderBy~modifiedDateSearch") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~modifiedDateSearch"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~modifiedDateSearch")))) {
                sql.append(" ORDER BY NAM.MODIFIED_DATE ");
                sql.append(String.valueOf(parameters.get("orderBy~modifiedDateSearch")));
            }
            if (parameters.get("orderBy~createdBy") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~createdBy"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~createdBy")))) {
                sql.append(" ORDER BY NAM.CREATED_BY ");
                sql.append(String.valueOf(parameters.get("orderBy~createdBy")));
            }
            if (parameters.get("orderBy~businessUnitName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~businessUnitName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~businessUnitName")))) {
                sql.append(" ORDER BY NAM.BUSINESS_UNIT ");
                sql.append(String.valueOf(parameters.get("orderBy~businessUnitName")));
        }
        }
        sql.append(" OFFSET ").append(startIndex).append(" ROWS FETCH NEXT ").append(offset).append(" ROWS ONLY");

        resultsList = (List) DAO.executeSelectQuery(String.valueOf(sql));
        return resultsList;
    }

    public int loadResultsTableCount(String projectionName, String getSelectedProducts, Object companyValueId, Object thearupeticValueId, int productGroupId, Set<Container.Filter> filters,Object businessUnit) throws PortalException, SystemException, ParseException {

        Map<String, Object> parameters = new HashMap<String, Object>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {

                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT;
                    if (!"createdBy".equals(stringFilter.getPropertyId())) {
                        parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);

                    } else {
                        String lastName = StringUtils.EMPTY;
                        if (filterString.contains(",")) {
                            String array[] = filterString.split(",");
                            lastName = array[0].trim();
                        } else {
                            lastName = filterString;
                            lastName = lastName.trim();
                        }

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
                    parameters.put(Constant.FILTER + betweenFilter.getPropertyId() + "~from", String.valueOf(startValue));
                    parameters.put(Constant.FILTER + betweenFilter.getPropertyId() + "~to", String.valueOf(endValue));
                } else if (filter instanceof Compare) {
                    Compare compare = (Compare) filter;
                    Compare.Operation operation = compare.getOperation();
                    Date value = (Date) compare.getValue();
                    if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        parameters.put(Constant.FILTER + compare.getPropertyId() + "~from", String.valueOf(value));
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
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }

            sql.append(" NAM.NA_PROJ_NAME like '").append(projName).append("' ");
        }
        if (companyId != 0) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append(" CM.COMPANY_MASTER_SID = ").append(companyId);

        }
        if (itemGroupId != 0) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append(" IG.ITEM_GROUP_SID = ").append(itemGroupId);

        }
        if (therapeuticId != 0) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append(" IM.THERAPEUTIC_CLASS =  ").append(therapeuticId);
        }
        if (!"0".equals(String.valueOf(businessUnit)) && !"null".equals(String.valueOf(businessUnit))) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append(" NAM.BUSINESS_UNIT =  ").append(businessUnit);
        }
        if (parameters.containsKey("filter~businessUnitName")) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }

            sql.append(" CM1.COMPANY_NAME like '").append(String.valueOf(parameters.get("filter~businessUnitName"))).append("'  \n ");

        }
        if (parameters.containsKey("filter~projectionName")) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }

            sql.append(" NAM.NA_PROJ_NAME like '").append(String.valueOf(parameters.get("filter~projectionName"))).append("'  \n ");

        }

        if (parameters.containsKey("filter~company")) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }

            sql.append(" CM.COMPANY_NAME like '").append(String.valueOf(parameters.get("filter~company"))).append("'  \n ");

        }

        if (parameters.containsKey("filter~productGroup")) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }

            sql.append(" IG.ITEM_GROUP_NAME like '").append(String.valueOf(parameters.get("filter~productGroup"))).append("'  \n ");

        }

        if (parameters.containsKey("filter~therapeuticClass")) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append("  HT.DESCRIPTION like '").append(String.valueOf(parameters.get("filter~therapeuticClass"))).append("'  \n ");

        }
        if ((parameters.get("filter~createdDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                && (parameters.get("filter~createdDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {

            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append(" NAM.CREATED_DATE BETWEEN '");
            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~from"))));
            sql.append(from);
            sql.append("' AND '");
            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~to"))));
            sql.append(to);
            sql.append("' ");
        } else if ((parameters.get("filter~createdDateSearch~from") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                || StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                && (parameters.get("filter~createdDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {

            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append("  NAM.CREATED_DATE < '");
            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~to"))));
            sql.append(to);
            sql.append("' ");
        } else if ((parameters.get("filter~createdDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~from")))
                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~from"))))
                && (parameters.get("filter~createdDateSearch~to") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~createdDateSearch~to")))
                || StringUtils.isBlank(String.valueOf(parameters.get("filter~createdDateSearch~to"))))) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }

            sql.append("  NAM.CREATED_DATE > '");
            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~createdDateSearch~from"))));
            sql.append(from);
            sql.append("' ");
        }
        if ((parameters.get("filter~modifiedDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))))
                && (parameters.get("filter~modifiedDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))
                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))))) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append("  NAM.MODIFIED_DATE BETWEEN '");
            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))));
            sql.append(from);
            sql.append("' AND '");
            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))));
            sql.append(to);
            sql.append("' ");
        } else if ((parameters.get("filter~modifiedDateSearch~from") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                || StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))))
                && (parameters.get("filter~modifiedDateSearch~to") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))
                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))))) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append("  NAM.MODIFIED_DATE < '");
            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String to = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))));
            sql.append(to);
            sql.append("' ");
        } else if ((parameters.get("filter~modifiedDateSearch~from") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~from")))
                && !StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))))
                && (parameters.get("filter~modifiedDateSearch~to") == null || Constants.NULL.equals(String.valueOf(parameters.get("filter~modifiedDateSearch~to")))
                || StringUtils.isBlank(String.valueOf(parameters.get("filter~modifiedDateSearch~to"))))) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }
            sql.append("  NAM.MODIFIED_DATE > '");
            SimpleDateFormat parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT.getConstant());
            String from = format.format(parse.parse(String.valueOf(parameters.get("filter~modifiedDateSearch~from"))));
            sql.append(from);
            sql.append("' ");
        }
        if (parameters.get("filter~createdBy") != null && !Constants.NULL.equals(String.valueOf(parameters.get("filter~createdBy")))) {
            if (flag) {
                sql.append(" and");
            } else {
                sql.append(" where ");
                flag = true;
            }

            sql.append("  NAM.CREATED_BY in(" + parameters.get("filter~createdBy") + ")");

        }

        if (flag) {
            sql.append(" and");
        } else {
            sql.append(" where ");
            flag = true;
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

        Map<String, Object> input = new HashMap<String, Object>();
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
        Map<String, Object> parameters = new HashMap<String, Object>();
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
                sql = sql + " WHERE ";
                whereAppend = false;
            }
            if (andAppend) {
                sql = sql + " AND ";
            }
            sql += " ITEM_GROUP_NO LIKE '" + productGroupNo + "' ";
            andAppend = true;
        }

        if (StringUtils.isNotBlank(productGroupName)) {
            if (whereAppend) {
                sql = sql + " WHERE ";
                whereAppend = false;
            }
            if (andAppend) {
                sql = sql + " AND ";
            }
            sql += " ITEM_GROUP_NAME LIKE '" + productGroupName + "' ";
            andAppend = true;
        }
        if (parameters.containsKey("filter~productGroupName")) {
            if (whereAppend) {
                sql = sql + " WHERE ";
                whereAppend = false;
            }
            if (andAppend) {
                sql = sql + " AND ";
            }

            sql += " ITEM_GROUP_NAME like '" + String.valueOf(parameters.get("filter~productGroupName")) + "'  \n ";

        }

        if (parameters.containsKey("filter~productGroup")) {
            if (whereAppend) {
                sql = sql + " WHERE ";
                whereAppend = false;
            }
            if (andAppend) {
                sql = sql + " AND ";
            }

            sql += (" IG.ITEM_GROUP_NO like '") + (String.valueOf(parameters.get("filter~productGroup"))) + ("'  \n ");

        }

        if (parameters.containsKey("filter~company")) {
            if (whereAppend) {
                sql = sql + " WHERE ";
                whereAppend = false;
            }
            if (andAppend) {
                sql = sql + " AND ";
            }

            sql += (" CM.COMPANY_NAME like '") + (String.valueOf(parameters.get("filter~company"))) + ("'  \n ");

        }

        try {
            countList = (List) DAO.executeSelectQuery(sql);
        } catch (PortalException ex) {
            Logger.getLogger(DataSelectionQueryUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(DataSelectionQueryUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;
        if (countList != null && !countList.isEmpty()) {
            count = Integer.parseInt(String.valueOf(countList.get(0)));
        }
        return count;

    }

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
    public List getProductGroups(String productGroupNo, String productGroupName, String company, String segment, int startIndex, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns) {

        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
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
                    sql = sql + " WHERE ";
                    whereAppend = false;
                }
                if (andAppend) {
                    sql = sql + " AND ";
                }
                sql += " ITEM_GROUP_NO LIKE '" + productGroupNo + "' ";
                andAppend = true;
            }

            if (StringUtils.isNotBlank(productGroupName)) {
                if (whereAppend) {
                    sql = sql + " WHERE ";
                    whereAppend = false;
                }
                if (andAppend) {
                    sql = sql + " AND ";
                }
                sql += " ITEM_GROUP_NAME LIKE '" + productGroupName + "' ";
                andAppend = true;
            }

            if (parameters.containsKey("filter~productGroupName")) {
                if (whereAppend) {
                    sql = sql + " WHERE ";
                    whereAppend = false;
                }
                if (andAppend) {
                    sql = sql + " AND ";
                }

                sql += " ITEM_GROUP_NAME like '" + String.valueOf(parameters.get("filter~productGroupName")) + "'  \n ";

            }

            if (parameters.containsKey("filter~productGroup")) {
                if (whereAppend) {
                    sql = sql + " WHERE ";
                    whereAppend = false;
                }
                if (andAppend) {
                    sql = sql + " AND ";
                }

                sql += (" IG.ITEM_GROUP_NO like '") + (String.valueOf(parameters.get("filter~productGroup"))) + ("'  \n ");

            }

            if (parameters.containsKey("filter~productGroupDescription")) {
                if (whereAppend) {
                    sql = sql + " WHERE ";
                    whereAppend = false;
                }
                if (andAppend) {
                    sql = sql + " AND ";
                }

                sql += (" IG.ITEM_GROUP_DESCRIPTION like '") + (String.valueOf(parameters.get("filter~productGroupDescription"))) + ("'  \n ");

            }

            if (parameters.containsKey("filter~company")) {
                if (whereAppend) {
                    sql = sql + " WHERE ";
                    whereAppend = false;
                }
                if (andAppend) {
                    sql = sql + " AND ";
                }

                sql += (" CM.COMPANY_NAME like '") + (String.valueOf(parameters.get("filter~company"))) + ("'  \n ");

            }

            parameters.put(Constant.ISORDERED, "false");

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

            if (parameters.get(Constant.ISORDERED) == null || "false".equalsIgnoreCase(String.valueOf(parameters.get(Constant.ISORDERED)))) {
                sql = sql + (" ORDER BY ITEM_GROUP_NAME DESC ");
            } else if (parameters.get(Constant.ISORDERED) != null && Constant.TRUE.equalsIgnoreCase(String.valueOf(parameters.get(Constant.ISORDERED)))) {
                if (parameters.get("orderBy~productGroupName") != null && !com.stpl.app.serviceUtils.Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productGroupName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productGroupName")))) {
                    sql = sql + (" ORDER BY ITEM_GROUP_NAME ");
                    sql = sql + (String.valueOf(parameters.get("orderBy~productGroupName")));
                }

                if (parameters.get("orderBy~productGroup") != null && !com.stpl.app.serviceUtils.Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productGroup"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productGroup")))) {
                    sql = sql + (" ORDER BY ITEM_GROUP_NO ");
                    sql = sql + (String.valueOf(parameters.get("orderBy~productGroup")));
                }
                if (parameters.get("orderBy~company") != null && !com.stpl.app.serviceUtils.Constants.NULL.equals(String.valueOf(parameters.get("orderBy~company"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~company")))) {
                    sql = sql + (" ORDER BY CM.COMPANY_NAME ");
                    sql = sql + (String.valueOf(parameters.get("orderBy~company")));
                }
                if (parameters.get("orderBy~productGroupDescription") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productGroupDescription"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productGroupDescription")))) {
                    sql = sql + (" ORDER BY IG.ITEM_GROUP_DESCRIPTION ");
                    sql = sql + (String.valueOf(parameters.get("orderBy~productGroupDescription")));
                }
            }
            sql += (" OFFSET ") + (startIndex) + (" ROWS FETCH NEXT ") + (offset) + (" ROWS ONLY");
            return (List) DAO.executeSelectQuery(sql);
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
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
        } catch (Exception e) {
            LOGGER.error(e);
            return new ArrayList();
        }
    }
    
}
