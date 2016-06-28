/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemGroupDetails;
import com.stpl.app.util.Constants;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
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
 * @author sooriya.lakshmanan
 */
public class ItemGroupDetailsFinderImpl extends BasePersistenceImpl<ItemGroupDetails> implements ItemGroupDetailsFinder{

    private static final Logger LOGGER = Logger.getLogger(ItemGroupDetailsFinderImpl.class);
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
        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
            if (filters != null) {
                for (Container.Filter filter : filters) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = "%" + stringFilter.getFilterString() + "%";
                        parameters.put("filter~" + stringFilter.getPropertyId(), filterString);
                    }
                }
            }
            session = openSession();

            sql = CustomSQLUtil.get("getProductGroups");
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
            //            { "productGroupName", "productGroup", "company" };
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

            parameters.put("isOrdered", "false");

            for (Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                SortByColumn orderByColumn = (SortByColumn) iterator.next();
                String columnId = orderByColumn.getName();
                if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                    parameters.put("orderBy~" + columnId, "asc");
                    parameters.put("isOrdered", "true");
                } else {
                    parameters.put("orderBy~" + columnId, "desc");
                    parameters.put("isOrdered", "true");
                }
            }

            if ((parameters.get("isOrdered") == null || "false".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered"))))) {
                sql = sql + (" ORDER BY ITEM_GROUP_NAME DESC ");
            } else if (parameters.get("isOrdered") != null && "true".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered")))) {
                if (parameters.get("orderBy~productGroupName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productGroupName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productGroupName")))) {
                    sql = sql + (" ORDER BY ITEM_GROUP_NAME ");
                    sql = sql + (String.valueOf(parameters.get("orderBy~productGroupName")));
                }

                if (parameters.get("orderBy~productGroup") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productGroup"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productGroup")))) {
                    sql = sql + (" ORDER BY ITEM_GROUP_NO ");
                    sql = sql + (String.valueOf(parameters.get("orderBy~productGroup")));
                }
                if (parameters.get("orderBy~company") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~company"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~company")))) {
                    sql = sql + (" ORDER BY CM.COMPANY_NAME ");
                    sql = sql + (String.valueOf(parameters.get("orderBy~company")));
                }
                if (parameters.get("orderBy~productGroupDescription") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~productGroupDescription"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~productGroupDescription")))) {
                    sql = sql + (" ORDER BY IG.ITEM_GROUP_DESCRIPTION ");
                    sql = sql + (String.valueOf(parameters.get("orderBy~productGroupDescription")));
                }
            }
            sql += (" OFFSET ") + (startIndex) + (" ROWS FETCH NEXT ") + (offset) + (" ROWS ONLY");
            LOGGER.info("getProductGroups query----------------"+sql);
            SQLQuery sqlQuery = session.createSQLQuery(sql);
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(sql);
            return null;
        } finally {
            closeSession(session);
        }
    }
    
    public List getItemMasterRecords(String query[]) {

        Session session = null;
        session = openSession();
        String sql = StringUtils.EMPTY;
        try {
            String cols = "im.ITEM_MASTER_SID\n" + //0
                    ", im.ITEM_ID\n" +// 1
                    ", im.ITEM_NO\n" +//2
                    ", im.ITEM_CODE\n" +//3
                    ", im.ITEM_NAME\n" +//4
                    ", im.BRAND_MASTER_SID\n" +//5
                    ", im.ITEM_DESC\n" + //6
                    ", im.PACKAGE_SIZE\n" + //7
                    ", im.PACKAGE_SIZE_INTRO_DATE\n" + //8
                    ", im.UPPS\n" + //9
                    ", im.ITEM_START_DATE\n" +//10
                    ", im.ITEM_END_DATE\n" + //11
                    ", im.ITEM_STATUS\n" + //12
                    ", im.FORM\n" + //13 
                    ", im.STRENGTH\n" + //14
                    ", im.THERAPEUTIC_CLASS\n" + //15
                    ", im.MANUFACTURER_ID\n" + //16
                    ", im.LABELER_CODE\n" + //17
                    ", im.ORGANIZATION_KEY\n" + //18
                    ", im.ACQUISITION_DATE\n" + //19
                    ", im.AUTHORIZED_GENERIC\n" + //20
                    ", im.AUTHORIZED_GENERIC_START_DATE\n" + //21
                    ", im.AUTHORIZED_GENERIC_END_DATE\n" + //22
                    ", im.FIRST_SALE_DATE\n" + //23
                    ", im.ITEM_TYPE_INDICATION\n" + //24
                    ", im.ITEM_CLASS\n" + //25
                    ", im.ITEM_TYPE\n" + //26
                    ", im.MARKET_TERMINATION_DATE\n" + //27
                    ", im.NEW_FORMULATION_INDICATOR\n" + //28
                    ", im.NEW_FORMULATION\n" + //29
                    ", im.NEW_FORMULATION_START_DATE\n" + //30
                    ", im.NEW_FORMULATION_END_DATE\n" + //31
                    ", im.PEDIATRIC_EXCLUSIVE_INDICATOR\n" + //32
                    ", im.PEDIATRIC_EXCLUSIVE_START_DATE\n" + //33
                    ", im.PEDIATRIC_EXCLUSIVE_END_DATE\n" + //34
                    ", im.CLOTTING_FACTOR_INDICATOR\n" + //35
                    ", im.CLOTTING_FACTOR_START_DATE\n" + //36
                    ", im.CLOTTING_FACTOR_END_DATE\n" + //37
                    ", im.PRIMARY_UOM\n" + //38
                    ", im.SECONDARY_UOM\n" + //39
                    ", im.SHELF_LIFE\n" + //40
                    ", im.SHELF_LIFE_TYPE\n" + //41
                    ", im.DUAL_PRICING_INDICATOR\n" + //42
                    ", im.ITEM_FAMILY_ID\n" + //43
                    ", im.ACQUIRED_AMP\n" + //44
                    ", im.ACQUIRED_BAMP\n" + //45
                    ", im.OBRA_BAMP\n" + //46
                    ", im.DRA\n" + //47
                    ", im.DOSES_PER_UNIT\n" + //48
                    ", im.DISCONTINUATION_DATE\n" + //49
                    ", im.LAST_LOT_EXPIRATION_DATE\n" + //50
                    ", im.DIVESTITURE_DATE\n" + //51
                    ", im.NON_FEDERAL_EXPIRATION_DATE\n" + //52
                    ", im.NDC9\n" + //53
                    ", im.NDC8\n" + //54
                    ", im.ITEM_CATEGORY\n" + //55
                    ", im.BASELINE_AMP\n" + //56
                    ", im.BASE_CPI_PERIOD\n" + //57
                    ", im.BASE_CPI\n" + //58
                    ", im.PACKAGE_SIZE_CODE\n"; //59

            sql = " select " + cols + " from Item_MAster im "
                    + " left join Hist_Item_Group_Details hit \n"
                    + "  on hit.ITEM_MASTER_SID=im.ITEM_MASTER_SID\n"
                    + "  where hit.version_No = " + Integer.valueOf(query[1]) + " "
                    + " and hit.action_Flag!='D' "
                    + " and hit.ITEM_GROUP_SID=" + Integer.valueOf(query[0]);
            SQLQuery sqlQuery = session.createSQLQuery(sql);
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(sql);
            return null;
        } finally {
            closeSession(session);
        }
    }
}
