/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.dao.CommonDao;
import com.stpl.app.arm.dao.impl.CommonImpl;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.supercode.LogicAble;
import com.stpl.app.arm.supercode.OriginalDataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtListDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Karthikeyan.Subraman
 * @param <T>
 */
public abstract class AbstractBPLogic<T extends AdjustmentDTO> implements LogicAble<T>, ExcelInterface {

    /**
     * DAO object is Singleton and also which is used here as class variable
     * Which can be used inherited classes
     */
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractBPLogic.class);

    protected static final CommonDao DAO = CommonImpl.getInstance();

    protected static final Map<Integer, String> idDescMap = HelperListUtil.getInstance().getIdDescMap();

    private final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    /**
     * The Currency Two Decimal Places Format.
     */
    protected static final DecimalFormat CUR_TWO = new DecimalFormat("$#,##,##0.00");
    /**
     * The Currency Six Decimal Places Format.
     */
    protected static final DecimalFormat CUR_SIX = new DecimalFormat("$#,##,##0.000000");
    /**
     * The Percent Three Decimal Places Format.
     */
    protected static final DecimalFormat PER_THREE = new DecimalFormat("#,##0.000%");
    /**
     * The Percent Three Decimal Places Format.
     */
    protected static final DecimalFormat PER_TWO = new DecimalFormat("#,##0.00%");
    /**
     * The Currency Zero Decimal Places Format.
     */
    protected static final DecimalFormat CURR_ZERO = new DecimalFormat("$#,##0");
    /**
     * The Numeric Zero Decimal Places Format.
     */
    protected static final DecimalFormat NUM_ZERO = new DecimalFormat("#,##0");

    /**
     * method will customize the raw or result list from data base it sets the
     * value to respective variable given in list index
     *
     * @param dtoFullPath - Type of the POJO class name with full path
     * @param varibales - List of variables need to set in given POJO or DTO
     * @param resultList - raw list which was queried from database
     * @return
     */
    public DataResult<T> customizier(List<String> varibales, List<Object[]> resultList) {
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

                for (Object[] objects : resultList) {
                    obj = new AdjustmentDTO();
                    for (int j = 0; j < variableSize; j++) {
                        if (obj instanceof ExtListDTO) {
                            if (varibales.get(j).startsWith("h_")) {
                                setHelperSidColumn((ExtListDTO) obj, objects[j], varibales.get(j));
                            } else if (varibales.get(j).matches("[a-zA-Z0-9]+\\.\\d+$")) {
                                if (objects[j] instanceof BigInteger) {
                                    (obj).addProperties(varibales.get(j), objects[j]);
                                } else if (varibales.get(j).contains("glDate.")) {
                                    (obj).addStringProperties(varibales.get(j), objects[j] == null ? StringUtils.EMPTY : format.format(objects[j]));
                                } else if (varibales.get(j).contains("Date")) {
                                    (obj).addStringProperties(varibales.get(j), objects[j] == null ? StringUtils.EMPTY : format.format(objects[j]));
                                } else if (!"itemID.27".equals(varibales.get(j)) && varibales.get(j).contains("itemID")) {
                                    String object = String.valueOf(objects[j]);
                                    (obj).addStringProperties(varibales.get(j), objects[j] == null ? StringUtils.EMPTY : object);
                                } else if (objects[j] != null) {
                                    if (objects[j].toString().matches("^(-?0[.]\\d+)$|^(-?[1-9]+\\d*([.]\\d+)?)$|^0$")) { // Allows only Numbers in it
                                        String value;
                                       if (varibales.get(j).contains("salesVariancePer") || varibales.get(j).contains(ARMConstants.getRate())
                                                || varibales.get(j).contains("deductionRate")) {
                                            value = getFormattedValue(PER_THREE, String.valueOf(objects[j]));
                                        }else  if (varibales.get(j).contains(ARMConstants.getAmount())
                                                || varibales.get(j).contains("deductionAmount") || varibales.get(j).contains("price")
                                                || varibales.get(j).contains("priceOverride") || varibales.get(j).contains("totalSales")
                                                || varibales.get(j).contains("excludedSales") || varibales.get(j).contains("netSales")
                                                || varibales.get(j).contains("netCalculatedSales") || varibales.get(j).contains("salesVariance")) {
                                            value = getFormattedValue(CUR_TWO, String.valueOf(objects[j]));
                                        }  else if (varibales.get(j).contains("totalUnits") || varibales.get(j).contains("excludedUnits") || varibales.get(j).contains("netUnits")) {
                                            value = getFormattedValue(NUM_ZERO, String.valueOf(objects[j]));
                                        } else if (varibales.get(j).contains("debit") || varibales.get(j).contains("credit")) {
                                            value = getFormattedValue(CUR_SIX, String.valueOf(objects[j]));
                                        } else {
                                            value = objects[j] == null ? StringUtils.EMPTY : String.valueOf(objects[j]);
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
            LOGGER.error("Error while setting property for given Inputs :", ex);
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Error in customizier ", ex);
        }
        OriginalDataResult<T> dataResult = new OriginalDataResult<>();
        dataResult.setDataResults(customizedList);
        return dataResult;
    }

    private void setHelperSidColumn(ExtListDTO obj, Object objects, String variables) {
        String desc;
        int value = objects == null || StringUtils.isBlank(String.valueOf(objects)) ? 0 : ARMUtils.getIntegerValue(String.valueOf(objects));
        if (value > 0) {
            desc = idDescMap.get(value) == null ? StringUtils.EMPTY : idDescMap.get(value);
            obj.addStringProperties(variables, desc);
        }
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

    public boolean checkProcedureCallAllowed(SelectionDTO selection, List<String> keys, List<Object> values) {
        if (values.size() != keys.size()) {
            throw new IllegalArgumentException("Keys and values diesn't match values.size() :" + values.size() + " keys.size :" + keys.size());
        }
        boolean allowed = false;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Object value = values.get(i);
            Object oldValue = selection.getProcedureInputs(key);
            selection.setProcedureInputs(key, value);
            if (value instanceof String) {
                if (!String.valueOf(value).equals(oldValue)) {
                    allowed = true;
                }
            } else if (value instanceof List) {
                if (oldValue != null) {
                    allowed = getAllowed(value, oldValue, allowed);
                } else {
                    allowed = true;
                }
            }
        }
        return allowed;
    }

    private boolean getAllowed(Object value, Object oldValue, boolean a) {
        boolean allowed = a;
        List<String> valList = (List<String>) value;
        List<String> oldValList = (List<String>) oldValue;
        if (valList.size() != oldValList.size()) {
            allowed = true;
        } else {
            allowed = checkProcedureAllowed(valList, oldValList, allowed);
        }
        return allowed;
    }

    private boolean checkProcedureAllowed(List<String> valList, List<String> oldValList, boolean alowed) {
        boolean allowed = alowed;
        for (int j = 0; j < valList.size(); j++) {
            String valString = valList.get(j);
            if (!valString.equals(oldValList.get(j))) {
                allowed = true;
                break;
            }
        }
        return allowed;
    }

    public String getItemId(String value) {
        String values;
        if (value.length() > NumericConstants.FOUR) {
            values = value.substring(0, NumericConstants.FOUR) + ARMUtils.DOT + value.substring(NumericConstants.FOUR, value.length());
        } else {
            values = value + ARMUtils.DOT;
        }
        return values;
    }

    public List<Object> getMonthYear() {
        String sql = SQlUtil.getQuery("getMonthYear");
        List result = HelperTableLocalServiceUtil.executeSelectQuery(sql);
        List<Object> defaultValues = new ArrayList<>();
        if (!result.isEmpty()) {
            Object[] value = (Object[]) result.get(0);
            for (Object value1 : value) {
                defaultValues.add(String.valueOf(value1));
            }
        }
        return defaultValues;
    }

    /**
     * Get month name from month number
     *
     * @param monthNo
     * @return Jan-1........Dec-12
     */
    public static String getMonthName(int monthNo) {
        String monthName = StringUtils.EMPTY;
        try {
            DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
            String[] months = dateFormatSymbols.getShortMonths();
            monthName = months[monthNo - 1];
        } catch (Exception e) {
            LOGGER.error("Error in getMonthName :", e);
        }
        return monthName;
    }
}
