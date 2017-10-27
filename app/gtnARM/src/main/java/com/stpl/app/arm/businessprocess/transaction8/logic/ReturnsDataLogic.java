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
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
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
import org.jboss.logging.Logger;

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
     *  Six Decimal Places Format.
     */

    protected static final DecimalFormat DECIMAL = new DecimalFormat("###0.000000");

    private final Logger logger = Logger.getLogger(getClass());

    @Override
    public int getCount(Criteria criteria) {
        logger.debug("--Inside getCount --");
        String query = SQlUtil.getQuery("returnsdata-count");
        String version = criteria.getSelectionDto().getDataSelectionDTO().getFromPeriodMonth().replace(" ", "-");
        query = query.replace("?", version);
        query = query + getFilters(criteria);
        logger.debug("query--" + query);
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return (Integer) list.get(0);
    }

    @Override
    public DataResult<T> getData(Criteria criteria) {
        logger.debug("--Inside getData--");
        String query = SQlUtil.getQuery("returnsdata-data");
        String version = criteria.getSelectionDto().getDataSelectionDTO().getFromPeriodMonth().replace(" ", "-");
        query = query.replace("?", version);
        query = query + getFilters(criteria) + getOrder(criteria);
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        logger.debug("--Exit getData--" + query);
        return customizier("com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO", criteria.getSelectionDto().getReturnsdatavariables(), list);
    }

    @Override
    public List getExcelResultList(AbstractSelectionDTO selection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DataResult<T> customizier(String dtoFullPath, List<String> varibales, List<Object[]> resultList) {
        logger.debug("--Inside customizier --" + varibales.size() + "--resultList--" + resultList.size());
        List customizedList = new ArrayList();
        AdjustmentDTO obj;
        int variableSize = varibales.size();
        try {
            if (resultList == null) {
                throw new NullPointerException("The given input resultList is null");
            }
            if (varibales == null) {
                throw new NullPointerException("The given input varibales List is null");
            }
            if (!resultList.isEmpty()) {
                if (varibales.size() != resultList.get(0).length) {
                    throw new IllegalArgumentException(varibales.size() + "The given parameters and variables size doesn't match : variables = "
                            + varibales.size() + "and resultList  = " + resultList.get(0).length);
                }

                for (int k = 0; k < resultList.size(); k++) {
                    Object[] objects = resultList.get(k);
                    obj = (AdjustmentDTO) Class.forName(dtoFullPath).newInstance();
                    for (int j = 0; j < variableSize; j++) {
                        if (obj instanceof ExtListDTO) {
                            if (varibales.get(j).matches("[a-zA-Z0-9_]+\\.\\d+$")) {
                                if (objects[j] != null) {
                                    if (objects[j].toString().matches("^(-?0[.]\\d+)$|^(-?[1-9]+\\d*([.]\\d+)?)$|^0$")) { // Allows only Numbers in it
                                        String value = objects[j] == null ? StringUtils.EMPTY : String.valueOf(objects[j]);
                                        if (Arrays.asList(ARMUtils.getCurrencyTwoDecColumns()).contains(varibales.get(j))) {
                                            value = getFormattedValue(CUR_TWO, value);
                                        }else if(Arrays.asList(ARMUtils.getPercentTwoDecColumns()).contains(varibales.get(j))){
                                            value = getFormattedValue(PERCENT, value);
                                        } else if (Arrays.asList(ARMUtils.getDecimalSix()).contains(varibales.get(j))) {
                                            value = getFormattedValue(DECIMAL, value);
                                        }
                                        (obj).addStringProperties(varibales.get(j), objects[j] == null ? StringUtils.EMPTY : value);
                                    } else {
                                        (obj).addStringProperties(varibales.get(j), objects[j] == null ? StringUtils.EMPTY : String.valueOf(objects[j]));
                                    }
                                } else {
                                    (obj).addStringProperties(varibales.get(j), objects[j] == null ? StringUtils.EMPTY : String.valueOf(objects[j]));
                                }
                            } else {
                                BeanUtils.setProperty(obj, varibales.get(j), objects[j]);
                            }
                        }

                    }
                    customizedList.add(obj);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException ex) {
            logger.error("Error while setting property for given Inputs :" + ex);
        } catch (InstantiationException | ClassNotFoundException ex) {
            logger.error("The given DTO path is not valid :" + ex + " the given path :" + dtoFullPath);
        } catch (IllegalArgumentException ex) {
            logger.error("Error in customizier :"+ex);
        }
        OriginalDataResult<T> dataResult = new OriginalDataResult<>();
        dataResult.setDataResults(customizedList);
        return dataResult;
    }

    public String getFilters(Criteria criteria) {
        logger.debug("--Inside getFilters --");
        StringBuilder filterQuery = new StringBuilder(StringUtils.EMPTY);
        if (criteria.getFilters() != null && !criteria.getFilters().isEmpty()) {
            for (Container.Filter filter : criteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    String propertyId = String.valueOf(stringFilter.getPropertyId());
                    filterQuery.append(filterQuery).append(" AND ");
                    filterQuery.append(propertyId.substring(0, propertyId.indexOf('.')));
                    filterQuery.append(" like '").append(filterString).append("'");
                }

            }
        }
        logger.debug("--Exit getFilters --" + filterQuery);
        return filterQuery.toString();
    }

    private String getOrder(Criteria criteria) {
        String order = StringUtils.EMPTY;
        logger.debug("Inside getOrder  " + order);
        boolean sortOrder = false;
        String orderByColumn = null;
        if (criteria.getSortByColumns() != null) {
            for (final Iterator<SortByColumn> iterator = criteria.getSortByColumns().iterator(); iterator.hasNext();) {
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
        order = order + " " + "OFFSET ";
        order = order + criteria.getStart();
        order = order + " ROWS FETCH NEXT " + criteria.getOffset();
        order = order + " ROWS ONLY;";
        logger.debug("Exit getOrder  " + order);
        return order;
    }
    
    public String getFormattedValue(DecimalFormat format, String value) {
        String values;
        if (value.contains("null")) {
            values = "- - -";
        } else {
            values = value;
            Double newValue = Double.valueOf(values);
            if (format.toPattern().contains("%")) {
                newValue = newValue / NumericConstants.HUNDRED;
            }
            values = format.format(newValue);
        }
        return values;
    }
}
