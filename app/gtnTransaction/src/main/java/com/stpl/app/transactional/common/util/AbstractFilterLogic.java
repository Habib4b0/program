/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.util;

import com.stpl.app.util.ConstantUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author mohamed.hameed
 */
public class AbstractFilterLogic {

    public static final SimpleDateFormat DBDate = new SimpleDateFormat("yyyy-MM-dd");
    private static AbstractFilterLogic instance;
    private boolean countFlag;
    static HashMap<String, String> pivotQueryFilterMap = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(AbstractFilterLogic.class);

    private AbstractFilterLogic() {
    }

    public static synchronized AbstractFilterLogic getInstance() {
        if (instance == null) {
            instance = new AbstractFilterLogic();
        }
        return instance;
    }

    public StringBuilder filterQueryGenerator(java.util.Set<Container.Filter> filterSet, Map<String, String> queryMap) {
        StringBuilder str = new StringBuilder("AND ( * LIKE ''?'' OR * IS NULL )");
        StringBuilder sql = new StringBuilder();
        if (filterSet != null && !filterSet.isEmpty()) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if (queryMap.get(stringFilter.getPropertyId().toString()) != null && !queryMap.get(stringFilter.getPropertyId().toString()).isEmpty()) {
                        if (sql.length() == 0) {
                            StringBuilder initial = new StringBuilder("where ( ( * LIKE ''?'' )");
                            StringBuilder temp = new StringBuilder(initial);
                            temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, "%".concat(stringFilter.getFilterString()).concat("%"));
                            sql.append(temp);

                        } else {
                            StringBuilder temp = new StringBuilder(str);
                            temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, "%".concat(stringFilter.getFilterString()).concat("%"));
                            sql.append(temp);
                        }
                    }
                }
                if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    StringBuilder dateStartstr = new StringBuilder(ConstantUtil.DATE_START_STRING);
                    StringBuilder dateEndstr = new StringBuilder(ConstantUtil.DATE_END_STRING);
                    if (!queryMap.get(betweenFilter.getPropertyId().toString()).isEmpty()) {
                        Date startValue = (Date) betweenFilter.getStartValue();
                        Date endValue = (Date) betweenFilter.getEndValue();
                        StringBuilder initialStart = new StringBuilder("where ( ( * >= ''?'' )");
                        StringBuilder initialEnd = new StringBuilder("where ( ( * <= ''?'' )");
                        if (!betweenFilter.getStartValue().toString().isEmpty()) {
                            StringBuilder tempStart;
                            if (sql.length() == 0) {
                                tempStart = new StringBuilder(initialStart);
                            } else {
                                tempStart = new StringBuilder(dateStartstr);
                            }
                            tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(betweenFilter.getPropertyId().toString()));
                            tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, DBDate.format(startValue));
                            sql.append(tempStart);
                        }
                        if (!betweenFilter.getEndValue().toString().isEmpty()) {
                            StringBuilder tempEnd;
                            if (sql.length() == 0) {
                                tempEnd = new StringBuilder(initialEnd);
                            } else {
                                tempEnd = new StringBuilder(dateEndstr);
                            }

                            tempEnd.replace(tempEnd.indexOf("*"), tempEnd.indexOf("*") + 1, queryMap.get(betweenFilter.getPropertyId().toString()));
                            tempEnd.replace(tempEnd.indexOf("?"), tempEnd.indexOf("?") + 1, DBDate.format(endValue));
                            sql.append(tempEnd);
                        }
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    if (!queryMap.get(stringFilter.getPropertyId().toString()).isEmpty()) {
                        Compare.Operation operation = stringFilter.getOperation();
                        if (operation.EQUAL.toString().equals(operation.name())) {
                            StringBuilder Startstr = new StringBuilder("AND ( * =''?'')");
                            StringBuilder intStartstr = new StringBuilder("where ( ( * = ''?'' )");
                            StringBuilder tempStart;
                            String value;
                            if (((Integer) stringFilter.getValue()) == 0) {
                                value = String.valueOf(stringFilter.getValue());
                            } else {
                                int val = (Integer) stringFilter.getValue();
                                value = String.valueOf(val);
                            }
                            if (!value.isEmpty()) {
                                if (sql.length() == 0) {
                                    tempStart = new StringBuilder(intStartstr);
                                } else {
                                    tempStart = new StringBuilder(Startstr);
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                sql.append(tempStart);
                            }
                        }
                        if (operation.GREATER.toString().equals(operation.name())) {
                            StringBuilder tempStart;
                            int val = (Integer) stringFilter.getValue();
                            String value = String.valueOf(val);
                            if (val < 0) {
                                if (sql.length() == 0) {
                                    tempStart = new StringBuilder("where ( ( * > ''?'' or * = '0')");
                                } else {
                                    tempStart = new StringBuilder("AND ( * >''?'' or * = '0')");
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                sql.append(tempStart);
                            } else {
                                if (sql.length() == 0) {
                                    tempStart = new StringBuilder("where ( ( * > ''?'')");
                                } else {
                                    tempStart = new StringBuilder("AND ( * >''?'')");
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                sql.append(tempStart);
                            }
                        }
                        if (operation.LESS.toString().equals(operation.name())) {
                            int val = (Integer) stringFilter.getValue();
                            StringBuilder tempStart;
                            String value = String.valueOf(val);
                            if (val > 0) {
                                if (sql.length() == 0) {
                                    tempStart = new StringBuilder("where ( ( * < ''?'' or * = '0')");
                                } else {
                                    tempStart = new StringBuilder("AND ( * <''?'' or * = '0')");
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                sql.append(tempStart);
                            } else {
                                if (sql.length() == 0) {
                                    tempStart = new StringBuilder("where ( ( * < ''?'')");
                                } else {
                                    tempStart = new StringBuilder("AND ( * <''?'')");
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                sql.append(tempStart);
                            }
                        }
                        if (stringFilter.getValue() instanceof Date) {
                            Date value = (Date) stringFilter.getValue();
                            StringBuilder tempStart;
                            if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                                if (sql.length() == 0) {
                                    tempStart = new StringBuilder("where ( ( * >= ''?'')");
                                } else {
                                    tempStart = new StringBuilder(" AND ( * >=''?'')");
                                }
                            } else if (sql.length() == 0) {
                                tempStart = new StringBuilder("where ( ( * <=''?'')");
                            } else {
                                tempStart = new StringBuilder("AND ( * <=''?'' )");
                            }
                            tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                            tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, DBDate.format(value));
                            sql.append(tempStart);
                        }
                    }
                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Container.Filter> value = stringFilter.getFilters();
                    for (Container.Filter filter1 : value) {
                        if (filter1 instanceof Compare.Less) {

                        }
                        if (filter1 instanceof Compare.Greater) {
                        }
                    }
                }
            }
            if (sql.length() != 0) {
                sql.append(")");
            }
        }
        return sql;
    }

    public StringBuilder orderByQueryGenerator(List<SortByColumn> sortByColumns, Map<String, String> queryMap, String defaultOrder, int start, int end) {
        boolean asc = false;
        StringBuilder tempStart = new StringBuilder("ORDER BY * ?");
        if (sortByColumns != null && !sortByColumns.isEmpty()) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                String columnName = sortByColumn.getName();
                // if condition for bussiness id, bussiness no and bussiness name (once galuat-808 changes implemented need to remove it)
                 if(queryMap.containsKey(columnName)){
                if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                    asc = false;
                    tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(columnName));
                } else {
                    asc = true;
                    tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(columnName));
                }
            }else{
                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, defaultOrder);     
                 }
            }
        } else {
            tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, defaultOrder);
        }
        tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, !asc ? "ASC" : "DESC");
        tempStart.append(" OFFSET " + start + " ROWS FETCH NEXT " + end + " ROWS ONLY'");
        return tempStart;
    }

    
       public StringBuilder filterQueryGenerator_ARP(java.util.Set<Container.Filter> filterSet, Map<String, String> queryMap,boolean count) {
       countFlag=count;
       loadsearchFilterMap();
        StringBuilder str = new StringBuilder("AND ( * LIKE ''?'' OR * IS NULL )");
        StringBuilder sql = new StringBuilder();
        if (filterSet != null && !filterSet.isEmpty()) {
            try {
                for (Container.Filter filter : filterSet) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        if (queryMap.get(stringFilter.getPropertyId().toString()) != null && !queryMap.get(stringFilter.getPropertyId().toString()).isEmpty()) {
                            if (sql.length() == 0) {
                                StringBuilder initial = new StringBuilder("where ( ( * LIKE ''?'' )");
                                StringBuilder temp = new StringBuilder(initial);
                                temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, "%".concat(stringFilter.getFilterString()).concat("%"));
                                sql.append(temp);

                            } else {
                                StringBuilder temp = new StringBuilder(str);
                                temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                temp.replace(temp.indexOf("*"), temp.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                temp.replace(temp.indexOf("?"), temp.indexOf("?") + 1, "%".concat(stringFilter.getFilterString()).concat("%"));
                                sql.append(temp);
                            }
                        }
                    }
                    if (filter instanceof Between) {
                        Between betweenFilter = (Between) filter;
                        StringBuilder dateStartstr = new StringBuilder("AND ( * >=''?'')");
                        StringBuilder dateEndstr = new StringBuilder("AND ( * <=''?'')");
                        if (!queryMap.get(betweenFilter.getPropertyId().toString()).isEmpty()) {
                            Date startValue = (Date) betweenFilter.getStartValue();
                            Date endValue = (Date) betweenFilter.getEndValue();
                            StringBuilder initialStart = new StringBuilder("where ( ( * >= ''?'' )");
                            StringBuilder initialEnd = new StringBuilder("where ( ( * <= ''?'' )");
                            if (!betweenFilter.getStartValue().toString().isEmpty()) {
                                StringBuilder tempStart;
                                if (sql.length() == 0) {
                                    tempStart = new StringBuilder(initialStart);
                                } else {
                                    tempStart = new StringBuilder(dateStartstr);
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(betweenFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, DBDate.format(startValue));
                                sql.append(tempStart);
                            }
                            if (!betweenFilter.getEndValue().toString().isEmpty()) {
                                StringBuilder tempEnd;
                                if (sql.length() == 0) {
                                    tempEnd = new StringBuilder(initialEnd);
                                } else {
                                    tempEnd = new StringBuilder(dateEndstr);
                                }

                                tempEnd.replace(tempEnd.indexOf("*"), tempEnd.indexOf("*") + 1, queryMap.get(betweenFilter.getPropertyId().toString()));
                                tempEnd.replace(tempEnd.indexOf("?"), tempEnd.indexOf("?") + 1, DBDate.format(endValue));
                                sql.append(tempEnd);
                            }
                        }
                    } else if (filter instanceof Compare) {
                        Compare stringFilter = (Compare) filter;
                        if (!queryMap.get(stringFilter.getPropertyId().toString()).isEmpty()) {
                            Compare.Operation operation = stringFilter.getOperation();
                            if (operation.EQUAL.toString().equals(operation.name())) {
                                StringBuilder Startstr = new StringBuilder("AND ( * =''?''" + getYear(stringFilter.getPropertyId().toString()) + ")");
                                StringBuilder intStartstr = new StringBuilder("where ( ( * = ''?'' " + getYear(stringFilter.getPropertyId().toString()) + ")");
                                StringBuilder tempStart;
                                String value;
                                if (((Double) stringFilter.getValue()) == 0) {
                                    value = String.valueOf(stringFilter.getValue());
                                } else {
                                    value = String.valueOf(stringFilter.getValue());
                                }
                                if (!value.isEmpty()) {
                                    if (sql.length() == 0) {
                                        tempStart = new StringBuilder(intStartstr);
                                    } else {
                                        tempStart = new StringBuilder(Startstr);
                                    }

                                    tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                    tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                    sql.append(tempStart);
                                }
                            }
                            if (operation.GREATER.toString().equals(operation.name())) {
                                StringBuilder tempStart;
                                Double val = (Double) stringFilter.getValue();
                                String value = String.valueOf(val);
                                if (val < 0) {
                                    if (sql.length() == 0) {
                                        tempStart = new StringBuilder("where ( ( * > ''?'' or * = ''0''" + getYear(stringFilter.getPropertyId().toString()) + ")");
                                    } else {
                                        tempStart = new StringBuilder("AND ( * >''?'' or * = ''0''" + getYear(stringFilter.getPropertyId().toString()) + ")");
                                    }
                                    tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                    tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                    tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                    sql.append(tempStart);
                                } else {
                                    if (sql.length() == 0) {
                                        tempStart = new StringBuilder("where ( ( * > ''?'' " + getYear(stringFilter.getPropertyId().toString()) + ")");
                                    } else {
                                        tempStart = new StringBuilder("AND ( * >''?'' " + getYear(stringFilter.getPropertyId().toString()) + ")");
                                    }
                                    tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                    tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                    sql.append(tempStart);
                                }
                            }
                            if (operation.LESS.toString().equals(operation.name())) {
                                Double val = (Double) stringFilter.getValue();
                                StringBuilder tempStart;
                                String value = String.valueOf(val);
                                if (val > 0) {
                                    if (sql.length() == 0) {
                                        tempStart = new StringBuilder("where ( ( * < ''?'' or * = ''0'' " + getYear(stringFilter.getPropertyId().toString()) + ")");
                                    } else {
                                        tempStart = new StringBuilder("AND ( * <''?'' or * = ''0'' " + getYear(stringFilter.getPropertyId().toString()) + ")");
                                    }
                                    tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                    tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                    tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                    sql.append(tempStart);
                                } else {
                                    if (sql.length() == 0) {
                                        tempStart = new StringBuilder("where ( ( * < ''?'' " + getYear(stringFilter.getPropertyId().toString()) + ")");
                                    } else {
                                        tempStart = new StringBuilder("AND ( * <''?'' " + getYear(stringFilter.getPropertyId().toString()) + ")");
                                    }
                                    tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                    tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, value);
                                    sql.append(tempStart);
                                }
                            }
                            if (stringFilter.getValue() instanceof Date) {
                                Date value = (Date) stringFilter.getValue();
                                StringBuilder tempStart;
                                if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                                    if (sql.length() == 0) {
                                        tempStart = new StringBuilder("where ( ( * >= ''?'')");
                                    } else {
                                        tempStart = new StringBuilder("AND ( * >=''?'')");
                                    }
                            } else {
                                if (sql.length() == 0) {
                                    tempStart = new StringBuilder("where ( ( * <=''?'')");
                                } else {
                                    tempStart = new StringBuilder("AND ( * <=''?'' )");
                                }
                            }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(stringFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, DBDate.format(value));
                                sql.append(tempStart);
                            }
                        }
                    } else if (filter instanceof And) {
                        And stringFilter = (And) filter;
                        StringBuilder tempStart = new StringBuilder();
                        Collection<Container.Filter> value = stringFilter.getFilters();
                        for (Container.Filter filter1 : value) {
                            Object propertyId = "";
                            if (filter1 instanceof Compare.Less) {
                                Compare.Less less = (Compare.Less) filter1;
                                propertyId = less.getPropertyId();
                                String toValue = String.valueOf(less.getValue());
                                if (sql.length() == 0) {
                                    tempStart = new StringBuilder("where (( * BETWEEN ''?'' and ''~'' " + getYear(String.valueOf(propertyId)) + ")");
                                } else {
                                    tempStart = new StringBuilder("AND (  * BETWEEN ''?'' and ''~'' " + getYear(String.valueOf(propertyId)) + ")");
                                }
                                if (!countFlag) {
                                    tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, pivotQueryFilterMap.get(String.valueOf(propertyId)));
                                    tempStart.replace(tempStart.indexOf("~"), tempStart.indexOf("~") + 1, toValue);
                                } else {
                                    tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, queryMap.get(String.valueOf(propertyId)));
                                    tempStart.replace(tempStart.indexOf("~"), tempStart.indexOf("~") + 1, toValue);
                                }
                            }
                            if (filter1 instanceof Compare.Greater) {

                                Compare.Greater greater = (Compare.Greater) filter1;
                                String fromValue = String.valueOf(greater.getValue());
                                if (!countFlag) {
                                    tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, fromValue);
                                } else {
                                    tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, fromValue);
                                }
                            }

                        }
                        sql.append(tempStart);
                    }
                }
            } catch (Exception e) {
                LOGGER.error(e);
            }
            if (sql.length() != 0) {
                sql.append(")");
            }
        }
        return sql;
    }

    private String getYear(String stringFilter) {
        Calendar cal = Calendar.getInstance();
        String year = "";
        int currentYear = cal.get(Calendar.YEAR);
        if (stringFilter.contains("1")) {
            year = String.valueOf(currentYear + 1);
        } else if (stringFilter.contains("2")) {
            year = String.valueOf(currentYear + NumericConstants.TWO);
        } else {
            year = String.valueOf(currentYear);
        }
        year = " AND ARP.YEAR = " + year;
        if (!countFlag) {
            year = "";
        }
        return year;
    }

    void loadsearchFilterMap() {
        if (pivotQueryFilterMap.isEmpty()) {
            pivotQueryFilterMap.put("arp_Id", "ARP_ID");
            pivotQueryFilterMap.put("arp_Name", "projection_name");
            pivotQueryFilterMap.put("company_Id", "COMPANY_ID");
            pivotQueryFilterMap.put("company_No", "COMPANY_NO");
            pivotQueryFilterMap.put("company_Name", "COMPANY_NAME");
            pivotQueryFilterMap.put("item_No", "ITEM_NO");
            pivotQueryFilterMap.put("item_Id", "ITEM_ID");
            pivotQueryFilterMap.put("item_Name", "ITEM_NAME");
            pivotQueryFilterMap.put("brand_Id", "BRAND_ID");
            pivotQueryFilterMap.put("brand_Name", "BRAND_NAME");
            pivotQueryFilterMap.put("account", "ACCOUNT");
            pivotQueryFilterMap.put("account_Type", "ACCOUNT_TYPE");
            pivotQueryFilterMap.put("category", "ht.DESCRIPTION");
            pivotQueryFilterMap.put("type", "ht1.description");
            pivotQueryFilterMap.put("program", "RS_NAME");
            pivotQueryFilterMap.put("arp_Creation_Date", "ARP_CREATION_DATE");
            pivotQueryFilterMap.put("arp_Approval_Date", "ARP_Approval_Date");
            pivotQueryFilterMap.put("outbound_Status", "OUTBOUND_STATUS");
            pivotQueryFilterMap.put("original_Batch_ID", "ORIGINAL_BATCH_ID");

            pivotQueryFilterMap.put("current_Year_Jan", "CURRENT_YEAR_M1");
            pivotQueryFilterMap.put("current_Year_Feb", "CURRENT_YEAR_M2");
            pivotQueryFilterMap.put("current_Year_Mar", "CURRENT_YEAR_M3");
            pivotQueryFilterMap.put("current_Year_Apr", "CURRENT_YEAR_M4");
            pivotQueryFilterMap.put("current_Year_May", "CURRENT_YEAR_M5");
            pivotQueryFilterMap.put("current_Year_June", "CURRENT_YEAR_M6");
            pivotQueryFilterMap.put("current_Year_July", "CURRENT_YEAR_M7");
            pivotQueryFilterMap.put("current_Year_Aug", "CURRENT_YEAR_M8");
            pivotQueryFilterMap.put("current_Year_Sep", "CURRENT_YEAR_M9");
            pivotQueryFilterMap.put("current_Year_Oct", "CURRENT_YEAR_M10");
            pivotQueryFilterMap.put("current_Year_Nov", "CURRENT_YEAR_M11");
            pivotQueryFilterMap.put("current_Year_Dec", "CURRENT_YEAR_M12");

            pivotQueryFilterMap.put("current_Year_1_Jan", "CURRENT_YEAR1_M1");
            pivotQueryFilterMap.put("current_Year_1_Feb", "CURRENT_YEAR1_M2");
            pivotQueryFilterMap.put("current_Year_1_Mar", "CURRENT_YEAR1_M3");
            pivotQueryFilterMap.put("current_Year_1_Apr", "CURRENT_YEAR1_M4");
            pivotQueryFilterMap.put("current_Year_1_May", "CURRENT_YEAR1_M5");
            pivotQueryFilterMap.put("current_Year_1_June", "CURRENT_YEAR1_M6");
            pivotQueryFilterMap.put("current_Year_1_July", "CURRENT_YEAR1_M7");
            pivotQueryFilterMap.put("current_Year_1_Aug", "CURRENT_YEAR1_M8");
            pivotQueryFilterMap.put("current_Year_1_Sep", "CURRENT_YEAR1_M9");
            pivotQueryFilterMap.put("current_Year_1_Oct", "CURRENT_YEAR1_M10");
            pivotQueryFilterMap.put("current_Year_1_Nov", "CURRENT_YEAR1_M11");
            pivotQueryFilterMap.put("current_Year_1_Dec", "CURRENT_YEAR1_M12");

            pivotQueryFilterMap.put("current_Year_2_Jan", "CURRENT_YEAR2_M1");
            pivotQueryFilterMap.put("current_Year_2_Feb", "CURRENT_YEAR2_M2");
            pivotQueryFilterMap.put("current_Year_2_Mar", "CURRENT_YEAR2_M3");
            pivotQueryFilterMap.put("current_Year_2_Apr", "CURRENT_YEAR2_M4");
            pivotQueryFilterMap.put("current_Year_2_May", "CURRENT_YEAR2_M5");
            pivotQueryFilterMap.put("current_Year_2_June", "CURRENT_YEAR2_M6");
            pivotQueryFilterMap.put("current_Year_2_July", "CURRENT_YEAR2_M7");
            pivotQueryFilterMap.put("current_Year_2_Aug", "CURRENT_YEAR2_M8");
            pivotQueryFilterMap.put("current_Year_2_Sep", "CURRENT_YEAR2_M9");
            pivotQueryFilterMap.put("current_Year_2_Oct", "CURRENT_YEAR2_M10");
            pivotQueryFilterMap.put("current_Year_2_Nov", "CURRENT_YEAR2_M11");
            pivotQueryFilterMap.put("current_Year_2_Dec", "CURRENT_YEAR2_M12");

        }

    }

}
