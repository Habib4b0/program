/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.periodConfiguration.logic;

import com.stpl.app.adminconsole.util.StringConstantUtils;
import com.stpl.app.adminconsole.common.util.AbstractFilterLogic;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.periodConfiguration.dto.PeriodConfigDTO;
import static com.stpl.app.adminconsole.util.CommonUtils.getUserMap;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.HelperListUtil;
import com.stpl.app.adminconsole.util.SysDataSourceConnection;
import com.stpl.app.adminconsole.util.xmlparser.SQlUtil;
import com.stpl.app.service.ForecastingMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author Mahesh.James
 */
public class PeriodConfigLogic implements ExtFilterGenerator{

    HelperListUtil helperListUtil = HelperListUtil.getInstance();
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(PeriodConfigLogic.class);
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * Gets the customized results.
     *
     * @param resultList the result list
     * @return the customized results
     */
    private List<PeriodConfigDTO> getCustomizedresults(final List resultList) {
        final List<PeriodConfigDTO> forecastList = new ArrayList<>();
        try {
            final Map<String, String> userInfoMap = CommonUtil.getCreatedByUser();
            for (int i = 0; i < resultList.size(); i++) {
                Object[] obj = (Object[]) resultList.get(i);

                final PeriodConfigDTO periodConfigDTO = new PeriodConfigDTO();
                periodConfigDTO.setModuleName(convertNullToEmpty(obj[0]));
                periodConfigDTO.setBuscinessProcessName(convertNullToEmpty(obj[1]));
                periodConfigDTO.setCompanyName(convertNullToEmpty(obj[NumericConstants.TWO]));
                periodConfigDTO.setBucinsessUnitName(convertNullToEmpty(obj[NumericConstants.THREE]));
                periodConfigDTO.setPeriodViewName(convertNullToEmpty(obj[NumericConstants.FOUR]));
                periodConfigDTO.setFromModeName(convertNullToEmpty(obj[NumericConstants.FIVE]));
                periodConfigDTO.setFromPeriodValue(convertNullToEmpty(obj[NumericConstants.SEVEN]));
                if (periodConfigDTO.getFromModeName().equals("Auto")) { 
                    periodConfigDTO.setFromFrequencyName(convertNullToEmpty(obj[NumericConstants.SIX]));
                } 
                if(obj[NumericConstants.EIGHT] !=null) {
                    periodConfigDTO.setFromPeriodDateValue(df.format(parsetDate(convertNullToEmpty(obj[NumericConstants.EIGHT]))));
                    periodConfigDTO.setFromPeriodDate(parsetDate(convertNullToEmpty(obj[NumericConstants.EIGHT])));
                }

                periodConfigDTO.setFromDefModeName(convertNullToEmpty(obj[NumericConstants.NINE]));
                periodConfigDTO.setFromDefPeriodValue(convertNullToEmpty(obj[NumericConstants.ELEVEN]));
                if (periodConfigDTO.getFromDefModeName().equals("Auto")) {
                    periodConfigDTO.setFromDefFrequencyName(convertNullToEmpty(obj[NumericConstants.TEN]));
                } 
             if(obj[NumericConstants.TWELVE]!=null) {
                    periodConfigDTO.setFromDefPeriodDateValue(df.format(parsetDate(convertNullToEmpty(obj[NumericConstants.TWELVE]))));
                    periodConfigDTO.setFromDefPeriodDate(parsetDate(convertNullToEmpty(obj[NumericConstants.TWELVE])));
             }

                periodConfigDTO.setPeriodViewName(convertNullToEmpty(obj[NumericConstants.TWENTY_THREE]));
                if (periodConfigDTO.getPeriodViewName().equals("Multiple")) {
                    periodConfigDTO.setToModeName(convertNullToEmpty(obj[NumericConstants.THIRTEEN]));
                    if (periodConfigDTO.getToModeName().equals("Auto")) {
                        periodConfigDTO.setToFrequencyName(convertNullToEmpty(obj[NumericConstants.FOURTEEN])); 
                    } 
                    periodConfigDTO.setToPeriodValue(convertNullToEmpty(obj[NumericConstants.FIFTEEN]));
                    if(obj[NumericConstants.SIXTEEN]!=null) {    
                    periodConfigDTO.setToPeriodDateValue(df.format(parsetDate(convertNullToEmpty(obj[NumericConstants.SIXTEEN]))));
                        periodConfigDTO.setToPeriodDate(parsetDate(convertNullToEmpty(obj[NumericConstants.SIXTEEN])));
                    }

                    periodConfigDTO.setToDefModeName(convertNullToEmpty(obj[NumericConstants.SEVENTEEN]));
                    periodConfigDTO.setToDefPeriodValue(convertNullToEmpty(obj[NumericConstants.NINETEEN]));
                    if (periodConfigDTO.getToDefModeName().equals("Auto")) {
                        periodConfigDTO.setToDefFrequencyName(convertNullToEmpty(obj[NumericConstants.EIGHTEEN]));
                        

                }}
                if(obj[NumericConstants.TWENTY]!=null) {   
                    periodConfigDTO.setToDefPeriodDate(parsetDate(convertNullToEmpty(obj[NumericConstants.TWENTY])));}
                periodConfigDTO.setVersionNo(Integer.parseInt(String.valueOf(obj[NumericConstants.TWENTY_ONE])));
                final String createdBy = userInfoMap.containsKey(String.valueOf(obj[NumericConstants.TWENTY_TWO])) ? userInfoMap.get(String.valueOf(obj[NumericConstants.TWENTY_TWO])) : "";
                if (StringUtils.isNotBlank(createdBy) && !createdBy.equals("null")) {
                    periodConfigDTO.setCreatedBy(createdBy);
                }
                periodConfigDTO.setActiveFlag(String.valueOf(obj[NumericConstants.TWENTY_FOUR]));

                forecastList.add(periodConfigDTO);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return forecastList;
    }

    /**
     * Search forecast results to load table .
     *
     * @param forecastDTO the forecast dto
     * @return object of list or count
     */
    public List<Object> getFieldsForSecurity(String moduleName, String tabName){
        List<Object> resultList = new ArrayList<>();
        try {            
            resultList = ImtdIfpDetailsLocalServiceUtil.fetchFieldsForSecurity(moduleName,tabName,null,null,null);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }
    public Object searchForecast(final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) {
        Connection connection = null;
        try {
            connection = SysDataSourceConnection.getConnection();
        } catch (SQLException sqlException) {
            LOGGER.error(sqlException);
        }
        Object forecastObj = null;
        try {
            String selectQuery = SQlUtil.getQuery("period-config-query");
            selectQuery = isCount ? selectQuery.replace("@VARIABLE", "COUNT(*)") : selectQuery.replace("@VARIABLE", "*");
            selectQuery = selectQuery.replace("@CATALOGUE", connection.getCatalog());
            String filterQuery = "";
            String finalQuery = "";
            HashMap<String, String> detailsColumn = new HashMap<>();
            detailsColumn.put("moduleName", "MODULES");
            detailsColumn.put("buscinessProcessName", "TRANSACTION_NAME");
            detailsColumn.put(StringConstantUtils.ACTIVE_FLAG, "active_flag");

            detailsColumn.put("companyName", "Company_NAME");
            detailsColumn.put("bucinsessUnitName", "BUSCINESS_UNIT");
            detailsColumn.put("periodViewName", "PV");
            detailsColumn.put("versionNo", "v_NO");
            detailsColumn.put(StringConstantUtils.FROM_MODE_NAME, "FROM_MODE");
            detailsColumn.put(StringConstantUtils.FROM_FREQUENCY_NAME, "FROM_FRE");
            detailsColumn.put(StringConstantUtils.FROM_PERIOD_VALUE, "FROM_PERIOD");
            detailsColumn.put("fromPeriodDate", "FROM_PERIOD_DATE");
            detailsColumn.put(StringConstantUtils.FROM_DEF_MODE_NAME, "FROM_DEF_MODE");
            detailsColumn.put(StringConstantUtils.FROM_DEF_FREQUENCY_NAME, "FROM_DEF_FRE");
            detailsColumn.put(StringConstantUtils.FROM_DEF_PERIOD_VALUE, "FROM_DEFAULT_PERIOD");
            detailsColumn.put("fromDefPeriodDate", "FROM_DEFAULT_PERIOD_DATE");

            detailsColumn.put(StringConstantUtils.TO_MODE_NAME, "TO_MODE");
            detailsColumn.put(StringConstantUtils.TO_FREQUENCY_NAME, "TO_FRE ");
            detailsColumn.put(StringConstantUtils.TO_PERIOD_VALUE, "TO_PERIOD");
            detailsColumn.put("toPeriodDate", "TO_PERIOD_DATE");
            detailsColumn.put(StringConstantUtils.TO_DEF_MODE_NAME, "TO_DEF_MODE");
            detailsColumn.put(StringConstantUtils.TO_DEF_FREQUENCY_NAME, "TO_DEF_FRE");
            detailsColumn.put(StringConstantUtils.TO_DEF_PERIOD_VALUE, "TO_DEFAULT_PERIOD");
            detailsColumn.put("toDefPeriodDate", "TO_DEFUALT_PERIOD_DATE");
            detailsColumn.put(StringConstantUtils.CREATED_BY, "CREATED_BY");

            if (filterSet != null) {
                filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(filterSet, detailsColumn).toString();

                //This iteration is added for filtering Frequency i.e From and default 
                for (Container.Filter filter : filterSet) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        if (stringFilter.getPropertyId().toString().equals(StringConstantUtils.FROM_FREQUENCY_NAME)) {
                            filterQuery += " AND " + detailsColumn.get(stringFilter.getPropertyId().toString()) + " IS NOT NULL AND FROM_MODE <> 'Defined'";
                        } else if (stringFilter.getPropertyId().toString().equals(StringConstantUtils.FROM_DEF_FREQUENCY_NAME)) {
                            filterQuery += " AND " + detailsColumn.get(stringFilter.getPropertyId().toString()) + " IS NOT NULL AND FROM_DEF_MODE <> 'Defined'";
                        }
                    }
                }
//                filterCountPropValue = getFilteredProperty(filterSet);
            }
            if (filterQuery.contains("or * = '0'")) {
                filterQuery = filterQuery.replace("or * = '0'", "");
            }

            String creadedBySubQuery = "";
            if (filterSet != null) {
                for (Container.Filter filter : filterSet) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        if (stringFilter.getPropertyId().equals(StringConstantUtils.CREATED_BY)) {

                            creadedBySubQuery = getUserFilterQuery(stringFilter.getFilterString());

                        } else if (stringFilter.getPropertyId().equals(StringConstantUtils.ACTIVE_FLAG) && !"null".equals(String.valueOf(stringFilter.getFilterString()))) {
                            if ("Inactive".equalsIgnoreCase(stringFilter.getFilterString())) {
                                creadedBySubQuery = creadedBySubQuery + " UDC1_VERSION_NO <> A.v_NO ";
                            } else if ("active".equalsIgnoreCase(stringFilter.getFilterString())) {
                                creadedBySubQuery = creadedBySubQuery + " UDC1_VERSION_NO = A.v_NO ";
                            } else {

                                creadedBySubQuery = creadedBySubQuery + " UDC1_VERSION_NO = 0 ";
                            }
                        }

                    }

                }
            }

            if (filterQuery != null && !filterQuery.isEmpty() && !creadedBySubQuery.isEmpty()) {
                filterQuery = filterQuery + " AND UDC1_VERSION_NO = v_NO AND " + creadedBySubQuery;
            } else if (filterQuery.isEmpty() && !creadedBySubQuery.isEmpty()) {
                filterQuery = " WHERE UDC1_VERSION_NO = v_NO AND " + creadedBySubQuery;
            } else if (filterQuery != null && !filterQuery.isEmpty()) {
                filterQuery = filterQuery + " AND UDC1_VERSION_NO = v_NO ";
            } else {
                filterQuery = " WHERE UDC1_VERSION_NO = v_NO ";
            }
            String order = "";
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
                    order = order + " ORDER BY active_flag,v_NO DESC ";
                } else if (StringConstantUtils.FROM_PERIOD_VALUE.equals(columnName) || StringConstantUtils.FROM_DEF_PERIOD_VALUE.equals(columnName)
                        || StringConstantUtils.TO_PERIOD_VALUE.equals(columnName) || StringConstantUtils.TO_DEF_PERIOD_VALUE.equals(columnName)) {
                    String additionOrderBy = StringConstantUtils.FROM_PERIOD_VALUE.equals(columnName) ? detailsColumn.get(StringConstantUtils.FROM_MODE_NAME)
                            : StringConstantUtils.FROM_DEF_PERIOD_VALUE.equals(columnName) ? detailsColumn.get(StringConstantUtils.FROM_DEF_MODE_NAME)
                            : StringConstantUtils.TO_PERIOD_VALUE.equals(columnName) ? detailsColumn.get(StringConstantUtils.TO_MODE_NAME)
                            : StringConstantUtils.TO_DEF_PERIOD_VALUE.equals(columnName) ? detailsColumn.get(StringConstantUtils.TO_DEF_MODE_NAME) : StringUtils.EMPTY;
                    additionOrderBy = "," + additionOrderBy + ((!sortOrder) ? StringConstantUtils.ASC_SPACE : StringConstantUtils.DESC_SPACE);
                    order = order + StringConstantUtils.ORDER_BY + orderByColumn + ((!sortOrder) ? StringConstantUtils.ASC_SPACE : StringConstantUtils.DESC_SPACE) + additionOrderBy;
                } else if (StringConstantUtils.FROM_FREQUENCY_NAME.equals(columnName) || StringConstantUtils.FROM_DEF_FREQUENCY_NAME.equals(columnName)
                        || StringConstantUtils.TO_FREQUENCY_NAME.equals(columnName) || StringConstantUtils.TO_DEF_FREQUENCY_NAME.equals(columnName)) {
                    String additionOrderBy = StringConstantUtils.FROM_FREQUENCY_NAME.equals(columnName) ? detailsColumn.get(StringConstantUtils.FROM_MODE_NAME)
                            : StringConstantUtils.FROM_DEF_FREQUENCY_NAME.equals(columnName) ? detailsColumn.get(StringConstantUtils.FROM_DEF_MODE_NAME)
                            : StringConstantUtils.TO_FREQUENCY_NAME.equals(columnName) ? detailsColumn.get(StringConstantUtils.TO_MODE_NAME)
                            : StringConstantUtils.TO_DEF_FREQUENCY_NAME.equals(columnName) ? detailsColumn.get(StringConstantUtils.TO_DEF_MODE_NAME) : StringUtils.EMPTY;
                    additionOrderBy = "," + additionOrderBy + ((!sortOrder) ? StringConstantUtils.DESC_SPACE : StringConstantUtils.ASC_SPACE);
                    order = order + StringConstantUtils.ORDER_BY + orderByColumn + ((!sortOrder) ? StringConstantUtils.ASC_SPACE : StringConstantUtils.DESC_SPACE) + additionOrderBy;
                } else {
                    order = order + StringConstantUtils.ORDER_BY + orderByColumn + ((!sortOrder) ? StringConstantUtils.ASC_SPACE : StringConstantUtils.DESC_SPACE);
                }

                order = order + " " + "OFFSET ";
                order = order + startIndex;
                order = order + " ROWS FETCH NEXT " + endIndex;
                order = order + " ROWS ONLY;";
                finalQuery = selectQuery + filterQuery + order;
                List resultList = ForecastingMasterLocalServiceUtil.getFileSearchResults(finalQuery);
                if (resultList != null && resultList.size() > 0) {
                    forecastObj = getCustomizedresults(resultList);
                }
            } else {
//                filterCountPropValue = "PCD.VERSION_NO AS v_NO".equals(filterCountPropValue.trim().replace(",", "")) ? "" : filterCountPropValue;
                finalQuery = selectQuery + filterQuery + order;
                List resultList = ForecastingMasterLocalServiceUtil.getFileSearchResults(finalQuery);
                if (resultList != null && resultList.size() > 0) {
                    forecastObj = resultList.get(0);
                } else {
                    forecastObj = 0;
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return forecastObj;
    }

    public static String convertNullToEmpty(Object value) {
        String returnValue;
        if (value == null || "null".equals(String.valueOf(value))) {
            returnValue = StringUtils.EMPTY;
        } else {
            returnValue = String.valueOf(value);
        }
        return returnValue;
    }
    public String getFilteredProperty(final Set<Container.Filter> filterSet) {
        String filterPropValue = StringUtils.EMPTY;
        HashMap<String, String> filtersColumn = new HashMap<>();
        filtersColumn.put("moduleName", "MODULES.DESCRIPTION AS MODULES");
        filtersColumn.put("buscinessProcessName", "CASE When  PCM.BUSINESS_PROCESS_TYPE=-1 then 'Adjustment Summary' WHEN PCM.BUSINESS_PROCESS_TYPE = - 2 THEN 'Balance Summary Report' else ADJUST_CONFIG.TRANSACTION_NAME end as TRANSACTION_NAME ");
        filtersColumn.put("companyName", "Company.COMPANY_NO+' - '+Company.COMPANY_NAME AS Company_NAME");
        filtersColumn.put("bucinsessUnitName", "Buciness_Unit.COMPANY_NO+' - '+Buciness_Unit.COMPANY_NAME AS BUSCINESS_UNIT");
        filtersColumn.put("periodViewName", "PERIOD_VIEW.DESCRIPTION AS PV");
        filtersColumn.put("versionNo", "PCD.VERSION_NO AS VERSION_NO");
        filtersColumn.put(StringConstantUtils.FROM_MODE_NAME, "FROM_MODE.DESCRIPTION  AS FROM_MODE");
        filtersColumn.put(StringConstantUtils.FROM_FREQUENCY_NAME, "FROM_FRE.DESCRIPTION AS FROM_FRE");
        filtersColumn.put(StringConstantUtils.FROM_PERIOD_VALUE, "PCD.FROM_PERIOD AS FROM_PERIOD");
        filtersColumn.put("fromPeriodDate", "PCD.FROM_PERIOD_DATE AS FROM_PERIOD_DATE");
        filtersColumn.put(StringConstantUtils.FROM_DEF_MODE_NAME, "FROM_DEF_MODE.DESCRIPTION AS FROM_DEF_MODE");
        filtersColumn.put(StringConstantUtils.FROM_DEF_FREQUENCY_NAME, "FROM_DEF_FRE.DESCRIPTION AS FROM_DEF_FRE");
        filtersColumn.put(StringConstantUtils.FROM_DEF_PERIOD_VALUE, "PCD.FROM_DEFAULT_PERIOD AS FROM_DEFAULT_PERIOD");
        filtersColumn.put("fromDefPeriodDate", "PCD.FROM_DEFAULT_PERIOD_DATE AS FROM_DEFAULT_PERIOD_DATE");
        filtersColumn.put(StringConstantUtils.TO_MODE_NAME, "TO_MODE.DESCRIPTION AS TO_MODE");
        filtersColumn.put(StringConstantUtils.TO_FREQUENCY_NAME, "TO_FRE.DESCRIPTION  As TO_FRE ");
        filtersColumn.put(StringConstantUtils.TO_PERIOD_VALUE, "PCD.TO_PERIOD AS TO_PERIOD");
        filtersColumn.put("toPeriodDate", "PCD.TO_PERIOD_DATE AS TO_PERIOD_DATE");
        filtersColumn.put(StringConstantUtils.TO_DEF_MODE_NAME, "TO_DEF_MODE.DESCRIPTION AS TO_DEF_MODE");
        filtersColumn.put(StringConstantUtils.TO_DEF_FREQUENCY_NAME, "TO_DEF_FRE.DESCRIPTION AS TO_DEF_FRE");
        filtersColumn.put(StringConstantUtils.TO_DEF_PERIOD_VALUE, "PCD.TO_DEFAULT_PERIOD AS TO_DEFAULT_PERIOD");
        filtersColumn.put("toDefPeriodDate", "PCD.TO_DEFUALT_PERIOD_DATE AS TO_DEFUALT_PERIOD_DATE");
        filtersColumn.put(StringConstantUtils.CREATED_BY, "PCD.CREATED_BY AS CREATED_BY");
        filtersColumn.put(StringConstantUtils.ACTIVE_FLAG, "CASE WHEN  UDC1.VERSION_NO=PCD.VERSION_NO then 'Active' else 'Inactive' end as active_flag");
        
        for (Container.Filter filter : filterSet) {
            if (filter instanceof SimpleStringFilter) {
                SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                filterPropValue += "," + filtersColumn.get(stringFilter.getPropertyId().toString());
                if (stringFilter.getPropertyId().toString().equals(StringConstantUtils.FROM_FREQUENCY_NAME)) {
                    filterPropValue += "," + filtersColumn.get(StringConstantUtils.FROM_MODE_NAME);
                }else if(stringFilter.getPropertyId().toString().equals(StringConstantUtils.FROM_DEF_FREQUENCY_NAME)){
                    filterPropValue += "," + filtersColumn.get(StringConstantUtils.FROM_DEF_MODE_NAME);
                }
            } else if (filter instanceof Between) {
                Between betweenFilter = (Between) filter;
                filterPropValue += "," + filtersColumn.get(betweenFilter.getPropertyId().toString());
            } else if (filter instanceof Compare) {
                Compare stringCompareFilter = (Compare) filter;
                filterPropValue += "," + filtersColumn.get(stringCompareFilter.getPropertyId().toString());
            } else if (filter instanceof And) {
                And stringAndFilter = (And) filter;
                Collection<Container.Filter> value = stringAndFilter.getFilters();
                Object propertyId = "";
                for (Container.Filter filter1 : value) {

                    if (filter1 instanceof Compare.Less) {
                        Compare.Less less = (Compare.Less) filter1;
                        propertyId = less.getPropertyId();
                    } else if (filter1 instanceof Compare.Greater) {
                        Compare.Greater greater = (Compare.Greater) filter1;
                        propertyId = greater.getPropertyId();
                    }
                }
                filterPropValue += "," + filtersColumn.get(propertyId.toString());
            }
        }
        return filterPropValue;
    }
    private static Date parsetDate(String value) throws java.text.ParseException {
        Date date = null;
        String tempDate;
        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        if (value != null && !StringUtils.EMPTY.equals(value) && !"null".equals(value)) {
            tempDate = format.format(parse.parse(value));
            date = format.parse(tempDate);
        }

        return date;
    }

    public void getTransactionList(ComboBox select) {
        String query = " SELECT ARM_ADJUSTMENT_CONFIG_SID,TRANSACTION_NAME FROM ARM_ADJUSTMENT_CONFIG ";
        getHelperList(select, query, true);

    }

    public void getBusinessUnitList(ComboBox select) {
        String query = "select COMPANY_MASTER_SID,COMPANY_NO+' - '+COMPANY_NAME as company \n"
                + "from COMPANY_MASTER CM JOIN  HELPER_TABLE HT ON HT.HELPER_TABLE_SID=CM.COMPANY_TYPE \n"
                + "where HT.LIST_NAME='COMPANY_TYPE' AND HT.DESCRIPTION='BUSINESS UNIT'";
        getHelperList(select, query, false);

    }

    public void getCompanyList(ComboBox select) {
        String query = "select COMPANY_MASTER_SID,COMPANY_NO+' - '+COMPANY_NAME as company \n"
                + "from COMPANY_MASTER CM JOIN  HELPER_TABLE HT ON HT.HELPER_TABLE_SID=CM.COMPANY_TYPE \n"
                + "where HT.LIST_NAME='COMPANY_TYPE' AND HT.DESCRIPTION='GLCOMP'";
        getHelperList(select, query, false);

    }

    public void getHelperList(final ComboBox select, String query, boolean isBucinessProcess) {

        List resultList = ForecastingMasterLocalServiceUtil.getFileSearchResults(query);

        final HelperDTO defaultValue = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);

        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<>(HelperDTO.class);
        List<HelperDTO> helperList = new ArrayList<>();
        helperList.add(defaultValue);
        if (isBucinessProcess) {
            final HelperDTO adjustSummery = new HelperDTO(-1, "Adjustment Summary");
            helperList.add(adjustSummery);
            String bsrQuery = SQlUtil.getQuery("balanceSummaryReport");
            List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(bsrQuery);
            for (Object[] objects : list) {
                String desc = (String.valueOf(objects[1])).replace("Balance Summary", StringUtils.EMPTY);
                desc = "Balance Summary Report - " + desc;
                final HelperDTO balanceSummaryReport = new HelperDTO((Integer) objects[0], desc);
                helperList.add(balanceSummaryReport);
            }
        }

        if (resultList != null) {
            for (int i = 0; i < resultList.size(); i++) {
                Object[] obj = (Object[]) resultList.get(i);
                helperList.add(new HelperDTO(Integer.parseInt(String.valueOf(obj[0])), String.valueOf(obj[1])));
            }
        }

        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : select.getValue()));
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : ((HelperDTO) select.getValue()).getDescription()));
            }
        });

    }

    public static Map<String, String> getPeriodViewMap() {
        Map<String, String> idValueMap = new HashMap<>();
        String query = "SELECT DESCRIPTION,HELPER_TABLE_SID from HELPER_TABLE where LIST_NAME='PERIODCONFIG_VIEW';";
        List resultList = ForecastingMasterLocalServiceUtil.getFileSearchResults(query);
        if (resultList != null) {
            for (int i = 0; i < resultList.size(); i++) {
                Object[] obj = (Object[]) resultList.get(i);
                idValueMap.put(String.valueOf(obj[0]), String.valueOf(obj[1]));
            }
        }

        return idValueMap;

    }

    public void savePeriodConfig(PeriodConfigDTO pconDTO) {
        try {
            java.sql.Timestamp fromPeriodDate = null;
            java.sql.Timestamp toPeriodDate = null;
            java.sql.Timestamp fromDefPeriodDate = null;
            java.sql.Timestamp toDefPeriodDate = null;

            if (pconDTO.getFromPeriodDate() != null) {
                fromPeriodDate = new Timestamp(pconDTO.getFromPeriodDate().getTime());
            }
            if (pconDTO.getToPeriodDate() != null) {
                toPeriodDate = new Timestamp(pconDTO.getToPeriodDate().getTime());
            }
            if (pconDTO.getFromDefPeriodDate() != null) {
                fromDefPeriodDate = new Timestamp(pconDTO.getFromDefPeriodDate().getTime());
            }
            if (pconDTO.getToDefPeriodDate() != null) {
                toDefPeriodDate = new Timestamp(pconDTO.getToDefPeriodDate().getTime());
            }
            final int userId = Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));

            String selectQString = " select PERIOD_CONFIG_MASTER_SID FROM  PERIOD_CONFIG_MASTER where MODULE=" + pconDTO.getModulesId() + " "
                    + "and BUSINESS_PROCESS_TYPE=" + pconDTO.getBuscinessProcessTypeID() + " and COMPANY_MASTER_SID=" + pconDTO.getCompanySid() + " and BU_COMPANY_MASTER_SID=" + pconDTO.getBuCompanySysId();
            int sysId = 0;
            List selectList = HelperTableLocalServiceUtil.executeSelectQuery(selectQString);
            if (selectList != null && selectList.size() > 0) {
                sysId = (((List<Integer>) selectList).get(0)).intValue();
            } else {
                String insertQuery = " INSERT INTO PERIOD_CONFIG_MASTER (\"MODULE\",BUSINESS_PROCESS_TYPE,COMPANY_MASTER_SID,BU_COMPANY_MASTER_SID)\n"
                        + "VALUES(" + pconDTO.getModulesId() + "," + pconDTO.getBuscinessProcessTypeID() + "," + pconDTO.getCompanySid() + "," + pconDTO.getBuCompanySysId() + ")  SELECT SCOPE_IDENTITY();\n";
                sysId = (((List<BigDecimal>) HelperTableLocalServiceUtil.executeSelectQuery(insertQuery)).get(0)).intValue();

            }
            String fromPeriod = isValid( String.valueOf(pconDTO.getFromPeriod())) ? String.valueOf(pconDTO.getFromPeriod()):df.format(pconDTO.getFromPeriodDate());
            String fromDefaultPeriod = isValid( String.valueOf(pconDTO.getFromDefPeriod())) ? String.valueOf(pconDTO.getFromDefPeriod()):df.format(pconDTO.getFromDefPeriodDate()) ;
            String toPeriod = isValid( String.valueOf(pconDTO.getToPeriod())) ? String.valueOf(pconDTO.getToPeriod()):getDateAsString((pconDTO.getToPeriodDate())) ;
            String toDefaultPeriod = isValid( String.valueOf(pconDTO.getToDefPeriod())) ? String.valueOf(pconDTO.getToDefPeriod()):getDateAsString(pconDTO.getToDefPeriodDate()) ; 
			String toDateValue =  pconDTO.getToPeriodDateValue() == null?null:"'"+pconDTO.getToPeriodDateValue()+"'";
			String toDefDateValue = pconDTO.getToDefPeriodDateValue() == null?null:"'"+pconDTO.getToDefPeriodDateValue()+"'" ;
            String insertQuery1 = "insert into PERIOD_CONFIG_DETAILS " + "(PERIOD_CONFIG_MASTER_SID,\n"
					+ "FROM_MODE,FROM_DEFAULT_MODE,FROM_FREQUENCY ,FROM_DEFAULT_FREQUERNCY,FROM_PERIOD, FROM_PERIOD_DATE ,FROM_DEFAULT_PERIOD, FROM_DEFAULT_PERIOD_DATE ,\n"
					+ "TO_MODE,TO_DEFAULT_MODE,TO_FREQUENCY,TO_DEFAULT_FREQUERNCY,TO_PERIOD ,TO_PERIOD_DATE , TO_DEFAULT_PERIOD , TO_DEFUALT_PERIOD_DATE,VERSION_NO,CREATED_BY,PERIOD_VIEW )"
					+ "VALUES(" + sysId + "," + pconDTO.getFromModeId() + "," + pconDTO.getFromDefModeId() + ","
					+ pconDTO.getFromFrequencyId() + "," + pconDTO.getFromDefFrequencyId() + ",'"
					+ fromPeriod+ "','"+pconDTO.getFromPeriodDateValue()+"' ,'"
					+ fromDefaultPeriod + "','"
					+ pconDTO.getFromDefPeriodDateValue()
					+ "'," + pconDTO.getToModeId() + "," + pconDTO.getToDefModeId() + ","
					+ pconDTO.getToFrequencyId() + "," + pconDTO.getToDefFrequencyId() + ",'" + toPeriod
					+ "'," +toDateValue
					+ ",'" + toDefaultPeriod + "',"
					+ toDefDateValue+ ","
					+ " (select ISNULL(max(VERSION_NO),0)+1   from PERIOD_CONFIG_DETAILS where PERIOD_CONFIG_MASTER_SID="
					+ sysId + " )," + userId + "," + pconDTO.getPeriodViewName() + " )";

			HelperTableLocalServiceUtil.executeUpdateQuery(insertQuery1);
		
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    
    private boolean isValid(String sourceString) {
    	return !"null".equals(sourceString) && !sourceString.isEmpty();
    }
    
    private String getDateAsString(Date date) {
    	if(date != null) {
    		return df.format(date);
    	}
    	return "null";
    	
    }

    private String getUserFilterQuery(String fiterString) {
        String query = "";
        try {

            final Map<String, String> userInfoMap = CommonUtil.getCreatedByUser();
            for (String key : userInfoMap.keySet()) {
                if ((userInfoMap.get(key).toLowerCase()).startsWith((fiterString.trim()).toLowerCase())) {
                    if (query.isEmpty()) {
                        query = " A.CREATED_BY IN (" + key;
                    } else {
                        query = query + "," + key;
                    }

                }
            }
            if (!query.isEmpty()) {
                query = query + ")";
            } 

        } catch (SystemException ex) {
            LOGGER.error(ex);
        }

        return query;

    }
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
            if (propertyId.toString().equals(StringConstantUtils.CREATED_BY)) {
                if (originatingField.getValue() != null) {
                    return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                } else {
                    return null;
                }
            }
            return null;
    }
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
            final ComboBox comboBox = new ComboBox();
            switch (propertyId.toString()) {
                case StringConstantUtils.CREATED_BY:
                    comboBox.addItem(0);
                    comboBox.setItemCaption(0, com.stpl.app.serviceUtils.ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Long, String> entry : getUserMap().entrySet()) {
                        comboBox.addItem(entry.getKey());
                        comboBox.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox.setNullSelectionAllowed(true);
                    comboBox.setNullSelectionItemId(0);
                    return comboBox;
                    default:
                    return null;
            }
        }

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
        return;
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
        return;
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
        return null;
    }
    }


