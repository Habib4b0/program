/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction8.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.supercode.LogicAble;
import com.stpl.app.arm.supercode.OriginalDataResult;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtListDTO;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author @param <T>
 */
public class ReturnsDataLogic<T extends AdjustmentDTO> implements LogicAble<T>, ExcelInterface {

    /**
     * The Currency Two Decimal Places Format.
     */
    protected static final DecimalFormat CUR_TWO = new DecimalFormat("$#,##,##0.00");

    /**
     * The Percentage Two Decimal Places Format.
     */
    protected static final DecimalFormat PERCENT = new DecimalFormat("#,##,##0.00%");
    /**
     * Six Decimal Places Format.
     */

    protected static final DecimalFormat DECIMAL = new DecimalFormat("###0.000000");

    private static final Logger RD_LOGIC_LOGGER = LoggerFactory.getLogger(ReturnsDataLogic.class);

    @Override
    public int getCount(Criteria criteria) {
        RD_LOGIC_LOGGER.debug("--Inside getCount --");
        String query = SQlUtil.getQuery("returnsdata-count");
        String version = criteria.getSelectionDto().getDataSelectionDTO().getFromPeriodMonth().replace(ARMUtils.SPACE.toString(), "-");
        query = query.replace(ARMUtils.CHAR_QUS, version);
        query = query + getFilters(criteria);
        RD_LOGIC_LOGGER.debug("query--{}", query);
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return (Integer) list.get(0);
    }

    @Override
    public DataResult<T> getData(Criteria criteria) {
        RD_LOGIC_LOGGER.debug("--Inside getData--");
        String query = SQlUtil.getQuery("returnsdata-data");
        String version = criteria.getSelectionDto().getDataSelectionDTO().getFromPeriodMonth().replace(ARMUtils.SPACE.toString(), "-");
        query = query.replace(ARMUtils.CHAR_QUS, version);
        query = query + getFilters(criteria) + getOrder(criteria);
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        RD_LOGIC_LOGGER.debug("--Exit getData--{}", query);
        return customizier(criteria.getSelectionDto().getReturnsdatavariables(), list);
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DataResult<T> customizier(List<String> rdVaribales, List<Object[]> rdResultList) {
        RD_LOGIC_LOGGER.debug("--Inside customizier --{}", (rdVaribales.size() + "--resultList--" + rdResultList.size()));
        List customizedList = new ArrayList();
        AdjustmentDTO obj;
        int variableSize = rdVaribales.size();
        try {
            if (rdResultList == null) {
                throw new NullPointerException("The given input resultList is null");
            }
            if (rdVaribales == null) {
                throw new NullPointerException("The given input varibales List is null");
            }
            if (!rdResultList.isEmpty()) {
                if (rdVaribales.size() != rdResultList.get(0).length) {
                    throw new IllegalArgumentException(rdVaribales.size() + "The given parameters and variables size doesn't match : variables = "
                            + rdVaribales.size() + "and resultList  = " + rdResultList.get(0).length);
                }

                for (int k = 0; k < rdResultList.size(); k++) {
                    Object[] objects = rdResultList.get(k);
                    obj = new AdjustmentDTO();
                    for (int j = 0; j < variableSize; j++) {
                        if (obj instanceof ExtListDTO) {
                            if (rdVaribales.get(j).matches("[a-zA-Z0-9_]+\\.\\d+$")) {
                                if (objects[j] != null) {
                                    if (objects[j].toString().matches("^(-?0[.]\\d+)$|^(-?[1-9]+\\d*([.]\\d+)?)$|^0$")) { // Allows only Numbers in it
                                        String value = objects[j] == null ? StringUtils.EMPTY : String.valueOf(objects[j]);
                                        if (Arrays.asList(ARMUtils.getCurrencyTwoDecColumns()).contains(rdVaribales.get(j))) {
                                            value = getFormattedValue(CUR_TWO, value);
                                        } else if (Arrays.asList(ARMUtils.getPercentTwoDecColumns()).contains(rdVaribales.get(j))) {
                                            value = getFormattedValue(PERCENT, value);
                                        } else if (Arrays.asList(ARMUtils.getDecimalSix()).contains(rdVaribales.get(j))) {
                                            value = getFormattedValue(DECIMAL, value);
                                        }
                                        (obj).addStringProperties(rdVaribales.get(j), objects[j] == null ? StringUtils.EMPTY : value);
                                    } else {
                                        (obj).addStringProperties(rdVaribales.get(j), objects[j] == null ? StringUtils.EMPTY : String.valueOf(objects[j]));
                                    }
                                } else {
                                    (obj).addStringProperties(rdVaribales.get(j), objects[j] == null ? StringUtils.EMPTY : String.valueOf(objects[j]));
                                }
                            } else {
                                BeanUtils.setProperty(obj, rdVaribales.get(j), objects[j]);
                            }
                        }

                    }
                    customizedList.add(obj);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException ex) {
            RD_LOGIC_LOGGER.error("Error while setting property for given Inputs :", ex);
        } catch (IllegalArgumentException ex) {
            RD_LOGIC_LOGGER.error("Error in customizier :", ex);
        }
        OriginalDataResult<T> dataResult = new OriginalDataResult<>();
        dataResult.setDataResults(customizedList);
        return dataResult;
    }

    public String getFilters(Criteria criteria) {
        RD_LOGIC_LOGGER.debug("--Inside getFilters --");
        StringBuilder filterQuery = new StringBuilder();
        if (criteria.getFilters() != null && !criteria.getFilters().isEmpty()) {
            for (Container.Filter filter : criteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    String propertyId = String.valueOf(stringFilter.getPropertyId());
                    filterQuery.append(filterQuery).append(" AND ");
                    filterQuery.append(propertyId.substring(0, propertyId.indexOf('.')));
                    filterQuery.append(" like '").append(filterString).append(ARMUtils.SINGLE_QUOTES);
                }

            }
        }
        RD_LOGIC_LOGGER.debug("--Exit getFilters --{}", filterQuery);
        return filterQuery.toString();
    }

    private String getOrder(Criteria criteria) {
        String order = StringUtils.EMPTY;
        RD_LOGIC_LOGGER.debug("Inside getOrder  {}", order);
        boolean sortOrder = false;
        String orderByColumn = null;
        if (criteria.getSortByColumns() != null) {
            List<SortByColumn> sortBycolumns = criteria.getSortByColumns();
            for (final Iterator<SortByColumn> iterator = sortBycolumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = iterator.next();
                orderByColumn = sortByColumn.getName().substring(0, sortByColumn.getName().indexOf('.'));
                sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
            }
        }
        if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
            order = order + " ORDER BY SKU ";
        } else {
            order = order + " ORDER BY " + orderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
        }
        order = order + ARMUtils.SPACE + "OFFSET ";
        order = order + criteria.getStart();
        order = order + " ROWS FETCH NEXT " + criteria.getOffset();
        order = order + " ROWS ONLY;";
        RD_LOGIC_LOGGER.debug("Exit getOrder {} ", order);
        return order;
    }

    public String getFormattedValue(DecimalFormat rdFormat, String rdValue) {
        String values;
        if (rdValue.contains("null")) {
            values = "- - -";
        } else {
            values = rdValue;
            Double newValue = Double.valueOf(values);
            if (rdFormat.toPattern().contains("%")) {
                newValue = newValue / NumericConstants.HUNDRED;
            }
            values = rdFormat.format(newValue);
        }
        return values;
    }
}
