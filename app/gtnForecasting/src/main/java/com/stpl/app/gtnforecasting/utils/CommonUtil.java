/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.discountProjection.form.NMDiscountProjection;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import static com.stpl.app.gtnforecasting.logic.CommonLogic.LOGGER;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.form.DataSelection;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.ComboBox;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.addons.lazycontainer.LazyContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class CommonUtil.
 *
 * @author pvinoth
 */
public class CommonUtil {

    /**
     * The object.
     */
    private static CommonUtil object;
    public static final String OPEN_PARANTHESIS = "(";
    public static final String CLOSE_PARANTHESIS = ")";
    public static final String BUSINESS_PROCESS = "businessProcess";
    public static final String BP_NAME = "ALLERGAN";
    public static final DecimalFormat FORMAT_NO_DECIMAL = new DecimalFormat("$#,##0");
    public static final DecimalFormat FORMAT_TWO_DECIMAL = new DecimalFormat("$#,##0.00");

    /**
     * The helper list util.
     */
    protected HelperListUtil helperListUtil = HelperListUtil.getInstance();

    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
    public static final String COMMA=",";

    /**
     * Instantiates a new common util.
     */
    private CommonUtil() {

    }

    /**
     * Gets the single instance of CommonUtil.
     *
     * @return single instance of CommonUtil
     */
    public static CommonUtil getInstance() {
        if (object == null) {
            object = new CommonUtil();
        }
        return object;
    }
    /**
     * To get the combo box select.
     *
     * @param select the select
     * @param listName the list name
     * @param isFilter the is filter
     * @return the native select
     * @throws Exception the exception
     */
    public ComboBox loadComboBox(final ComboBox select,
            String listName, boolean isFilter) {
        select.removeAllItems();
        final HelperDTO defaultValue = new HelperDTO(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        select.setData(listName);
        List<HelperDTO> helperList = new ArrayList<>();
        helperList.add(defaultValue);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<>(HelperDTO.class);
        if (helperListUtil.getListNameMap().get(listName) != null) {
            helperList.addAll(helperListUtil.getListNameMap().get(listName));
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
                select.setDescription(String.valueOf(select.getValue() == null ? ConstantsUtils.SELECT_ONE : ((HelperDTO) select.getValue()).getDescription()));
            }
        });
        return select;
    }

    public ComboBox loadComboBoxWithInteger(final ComboBox select,
            String listName, boolean isFilter)  {
        select.removeAllItems();
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(0);
        select.setData(listName);
        select.addItem(0);

        select.setItemCaption(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
        List<HelperDTO> list = helperListUtil.getListNameMap().get(listName);
        if ("PRICE_TOLERANCE_INTERVAL".equalsIgnoreCase(listName)) {
            getHelperDTOSortByInteger(list);
        }
        for (HelperDTO helperDTO : list) {
            select.addItem(helperDTO.getId());
            select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
        }
        select.select(0);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == DASH ? ConstantsUtils.SELECT_ONE : select.getItemCaption(select.getValue())));

        return select;
    }

    /**
     * To get the combo box select.
     *
     * @param select the select
     * @param listName the list name
     * @param isFilter the is filter
     * @return the native select
     * @throws Exception the exception
     */
    public ComboBox loadYesNoDDLB(final ComboBox select,
            boolean isFilter)  {

        final String defaultValue = isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE;
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.addItem(defaultValue);
        select.addItem(ConstantsUtils.YES_VARIABLE);
        select.addItem(ConstantsUtils.NO_VARIABLE);
        select.select(defaultValue);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : select.getValue()));
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : select.getValue()));
            }
        });
        return select;
    }

    /**
     * To get the combo box select.
     *
     * @param select the select
     * @param listName the list name
     * @param isFilter the is filter
     * @return the native select
     * @throws Exception the exception
     */
    public ComboBox loadActiveInactiveDDLB(final ComboBox select,
            boolean isFilter) throws SystemException {

        final HelperDTO defaultValue = new HelperDTO(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : select.getValue()));

        final DynamicQuery dynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.DESCRIPTION, new Object[]{Constant.ACTIVE, Constant.INACTIVE}));
        dynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
        List<HelperTable> list = helperListUtil.getDynamicQuery(dynamicQuery);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<>(HelperDTO.class);
        resultContainer.addItem(defaultValue);
        for (Iterator<HelperTable> it = list.iterator(); it.hasNext();) {
            HelperTable helperTable = it.next();
            HelperDTO dto = new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription());
            resultContainer.addItem(dto);
        }
        select.setContainerDataSource(resultContainer);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        select.select(defaultValue);

        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription(String.valueOf(select.getValue() == null ? ConstantsUtils.SELECT_ONE : select.getValue()));
            }
        });
        return select;
    }

    public ComboBox loadActiveInactiveIntergerDDLB(final ComboBox select,
            boolean isFilter) throws SystemException {

        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(0);
        select.addItem(0);
        select.setItemCaption(0, ConstantsUtils.SELECT_ONE);
        select.markAsDirty();
        select.setDescription((String) (select.getValue() == DASH ? ConstantsUtils.SELECT_ONE : select.getItemCaption(select.getValue())));
        final DynamicQuery dynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.DESCRIPTION, new Object[]{Constant.ACTIVE, Constant.INACTIVE}));
        dynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
        List<HelperTable> list = helperListUtil.getDynamicQuery(dynamicQuery);
        select.setItemCaption(select.addItem(0), isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
        for (Iterator<HelperTable> it = list.iterator(); it.hasNext();) {
            HelperTable helperTable = it.next();
            select.addItem(helperTable.getHelperTableSid());
            select.setItemCaption(helperTable.getHelperTableSid(), helperTable.getDescription());
        }
        select.select(0);
        return select;
    }

    /**
     * To get the combo box select.
     *
     * @param select the select
     * @param lazyContainer
     * @return the native select
     * @throws java.lang.Exception
     */
   public ComboBox loadLazyComboBox(final ComboBox select,
            LazyContainer lazyContainer, final Object defaultValue)  {
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        select.setInputPrompt(ConstantsUtils.SELECT_ONE);
        lazyContainer.setMinFilterLength(Constant.ZERO);
        select.setContainerDataSource(lazyContainer);
        select.select(defaultValue);
        select.setPageLength(NumericConstants.SEVEN);
        select.markAsDirty();

        return select;
    }

    public ComboBox loadIndexedComboBox(final ComboBox select,
            BeanItemContainer container, final Object defaultValue)  {
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setInputPrompt(ConstantsUtils.SELECT_ONE);
        select.setContainerDataSource(container);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        select.select(defaultValue);
        select.setPageLength(NumericConstants.SEVEN);
        select.markAsDirty();
        return select;
    }

    public ComboBox loadIntegerComboBox(final ComboBox select, List<HelperDTO> list) {
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(0);
        select.setInputPrompt(ConstantsUtils.SELECT_ONE);

        for (HelperDTO helperDTO : list) {
            select.addItem(helperDTO.getId());
            select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
        }
        select.select(0);
        select.setPageLength(NumericConstants.SEVEN);
        select.markAsDirty();
        return select;
    }
    /**
     * 
     * @param select
     * @param lazyContainer
     * @param defaultValue
     * @param propertyId
     * @return
     * @throws Exception 
     */
    public ComboBox loadLazyitemComboBox(final ComboBox select,
            LazyContainer lazyContainer, final Object defaultValue,String propertyId)  {
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.setItemCaptionPropertyId(propertyId);
        select.setInputPrompt(ConstantsUtils.SELECT_ONE);
        lazyContainer.setMinFilterLength(Constant.ZERO);
        select.setContainerDataSource(lazyContainer);
        select.select(defaultValue);
        select.setPageLength(NumericConstants.SEVEN);
        select.markAsDirty();

        return select;
    }
    
    /**
     * Sort by Integer
     * @param list
     * @return 
     */
    public List<HelperDTO> getHelperDTOSortByInteger(List<HelperDTO> list) {
        Collections.sort(list, new Comparator<HelperDTO>() {
            @Override
            public int compare(final HelperDTO lhs, HelperDTO rhs) {
                if (Integer.parseInt(lhs.getDescription()) > Integer.parseInt(rhs.getDescription())) {
                    return 1;
                } else if (Integer.parseInt(lhs.getDescription()) < Integer.parseInt(rhs.getDescription())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return list;
    }
    
    
    
    
    /**
 * To create runnable thread and call the appropriate block based on the case name 
 * Used Varargs parameter. So assuming 1st argument should be switch case name always
     * 
 * @param inputs
     * @return 
     */
    public Runnable createRunnable(final Object... inputs) {
        final String caseName = inputs[0].toString();
        VaadinSession session = VaadinSession.getCurrent();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                VaadinSession.setCurrent(session);
                switch (caseName) {
                    case Constant.MERGE_QUERY:
                        HelperTableLocalServiceUtil.executeUpdateQuery(inputs[1].toString());
                        break;
                    case Constant.CUST_HIERARCHY_INSERT:
                        Thread.currentThread().setName(Constant.CUST_HIERARCHY_INSERT);
                        DataSelectionLogic.hierarchyDetailsInsert((List<String>) inputs[NumericConstants.TWO], "PROJECTION_CUST_HIERARCHY", Integer.parseInt(inputs[1].toString()), Boolean.parseBoolean(inputs[NumericConstants.THREE].toString()));
                        break;
                    case Constant.PROD_HIERARCHY_INSERT:
                        Thread.currentThread().setName(Constant.PROD_HIERARCHY_INSERT);
                        DataSelectionLogic.hierarchyDetailsInsert((List<String>) inputs[NumericConstants.TWO], "PROJECTION_PROD_HIERARCHY", Integer.parseInt(inputs[1].toString()),Boolean.parseBoolean(inputs[NumericConstants.THREE].toString()));
                        break;
                    case Constant.DATA_SELECTION_TAB_LOAD:
                        Thread.currentThread().setName(Constant.DATA_SELECTION_TAB_LOAD);
                        if (inputs.length == NumericConstants.THREE) {
                            //It will wait for Projection_cust_hierarchy and Projection_Prod_Hierarchy insert
                            for (Future future : (Future[]) inputs[NumericConstants.TWO]) {
                                waitsForOtherThreadsToComplete(future);
                }
            }
                        ((DataSelection) inputs[1]).init();
                        break;
                    case Constant.PROJECTION_DETAILS_INSERT:
                        Thread.currentThread().setName(Constant.PROJECTION_DETAILS_INSERT);
                        DataSelectionLogic.projectionDetailsInsert(Integer.parseInt(inputs[1].toString()), (GtnSmallHashMap) inputs[NumericConstants.TWO],Boolean.parseBoolean(inputs[NumericConstants.THREE].toString()));
                        break;
                    case Constant.PROCEDURE_CALL:
                        Thread.currentThread().setName(inputs[1].toString());
                        new DataSelectionLogic().callInsertProcedureForNm(Integer.parseInt(inputs[NumericConstants.TWO].toString()),(SessionDTO)inputs[NumericConstants.SIX], inputs[1].toString() ,inputs[NumericConstants.FIVE].toString());
                        break;
                    case Constant.DP_PROCEDURE_CALL:
                        Thread.currentThread().setName(inputs[1].toString());
                        new DataSelectionLogic().callInsertProcedureForNm(Integer.parseInt(inputs[NumericConstants.TWO].toString()),(SessionDTO)inputs[NumericConstants.SIX] , inputs[1].toString() ,inputs[NumericConstants.FIVE].toString());
                        break;
                    case Constant.INSERTORUPDATE:
                            //For Discount Projection insert alone will wait for list view get saved.(Used in temp to main insert)
                            if (inputs.length == NumericConstants.THREE) {
                                waitsForOtherThreadsToComplete((Future) inputs[NumericConstants.TWO]);
                            }
                            HelperTableLocalServiceUtil.executeUpdateQuery(inputs[1].toString());                            
                        break;
                    case Constant.DISCOUNT_LIST_VIEW_SAVE:
                        Thread.currentThread().setName(Constant.DISCOUNT_LIST_VIEW_SAVE);
                        ((NMDiscountProjection) inputs[1]).saveDiscountProjectionScreen(false);
                        break;
                    default:
                        break;
                }
            }
        };
        return runnable;
    }
     
     
    /**
     * Used to wait for the Data Selection thread(Once the Data Selection tab is
     * loaded screen will be visible to the user).
     * 
     * @param t
     */
    public void waitFor(Thread t) {
        if (t!=null && t.isAlive()) {
            try {
                synchronized (t) {
                    t.wait();
                }
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }
    
    /**
     * Waits for other thread to get execute
     * 
     * @param futureObject
     */
    public void waitsForOtherThreadsToComplete(Future futureObject) {
        if (futureObject != null) {
            try {
                futureObject.get();
            } catch (InterruptedException | ExecutionException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
    }
     public void waitsForOtherThreadsToComplete(Future[] futureObject) {
        if (futureObject != null) {
            try {
                for (Future future : futureObject) {
                      future.get();
}

            } catch (InterruptedException | ExecutionException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
    }
     
     /**
      * It should call after the sales insert, so checked that validation
      * 
      * @param procedureName
      * @param projectionId
      * @param session
      * @param future
      * @return 
      */
    public Runnable createRunnableForPPAProcedureCall(final String procedureName,final int projectionId,final SessionDTO session,final Future future) {
         Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName(procedureName);
                waitsForOtherThreadsToComplete(session.getFutureValue(Constant.SALES_PROCEDURE_CALL));
                //It will wait untill PPA Main to temp Query compelete
                waitsForOtherThreadsToComplete(session.getFutureValue(Constant.PPA_SMALL));
                if (future!=null) {
                    //It will wait until the ppa insert procedure complete                         
                    waitsForOtherThreadsToComplete(future);
                }
                new DataSelectionLogic().callInsertProcedureForNm(projectionId, session, procedureName, Constant.PPA_SMALL);
            }
        };
        return runnable;
    }
    public Runnable createRunnableForPPAInitProcedure(final String procedureName, final SessionDTO session) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName(procedureName);//PRC_NM_PPA_PROJ_INIT
                 //It will wait until the ppa insert procedure complete 
                waitsForOtherThreadsToComplete(session.getFutureValue(Constant.PPA_PROCEDURE_CALL));
                StringBuilder query = new StringBuilder("EXEC ");
                query.append(procedureName);
                query.append(' ').append(session.getProjectionId()).append(',');
                query.append(session.getUserId()).append(",'").append(session.getSessionId()).append('\'');
                HelperTableLocalServiceUtil.executeUpdateQuery(query.toString());
            }
        };
        return runnable;
    }
    
    public static boolean isValueEligibleForLoading() {
        return System.getProperty(BUSINESS_PROCESS).equals(BP_NAME);
    }
    
    public static String getDisplayFormattedName(String hierarchyNumber, String indicator, Map<String, List> relationshipDetails, SessionDTO session, Object[] displayFormatIndex) {
        StringBuilder formattedName = new StringBuilder();
        try {
            List<Object> relationshipValues = relationshipDetails.get(hierarchyNumber);
            if (displayFormatConditionCheck(relationshipValues, displayFormatIndex)) {
                List<Object> levelName = (List<Object>) relationshipValues.get(NumericConstants.FIVE);
                if (displayFormatIndex.length > 0 && !containsAllNull(levelName)) {
                    for (int i = 0; i < displayFormatIndex.length; i++) {
                        formattedName.append(setLevelNameValues(i, levelName, displayFormatIndex));
                    }
                    if (displayFormatIndex.length == 1 && StringUtils.isBlank(formattedName.toString())) {
                        return String.valueOf(levelName.get(NumericConstants.ZERO));
                    }
                } else {
                    return String.valueOf(levelName.get(NumericConstants.ZERO));
                }
            } else {
                return session.getLevelValueDiscription(hierarchyNumber, indicator);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return formattedName.toString();
    }
    
    private static boolean displayFormatConditionCheck(List<Object> relationshipValues, Object[] displayFormatIndex) {
        return !nullCheck(relationshipValues) && !nullCheck(displayFormatIndex) && !relationshipValues.isEmpty() && relationshipValues.size() > NumericConstants.FIVE;
    }
    
    private static boolean getLevelName(Object value) {
        String objValue = String.valueOf(value);
        return StringUtils.isBlank(objValue) || Constant.NULL.equals(objValue);
    }
    
    private static String setLevelNameValues(int index, List<Object> levelName, Object[] displayFormatIndex) {
        String formattedName = StringUtils.EMPTY;
        int indexFrom = (int) displayFormatIndex[index];
        Object value = levelName.get(indexFrom + 1);
        if (!getLevelName(value)) {
            if (index != 0) {
                formattedName += " - ";
            }
            formattedName += value;
        } 
        return formattedName;
    }
    
    public static boolean nullCheck(Object value) {
        return value == null;
    }
    
    public static Object[] getDisplayFormatSelectedValues(CustomMenuBar.CustomMenuItem displayFormatValues) {
        List<Object> productList = new ArrayList<>();
        if (displayFormatValues != null && displayFormatValues.getSize() > 0) {
            List<CustomMenuBar.CustomMenuItem> items = displayFormatValues.getChildren();
            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem1 = it.next();
                if (customMenuItem1.isChecked()) {
                    productList.add(customMenuItem1.getMenuItem().getId());
                }
            }
        }
        return productList.toArray();
    }
    
    private static boolean containsAllNull(List<Object> levelName) {
        boolean flag = true;
        for (int i = 1; i < levelName.size(); i++) {
            if (!nullCheck(levelName.get(i))) {
                flag = false;
                break;
            }
        }
        return flag;
    }
    
    public static void setCustomMenuBarValuesInEdit(Object value, CustomMenuBar.CustomMenuItem customMenuItem) {
        if (value != null && customMenuItem != null && value.toString().length() > 0) {
            String val = value.toString();
            final String[] col = val.split(",");
            for (int i = 0; i < col.length; i++) {
                setChecked(customMenuItem.getChildren(), col, i);
            }
        }
    }

    private static void setChecked(List<CustomMenuBar.CustomMenuItem> customMenuItem, String[] col, int i) {
        if (!nullCheck(customMenuItem)) {
            for (CustomMenuBar.CustomMenuItem string : customMenuItem) {
                if (string.getMenuItem().getId() == Integer.parseInt(col[i])
                        || (!nullCheck(string.getMenuItem().getWindow()) && string.getMenuItem().getWindow().equals(col[i].trim()))
                        || string.getText().equals(String.valueOf(col[i]).trim()) || String.valueOf(string.getMenuItem().getWindow()).startsWith(String.valueOf(col[i]))) {
                    string.setChecked(true);
                }
            }
        }
    }
    
    public static boolean stringNullCheck(Object value) {
        return Constant.NULL.equals(String.valueOf(value));
    }
    
    public ComboBox loadConvertionFactorComboBox(final ComboBox select, String listName) {
        try {
            select.removeAllItems();
            select.addItem(Constant.CONVERSION_FACTOR_DEFALUT_VALUE);
            select.setValidationVisible(true);
            select.setImmediate(true);
            select.setNullSelectionAllowed(true);
            select.setNullSelectionItemId(Constant.CONVERSION_FACTOR_DEFALUT_VALUE);
            List<HelperDTO> helperList = new ArrayList<>();
            if (helperListUtil.getListNameMap().get(listName) != null) {
                helperList.addAll(helperListUtil.getListNameMap().get(listName));
            }
            for (HelperDTO helperDTO : helperList) {
                if (helperDTO.getDescription().contains("~")) {
                    String[] values = helperDTO.getDescription().split("~");
                    select.addItem(values[1]);
                    select.setItemCaption(values[1], values[0]);
                } else {
                    select.addItems(helperDTO.getId());
                    select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
                }
            }
            select.select(Constant.CONVERSION_FACTOR_DEFALUT_VALUE);
            return select;
        } catch (UnsupportedOperationException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public static String getConversionFormattedValue(ProjectionSelectionDTO selection, Object value, boolean needZeroForNull) {
        if (stringNullCheck(selection.getConversionFactor())
                || StringUtils.isBlank(String.valueOf(selection.getConversionFactor()))
                || Constant.CONVERSION_FACTOR_DEFALUT_VALUE.equals(String.valueOf(selection.getConversionFactor()))) {
            if (nullCheck(value) && needZeroForNull) {
                return FORMAT_NO_DECIMAL.format(Double.parseDouble(DASH));
            } else if (nullCheck(value)) {
                return String.valueOf(value);
            }
            return FORMAT_NO_DECIMAL.format(Double.parseDouble(String.valueOf(value)));
        }
        if (nullCheck(value) && needZeroForNull) {
            return FORMAT_TWO_DECIMAL.format(Double.parseDouble(DASH));
        } else if (nullCheck(value)) {
            return String.valueOf(value);
        }
        double doubleValue = Double.parseDouble(selection.getConversionFactor().toString());
        double doubleFinalValue = Double.parseDouble(value.toString()) / doubleValue;
        return FORMAT_TWO_DECIMAL.format(doubleFinalValue);
    }

    public static double getConversionFormattedMultipleValue(ProjectionSelectionDTO selection, double value) {
        if (stringNullCheck(selection.getConversionFactor())
                || StringUtils.isBlank(String.valueOf(selection.getConversionFactor()))
                || Constant.CONVERSION_FACTOR_DEFALUT_VALUE.equals(String.valueOf(selection.getConversionFactor()))
                || 0.0 == value || 0 == value) {
            return value;
        }
        double doubleValue = Double.parseDouble(selection.getConversionFactor().toString());
        return value * doubleValue;
    }
    
    /**-----------------------------alg-2696--------------------------------------------**/
    public void loadOnDemandCombobox(final ComboBox allocationBasis, final String listName) {
        try {
            final DynamicQuery dynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
            dynamicQuery.add(RestrictionsFactoryUtil.in(ConstantsUtils.LIST_NAME, new Object[]{listName}));
            dynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
            List<HelperTable> list = helperListUtil.getDynamicQuery(dynamicQuery);
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    HelperTable helperTable = list.get(i);
                    allocationBasis.addItem(helperTable.getDescription());
                }
            }
        } catch (SystemException | UnsupportedOperationException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

	public static String getParentHierarchyNumber(String parentHierarchyNo, String key) {
		String parentKey;
		if (parentHierarchyNo == null || "null".equals(parentHierarchyNo)) {
			parentKey = key.substring(0, key.lastIndexOf('.'));
		} else {
			parentKey = parentHierarchyNo;
			if (parentKey.contains("~")) {
				parentKey = parentKey.substring(parentKey.lastIndexOf('~') + 1);

			}
			parentKey = parentKey.substring(parentKey.indexOf('-') + 1);
		}

		return parentKey;
	}

	public static String getParentItemId(String key, boolean isCustomHierarchy, String parentHierarchyNo) {
		String parentKey;

		if (!isCustomHierarchy) {
			parentKey = key.substring(0, key.lastIndexOf('.'));
		} else {
			parentKey = getParentHierarchyNumber(parentHierarchyNo, key);

		}
		if (parentKey.lastIndexOf('.') >= 0) {
			parentKey = parentKey.substring(0, parentKey.lastIndexOf('.') + 1);
		}

		return parentKey;
	}
   
    private static String setLevelNameValuesForDP(int index, List<Object> levelNameList, Object[] displayFormatIndex) {
        String formattedNameValue = StringUtils.EMPTY;
        int fromIndex = (int) displayFormatIndex[index];
        Object objValue = levelNameList.get(fromIndex + 1);
        if (!getLevelName(objValue)) {
            formattedNameValue = String.valueOf(objValue);
        }
        return formattedNameValue;
    }
        
    public static List<String> getFormattedDisplayName(String hierarchyNumber, String indicator, Map<String, List> relationshipDetails, SessionDTO session, Object[] displayFormatIndexValue) {
        List<String> formattedNameList = new ArrayList();
        try {
            List<Object> relationshipListValues = relationshipDetails.get(hierarchyNumber);
            if (displayFormatConditionCheck(relationshipListValues, displayFormatIndexValue)) {
                List<Object> listOfLevelName = (List<Object>) relationshipListValues.get(NumericConstants.FIVE);
                if (displayFormatIndexValue.length > 0 && !containsAllNull(listOfLevelName)) {
                    for (int i = 0; i < displayFormatIndexValue.length; i++) {
                        formattedNameList.add(setLevelNameValuesForDP(i, listOfLevelName, displayFormatIndexValue));
                    }
                    if (displayFormatIndexValue.length == 1 && formattedNameList.isEmpty()) {
                        formattedNameList.add(String.valueOf(listOfLevelName.get(NumericConstants.ZERO)));
                    }
                } else {
                    formattedNameList.add(String.valueOf(listOfLevelName.get(NumericConstants.ZERO)));
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return formattedNameList;
    }
    
}
